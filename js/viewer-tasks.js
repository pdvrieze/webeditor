/**
 * Viewer tasks page controller
 *
 * @module ControllerViewer
 */
define(['jquery', 'store/task', 'util/simple-template'],
       function ($, store, template) {
    "use strict";

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
            var $html = render($list, tasks);
            if ($load) $load.resolve($html);
            else $list.html($html);
        });

        // no async neede after all
        $def.resolve($html, manager);
        return $def;
    }

    function render($list, tasks) {
        var list = [];
        $.each(tasks, function (i, task) {
            var $html = template.render('viewer-task', task);
            $html.data('task', task);
            list.push($html);
        });
        $list.empty().append(list);
    }

    function destroy(manager) {
        console.log(manager);
        manager.destroy();
    }

    // export
    return { init: init, destroy: destroy };
});
