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

    /*
     * Fakes 'multipart/form-data' form but the data is coming from its
     * second argument, and not users filesystem
     *
     * http://stackoverflow.com/a/3012611
     */
    function upload(url, name, data) {
        var $def = $.Deferred(); // we will return promise

        var boundary = "---------------------------7da24f2e50046";
        var xhr = new XMLHttpRequest();
        var body = '--' + boundary + '\r\n'
                 + 'Content-Disposition: form-data; name="' + name + '"; '
                 + 'filename="temp.xml"\r\n' // any name is OK
                 + 'Content-type: plain/text\r\n\r\n'
                 + data + '\r\n'
                 + '--' + boundary + '--';

        xhr.open("POST", url, true);
        xhr.setRequestHeader( // set appropraite headers
            "Content-type", "multipart/form-data; boundary=" + boundary
        );

        xhr.onreadystatechange = function () {
            if (xhr.readyState == 4) { // finished
                if (xhr.status == 200) { // success
                    $def.resolve(xhr.responseText);
                }
                else $def.reject(xhr);
            }
        }

        xhr.send(body); // start request

        return $def;
    }

    /*
     * Replaces or removes the attribute in an XML string
     */
    function replaceAttr(str, attr, value, replace) {
        if (value) return str.replace(attr + '="' + value + '"',
                                      attr + '="' + replace + '"');
        else {
            var regex = new RegExp(attr + '=".*?"');
            return str.replace(regex, '');
        }
    }

    /*
     * Pretty format given xml
     * https://gist.github.com/sente/1083506
     */
    function formatXml(xml) {
        var formatted = '';
        var reg = /(>)(<)(\/*)/g;
        xml = xml.replace(reg, '$1\r\n$2$3');
        var pad = 0;
        jQuery.each(xml.split('\r\n'), function(index, node) {
            var indent = 0;
            if (node.match( /.+<\/\w[^>]*>$/ )) {
                indent = 0;
            } else if (node.match( /^<\/\w/ )) {
                if (pad != 0) {
                    pad -= 1;
                }
            } else if (node.match( /^<\w[^>]*[^\/]>.*$/ )) {
                indent = 1;
            } else {
                indent = 0;
            }

            var padding = '';
            for (var i = 0; i < pad; i++) {
                padding += '  ';
            }

            formatted += padding + node + '\r\n';
            pad += indent;
        });

        return formatted;
    }

    // export
    return {
        xmlSel: xmlSel,
        upload: upload,
        formatXml: formatXml,
        replaceAttr: replaceAttr
    }
});
