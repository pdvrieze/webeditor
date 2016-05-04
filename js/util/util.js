/*
 * Utilities and shared functions
 */
define(function () {
    "use strict";

    /*
     * Crossbrower xml tag selection
     *
     * Apparently chrome can't do namespaces, and firefox can't ignore them
     * Additionally escapes the colon
     */
    function xmlSel(sel) {
        var split = sel.split(':');
        return sel.replace(':', '\\:') + ',' + split[1];
    }

    // export
    return {
        xmlSel: xmlSel
    }
});
