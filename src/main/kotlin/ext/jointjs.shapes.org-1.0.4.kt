@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS")
@file:JsQualifier("shapes.org")
package joint.shapes.org

import joint.shapes.GenericAttributes
import joint.shapes.ShapeAttrs

external interface MemberAttrs {
    var rect: ShapeAttrs? get() = definedExternally; set(value) = definedExternally
    var image: ShapeAttrs? get() = definedExternally; set(value) = definedExternally
}
external open class Member(attributes: GenericAttributes<MemberAttrs>? = definedExternally /* null */, options: Any? = definedExternally /* null */) : joint.dia.Element
external open class Arrow(attributes: joint.dia.LinkAttributes? = definedExternally /* null */, options: Any? = definedExternally /* null */) : joint.dia.Link
