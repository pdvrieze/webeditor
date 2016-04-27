define(['jquery', 'util/simple-template'], function ($, template) {
    "use strict";

    $('#page').on('click', '.a_content', function (event) {
        event.preventDefault();

        var $content = $('#content');
        var href = $(this).attr('href');
        $content.attr('page', href).trigger('page-change');
    });

    $('#page').on('page-change', '#content', function () {
        var $this = $(this);
        var href = $this.attr('page');
        template.render($this, href).then(function (done) {
            $('li.active').removeClass('active');
            $('li a[href=' + href + ']').parent().addClass('active');
            $this.trigger('loaded', href);
        });
    });
});
