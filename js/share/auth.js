define(['jquery', 'util/simple-template'], function ($, template) {
    "use strict";
    
    var username = null;
    var $page = $('#page').addClass('user-lo');

    function onLogin(user) {
        username = user;
        $page.removeClass('user-lo').addClass('user-li').trigger('auth');
        template.setGlobal('username', user);
    }

    function error() {
        var $login = $('#alert_login');
        var x = $login.clone().attr('id', null)
            .removeClass('hidden')
            .insertAfter($login);
    }

    function login() {
        if ($page.hasClass('user-li')) return;

        var user = $('#user').val();
        var pass = $('#pass').val();

        var $this = $(this);
        $this.button('loading');

        $.get('/accounts/login', {
            username: user,
            password: pass
        }).success(function () {
            $('#dialogue_auth').modal('hide');
            onLogin(user);
        }).fail(function () {
            error();
        }).always(function () {
            $this.button('reset');
        })

        return false;
    }

    function logout() {
        if ($page.hasClass('user-lo')) return;
        $.get('/accounts/logout').success(function () {
            username = null;
            $page.removeClass('user-li').addClass('user-lo').trigger('auth');
        });
        return false;
    }

    $('#btn_login').click(login);
    $('#btn_logout').click(logout);

    return {
        isLoggedIn: function () {
            return username != null;
        },

        init: function () {
            $.get('/accounts/login').success(function (res) {
                onLogin(res.replace(/^login:/, ''));
            });
        }
    };
});
