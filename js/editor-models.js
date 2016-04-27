define(['jquery', 'store/model', 'util/simple-template'],
       function ($, store, template) {
    "use strict";

    var $content = $('#content');
    $content.on('loaded', function (event, name) {
        if (name == 'editor-models') init();
    });

    $content.on('click', 'span.edit', function (event) {
        event.preventDefault();
        toggle($(this).parents('a'));
        return false;
    });

    function init() {
        var $list = $('#list_models');
        var list = store.getList();

        list.done(function (list) {
            if (!Object.keys(list)) $('#models_new').removeClass('hidden');
            else $('#models_notnew').removeClass('hidden');
        });

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
