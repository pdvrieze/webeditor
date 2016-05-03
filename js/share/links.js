define(['jquery', 'util/simple-template'], function ($, template) {
    "use strict";

    function render(what, $where) {
        return template.render($where, what).then(function (done) {
            $('.when-needed').hide();
            $('li.active').removeClass('active');
            $('li a[href=' + what + ']').parent().addClass('active');
            $where.trigger('loaded', what);
        });
    }

    $('#page').on('click', '.a_content', function (event) {
        event.preventDefault();

        var $content = $('#content');
        var href = $(this).attr('href');
        $content.attr('page', href).trigger('page-change');
    });

    $('#page').on('page-change', '#content', function () {
        var $this = $(this);
        var href = $this.attr('page');
        render(href, $this);
    });

    return {
        render: render
    }
});
