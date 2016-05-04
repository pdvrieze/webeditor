/*
 * Navigation controller
 *
 * Provides a set of functions to route and navigate the website
 * Additionally handles the look and behaviour of the navigation bar
 */
define(['jquery', './auth', 'util/simple-template', 'lodash'],
       function ($, auth, template, _) {
    "use strict";

    // list of hardcoded controllers that will need to be loaded for page change
    var controllers = [ 'editor-models', 'workspace' ];

    var $nav = $('nav'); // navigation bar (should be one per page)

    var destroy = null; // current controller destroy method

    /*
     * Initialise navigation bar
     */
    function init() {
        initLoginBtn();
        initLogoutBtn();

        // links with .a_content will automatically change page
        $('nav').on('click', 'a.a_content', function (e) {
            e.preventDefault(); // no url change
            changePage($(this).attr('href').replace(/^#/, ''));
        });

        // initialise history
        window.onpopstate = function (e) {
            if (e.state && e.state.page) {
                changePage(e.state.page, e.state.args);
            }
        };
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
        var $load = template.load('#content');

        // render new page in content section
        var $html = template.render(page);

        // unload current page if needed
        if (destroy) {
            destroy(); // destroy
            destroy = null;
        }

        // reselect the buttons in the navigation if needed
        $('li.active').removeClass('active');
        $('li a[href="#' + page + '"]').parent().addClass('active');

        // remove elements that have specific pages to be shown on
        $('[show-page][show-page!="' + page + '"]').hide();
        $('[show-page="' + page + '"]').show();

        var state = history.state;
        if (!state || state.page != page || state.args != args) {
            var url = page;
            if (args) url = _.flatten([url, args]).join('/');

            var newState = { page: page, args: args };

            if (state && state.page == 'index') {
                history.replaceState(newState, page, '#' + url);
            }
            else history.pushState(newState, page, '#' + url);
        }

        // load controller if page has one in the hardcoded list
        if (controllers.indexOf(page) !== -1) {
            require([page], function (controller) {
                // run its initialisation method
                controller.init($html, args).then(function ($html, args) {
                    $load.resolve($html);

                    // setup destroy function
                    if (controller.destroy) {
                        destroy = function () { controller.destroy(args); };
                    }

                    // controller may run pos-html-append stuff, that is
                    // when all elements are rendered on the page
                    if (controller.post) controller.post($html, args);
                });
            })
        }
        else $load.resolve($html);

        return $load;
    }

    /*
     * Automatically chooses the landing page depending on the hash
     */
    function autoHash() {
        var args = null;
        var page = 'editor-models'; // default landing page

        if (location.hash && location.hash != '#index') {
            // remove hash and separate arguments, but make sure no
            // consequtive slashes are used
            var url = location.hash.substr(1).replace(/\/+/, '/').split('/');
            var name = url[0]; // name is the first element

            if (template.has(name)) {
                page = name; // definitely not 404
                if (url.length == 2) args = url[1] // only one argument
                else if (url.length > 2) args = url.slice(1); // get all
            }
        }

        // change to this page
        return changePage(page, args);
    }

    // export
    return {
        init: init,
        login: login,
        logout: logout,
        changePage: changePage
    }
});
