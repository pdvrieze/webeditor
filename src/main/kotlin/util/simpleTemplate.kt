import jquery.JQuery
import jquery.JQueryDeferred
import jquery.JQueryPromise
import jquery.jQuery
import kotlin.browser.document
import kotlin.js.Json

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

/**
 * Simple and small template engine
 *
 * Requires view/all url to export JSON templates
 * Only limited features:
 *  - Outer layout
 *  - Inserts
 *  - View variable substitution
 *  - Loading with promise support
 *
 * @module Template
 */

object simpleTemplate {

    // templates are stored in this variable
    // nohing will render before they are loaded
    var templates: Json = js("{}");

    private var _templates = jQuery.getJSON("view/all").then<Map<String, String>>({ json, _, _ ->
                                                                 templates = json as Json
                                                                 templates
                                                             })

    /**
     * Initialise and wait on templates to be loaded
     *
     * @return {Promise}
     */
    @JsName("init")
    fun _init(): JQueryPromise<Map<String, String>> {
        return _templates
    }

    /**
     * Renders template substituting templated parts like inserts and view,
     * and provides outer layout if needed
     *
     * @param name {String} template name
     * @param view {Object} view
     *
     * @return {Object} jQuery html
     */
    @JsName("render")
    fun render(name: String, view: dynamic = null): JQuery {
        if (templates[name]==null) { // if template is not there, fail
            throw Error("Layout \"$name\" not found");
        }

        // get template
        var _html: JQuery = jQuery(html = getHtml(name, view))

        // apply outer layout
        if (_html.`is`("layout")) {
            var layout = _html.attr("name");
            _html = applyLayout(layout, view, _html)
        }

        // wrap in div to access full html
        if (_html.length.toInt() > 1) _html = jQuery(html = "<div>").append(_html);

        // substitute inner inserts
        _html.find("insert[name]")?.each({ index, elem ->
                                            var insert = jQuery(elem).attr("name");
                                            jQuery(elem).replaceWith(render(insert, view));
                                        });

        return _html
    }

    /**
     * Extract HTML text from template, and perform regex to substitute view
     * variables
     *
     * @param name {String} template name
     * @param view {Object} view
     *
     * @return {String} template html substituted
     */
    private fun getHtml(name: String, view: dynamic): String {
        var html: String? = templates[name] as String?
        if (view) {
            // view variable substitution
            html = html?.replace(Regex("\\{\\{(\\s*\\w+?\\s*)\\}\\}"), { matchResult ->
                val key = matchResult.groups[1]?.value
                if (view.hasOwnProperty(key)) view[key] else "";
            });
        }
        return html ?: ""
    }

    /**
     * Apply outer layout with sections
     *
     * @param name {String} name of layout
     * @param view {Object} view
     * @param _sections {Array} sections of layout
     */
    private fun applyLayout(name: String, view: dynamic, _sections: JQuery): JQuery {
        val _html = render(name, view);
        _html.find("insert[section]")?.each({ index, elem ->
                                               val jElem = jQuery(elem)
                                               var selector = "section[name=${jElem.attr("section")}]";
                                               jElem.replaceWith(_sections.find(selector)?.children() ?: jQuery());
                                           });
        return _html;
    }

    /**
     * Promise based loader
     *
     * @param load {String} selector of object to render to
     *
     * @return {Promise}
     */
    @JsName("load")
    fun load(target: String): JQueryDeferred<JQuery> {
        val _target = jQuery(selector = target,
                             context = document.documentElement); // find target node (or wrap with jquery)
        _target.empty().addClass("loader"); // start loading animation

        var _def = jQuery.Deferred<JQuery>();

        _def.then({ _html, _ ->
                      _target.removeClass("loader")
                      if(_html!=null) _target.append(_html);
                  });

        return _def;
    }

    /**
     * Render something directly into element
     *
     * @param _where {Object} jQuery element where to render
     * @param what {String} name of the template to render
     * @param view {Object} view
     */
    @JsName("renderTo")
    fun renderTo(_where: JQuery, what: String, view: dynamic) {
        _where.empty().append(render(what, view));
    }

    /**
     * Return true if template with given name exists
     *
     * @return {Boolean}
     */
    @JsName("has")
    fun has(name: String) = templates[name]!=null
}
