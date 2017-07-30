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

package joint

import joint.dia.DfsBfsOptions
import joint.dia.ElementViewAttributes
import joint.dia.InOutBoundOptions
import joint.shapes.ShapeAttrs

/**
 * Created by pdvrieze on 11/07/17.
 */

fun inOutBoundOptions(inbound:Boolean?, outbound:Boolean?): InOutBoundOptions {
    val x = js("{}")
    inbound?.let { x.inbound = it }
    outbound?.let { x.outbound = it }
    return x
}

fun DfsBfsOptions(inbound: Boolean? = null, outbound: Boolean? = null, deep: Boolean? = null): DfsBfsOptions {
    val x = js("{}")
    inbound?.let { x.inbound = it }
    outbound?.let { x.outbound = it }
    deep?.let { x.deep = it }
    return x
}

@JsName("Padding")
class Padding(@JsName("top") var top: Number? = null,
              @JsName("right") var right: Number? = null,
              @JsName("bottom") var bottom: Number? = null,
              @JsName("left") var left: Number? = null) {

    constructor(value: Number): this (value, value, value, value)
}

inline var ElementViewAttributes.refX
    get() = get("ref-x") as Any?
    set(value) {set("ref-x", value)}

inline var ElementViewAttributes.refY
    get() = get("ref-y") as Any?
    set(value) {set("ref-y", value)}

inline var ElementViewAttributes.refDX:Number?
    get() = get("ref-dx") as Number?
    set(value) {set("ref-dx", value)}

inline var ElementViewAttributes.refDY:Number?
    get() = get("ref-dy") as Number?
    set(value) {set("ref-dy", value)}

inline var ElementViewAttributes.refWidth
    get() = get("ref-width") as Any?
    set(value) {set("ref-width", value)}

inline var ElementViewAttributes.refHeight
    get() = get("ref-height") as Any?
    set(value) {set("ref-height", value)}

inline var ShapeAttrs.refX
    get() = get("ref-x") as Any?
    set(value) {set("ref-x", value)}

inline var ShapeAttrs.refY
    get() = get("ref-y") as Any?
    set(value) {set("ref-y", value)}

inline var ShapeAttrs.strokeWidth
    get() = get("stroke-width") as Any?
    set(value) {set("stroke-width", value)}

/*
    "ref-y": String? | Number?
    "ref-dx": Number?
    "ref-dy": Number?
    "ref-width": String? | Number?
    "ref-height": String? | Number?

 */