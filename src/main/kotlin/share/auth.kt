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

import org.w3c.xhr.FormData
import org.w3c.xhr.XMLHttpRequest
import util.getAsync
import util.postAsync

private var username: String? = null
private const val LOGIN_LOCATION = "/accountmgr/login"
private const val LOGOUT_LOCATION = "/accountmgr/logout"

fun login(user:String, password: String) {
  postAsync(LOGIN_LOCATION, mapOf("username" to user, "password" to password)) {
    username = user
    true
  }
}

fun logout() {
  getAsync(LOGOUT_LOCATION) {
    username = null
    true
  }
}

fun tryLogin() {
  getAsync(LOGIN_LOCATION) { event ->
    val responseText = (event.target as XMLHttpRequest).responseText
    if (responseText.startsWith("login:")) {
      username = responseText.substring(6)
    }
  }
}

fun getUser() = username

fun isLoggedIn() = ! username.isNullOrEmpty()