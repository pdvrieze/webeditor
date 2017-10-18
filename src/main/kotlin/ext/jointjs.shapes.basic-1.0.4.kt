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
@file:JsQualifier("shapes.basic")
@file:JsModule("joint")
package joint.shapes.basic

import joint.dia.*
import joint.shapes.GenericAttributes
import joint.shapes.ShapeAttrs

external open class Generic(attributes: GenericAttributes<SVGAttributes>? = definedExternally /* null */, options: Any? = definedExternally /* null */) : Element {}
external interface RectAttrs : TextAttrs {
    var rect: ShapeAttrs? get() = definedExternally; set(value) = definedExternally
}
external open class Rect(attributes: GenericAttributes<RectAttrs>? = definedExternally /* null */, options: Any? = definedExternally /* null */) : Generic
external open class Text(attributes: GenericAttributes<TextAttrs>? = definedExternally /* null */, options: Any? = definedExternally /* null */) : Generic
external interface CircleAttrs : TextAttrs {
    var circle: ShapeAttrs? get() = definedExternally; set(value) = definedExternally
}
external open class Circle(attributes: GenericAttributes<CircleAttrs>? = definedExternally /* null */, options: Any? = definedExternally /* null */) : Generic
external interface EllipseAttrs : TextAttrs {
    var ellipse: ShapeAttrs? get() = definedExternally; set(value) = definedExternally
}
external open class Ellipse(attributes: GenericAttributes<EllipseAttrs>? = definedExternally /* null */, options: Any? = definedExternally /* null */) : Generic
external interface PolygonAttrs : TextAttrs {
    var polygon: ShapeAttrs? get() = definedExternally; set(value) = definedExternally
}
external open class Polygon(attributes: GenericAttributes<PolygonAttrs>? = definedExternally /* null */, options: Any? = definedExternally /* null */) : Generic
external interface PolylineAttrs : TextAttrs {
    var polyline: ShapeAttrs? get() = definedExternally; set(value) = definedExternally
}
external open class Polyline(attributes: GenericAttributes<PolylineAttrs>? = definedExternally /* null */, options: Any? = definedExternally /* null */) : Generic
external open class Image(attributes: GenericAttributes<TextAttrs>? = definedExternally /* null */, options: Any? = definedExternally /* null */) : Generic
external interface PathAttrs : TextAttrs {
    var path: ShapeAttrs? get() = definedExternally; set(value) = definedExternally
}
external open class Path(attributes: GenericAttributes<PathAttrs>? = definedExternally /* null */, options: Any? = definedExternally /* null */) : Generic
external interface RhombusAttrs : TextAttrs {
    var path: ShapeAttrs? get() = definedExternally; set(value) = definedExternally
}
external open class Rhombus(attributes: GenericAttributes<RhombusAttrs>? = definedExternally /* null */, options: Any? = definedExternally /* null */) : Generic
external interface TextBlockAttrs : TextAttrs {
    var rect: ShapeAttrs? get() = definedExternally; set(value) = definedExternally
}
external open class TextBlock(attributes: GenericAttributes<TextBlockAttrs>? = definedExternally /* null */, options: Any? = definedExternally /* null */) : Generic {
    open fun updateSize(cell: Cell, size: Size): Unit = definedExternally
    open fun updateContent(cell: Cell, content: String): Unit = definedExternally
}
