define(['jquery', 'store/model', 'util/simple-template', 'joint',
       'model/model', 'model/node', 'share/nav'],
       function ($, store, template, joint, Model, Nodes, links) {
    "use strict";

    var HEIGHT = 120;
    var SCALE = 10;

    var $content = $('#content');
    $content.on('loaded', function (event, name) {
        if (name == 'editor-models') init();
    });

    $content.on('click', 'span.edit', function (event) {
        event.preventDefault();
        toggle($(this).parents('a'));
        return false;
    });

    function getBounds($xml) {
        var bounds = {
            min: { x: Infinity, y: Infinity },
            max: { x: -Infinity, y: -Infinity }
        };
        $xml.find('pe\\:processModel,processModel').children()
            .each(function () {
                var x = $(this).attr('x') - 0;
                var y = $(this).attr('y') - 0;
                if (x < bounds.min.x) bounds.min.x = x;
                if (x > bounds.max.x) bounds.max.x = x;
                if (y < bounds.min.y) bounds.min.y = y;
                if (y > bounds.max.y) bounds.max.y = y;
            });
        return bounds;
    }

    function setupPaper($xml, paper) {
        var bounds = getBounds($xml);

        var x = bounds.max.x - bounds.min.x + Nodes.SIZE;
        var y = bounds.max.y - bounds.min.y + Nodes.SIZE;

        var scaleX = HEIGHT / y;
        var scaleY = paper.$el.width() / x;
        var scale = Math.min(scaleX, scaleY);

        var offsetX = (paper.$el.width() / 2 - scale * (x / 2 + bounds.min.x));
        var offsetY = -bounds.min.y * scale;

        paper.scale(scale);
        paper.setOrigin(offsetX, offsetY);
    }

    function initPreview($list) {
        $list.on('shown.bs.collapse', '.collapse', function () {
            var $ptr = $(this).find('.model-preview').empty();

            var id = $(this).attr('id').replace(/^model_/, '');

            var graph = new joint.dia.Graph();
            var paper = new joint.dia.Paper({
                el: $ptr,
                width: $ptr.width(),
                height: HEIGHT,
                gridSize: 1,
                model: graph
            });

            var $xml = $(store.getModel(id).xml);
            setupPaper($xml, paper);

            var model = new Model(graph, paper);
            model.fromXml($xml);
        });

        $list.on('hide.bs.collapse', '.collapse', function () {
            $(this).find('.model-preview').empty(); 
        });

        $list.on('click', '.model-delete', function () {
            var id = $(this).attr('handle');
            if (confirm('Are you sure you want to delete this model?')) {
                store.deleteModel(id).success(function () {
                    $list.find('#model_' + id).remove();
                    $list.find('a[handle=' + id + ']').remove();

                });
            }
        });

        $list.on('click', '.model-clone', function () {
            var id = $(this).attr('handle');
            var name = prompt('New name');
            store.cloneModel(id, name).done(function () { init(); });
        });
    };

    function init() {
        var $list = $('#list_models');
        var list = store.getList();

        list.done(function (list) {
            if (!Object.keys(list)) $('#models_new').removeClass('hidden');
            else $('#models_notnew').removeClass('hidden');
        });

        initPreview($list);

        template.render($list, 'editor-model', list).then(function () {
            $list.find('a.list-group-item').click(function () {
                var handle = $(this).attr('handle');
                var $content = $('#content');
                $content.attr('handle', handle);
                links.render('editor-workspace', $content);
            });

            $list.find('.clickable.edit').click(function (e) {
                e.preventDefault();
                var handle = $(this).parent().attr('handle');
                $list.find('#model_' + handle).collapse('toggle');
                return false;
            });

            $list.find('.clickable.rename').click(function (e) {
                e.preventDefault();
                toggle($(this).parent());
                return false;
            });
        });

        $('#refresh').off().click(function () {
            store.reset();
            init();
        });
    }

    function toggle($item) {
        if ($item.data('edit-mode')) {
            $item.removeData('edit-mode');
            save($item);
        }
        else {
            $item.data('edit-mode', true);
            edit($item);
        }
    }

    function edit($item) {
        var $title = $item.find('.item-title');
        var title = $title.text();
        var $input = $('<input>', {
            type: 'text',
            'class': 'form-control',
            placeholder: 'Title',
            value: title
        }).click(function (e) {
            e.preventDefault();
            return false;
        }).data('old-title', title)

        $title.replaceWith($input);
        $item.find('.glyphicon')
            .removeClass('glyphicon-pencil')
            .addClass('glyphicon-ok');
    }
    
    function save($item) {
        var $input = $item.find('input');
        if ($input.prop('disabled')) return;

        var $title = $('<span class="item-title">').append($input.val());
        $item.find('.glyphicon')
            .removeClass('glyphicon-ok')
            .addClass('glyphicon-pencil');

        $input.prop('disabled', true);

        store.renameModel($item.attr('handle'), $input.val()).then(function () {
            $input.replaceWith($title);
        }).fail(function () {
            $title.html($input.data('old-title'));
            $input.replaceWith($title);
        });
    }

    return {
        init: function () {
            console.log('im here');
        }
    };
});
