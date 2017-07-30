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

@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS")
@file:JsQualifier("shapes.devs")
package joint.shapes.devs

import joint.dia.SVGAttributes
import joint.shapes.GenericAttributes

external interface ModelAttributes : GenericAttributes<SVGAttributes> {
    var inPorts: Array<String>? get() = definedExternally; set(value) = definedExternally
    var outPorts: Array<String>? get() = definedExternally; set(value) = definedExternally
    var ports: Any? get() = definedExternally; set(value) = definedExternally
}
external open class Model(attributes: ModelAttributes? = definedExternally /* null */, options: Any? = definedExternally /* null */) : joint.shapes.basic.Generic {
    open fun changeInGroup(properties: Any, opt: Any? = definedExternally /* null */): Boolean = definedExternally
    open fun changeOutGroup(properties: Any, opt: Any? = definedExternally /* null */): Boolean = definedExternally
    open fun createPortItem(group: String, port: String): Any = definedExternally
    open fun createPortItems(group: String, ports: Array<String>): Array<Any> = definedExternally
    open fun addOutPort(port: String, opt: Any? = definedExternally /* null */): Model /* this */ = definedExternally
    open fun addInPort(port: String, opt: Any? = definedExternally /* null */): Model /* this */ = definedExternally
    open fun removeOutPort(port: String, opt: Any? = definedExternally /* null */): Model /* this */ = definedExternally
    open fun removeInPort(port: String, opt: Any? = definedExternally /* null */): Model /* this */ = definedExternally
}
external open class Coupled(attributes: ModelAttributes? = definedExternally /* null */, options: Any? = definedExternally /* null */) : Model
external open class Atomic(attributes: ModelAttributes? = definedExternally /* null */, options: Any? = definedExternally /* null */) : Model
external open class Link(attributes: joint.dia.LinkAttributes? = definedExternally /* null */, options: Any? = definedExternally /* null */) : joint.dia.Link
