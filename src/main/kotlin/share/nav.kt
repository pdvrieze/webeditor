/*
 * Copyright (c) 2017.
 *
 * This file is part of ProcessManager.
 *
 * ProcessManager is free software: you can redistribute it and/or modify it under the terms of version 3 of the
 * GNU Lesser General Public License as published by the Free Software Foundation.
 *
 * ProcessManager is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even
 * the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License along with ProcessManager.  If not,
 * see <http://www.gnu.org/licenses/>.
 */

package share

import bootstrap
import jquery.*
import lodash.lodash
import org.w3c.dom.events.Event
import share.auth.isLoggedIn
import simpleTemplate
import kotlin.browser.window
import kotlin.js.*

/**
 * Navigation controller
 *
 * Provides a set of functions to route and navigate the website
 * Additionally handles the look and behaviour of the navigation bar
 *
 * @module Nav
 */
private typealias DestroyFun = ()->Unit

object nav {

    // list of hardcoded controllers that will need to be loaded for page change
    val controllers = arrayOf("editor-models", "workspace", "viewer-tasks")

    var _nav = jQuery(selector="nav") // navigation bar (should be one per page)


    var destroy: DestroyFun? = null // current controller destroy method
    
    var defaultPage:String? = null // default page

    /**
     * Initialise navigation bar
     */
    @JsName("init")
    fun init(page:String) {
        defaultPage = page

        initLoginBtn()
        initLogoutBtn()

        // links with .a_content will automatically change page
        jQuery(selector = "#page").on("click", "a.a_content", { e: JQueryEventObject, _ ->
            e.preventDefault() // no url change
            changePage(jQuery(this).attr("href").replace(Regex("^#"), ""))
        })

        // initialise history
        window.onpopstate = fun(e: Event) {
            val state = e.asDynamic().state

            if (state?.page!=null) {
                changePage(state.page, state.args)
            }
        }
    }

    /**
     * Initialise login button in the navigation bar
     */
    private fun initLoginBtn() {
        val loginButton = jQuery(selector = "#btn_login").bootstrap
        loginButton.click(fun(e: Event) {
            e.preventDefault()
            if (isLoggedIn()) return // already logged in

            jQuery(element=js("this")).bootstrap.button("loading") // start button loading animation

            // try to login with credentials
            share.auth.login(jQuery(selector = "#user").`val`() as String, jQuery(selector = "#pass").`val`() as String).then({ _: Any? ->
                jQuery(selector = "#alert_login_shown").remove() // remove alert if exists
                jQuery(selector = "#dialogue_auth").bootstrap.modal("hide")
                login()
            }).fail(JQueryPromiseCallback<Any> { value ->
                // show error if it doesn't
                val _login = jQuery(selector = "#alert_login")
                _login.clone().attr("id", "alert_login_shown")
                    .removeClass("hidden")
                    .insertAfter(_login)
            }).always(JQueryPromiseCallback { ->
                loginButton.button("reset") // end button loading animation
            })
        })
    }

    /**
     * Initialise logout button in the navigation bar
     */
    private fun initLogoutBtn() {
        jQuery(selector = "#btn_logout").click(fun(e) {
            e.preventDefault()
            if (!share.auth.isLoggedIn()) return // already logged out

            share.auth.logout().then({ _:Any? ->
                logout()
            }).fail(JQueryPromiseCallback {
                console.error("Loggin out failed", e)
            })
        })
    }

    /**
     * Sets up navigation bar for logged in user
     */
    @JsName("login")
    fun login() {
        autoHash().then({ value->
            _nav.find(".hidden-guest")?.addClass("hidden")
            _nav.find(".hidden-auth")?.removeClass("hidden")
            _nav.find("#username")?.html(share.auth.getUser()?:"")
        })
    }

    /**
     * Sets up navigation bar for logged out user
     */
    @JsName("logout")
    fun logout() {
        _nav.find(".hidden-auth")?.addClass("hidden")
        _nav.find(".hidden-guest")?.removeClass("hidden")
        changePage("index")
    }
    
    /**
     * Changes current page, can pass argument to the page controller
     *
     * @param page {String} name of the page to change to
     * @param args {Mixed} params to be passed to controller
     *
     * @return {Promise}
     */
    @JsName("changePage")
    fun changePage(page:String, vararg args:String): JQueryDeferred<JQuery> {
        var _load = simpleTemplate.load("#content")

        // render new page in content section
        var _html = simpleTemplate.render(page)

        // unload current page if needed
        if (destroy!=null) {
            destroy?.invoke() // destroy
            destroy = null
        }

        // reselect the buttons in the navigation if needed
        jQuery(selector = "li.active").removeClass("active")
        jQuery(selector = "li a[href=\"#$page\"]").parent().addClass("active")

        // remove elements that have specific pages to be shown on
        jQuery(selector = "[show-page][show-page!=\"$page\"]").hide()
        jQuery(selector = "[show-page=\"$page\"]").show()

        val state = window.history.state.asDynamic()
        if (state==null || state.page != page || state.args != args) {
            var url = page
            if (args.isNotEmpty()) url = (arrayOf(url)+args).joinToString("/")

            var newState = json("page" to page, "args" to args)

            if (state && state.page == "index") {
                window.history.replaceState(newState, page, '#' + url)
            }
            else window.history.pushState(newState, page, '#' + url)
        }

        // load controller if page has one in the hardcoded list
        if (controllers.indexOf(page) !== -1) {
            val requirejs = js("require")
            requirejs(arrayOf(page), fun (controller:dynamic) {
                // run its initialisation method
                controller.init(_html, args).then(fun (_html:JQuery, args:dynamic) {
                    _load.resolve(_html)

                    // setup destroy fun
                    if (controller.destroy) {
                        destroy = fun () { controller.destroy(args); }
                    }

                    // controller may run pos-html-append stuff, that is
                    // when all elements are rendered on the page
                    if (controller.post) controller.post(_html, args)
                })
            })
        }
        else _load.resolve(_html)

        return _load
    }

    /**
     * Automatically chooses the landing page depending on the hash
     *
     * @return {Promise}
     */
    private fun autoHash(): JQueryDeferred<JQuery> {
        var args:Array<String> = arrayOf()
        var page = defaultPage

        if (window.location.hash.isNotBlank() && window.location.hash != "#index") {
            // remove hash and separate arguments, but make sure no
            // consequtive slashes are used
            var url = window.location.hash.substring(1).split(Regex("/+"))
            var name = url[0] // name is the first element

            if (simpleTemplate.has(name)) {
                page = name // definitely not 404
                if (url.size == 2) args = arrayOf(url[1]) // only one argument
                else if (url.size > 2) args = url.drop(1).toTypedArray() // get all
            }
        }

        // change to this page
        return changePage(page!!, *args)
    }

    /**
     * Creates link in the menu for another page
     *
     * @param name {String} another page's name
     */
    @JsName("createOther")
    fun createOther(name:String) {
        jQuery(selector = "#other_app").attr("href", name + ".html")
            .html("Open " + lodash.capitalize(name))
    }
}
