/**
 * Editor model page controller
 *
 * Handles the interactions on model page: shows the list, and allows to
 * run, rename, remove and add models
 *
 * @module ControllerEditor
 */
define(['jquery', 'store/model', 'util/simple-template', 'joint',
       'model/model', 'model/node', 'share/nav', 'util/util', 'store/task'],
       function ($, store, template, joint, Model, Nodes, nav, util,
                 taskStore) {
    "use strict";

    /**
     * Initialisation of the editor models page
     *
     * @param $html {Object} jQuery html to render
     *
     * @return {Promise}
     */
    function init($html) {
        var $def = $.Deferred();
        var $list = $html.find('#list_models'); // list that will contain models

        var local = {}; // local store

        renderList($html, $list, local);
        setupListeners($html, $list);
        initPreview($html, $list, local);
        
        // no async neede after all
        $def.resolve($html, local);
        return $def;
    }

    /**
     * Render list of models
     *
     * @param $html {Object} jQuery html to render
     * @param $list {Object} jQuery list element
     */
    function renderList($html, $list, local) {
        // when models are loaded, show one big new button if needed or default
        // to normal sized button, and models of course
        var $load = template.load($list);
        var list = store.getList().then(function (list) {
            var $list = [];
            if (Object.keys(list).length) { // we have models yay
                $html.find('#models_notnew').removeClass('hidden');

                $.each(list, function (key, view) {
                    // append each model to the list
                    $list.push(template.render('editor-model', view));
                });
            }
            else $html.find('#models_new').removeClass('hidden');

            // make sure we are not running any more tasks
            if (local.task) local.task.destroy();

            $load.resolve($list);
        });
    }

    /**
     * Setup listeners for the editor models page
     *
     * @param $html {Object} jQuery html to render
     * @param $list {Object} jQuery list element
     */
    function setupListeners($html, $list) {
        // setup listener for list item click - switch page to workspace
        $list.on('click', 'a.list-group-item', function (e) {
            e.preventDefault();
            nav.changePage('workspace', $(this).attr('handle'));
        });

        // setup view button listener
        $list.on('click', '.clickable.view', function (e) {
            $list.find('.collapse.in').collapse('hide');
            $(this).parents('a').next().collapse('toggle');
            return false;
        });

        // setup rename button listener
        $list.on('click', '.clickable.rename', function (e) {
            rename($(this).parents('a'));
            return false;
        });

        // when refresh button is clicked we reset the store and rerender
        $html.find('#refresh').click(function () {
            store.update();
            renderList($html, $list);
        });
    }

    /**
     * Initialise preview for the model
     *
     * @param $html {Object} jQuery html to render
     * @param $list {Object} jQuery list element
     * @param local {Object} local store
     */
    function initPreview($html, $list, local) {
        $list.on('shown.bs.collapse', '.collapse', function () {
            var $target = $(this).find('.model-preview'); // target div
            var handle = $(this).attr('handle'); // extract handle

            // create paper to draw jointjs diagram on
            var graph = new joint.dia.Graph();
            var paper = new joint.dia.Paper({
                el: $target,
                width: $target.width(),
                height: $target.height(),
                model: graph
            });

            // load xml for the model
            var $xml = $(store.getModel(handle).xml);

            // setup paper origin and scale based on xml element positions
            setupPaper($xml, paper);
            $(window).off('resize.pe').on('resize.pe', function () {
                setupPaper($xml, paper);
            });


            // load model from xml
            var model = new Model(graph, paper);
            model.fromXml($xml);

            // save model
            $target.data('model', model);

            var $this = $(this);
            if (local.task) local.task.destroy();
            local.task = new taskStore.ModelTaskManager(handle, function () {
                updatePreview($this, local.task);
            });
        }).on('hide.bs.collapse', '.collapse', function () {
            // remove everything inside to save on memory
            $(this).find('.model-preview').empty(); 
        }).on('show.bs.collapse', '.collapse', function () {
            // unshow control buttons and initialise task store
            var $this = $(this);
            $this.find('.clickable.stop,.clickable.execute')
                .addClass('hidden');
        });
        setupPreviewListeners($html, $list, local);
    }

    /**
     * Set up listeners for the preview window and its controls
     *
     * @param $html {Object} jQuery html to render
     * @param $list {Object} jQuery list element
     * @param local {Object} local store
     */
    function setupPreviewListeners($html, $list, local) {
        // Listener for the delete model button
        $list.on('click', '.model-delete', function () {
            var handle = $(this).attr('handle'); // get handle
            if (confirm('Are you sure you want to delete this model?')) {
                store.deleteModel(handle).then(function () {
                    // remove model from the list now that is not in the store
                    $list.find('#model_' + handle).remove();
                    $list.find('a[handle=' + handle + ']').remove();
                }).fail(function (e) {
                    console.error('Cannot remove Model', e);
                });
            }
        });

        // Listener for the clone button
        $list.on('click', '.model-clone', function () {
            var handle = $(this).attr('handle'); //get handle
            var name = prompt('New Name'); // get new name from the user
            if (name && name.trim()) { // make sure it is not empty
                store.cloneModel(handle, name).then(function () {
                    renderList($html, $list);
                });
            }
        });

        // Listener for the execute button
        $list.on('click', '.model-execute', function () {
            local.task.execute();
        });

        // Listener for the stop button
        $list.on('click', '.model-stop', function () {
            local.task.stop();
        });
    }

    /**
     * Rename model
     *
     * @param $a {Object} jQuery <a> called
     */
    function rename($a) {
        var handle = $a.attr('handle'); // get handle
        var newName = prompt('New Name', store.getModel(handle).name);
        var $title = $a.find('.item-title'); // find title element

        if (newName && newName.trim() && name != newName) {
            // only update name if its different and/or existent
            store.renameModel(handle, newName).then(function () {
                $title.html(newName); // replace the item title with new name
            }).fail(function (e) { console.log('Failed to rename', e); });
        }
    }

    /**
     * Setup paper to scale and centre the model
     *
     * @param $xml {Object} xml node
     * @param paper {Object} jointjs paper
     */
    function setupPaper($xml, paper) {
        var bounds = getBounds($xml); // calculate mins and maxs
        if (bounds.min.x == Infinity) return; // no elements yet

        var x = bounds.max.x - bounds.min.x + Nodes.SIZE; // model width
        var y = bounds.max.y - bounds.min.y + Nodes.SIZE; // model heigth

        var scaleX = paper.$el.height() / y;
        var scaleY = paper.$el.width() / x;
        var scale = Math.min(scaleX, scaleY); // minimal scale

        // calculate offset to centre model
        var xpart = x / 2 + bounds.min.x - Nodes.SIZE / 2;
        var offsetX = paper.$el.width() / 2 - scale * xpart;
        var offsetY = -(bounds.min.y - Nodes.SIZE / 2) * scale;

        // apply calculated values
        paper.scale(scale);
        paper.setOrigin(offsetX, offsetY);
    }

    /**
     * Caclulates minimum and maximum coordinates
     *
     * @param $xml {Object} xml node
     * @return {Object} bounds
     */
    function getBounds($xml) {
        // We start from infinity to simplify calculation
        var bounds = {
            min: { x: Infinity, y: Infinity },
            max: { x: -Infinity, y: -Infinity }
        };

        // go through every element
        $xml.find(util.xmlSel('pe:processModel')).children().each(function () {
            var x = parseInt($(this).attr('x'));
            var y = parseInt($(this).attr('y'));
            if (x < bounds.min.x) bounds.min.x = x;
            if (x > bounds.max.x) bounds.max.x = x;
            if (y < bounds.min.y) bounds.min.y = y;
            if (y > bounds.max.y) bounds.max.y = y;
        });
        return bounds;
    }

    /**
     * Updates model preview based on current task state
     *
     * @param $ptr {Object} html element
     * @param task {Object} current task
     */
    function updatePreview($ptr, task) {
        var model = $ptr.find('.model-preview').data('model');

        if (task.handle) {
            $ptr.find('.model-stop').removeClass('hidden');   
            $ptr.find('.model-execute').addClass('hidden');

            if (Object.keys(task.tasks).length) {
                _.each(model.nodes, function (node) {
                    if (task.tasks[node.eid]) {
                        node.setState(task.tasks[node.eid]);
                    }
                    else node.setState(null);
                });
            }
        }
        else {
            $ptr.find('.model-execute').removeClass('hidden');
            $ptr.find('.model-stop').addClass('hidden');

            _.each(model.nodes, function (node) { node.setState(null); });
        }
    }

    /**
     * Destroy the local store: task store if exists
     *
     * @param local {Object} local store
     */
    function destroy(local) {
        if (local.task) local.task.destroy();
        local.task = null;

        // remove window handlers
        $(window).off('.pe');
    }
    
    // export
    return { init: init, destroy: destroy };
});
