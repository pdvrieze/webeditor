@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS")
@file:JsQualifier("shapes.fsa")
package joint.shapes.fsa

import joint.shapes.GenericAttributes
import joint.shapes.basic.CircleAttrs

external open class State(attributes: GenericAttributes<joint.shapes.basic.CircleAttrs>? = definedExternally /* null */, options: Any? = definedExternally /* null */) : joint.shapes.basic.Circle
external open class StartState(attributes: GenericAttributes<CircleAttrs>? = definedExternally /* null */, options: Any? = definedExternally /* null */) : joint.dia.Element
external open class EndState(attributes: GenericAttributes<joint.dia.SVGAttributes>? = definedExternally /* null */, options: Any? = definedExternally /* null */) : joint.dia.Element
external open class Arrow(attributes: joint.dia.LinkAttributes? = definedExternally /* null */, options: Any? = definedExternally /* null */) : joint.dia.Link
