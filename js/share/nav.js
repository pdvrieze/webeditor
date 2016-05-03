/*
 * Navigation controller
 *
 * Provides a set of functions to route and navigate the website
 * Additionally handles the look and behaviour of the navigation bar
 */
define(['jquery', './auth', 'util/simple-template'],
       function ($, auth, template) {
    "use strict";

    var $nav = $('nav'); // navigation bar (should be one per page)
    
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
     * Initialise navigation bar
     */
    function init() {
        initLoginBtn();
        initLogoutBtn();
    }

    /*
     * Changes current page, can pass argument to the page controller
     */
    function changePage(page, args) {
        // content section that will be changing
        var $content = $('#content');

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

    /*
     * Sets up navigation bar for logged in user
     */
    function login() {
        $nav.find('.hidden-guest').addClass('hidden');
        $nav.find('.hidden-auth').removeClass('hidden');
        $nav.find('#username').html(auth.getUser());
    }

    /*
     * Sets up navigation bar for logged out user
     */
    function logout() {
        $nav.find('.hidden-auth').addClass('hidden');
        $nav.find('.hidden-guest').removeClass('hidden');
        changePage('index');
    }

    // export
    return {
        init: init,
        login: login,
        logout: logout,
        autoHash: autoHash
    }
});
