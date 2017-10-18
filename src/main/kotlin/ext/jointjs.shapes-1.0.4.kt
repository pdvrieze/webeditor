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

@file:JsQualifier("shapes")
@file:JsModule("joint")
package joint.shapes

import joint.dia.CSSSelector
import joint.dia.CellAttributes

external interface GenericAttributes<T> : CellAttributes {
    var position: joint.dia.Point;
    var size: joint.dia.Size;
    var angle: Number?;
    var attrs: T?;
}
external interface ShapeAttrs : CSSSelector {
    var fill: String;
    var stroke: String?;
    var r: Any? //String? | number;
    var rx: Any? //String? | number;
    var ry: Any? //String? | number;
    var cx: Any? //String? | number;
    var cy: Any? //String? | number;
    var height: Any?//String? | number;
    var width: Any?//String? | number;
    var transform: String?;
    var points: String?;
//    'stroke-width': String? | number?;
//    'ref-x': String? | number?;
//    'ref-y': String? | number?;
    var ref: String?
}
