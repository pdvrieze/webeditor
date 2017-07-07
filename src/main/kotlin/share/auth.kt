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

package share.auth

import jquery.JQueryPromise
import jquery.jQuery
import org.w3c.dom.events.Event
import org.w3c.xhr.XMLHttpRequest
import util.getAsync
import kotlin.js.json

private var username: String? = null
private const val LOGIN_LOCATION = "/accountmgr/login"
private const val LOGOUT_LOCATION = "/accountmgr/logout"

@JsName("login")
fun login(user:String, password: String): JQueryPromise<Unit> {
    return jQuery.post(LOGIN_LOCATION, json("username" to user, "password" to password)).then (doneCallback = { _, _, _ ->
        username = user;
        Unit
    })
}

@JsName("logout")
fun logout():JQueryPromise<Unit> {
    return jQuery.get(LOGOUT_LOCATION).then( { _,_,_ ->
        username = null;
        Unit
    });
}

@JsName("tryLogin")
fun tryLogin(onload: dynamic, onfail:dynamic) {
    getAsync(LOGIN_LOCATION, {onfail(it)}) { event: Event ->
        val responseText = (event.target as XMLHttpRequest).responseText
        if (responseText.startsWith("login:")) {
            username = responseText.substring(6)
            onload()
        } else {
            onfail()
        }
    }
}

@JsName("getUser")
fun getUser() = username

@JsName("isLoggedIn")
fun isLoggedIn() = ! username.isNullOrEmpty()