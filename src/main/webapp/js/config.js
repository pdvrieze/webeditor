/**
 * Configuration file
 *
 * Sets up library paths for jointjs
 */
define(function () {
    "use strict";

    require.config({
        baseUrl: 'js/',
        paths: {
            jquery: '../bower_components/jquery/dist/jquery',
            bootstrap: '../bower_components/bootstrap/dist/js/bootstrap.min',
            joint: '../bower_components/jointjs/dist/joint',
            lodash: '../bower_components/lodash/lodash.min',
            underscore: '../bower_components/underscore/underscore-min',
            backbone: '../bower_components/backbone/backbone',
            Sortable: '../bower_components/Sortable/Sortable.min',
            dagre: '../bower_components/dagre/dist/dagre.core.min',
            graphlib: '../bower_components/graphlib/dist/graphlib.core.min'
        },
        shim: {
            dagre: {
                deps: [ 'graphlib' ]
            }
        }
    });
});
