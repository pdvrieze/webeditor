/**
 * Viewer tasks page controller
 *
 * @module ControllerViewer
 */
define(['jquery', 'store/task'], function ($, store) {
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

        var manager = new store.PendingTaskManager();

        // no async neede after all
        $def.resolve($html, manager);
        return $def;
    }

    function destroy(manager) {
        console.log(manager);
        manager.destroy();
    }

    // export
    return { init: init, destroy: destroy };
});
