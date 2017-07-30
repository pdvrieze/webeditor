@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS")
@file:JsQualifier("shapes.erd")
package joint.shapes.erd

import joint.dia.TextAttrs
import joint.shapes.GenericAttributes
import joint.shapes.ShapeAttrs

external open class Entity(attributes: GenericAttributes<TextAttrs>? = definedExternally /* null */, options: Any? = definedExternally /* null */) : joint.shapes.basic.Generic
external open class WeakEntity(attributes: GenericAttributes<joint.dia.TextAttrs>? = definedExternally /* null */, options: Any? = definedExternally /* null */) : Entity
external open class Relationship(attributes: GenericAttributes<joint.dia.TextAttrs>? = definedExternally /* null */, options: Any? = definedExternally /* null */) : joint.dia.Element
external open class IdentifyingRelationship(attributes: GenericAttributes<joint.dia.TextAttrs>? = definedExternally /* null */, options: Any? = definedExternally /* null */) : Relationship
external interface AttributeAttrs : joint.dia.TextAttrs {
    var ellipse: ShapeAttrs? get() = definedExternally; set(value) = definedExternally
}
external open class Attribute(attributes: GenericAttributes<AttributeAttrs>? = definedExternally /* null */, options: Any? = definedExternally /* null */) : joint.dia.Element
external open class Multivalued(attributes: GenericAttributes<AttributeAttrs>? = definedExternally /* null */, options: Any? = definedExternally /* null */) : Attribute
external open class Derived(attributes: GenericAttributes<AttributeAttrs>? = definedExternally /* null */, options: Any? = definedExternally /* null */) : Attribute
external open class Key(attributes: GenericAttributes<AttributeAttrs>? = definedExternally /* null */, options: Any? = definedExternally /* null */) : Attribute
external open class Normal(attributes: GenericAttributes<AttributeAttrs>? = definedExternally /* null */, options: Any? = definedExternally /* null */) : Attribute
external interface ISAAttrs : joint.dia.Element {
    var polygon: ShapeAttrs? get() = definedExternally; set(value) = definedExternally
}
external open class ISA(attributes: GenericAttributes<ISAAttrs>? = definedExternally /* null */, options: Any? = definedExternally /* null */) : joint.dia.Element
external open class Line(attributes: joint.dia.LinkAttributes? = definedExternally /* null */, options: Any? = definedExternally /* null */) : joint.dia.Link {
    open fun cardinality(value: String): Unit = definedExternally
    open fun cardinality(value: Number): Unit = definedExternally
}
