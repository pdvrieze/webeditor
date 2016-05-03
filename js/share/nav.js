/*
 * Navigation controller
 *
 * Provides a set of functions to route and navigate the website
 */
define(['jquery', 'util/simple-template'], function ($, template) {
    "use strict";
    
    // main page
    var $page = $('#page');

    // content section that will be changing
    var $content = $('#content');

    /*
    $('#page').on('click', '.a_content', function (event) {
        var $content = $('#content');
        var href = $(this).attr('href').replace(/^#/, '');
        $content.attr('page', href).trigger('page-change');
    });

    $('#page').on('page-change', '#content', function () {
        var $this = $(this);
        var href = $this.attr('page');
        render(href, $this);
    });

    $page.on('show.bs.collapse', '#list_models .collapse', function () {
        var href = $(this).attr('id');
        $('#list_models a[href="#' + href + '"]').addClass('active');
    });
    $page.on('hide.bs.collapse', '#list_models .collapse', function () {
        var href = $(this).attr('id');
        $('#list_models a[href="#' + href + '"]').removeClass('active');
    });
    */

    /*
     * Changes current page, can pass argument to the page controller
     */
    function changePage(page, args) {
        // render new page in content section
        template.render($content, page).then(function () {
            // reselect the buttons in the navigation if needed
            $('li.active').removeClass('active');
            $('li a[href="#' + page + '"]').parent().addClass('active');
        });
    }

    /*
     * Automatically chooses the landing page depending on the hash
     */
    function autoHash() {
        var page = 'editor-models'; // default landing page

        // route to about page if needed
        if (location.hash == '#about') page = 'about';

        // if model is needed go there and select the correct model
        var match = location.hash.match(/^#model_(\d+)/);
        if (match) changePage('workspace', parseInt(match[1]));
    }

    // export
    return {
        autoHash: autoHash,
        index: function () { changePage('index'); }
    }
});
