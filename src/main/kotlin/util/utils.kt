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

import kotlinx.html.*
import org.w3c.dom.Element
import org.w3c.dom.Node
import org.w3c.dom.events.Event
import org.w3c.xhr.*

typealias EventHandler = (Event)->dynamic
typealias ProgressEventHandler = (ProgressEvent)->dynamic

internal fun postAsync(url:String, data: Map<String, String>, onerror: EventHandler? = null, onload: EventHandler) {
  requestAsync(url, "POST", data, onerror, onload)
}

internal fun getAsync(url:String, onerror: EventHandler? = null, onload: EventHandler) {
  requestAsync(url, "GET", emptyMap(), onerror, onload)
}

internal fun getASync(url:String, onLoad: (String)->Boolean) {
  requestAsync(url, "GET", emptyMap<String, String>(),
               responseType = XMLHttpRequestResponseType.TEXT,
               onerror = null,
               onload = { onLoad((it.target as XMLHttpRequest).responseText) })
}

internal fun requestAsync(url:String, method: String, data: Map<String, String>, onerror: EventHandler? = null, onload: EventHandler, responseType: XMLHttpRequestResponseType = XMLHttpRequestResponseType.TEXT) {
  val request = XMLHttpRequest().apply {
    this.responseType = responseType
    open(method, url)
    setRequestHeader("Accept", "text/plain")
    onreadystatechange = { event ->
      if (readyState==XMLHttpRequest.DONE) {
        if (status>=200 && status<400) {
          onload(event)
        } else {
          onerror?.invoke(event)
        }
      }
    }
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


@Suppress("unused")
open class LAYOUT(initialAttributes : Map<String, String>, override val consumer : TagConsumer<*>) : HTMLTag("layout", consumer, initialAttributes, null, false, false), HtmlBlockTag {
  var name : String?
    get()  = attributes.get("name")
    set(newValue) { newValue?.let { attributes["name"] = it } ?: run { attributes.remove("name") } }

}

fun <R> TagConsumer<R>.layout(name:String, block: LAYOUT.()->Unit) = LAYOUT(mapOf("name" to name), this).visit(block)

fun <E:Element> E.findChildren(name: String, clazz:String?=null, body: (E)->Unit) {
  var child: Node? = null
  while(child!=null) {
    if (child is Element) {
      (child as E).findChildren(name, clazz, body)
      if (child.localName==name && (clazz==null || child.classList.contains(clazz))) {
        body(child!!)
      }
    }

    child=child?.nextSibling
  }
}