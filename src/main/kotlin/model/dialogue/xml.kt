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

package model.dialogue

import jquery.JQuery

/**
 * Kotlin wrapper for the xml class
 */
typealias ModelType = Any

external interface XmlStatic {
    operator fun invoke(model:ModelType): Xml
}

external interface Xml {
    var model: ModelType
    @JsName("\$target")
    var _target: JQuery
    @JsName("\$body")
    var _body: JQuery
}

@JsModule("model/dialogue/xml")
@JsNonModule
external val xml:XmlStatic = definedExternally