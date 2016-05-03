define(['jquery', 'store/model', 'util/simple-template', 'joint',
       'model/model', 'model/node', 'share/links'],
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

        var x = Math.abs(bounds.max.x) + Math.abs(bounds.min.x);
        var y = Math.abs(bounds.max.y) + Math.abs(bounds.min.y);

        var scale = HEIGHT / (y + Nodes.SIZE);

        paper.scale(scale);
    }

    function initPreview($list) {
        $list.on('show.bs.collapse', '.collapse', function () {
            var $ptr = $(this).find('.model-preview').empty();

            var id = $(this).attr('id').replace(/^model_/, '');

            var graph = new joint.dia.Graph();
            var paper = new joint.dia.Paper({
                el: $ptr,
                width: '100%',
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

        $list.on('click', '.model-edit', function () {
            var id = $(this).attr('handle');
            console.log('click')
            links.render('editor-workspace', $('#content')).then(function () {
                console.log('yay');
            });
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

        template.render($list, 'editor-model', list);
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
        }).click(function () { return false; }).data('old-title', title)
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

        store.saveModel($item.attr('handle'), {
            name: $input.val()
        }).done(function () {
            $input.replaceWith($title);
        }).fail(function () {
            $title.html($input.data('old-title'));
            $input.replaceWith($title);
        });
    }
});
