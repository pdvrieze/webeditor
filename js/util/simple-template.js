define(['jquery'], function ($) {
    "use strict";

    var $page = $('#page');

    var globals = {};
    var templates = {};
    var $templates = $.getJSON('view/all').done(function (json) {
        templates = json;
    });

    function render(name, view, prefix) {
        if (!templates[name]) {
            throw new Error('Layout "' + name + '" not found');
        }

        var regex = /{{(\s*\w+?\s*)}}/g;
        var html = templates[name].replace(regex, function (match, key) {
            if (view && view.hasOwnProperty(key)) return view[key];
            return '';
        })
        var $layout = $(html);
        if ($layout.is('layout')) {
            var layout = $layout.attr('name');
            var layoutPrefix = $layout.attr('prefix');
            var $doc = render(layout, view, layoutPrefix);
            $doc.find('insert[section]').each(function () {
                var $this = $(this);
                var selector = 'section[name=' + $this.attr('section') + ']';
                $this.replaceWith($layout.find(selector).children());
            })
            $layout = $doc;
        }

        $layout = $('<div>').append($layout);

        $layout.find('insert[name]').each(function () {
            var $this = $(this);
            var insert = $this.attr('name');
            if (prefix && !$this.attr('noprefix')) {
                insert = prefix + '-' + insert;
            }
            var $render = render(insert, view);
            $this.replaceWith($render);
        });

        $layout.find('[int]').each(function () {
            var intName = $(this).attr('int');
            $(this).html(globals[intName]);
        });

        return $layout.children();
    }

    function values(object) {
        var values = [];
        for (var key in object) {
            if (object.hasOwnProperty(key)) values.push(object[key]);
        }
        return values;
    }

    return {
        render: function (selector, name, view) {
            var $ptr = $(selector);
            $ptr.empty().addClass('loader');

            var $def = $.Deferred();


            $.when( $templates, view ).done(function (_, view) {
                if (view && !Array.isArray(view)) view = values(view);

                try {
                    if (view) {
                        var $layout = [];
                        $.each(view, function (i, view) {
                            $layout.push(render(name, view))
                        });
                    }
                    else var $layout = render(name);
                }
                catch (e) {
                    console.error(e);
                    $def.reject();
                }

                $ptr.removeClass('loader').append($layout);
                $def.resolve();
            });

            return $def;
        },

        setGlobal: function (name, value) {
            globals[name] = value;
            $page.find('[int=' + name + ']').html(value);
        }
    };
});
