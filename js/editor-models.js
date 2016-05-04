/*
 * Editor model page controller
 *
 * Handles the interactions on model page: shows the list, and allows to
 * run, rename, remove and add models
 */
define(['jquery', 'store/model', 'util/simple-template', 'joint',
       'model/model', 'model/node', 'share/nav', 'util/util'],
       function ($, store, template, joint, Model, Nodes, nav, util) {
    "use strict";

    /*
     * Initialisation of the editor models page
     */
    function init($html) {
        var $def = $.Deferred();
        var $list = $html.find('#list_models'); // list that will contain models

        renderList($html, $list);
        setupListeners($html, $list);
        initPreview($list);
        
        // no async neede after all
        $def.resolve($html);
        return $def;
    }

    /*
     * Render list of models
     */
    function renderList($html, $list) {
        // when models are loaded, show one big new button if needed or default
        // to normal sized button, and models of course
        var $load = template.load($list);
        var list = store.getList().then(function (list) {
            if (Object.keys(list)) { // we have models yay
                $html.find('#models_notnew').removeClass('hidden');

                var $list = [];
                $.each(list, function (key, view) {
                    // append each model to the list
                    $list.push(template.render('editor-model', view));
                });
                $load.resolve($list);
            }
            else $html.find('#models_new').removeClass('hidden');
        });
    }

    /*
     * Setup listeners for the editor models page
     */
    function setupListeners($html, $list) {
        // setup listener for list item click - switch page to workspace
        $list.on('click', 'a.list-group-item', function (e) {
            e.preventDefault();
            nav.changePage('workspace', $(this).attr('handle'));
        });

        // setup view button listener
        $list.on('click', '.clickable.view', function (e) {
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
            renderList($list);
        });
    }

    /*
     * Initialise preview for the model
     */
    function initPreview($list) {
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

            // load model from xml
            var model = new Model(graph, paper);
            model.fromXml($xml);
        }).on('hide.bs.collapse', '.collapse', function () {
            // remove everything inside to save on memory
            $(this).find('.model-preview').empty(); 
        });
        setupPreviewListeners($list);
    }

    /*
     * Set up listeners for the preview window and its controls
     */
    function setupPreviewListeners($list) {
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
                store.cloneModel(handle, name).done(function () {
                    renderList($list);
                });
            }
        });
    };

    /*
     * Rename model
     */
    function rename($a) {
        var handle = $a.attr('handle'); // get handle
        var newName = prompt('New Name', store.getModel(handle).name);
        var $title = $a.find('.item-title'); // find title element

        if (newName && newName.trim() && name != newName) {
            // only update name if its different and/or existent
            store.renameModel(handle, newName).then(function () {
                $title.html(newName); // replace the item title with new name
            }).fail(function (e) { console.log('Failed to rename', e) });
        }
    }

    /*
     * Setup paper to scale and centre the model
     */
    function setupPaper($xml, paper) {
        var bounds = getBounds($xml); // calculate mins and maxs

        var x = bounds.max.x - bounds.min.x + Nodes.SIZE; // model width
        var y = bounds.max.y - bounds.min.y + Nodes.SIZE; // model heigth

        var scaleX = paper.$el.height() / y;
        var scaleY = paper.$el.width() / x;
        var scale = Math.min(scaleX, scaleY); // minimal scale

        // calculate offset to centre model
        var offsetX = (paper.$el.width() / 2 - scale * (x / 2 + bounds.min.x));
        var offsetY = -bounds.min.y * scale;

        // apply calculated values
        paper.scale(scale);
        paper.setOrigin(offsetX, offsetY);
    }

    /*
     * Caclulates minimum and maximum coordinates
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
    
    // export
    return { init: init };
});
