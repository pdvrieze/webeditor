require.config({
    urlArgs: "bust=" + (new Date()).getTime()
});

requirejs(['config'], function () {
    "use strict";

    function init($) {
        var $page = $('#page');
        $page.on('auth', function () {
            var page = 'index';
            if (!$page.hasClass('user-lo')) {
                page = 'editor-models';
                if (location.hash == '#about') page = 'about';
                else if (/^#model_/.test(location.hash)) {
                    page = 'editor-workspace';
                    var handle = location.hash.replace('#model_', '');
                    $('#content').attr('handle', handle);
                }
            }
            $('#content').attr('page', page).trigger('page-change');
        });

        $page.on('show.bs.collapse', '#list_models .collapse', function () {
            var href = $(this).attr('id');
            $('#list_models a[href="#' + href + '"]').addClass('active');
        });
        $page.on('hide.bs.collapse', '#list_models .collapse', function () {
            var href = $(this).attr('id');
            $('#list_models a[href="#' + href + '"]').removeClass('active');
        });
    }

    requirejs(['util/simple-template'], function (template) {
        template.render('#page', 'editor').then(function () {
            requirejs(['bootstrap'], function () {
                require(['jquery', 'share/auth', 'share/links', 'editor-models',
                        'workspace'], function ($, auth) {
                            init($);
                            auth.init();
                        });
            });
        });
    })
});
