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

package view

import kotlinx.html.*
import kotlinx.html.dom.append
import util.layout
import kotlin.browser.document

fun <R> TagConsumer<R>.about()
{
  layout("container") {
    section {
      attributes["name"] = "content"

      h1 { +"About" }
      p { b { +"The MIT License (MIT)" } }
      p { +"Copyright (c) 2016 Aleksandr Belkin" }
      p {
        +"""Permission is hereby granted, free of charge, to any person
            obtaining a copy of this software and associated documentation files
            (the "Software"), to deal in the Software without restriction,
            including without limitation the rights to use, copy, modify,
            merge, publish, distribute, sublicense, and/or sell copies of
            the Software, and to permit persons to whom the Software is
            furnished to do so, subject to the following conditions:""".trimIndent()
      }
      p {
        +"""The above copyright notice and this permission notice shall be
            included in all copies or substantial portions of the Software.""".trimIndent()
      }
      p {
        +"""THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY
            KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
            WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
            NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
            HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
            WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
            ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR
            THE USE OR OTHER DEALINGS IN THE SOFTWARE.""".trimIndent()
      }
    }
  }
}

fun bar(x:Int) {
  document.append { this.about() }
}
