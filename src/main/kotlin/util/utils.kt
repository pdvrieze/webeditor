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

package util

import org.w3c.dom.events.Event
import org.w3c.xhr.FormData
import org.w3c.xhr.ProgressEvent
import org.w3c.xhr.XMLHttpRequest

typealias EventHandler = (Event)->dynamic
typealias ProgressEventHandler = (ProgressEvent)->dynamic

fun postAsync(url:String, data: Map<String, String>, onerror: EventHandler? = null, onload: EventHandler) {
  requestAsync(url, "POST", data, onerror, onload)
}

fun getAsync(url:String, onerror: EventHandler? = null, onload: EventHandler) {
  requestAsync(url, "GET", emptyMap(), onerror, onload)
}

fun requestAsync(url:String, method: String, data: Map<String, String>, onerror: EventHandler? = null, onload: EventHandler) {
  val request = XMLHttpRequest().apply {
    open(method, url)
    setRequestHeader("Accept", "text/plain")
    this.onload = onload
    if (onerror !=null) this.onerror = onerror
  }

  val postData = if (data.isNotEmpty()) {
    FormData().apply {
      for ((key, value) in data.entries)
      {
        append(key, value)
      }
    }
  } else null

  try {
    request.send(postData)
  } catch (e: Exception) {
    console.log("Could not send request to $url", e)
  }

}
