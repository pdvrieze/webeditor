/*
 * Copyright (c) 2017. 
 *
 * This file is part of ProcessManager.
 *
 * ProcessManager is free software: you can redistribute it and/or modify it under the terms of version 3 of the 
 * GNU Lesser General Public License as published by the Free Software Foundation.
 *
 * ProcessManager is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even
 * the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the 
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License along with ProcessManager.  If not,
 * see <http://www.gnu.org/licenses/>.
 */

package util

import jquery.JQueryDeferred
import jquery.jQuery
import org.w3c.dom.events.Event
import org.w3c.xhr.XMLHttpRequest

/**
 * Utilities and shared functions
 *
 * @module Util
 */
object util {

    /**
     * Crossbrower xml tag selection
     *
     * Apparently chrome can't do namespaces, and firefox can't ignore them
     * Additionally escapes the colon
     *
     * @param sel {String} full xml tag with namespace
     * @return {String} fixed selector
     */
    @JsName("xmlSel")
    fun xmlSel(sel:String): String {
        var split = sel.split(':');
        return sel.replace(":", "\\:") + ',' + split[1];
    }

    /**
     * Fakes 'multipart/form-data' form but the data is coming from its
     * second argument, and not users filesystem
     *
     * http://stackoverflow.com/a/3012611
     *
     * @param url {String} url
     * @param name {String} attribute name
     * @param data {String} data to upload
     * @param nobody {Boolean} discard attachment emulation
     *
     * @return {Promise}
     */
    @JsName("upload")
    fun upload(url:String, name:String, data:String, nobody:Boolean=false): JQueryDeferred<dynamic> {
        var def = jQuery.Deferred<dynamic>(); // we will return promise

        var boundary = "---------------------------7da24f2e50046";
        var xhr = XMLHttpRequest()
        var body = "--$boundary\r\n" +
                   "Content-Disposition: form-data; name=\"$name\"; filename=\"temp.xml\"\r\n" +
                   "Content-type: plain/text\r\n" +
                   "\r\n" +
                   "$data\r\n" +
                   "--$boundary--";

        if (nobody) body = data;

        xhr.open("POST", url, true);
        if (!nobody) {
            xhr.setRequestHeader( // set appropraite headers
                "Content-type", "multipart/form-data; boundary=" + boundary
            )
        }

        xhr.onreadystatechange = {event: Event ->
            if (xhr.readyState == 4.toShort()) { // finished
                if (xhr.status == 200.toShort()) { // success
                    def.resolve(xhr.responseText);
                }
                else def.reject(xhr);
            }
        }

        xhr.send(body); // start request

        return def;
    }

    /**
     * Replaces or removes the attribute in an XML string
     *
     * @param str {String} string
     * @param attr {String} attribute name
     * @param value {String} old value
     * @param replace {String} replacement
     *
     * @return {String} replaced string
     */
    @JsName("replaceAttr")
    fun replaceAttr(str:String, attr:String, value:String?=null, replace:String=""): String {
        if (value!=null) return str.replace("$attr=\"$value\"",
                                            "$attr=\"$replace\"");
        else {
            return str.replace(Regex(attr + "=\".*?\""), "");
        }
    }

    /**
     * Pretty format given xml
     * https://gist.github.com/sente/1083506
     *
     * @param xml {String} xml to format
     *
     * @return {String}
     */
    @JsName("formatXml")
    fun formatXml(xml:String):String {
        var formatted = "";
        val xml = xml.replace(Regex("(>)(<)(\\/*)"), "$1\r\n$2$3");
        var pad = 0;
        xml.split("\r\n").forEach { node ->

            var indent = 0;
            if (node.matches( Regex(".+<\\/\\w[^>]*>$") )) {
                indent = 0;
            } else if (node.matches( "^<\\/\\w" )) {
                if (pad != 0) {
                    pad -= 1;
                }
            } else if (node.matches( "^<\\w[^>]*[^\\/]>.*$" )) {
                indent = 1;
            } else {
                indent = 0;
            }

            var padding = "";
            for (i in 0 until pad) {
                padding += "  ";
            }

            formatted += padding + node + "\r\n";
            pad += indent;
        }

        return formatted;
    }
}
