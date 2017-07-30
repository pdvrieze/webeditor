@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS")
@file:JsQualifier("shapes.pn")
package joint.shapes.pn

import joint.dia.SVGAttributes
import joint.shapes.GenericAttributes

external open class Place(attributes: GenericAttributes<SVGAttributes>? = definedExternally /* null */, options: Any? = definedExternally /* null */) : joint.shapes.basic.Generic
external open class PlaceView : joint.dia.ElementView {
    open fun renderTokens(): Unit = definedExternally
}
external open class Transition(attributes: GenericAttributes<joint.shapes.basic.RectAttrs>? = definedExternally /* null */, options: Any? = definedExternally /* null */) : joint.shapes.basic.Generic
external open class Link(attributes: joint.dia.LinkAttributes? = definedExternally /* null */, options: Any? = definedExternally /* null */) : joint.dia.Link
