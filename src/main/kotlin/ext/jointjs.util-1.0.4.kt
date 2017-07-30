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

@file:JsQualifier("util")
package joint.util

import joint.dia.BBox
import joint.dia.Element
import joint.dia.Point
import jquery.JQuery
import org.w3c.dom.svg.SVGElement


external fun uuid(): String
external fun guid(obj: Any?): String
external fun nextFrame(callback: () -> Unit, context: Any?): Number
external fun cancelFrame(requestId: Number): Unit
external fun flattenAny(obj: Any, delim: String, stop: (node: Any) -> Boolean): Any
external fun getByPath(obj: Any, path: String, delim: String): Any
external fun setByPath(obj: Any, path: String, value: Any, delim: String): Any
external fun unsetByPath(obj: Any, path: String, delim: String): Any

external interface SvgDocumentOptions { var svgDocument: SVGElement? }

external fun breakText(text: String, size: joint.dia.Size, attrs: joint.dia.SVGAttributes?, options: SvgDocumentOptions?): String
external fun normalizeSides(box: Number): joint.dia.BBox
external fun normalizeSides(box: BBox): joint.dia.BBox
external fun getElementBBox(el: Element): joint.dia.BBox
external fun setAttributesBySelector(el: Element, attrs: joint.dia.SVGAttributes): Unit
external fun sortElements(elements: Array<Element>, comparator: (a: Element, b: Element) -> Number): Array<Element>
external fun sortElements(elements: String, comparator: (a: Element, b: Element) -> Number): Array<Element>
external fun sortElements(elements: JQuery, comparator: (a: Element, b: Element) -> Number): Array<Element>
external fun shapePerimeterConnectionPoint(linkView: joint.dia.LinkView, view: joint.dia.ElementView, magnet: SVGElement, ref: Point): Point
external fun imageToDataUri(url: String, callback: (err: Error, dataUri: String) -> Unit): Unit

// Not documented but used in examples
/** @deprecated use lodash _.defaultsDeep */
external fun deepSupplement(objects: Any, defaultIndicator: Any? = definedExternally): Any

/*

// Private functions
*/
/** @deprecated use lodash _.assign *//*

external fun mixin(objects: Array<Any>): Any
*/
/** @deprecated use lodash _.defaults *//*

external fun supplement(objects: Array<Any>): Any
*/
/** @deprecated use lodash _.mixin  *//*

external fun deepMixin(objects: Array<Any>): Any
*/
