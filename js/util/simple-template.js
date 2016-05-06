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
define(['jquery'], function ($) {
    "use strict";

    // templates are stored in this variable
    // nohing will render before they are loaded
    var templates = {};
    var $templates = $.getJSON('view/all').then(function (json) {
        templates = json;
    });

    /**
     * Initialise and wait on templates to be loaded
     *
     * @return {Promise}
     */
    function init() {
        return $templates;
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
    function render(name, view) {
        if (!templates[name]) { // if template is not there, fail
            throw new Error('Layout "' + name + '" not found');
        }

        // get template
        var $html = $(getHtml(name, view));

        // apply outer layout
        if ($html.is('layout')) {
            var layout = $html.attr('name');
            $html = applyLayout(layout, view, $html);
        }

        // wrap in div to access full html
        if ($html.length > 1) $html = $('<div>').append($html);

        // substitute inner inserts
        $html.find('insert[name]').each(function () {
            var insert = $(this).attr('name');
            $(this).replaceWith(render(insert, view));
        });

        return $html;
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
    function getHtml(name, view) {
        var html = templates[name];
        if (view) {
            // view variable substitution
            html = html.replace(/{{(\s*\w+?\s*)}}/g, function (match, key) {
                return view.hasOwnProperty(key) ? view[key] : '';
            });
        }
        return html;
    }

    /**
     * Apply outer layout with sections
     *
     * @param name {String} name of layout
     * @param view {Object} view
     * @param $sections {Array} sections of layout
     */
    function applyLayout(name, view, $sections) {
        var $html = render(name, view);
        $html.find('insert[section]').each(function () {
            var selector = 'section[name=' + $(this).attr('section') + ']';
            $(this).replaceWith($sections.find(selector).children());
        });
        return $html;
    }

    /**
     * Promise based loader
     *
     * @param load {String} selector of object to render to
     *
     * @return {Promise}
     */
    function load(target) {
        var $target = $(target); // find target node (or wrap with jquery)
        $target.empty().addClass('loader'); // start loading animation

        var $def = $.Deferred();

        $def.then(function ($html) {
            $target.removeClass('loader').append($html);
        });

        return $def;
    }

    /**
     * Render something directly into element
     *
     * @param $where {Object} jQuery element where to render
     * @param what {String} name of the template to render
     * @param view {Object} view
     */
    function renderTo($where, what, view) {
        $where.empty().append(render(what, view));
    }

    /**
     * Return true if template with given name exists
     *
     * @return {Boolean}
     */
    function has(name) { return templates.hasOwnProperty(name); }

    // export
    return {
        has: has,
        init: init,
        load: load,
        render: render,
        renderTo: renderTo
    };
});
