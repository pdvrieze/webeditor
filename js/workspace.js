define(['jquery', 'joint', 'model/model', 'model/node', 'lodash',
        'store/model'],
       function ($, joint, Model, Nodes, _, store) {
    "use strict";

    var $content = $('#content');
    $content.on('loaded', function (event, name) {
        if (name == 'editor-workspace') init();
    });

    function init() {
        var graph = new joint.dia.Graph();

        var $board = $('#board');
        var top = $board[0].getBoundingClientRect().top;

        var paper = new joint.dia.Paper({
            el: $board,
            width: $board.width(),
            height: $board.height(),
            gridSize: 1,
            model: graph,
            interactive: function(cellView) {
                if (cellView.model instanceof joint.dia.Link) {
                    return { vertexAdd: false };
                }
                return true;
            }
        });

        var origin;
        $(window).on('resize', function () {
            $board.height($(window).height() - top);

            paper.setDimensions($board.width() - 8, $board.height() - 8);
            origin = [$board.width() / 2, $board.height() / 2];
            paper.setOrigin(origin[0], origin[1]);
        }).trigger('resize');

        var scale = 2.0;
        paper.scale(scale);
        $('#content').bind('mousewheel DOMMouseScroll', function(event){
            if (event.originalEvent.wheelDelta > 0 ||
                    event.originalEvent.detail < 0) {
                paper.scale(scale *= 1.1);
            }
            else {
                paper.scale(scale *= 0.9);
            }
            return false;
        });

        var model = new Model(graph, paper);

        var $tpCreate = $('#tooltip_create');
        var $tpEdit = $('#tooltip_edit');

        paper.on('blank:pointerup', function (event, x, y) {
            if (moved) return;
            if ($('#dialogue:visible').size()) return;
            var oe = event.originalEvent;
            if (oe.touches && oe.touches.length == 2) return;
            if (oe.changedTouches && oe.changedTouches.length) {
                oe = oe.changedTouches[0];
            }
            if (oe.pageY < top) return;
            if ($('.workspace-tooltip:visible').size()) $('.workspace-tooltip').hide();
            else showTpCreate(x, y, oe.pageX, oe.pageY);
        });

        var cellmoved = false;
        paper.on('cell:pointerdown', function (cellView, event) {
            cellmoved = false;
        });

        paper.on('cell:pointerup', function (cellView, event) {
            if (cellmoved) return;
            if ($('#dialogue:visible').size()) return;
            $('.workspace-tooltip').hide();
            model.click(cellView, function (x, y) {
                var oe = event.originalEvent;
                if (oe.touches && oe.touches.length == 2) return;
                if (oe.changedTouches && oe.changedTouches.length) {
                    oe = oe.changedTouches[0];
                }
                if (oe.pageY < top) return;
                showTpEdit(cellView, oe.pageX, oe.pageY);
            });
        });

        var dragging = false;
        var moved = false;
        var pinch = false;
        paper.on('blank:pointerdown', function (event) {
            var oe = event.originalEvent;
            if (oe.changedTouches && oe.changedTouches.length) {
                if (oe.touches.length == 2) {
                    pinch = pinchMove(oe);
                    return;
                }
                oe = oe.changedTouches[0];
            }
            dragging = [oe.pageX, oe.pageY];
            moved = false;
        });

        $(window).on('mousemove touchmove', function (event) {
            if (cellmoved === false) cellmoved = true;
            if (dragging) {
                var oe = event.originalEvent;
                if (oe.changedTouches && oe.changedTouches.length) {
                    if (pinch) {
                        pinchMove(oe);
                        return;
                    }
                    oe = oe.changedTouches[0];
                }
                origin[0] -= dragging[0] - oe.pageX;
                origin[1] -= dragging[1] - oe.pageY;
                dragging = [oe.pageX, oe.pageY];
                paper.setOrigin(origin[0], origin[1]);
                moved = true;
            }
        });

        $(window).on('mouseup touchend', function (event) {
            dragging = false;
            pinch = false;
        });

        function pinchMove(e) {
            var dist = Math.sqrt(
                (e.touches[0].pageX-e.touches[1].pageX) *
                (e.touches[0].pageX-e.touches[1].pageX) +
                    (e.touches[0].pageY-e.touches[1].pageY) *
                    (e.touches[0].pageY-e.touches[1].pageY));
            if (pinch) {
                var percent = 1 - (pinch - dist) / (pinch * 32);
                paper.scale(scale *= percent);
            }
            moved = true;
            return dist;
        }

        function showTooltip($tooltip, x, y) {
            $('.workspace-tooltip').hide();
            $tooltip
                .show()
                .offset({
                    left: x - $tooltip.width() / 2,
                    top: y - $tooltip.height() / 2
                });
        }

        function showTpCreate(x, y, ox, oy) {
            $('#btn_start').css('display', model.count.start ? 'none' : 'inline');
            $('#btn_end').css('display', model.count.end ? 'none' : 'inline');
            $tpCreate.data('offset', {x: x, y: y})
            showTooltip($tpCreate, ox, oy);
        }

        function showTpEdit(cellView, x, y) {
            $tpEdit.data('cellView', cellView);
            var node = model.find(cellView);
            $('#btn_edit').css('display', node.canEdit ? 'inline' : 'none');
            $('#btn_connect').css('display',
                                  model.linkPossible(node) ? 'inline' : 'none');
            showTooltip($tpEdit, x, y);
        }

        $tpCreate.on('click touchend', 'a', function (event) {
            $tpCreate.hide();
            var offset = $tpCreate.data('offset');
            var object = $(this).attr('rel');
            model.add(new Nodes[object](), offset);
            return false;
        });

        $tpEdit.on('click touchend', 'a', function (event) {
            $tpEdit.hide();
            var cellView = $tpEdit.data('cellView');
            var action = $(this).attr('rel');
            model[action](cellView);
            return false;
        });

        var handle = $content.attr('handle');
        if (handle) {
            var $xml = $(store.getModel(handle).xml);
            model.fromXml($xml);
            $content.removeAttr('handle');
        }
        else {
            var name = prompt('Model Name');
            model.name = name;
        }

        window.saveModel = function () {
            console.log(model.toXml());
        }
    }
});
