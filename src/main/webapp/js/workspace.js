/**
 * Workspace controller
 *
 * Handles the editor part of the project, allows to interact with the graph,
 * zoom, pan the paper, etc
 *
 * @module ControllerWorkspace
 */
define(['jquery', 'joint', 'model/model', 'model/node', 'webeditor'],
       function ($, joint, Model, Nodes, webeditor) {
    "use strict";

    var store = webeditor.store.model;

    /**
     * Zooming multiplier for the phone
     *
     * @final
     * @type Integer
     */
    var ZOOM = 32;

    /**
     * Initialisation of the workspace page
     *
     * @param $html {Object} jQuery html to render
     * @param handle {Integer} open model handle
     *
     * @return {Promise}
     */
    function init($html, handle) {
        var $def = $.Deferred();

        var joint = initJoint($html);

        initResize(joint);
        initScroll(joint);
        initTooltipCreate($html, joint);
        initTooltipEdit($html, joint);
        initInteraction(joint);
        initModel(joint, handle);

        // no async neede after all
        $def.resolve($html, joint);
        return $def;

    }

    /**
     * HTML is already rendered, so we can run resizing methods for scaling the
     * graphs
     *
     * @param $html {Object} jQuery html to render
     * @param joint {Object} storage for jointjs related elements
     */
    function post($html, joint) {
        $(window).trigger('resize');

        if (joint.model.handle) {
            // load model from xml
            store.getList().then(function () {
                joint.model.fromXml($(store.getModel(joint.model.handle).xml));
                initInterface(joint);
            });
        }
        else {
            // create new model with new name
            var name = prompt('Model Name', 'Unnamed Model') || 'Unnamed Model';
            joint.model.name = name;

            // create model on server
            store.createModel(joint.model.toXml()).done(function (handle) {
                joint.model.handle = handle;
                initInterface(joint);

                history.replaceState({
                    page: 'workspace',
                    args: joint.model.handle
                }, 'workspace', '#workspace/' + joint.model.handle);
            }).fail(function () {
                alert('Connection Error');
            });
        }
    }

    /**
     * Initialise interface, especially navigation buttons
     *
     * @param joint {Object} storage for jointjs related elements
     */
    function initInterface(joint) {
        joint.model.nosave = false;

        // export xml
        var handle = joint.model.handle;
        $('#export_xml').attr('href', '/ProcessEngine/processModels/' + handle)
            .prop('download', joint.model.name + '.xml');

        // import xml
        $('#import_xml').off().click(function () {
            joint.model.editXml();
            return false;
        });

        // auto algin
        $('#autoalign').off().click(function (e) {
            e.preventDefault();
            joint.model.autoalign();
            return false;
        });
    }

    /**
     * Initialise jointjs diagram
     *
     * @param $html {Object} jQuery html to render
     *
     * @return {Object} storage for jointjs related elements
     */
    function initJoint($html) { 
        // create graph
        var graph = new joint.dia.Graph();

        // get main board
        var $board = $html.find('#board');

        // initialise paper
        var paper = new joint.dia.Paper({
            el: $board,
/*
            width: $board.width(), // this is actually probably 0
            height: $board.height(), // this too
*/
            model: graph,
            interactive: function(cellView) {
                // turn off interactions with arrow-links
                if (cellView.model instanceof joint.dia.Link) {
                    return { vertexAdd: false };
                }
                return true;
            }
        });

        var scale = 2.0;
        paper.scale(scale);

        return {
            paper: paper,
            graph: graph,

            // these are dynamic values - and its a perfect place to store them
            origin: null,
            scale: scale,
            moved: false,
            cellmoved: false,
            dragging: false,
            princh: false,
            changed: false
        };
    }

    /**
     * Takes care of window resizing
     *
     * @param joint {Object} storage for jointjs related elements
     */
    function initResize(joint) {
        $(window).off('resize.pe').on('resize.pe', function () {
            // get top of the paper
            var top = joint.paper.el.getBoundingClientRect().top;

            // calculate distance to the bottom of the screen
            joint.paper.$el.height($(window).height() - top);
            joint.paper.$el.width("100%");

            var offset = 8; // ideal offset for most screens

            var width = joint.paper.$el.parent().width() - offset;
            var height = joint.paper.$el.parent().height() - offset;
            if (width > 0 && height > 0) { // make sure we are in real world
                // scale to fit the screen
                joint.paper.setDimensions(width, height);

                /*
                 * I use -200 and -300 because lower right corner on the android
                 * phone seems to be 200:300, and since it is preferable to see
                 * the graph centred, we have to adjust the origin. Sad but
                 * needed
                 *
                 * tl;dr: this centres most models
                 */
                var phoneX = 200, phoneY = 300;
                joint.origin = [
                    (width - phoneX * joint.scale) / 2,
                    (height - phoneY * joint.scale) / 2
                ];
                joint.paper.setOrigin(joint.origin[0], joint.origin[1]);
            }
        });
    }

    /**
     * Makes it possible to resize the paper by scrolling the mouse wheel
     *
     * @param joint {Object} storage for jointjs related elements
     */
    function initScroll(joint) {
        joint.paper.$el.on('mousewheel.pe DOMMouseScroll.pe', function(event) {
            // make sure we are not inside the dialogue
            if ($('#dialogue:visible').length>0) return;

            // cross-browser way of doing this
            var oe = event.originalEvent;
            if (oe.wheelDelta > 0 || oe.detail < 0) joint.scale *= 1.1;
            else joint.scale *= 0.9;

            // calculate position we want to move
            var pointWas = joint.paper.clientToLocalPoint({
                x: oe.pageX, y: oe.pageY
            });

            joint.paper.scale(joint.scale, joint.scale);

            // calculate position we want to move to
            var pointNow = joint.paper.clientToLocalPoint({
                x: oe.pageX, y: oe.pageY
            });

            // calculate difference in positions
            var diffX = pointWas.x - pointNow.x;
            var diffY = pointWas.y - pointNow.y;

            joint.origin[0] -= diffX * joint.scale;
            joint.origin[1] -= diffY * joint.scale;

            // move
            joint.paper.setOrigin(joint.origin[0], joint.origin[1]);

            return false; // no bubbling, to aviod scrolling page
        });
    }

    /**
     * Allows to show the tooltip when the blank space is clicked
     *
     * @param $html {Object} jQuery html to render
     * @param joint {Object} storage for jointjs related elements
     */
    function initTooltipCreate($html, joint) {
        var $tooltip = $html.find('#tooltip_create');

        joint.paper.on('blank:pointerup', function (event, x, y) {
            // make sure that we are not in the dialogue
            // and make sure we've not just moved (paned)
            if (joint.moved || $('#dialogue:visible').length) return;

            // we don't care about two finger events for tooltips
            var oe = event.originalEvent;
            if (oe.touches && oe.touches.length == 2) return;

            // if we use touchescreen, we have different events
            if (oe.changedTouches && oe.changedTouches.length) {
                oe = oe.changedTouches[0];
            }

            // make sure we are on the board/paper, ignore outside,
            // in this case navigation menu
            if (oe.pageY < $('nav').height()) return;

            if ($('.workspace-tooltip:visible').length>0) {
                // clicked on a blank space and tooltip is visible - we need
                // to hide it then
                $('.workspace-tooltip').hide();
            }
            else {
                $('#btn_start').css(
                    'display',
                    joint.model.count.start ? 'none' : 'inline'
                );
                $('#btn_end').css(
                    'display',
                    joint.model.count.end ? 'none' : 'inline'
                );
                $tooltip.data('offset', { x: x, y: y });
                showTooltip($tooltip, oe.pageX, oe.pageY);
            }
        });

        $tooltip.on('click touchend', 'a', function () {
            $tooltip.hide();
            var offset = $tooltip.data('offset');
            var object = $(this).attr('rel');
            joint.model.add(new Nodes[object](), offset);
            return false;
        });
    }

    /**
     * Allows to show the edit tooltip when clicked on the cell
     *
     * @param $html {Object} jQuery html to render
     * @param joint {Object} storage for jointjs related elements
     */
    function initTooltipEdit($html, joint) {
        var $tooltip = $html.find('#tooltip_edit');

        joint.paper.on('cell:pointerup', function (cellView, event) {
            // make sure we not just moved the cell
            // and we are not in the dialogue
            if (joint.cellmoved || $('#dialogue:visible').length>0) return;

            // event if not needed lets make sure the create tooltip is hidden
            $('.workspace-tooltip').hide();

            joint.model.click(cellView, function () {
                // we don't care about two finger events for tooltips
                var oe = event.originalEvent;
                if (oe.touches && oe.touches.length == 2) return;

                // if we use touchescreen, we have different events
                if (oe.changedTouches && oe.changedTouches.length) {
                    oe = oe.changedTouches[0];
                }

                // make sure we are on the board/paper, ignore outside,
                // in this case navigation menu
                if (oe.pageY < $('nav').height()) return;

                $tooltip.data('cellView', cellView);
                var node = joint.model.find(cellView);
                $('#btn_edit').css(
                    'display',
                    node.canEdit ? 'inline' : 'none'
                );
                $('#btn_connect').css(
                    'display',
                    joint.model.linkPossible(node) ? 'inline' : 'none'
                );
                showTooltip($tooltip, oe.pageX, oe.pageY);
            });
        });

        $tooltip.on('click touchend', 'a', function () {
            $tooltip.hide();
            var cellView = $tooltip.data('cellView');
            var action = $(this).attr('rel');
            joint.model[action](cellView);
            return false;
        });
    }

    /**
     * Initialise panning and zooming on both desktop and touchscreen
     *
     * @param joint {Object} storage for jointjs related elements
     */
    function initInteraction(joint) {
        // nullify cellmoved when cell is grabbed
        joint.paper.on('cell:pointerdown', function () {
            joint.cellmoved = false;
        });
        
        // identify pinch or dragging
        joint.paper.on('blank:pointerdown', function (event) {
            var oe = event.originalEvent;
            if (oe.changedTouches && oe.changedTouches.length) {
                // if two fingers - we are looking at pinch
                if (oe.touches.length == 2) {
                    // pinch it is, so lets quit - it's not dragging
                    joint.pinch = pinchMove(joint, oe);
                    return;
                }

                // ok its not pinch but still touch event
                oe = oe.changedTouches[0];
            }
            joint.dragging = [oe.pageX, oe.pageY];
            joint.moved = false; // just started
        });

        joint.graph.on('change remove', function () {
            joint.changed = true;
        });

        // drag or pinch when mouse (or fingers) moves
        var events = 'mousemove.pe touchmove.pe';
        $(window).off(events).on(events, function (event) {
            // start moving cell
            if (joint.cellmoved === false) joint.cellmoved = true;

            if (joint.dragging) { // we are in draggin mode
                var oe = event.originalEvent;
                if (oe.changedTouches && oe.changedTouches.length) {
                    if (joint.pinch) { // pinch mode
                        pinchMove(joint, oe); // pinch zoom
                        return;
                    }
                    // touch event fix
                    oe = oe.changedTouches[0];
                }

                if (! joint.origin) { joint.origin = [0,0]; }
                // calculate new origin from dragging
                joint.origin[0] -= joint.dragging[0] - oe.pageX;
                joint.origin[1] -= joint.dragging[1] - oe.pageY;
                joint.dragging = [oe.pageX, oe.pageY];

                // set new origin
                joint.paper.setOrigin(joint.origin[0], joint.origin[1]);
                joint.moved = true; // yes, we paned
            }
        });

        var events2 = 'mouseup.pe touchend.pe';
        $(window).off(events2).on(events2, function () {
            joint.dragging = false;
            joint.pinch = false;
            if (joint.changed) {
                joint.model.save();
                joint.changed = false;
            }
        });
    }

    /**
     * Calculates distances between two fingers and sets appropraite vars
     *
     * @param joint {Object} storage for jointjs related elements
     * @param e {Event} touch event
     *
     * @return {Object} calculated distance and central point
     */
    function pinchMove(joint, e) {
        // calculate distance
        var dist = Math.sqrt(
            (e.touches[0].pageX - e.touches[1].pageX) *
            (e.touches[0].pageX - e.touches[1].pageX) +
                (e.touches[0].pageY - e.touches[1].pageY) *
                (e.touches[0].pageY - e.touches[1].pageY));

        // calculate central point
        var point = joint.paper.clientToLocalPoint({
            x: (e.touches[0].pageX + e.touches[1].pageX) / 2,
            y: (e.touches[0].pageY + e.touches[1].pageY) / 2
        });

        // if we are in the process of zooming, zoom
        if (joint.pinch) {
            var olddist = joint.pinch.dist; // retrieve first distance

            // calculate scale
            var percent = 1 - (olddist - dist) / (olddist * ZOOM);
            joint.scale *= percent;

            joint.paper.scale(joint.scale);

            // calculate offset to centre
            var diffX = joint.pinch.point.x - point.x;
            var diffY = joint.pinch.point.y - point.y;

            joint.origin[0] -= diffX * joint.scale;
            joint.origin[1] -= diffY * joint.scale;

            // centre
            joint.paper.setOrigin(joint.origin[0], joint.origin[1]);
        }

        joint.moved = true;

        return { dist: dist, point: point };
    }

    /**
     * Initialise the model itself
     *
     * @param joint {Object} storage for jointjs related elements
     * @param handle {Integer} model handle
     */
    function initModel(joint, handle) {
        joint.model = new Model(joint.graph, joint.paper);
        joint.model.handle = handle;
    }

    /**
     * Show tooltip - centre it
     *
     * @param $tooltip {Object} jquery tooltip to display
     * @param x {Integer}
     * @param y {Integer} Top of the tooltip
     */
    function showTooltip($tooltip, x, y) {
        $('.workspace-tooltip').hide();
        $tooltip.show() .offset({
            left: x - $tooltip.width() / 2,
            top: y
        });
    }

    /**
     * Takes care of destrying the content and removin the event listeners
     *
     * @param joint {Object} storage for jointjs related elements
     */
    function destroy(joint) {
        // unlink and remove paper
        joint.paper.off();
        joint.paper.$el.off().empty();
        joint.paper = null;

        joint.graph = null;
        joint.model = null;

        joint = null;

        // remove our events from window
        $(window).off('.pe');
    }

    // export
    return { init: init, post: post, destroy: destroy };
});
