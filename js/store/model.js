define(['jquery', 'share/auth'], function ($, auth) {
    "use strict";

    var models = {};
    var $def = null;

    function saveModel(handle, data, $def) {
        $def.reject();
    }

    function fetchModel(handle, name) {
        return $.get('/ProcessEngine/processModels/' + handle)
            .done(function (xml) {
                models[handle] = {
                    handle: handle,
                    name: name,
                    xml: xml
                };
            });
    }

    function deleteModel(handle) {
        return $.ajax({
            url: '/ProcessEngine/processModels/' + handle,
            type: 'DELETE'
        });
    }
    
    return {
        getList: function (callback) {
            return $def ? $def : this.update();
        },

        update: function () {
            $def = $.Deferred();
            var deferreds = [];
            $.get('/ProcessEngine/processModels').done(function (xml) {
                models = {};
                $(xml).find('processModel').each(function () {
                    var $this = $(this);
                    var handle = $this.attr('handle');
                    deferreds.push(fetchModel(handle, $this.attr('name')));
                });
                $.when.apply($, deferreds).then(function () {
                    $def.resolve(models);
                });
            });
            return $def;
        },

        renameModel: function (handle, name) {
            var $def = $.Deferred();
            renameModel(handle, name, $def);
            return $def;
        },

        saveModel: function (handle, data) {
            var $def = $.Deferred();
            saveModel(handle, data, $def);
            return $def;
        },

        getModel: function (id) {
            return models[id];
        },

        deleteModel: deleteModel
    };
});
