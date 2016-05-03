define(['jquery', 'share/auth'], function ($, auth) {
    "use strict";

    var models = {};
    var $def = null;

    var URL = '/ProcessEngine/processModels';

    // http://stackoverflow.com/a/3012611
    function beginQuoteFileUnquoteUpload(data, url) {
        var $def = $.Deferred();

        var boundary = "---------------------------7da24f2e50046";
        var xhr = new XMLHttpRequest();
        var body = '--' + boundary + '\r\n'
                 + 'Content-Disposition: form-data; name="processUpload"; '
                 + 'filename="temp.xml"\r\n'
                 + 'Content-type: plain/text\r\n\r\n'
                 + data + '\r\n'
                 + '--' + boundary + '--';

        xhr.open("POST", url, true);
        xhr.setRequestHeader(
            "Content-type", "multipart/form-data; boundary="+boundary
        );

        xhr.onreadystatechange = function ()
        {
            if (xhr.readyState == 4) {
                if (xhr.status == 200) {
                    $def.resolve();
                }
                else $def.reject();
            }
        }

        xhr.send(body);

        return $def;
    }

    function renameModel(handle, name) {
        var xml = models[handle].xml;
        var str = new XMLSerializer().serializeToString(xml)
            .replace('name="' + models[handle].name + '"',
                     'name="' + name + '"');
        return beginQuoteFileUnquoteUpload(str, URL + '/' + handle);
    }

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
        }).success(function () {
            delete models[handle];
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

        renameModel: renameModel,
        saveModel: saveModel,
        deleteModel: deleteModel,
        getModel: function (id) { return models[id]; }
    };
});
