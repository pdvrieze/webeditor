define(['jquery'], function ($) {
    "use strict";
    
    var username = null; // username (if null user is not logged in)

    var urlLogin = '/accounts/login'; // login url

    /*
     * Log user in
     */
    function login(user, pass) {
        return $.get(urlLogin, {
            username: user, password: pass
        }).then(function () { username = user; });
    }

    /*
     * Log user out
     */
    function logout() {
        return $.get('/accounts/logout').then(function () {
            username = null;
        });
    }

    /*
     * Accesses the accounts login page, and tries to automatically
     * get authenticated
     */
    function tryLogin() {
        return $.get(urlLogin).then(function (res) {
            // on success page return 'login:[username]'
            username = res.replace(/^login:/, '');
        });
    }

    // export
    return {
        login: login,
        logout: logout,
        tryLogin: tryLogin,
        getUser: function () { return username; },
        isLoggedIn: function () { return username != null; }
    };
});
