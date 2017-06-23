/**
 * Viewer tasks page controller
 *
 * @module ControllerViewer
 */
define(['jquery', 'store/task', 'webeditor'],
       function ($, store, webeditor) {
    "use strict";

    var template=webeditor.simpleTemplate

    /**
     * Initialisation of the viewer
     *
     * @param $html {Object} jQuery html to render
     *
     * @return {Promise}
     */
    function init($html) {
        var $def = $.Deferred();

        var $list = $html.find('#list');
        var $load = template.load($list);
        var manager = new store.PendingTaskManager(function (tasks) {
            var $html2 = render($list, tasks);
            if ($load) $load.resolve($html2);
            else $list.html($html2);
        });

        initListeners($list, manager);

        // no async neede after all
        $def.resolve($html, manager);
        return $def;
    }

    function render($list, tasks) {
        var list = [];
        $.each(tasks, function (i, task) {
            var view = $.extend({}, task);

            // rename acknowledged state
            if (view.state === 'Acknowledged') view.state = 'Available';

            var $html = template.render('viewer-task', view);
            $html.data('task', task);

            if (task.state != 'Acknowledged') {
                $html.find('.clickable.accept').hide();
            }

            list.push($html);
        });
        $list.empty().append(list);
    }

    function initListeners($list, manager) {
        $list.on('click', '.clickable.accept', function (e) {
            e.preventDefault();
            var task = $(this).parent().parent().data('task');
            manager.accept(task.handle);
            return false;
        });

        $list.on('click', '.clickable.remove', function (e) {
            e.preventDefault();
            var task = $(this).parent().parent().data('task');
            manager.cancel(task.handle);
            return false;
        });
        
        $list.on('click', '.list-group-item', function (e) {
            e.preventDefault();

            var task = $(this).parent().data('task');

            var $div = $(this).next();
            $list.find('collapse.in').collapse('hide');
            $div.collapse('show');

            renderInside($div, task);
            if (task.state != 'Taken' && task.state!='Started') {
                $div.find('input,select,button').prop('disabled', true);
            }

            return false;
        });

        $list.on('click', '.task-save', function () {
            var task = $(this).parents('.collapse')
                .parent()
                .data('task');
            manager.submit(task.handle, generateXml(task, $(this).prev()));
        });
    }

    var renderItem = {
        text: function (item) {
            return template.render('task-element-text', item);
        },

        list: function (item) {
            var view = $.extend({}, item);
            if (item.value && item.value.length) {
                var list = item.value.split(';').join('</option><option>');
                view.value = '<option>' + list + '</option>';
            }
            return template.render('task-element-list', view);
        },

        label: function (item) {
            return template.render('task-element-label', item);
        },

        password: function (item) {
            return template.render('task-element-password', item);
        },
    };

    function renderInside($div, task) {
        var elements = [];

        _.each(task.items, function (item) {
            var $item = $('<div>').append(renderItem[item.type](item));
            elements.push($item);
        });

        $div.find('.task-elements').empty().append(elements);
    }

    function generateXml(task, $elements) {
        var url = 'http://adaptivity.nl/userMessageHandler';
        var xml = '<umh:task xmlns:umh="' + url + '" ' +
                  'state="Complete" ' +
                  'handle="' + task.handle + '" ' + 
                  'remotehandle="' + task.remotehandle + '" ' + 
                  'instancehandle="' + task.instancehandle + '" ' +
                  'owner="' + task.owner + '">';

        $elements.find('input,select').each(function () {
            var name = $(this).attr('name');
            var type = $(this).attr('task-type');
            var value = $(this).val();
            xml += '<umh:item name="' + name + '" type="' + type + '" ' +
                   'value="' + value + '"/>';
        });

        return xml + '</umh:task>';
    }

    function destroy(manager) {
        manager.destroy();
    }

    // export
    return { init: init, destroy: destroy };
});
