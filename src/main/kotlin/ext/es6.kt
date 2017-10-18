package es6

import org.w3c.dom.Element
import org.w3c.dom.events.EventTarget
import kotlin.js.*

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

@JsName("Function")
external interface JSFunction {
    /**
     * Calls the function, substituting the specified object for the this value of the function, and the specified array for the arguments of the function.
     * @param thisArg The object to be used as the this object.
     * @param argArray A set of arguments to be passed to the function.
     */
    fun apply(thisArg: dynamic, vararg argArray: dynamic): dynamic

    /**
     * Calls a method of an object, substituting another object for the current object.
     * @param thisArg The object to be used as the current object.
     * @param argArray A list of arguments to be passed to the method.
     */
    fun call(thisArg: dynamic, vararg argArray: dynamic): dynamic

    /**
     * For a given function, creates a bound function that has the same body as the original function.
     * The this object of the bound function is associated with the specified object, and has the specified initial parameters.
     * @param thisArg An object to which the this keyword can refer inside the new function.
     * @param argArray A list of arguments to be passed to the new function.
     */
    fun bind(thisArg: dynamic, vararg argArray: dynamic): dynamic

    var prototype: dynamic
    val length: Int
    val arguments: dynamic
    val caller: JSFunction
}

fun json(input:Map<String, Any>):Json {
    return json().apply {
        for ((key, value) in input.entries) {
            this[key]=value
        }
    }
}

typealias JsEvent=Event
external interface Event {
    fun stopImmediatePropagation()
    fun stopPropagation()
    fun preventDefault()

    fun initEvent(eventTypeArg: String, canBubbleArg: Boolean, cancelableArg: Boolean)

    var target: Element
    var currentTarget: Element
}