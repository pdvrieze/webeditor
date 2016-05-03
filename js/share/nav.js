/*
 * Navigation controller
 *
 * Provides a set of functions to route and navigate the website
 * Additionally handles the look and behaviour of the navigation bar
 */
define(['jquery', './auth', 'util/simple-template'],
       function ($, auth, template) {
    "use strict";

    // list of hardcoded controllers that will need to be loaded for page change
    var controllers = [ 'editor-models', 'workspace' ];

    var $nav = $('nav'); // navigation bar (should be one per page)

    /*
     * Initialise navigation bar
     */
    function init() {
        initLoginBtn();
        initLogoutBtn();
    }

    /*
     * Initialise login button in the navigation bar
     */
    function initLoginBtn() {
        $('#btn_login').click(function (e) {
            e.preventDefault();
            if (auth.isLoggedIn()) return; // already logged in

            var $this = $(this);
            $this.button('loading'); // start button loading animation

            // try to login with credentials
            auth.login($('#user').val(), $('#pass').val()).then(function () {
                $('#alert_login_shown').remove(); // remove alert if exists
                $('#dialogue_auth').modal('hide');
                login();
            }).fail(function () {
                // show error if it doesn't
                var $login = $('#alert_login');
                $login.clone().attr('id', 'alert_login_shown')
                    .removeClass('hidden')
                    .insertAfter($login);
            }).always(function () {
                $this.button('reset'); // end button loading animation
            });
        });
    }

    /*
     * Initialise logout button in the navigation bar
     */
    function initLogoutBtn() {
        $('#btn_logout').click(function (e) {
            e.preventDefault();
            if (!auth.isLoggedIn()) return; // already logged out

            auth.logout().then(function () {
                logout();
            }).fail(function (e) {
                console.error('Loggin out failed', e);
            });
        });
    }

    /*
     * Sets up navigation bar for logged in user
     */
    function login() {
        autoHash().then(function () {
            $nav.find('.hidden-guest').addClass('hidden');
            $nav.find('.hidden-auth').removeClass('hidden');
            $nav.find('#username').html(auth.getUser());
        });
    }

    /*
     * Sets up navigation bar for logged out user
     */
    function logout() {
        $nav.find('.hidden-auth').addClass('hidden');
        $nav.find('.hidden-guest').removeClass('hidden');
        changePage('index');
    }


    
    /*
     * Changes current page, can pass argument to the page controller
     */
    function changePage(page, args) {
        // content section that will be changing
        var $content = $('#content');

        var $def = $.Deferred(); // we don't know if we need to load controller

        // render new page in content section
        template.render($content, page).then(function () {
            // reselect the buttons in the navigation if needed
            $('li.active').removeClass('active');
            $('li a[href="#' + page + '"]').parent().addClass('active');

            // remove elements that have specific pages to be shown on
            $('[show-page][show-page!="' + page + '"]').hide();
            $('[show-page="' + page + '"]').show();

            // load controller if page has one in the hardcoded list
            if (controllers.indexOf(page) !== -1) {
                require([page], function (controller) {
                    // run its initialisation method
                    controller.init(args);
                })
            }
            else $def.resolve();
        }).fail(function (e) { $def.reject(e); });

        return $def;
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
        if (match) return changePage('workspace', parseInt(match[1]));

        return changePage(page);
    }

    // export
    return {
        init: init,
        login: login,
        logout: logout
    }
});
