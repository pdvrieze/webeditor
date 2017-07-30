@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS")
@file:JsQualifier("shapes.uml")
package joint.shapes.uml

import joint.shapes.GenericAttributes
import joint.shapes.ShapeAttrs

external interface ClassAttributes : GenericAttributes<joint.shapes.basic.RectAttrs> {
    var name: Array<String>
    var attributes: Array<String>
    var methods: Array<String>
}
external open class Class(attributes: ClassAttributes? = definedExternally /* null */, options: Any? = definedExternally /* null */) : joint.shapes.basic.Generic {
    open fun getClassName(): Array<String> = definedExternally
    open fun updateRectangles(): Unit = definedExternally
}
external open class ClassView : joint.dia.ElementView
external open class Abstract(attributes: ClassAttributes? = definedExternally /* null */, options: Any? = definedExternally /* null */) : Class
external open class AbstractView(attributes: ClassAttributes? = definedExternally /* null */, options: Any? = definedExternally /* null */) : ClassView
external open class Interface(attributes: ClassAttributes? = definedExternally /* null */, options: Any? = definedExternally /* null */) : Class
external open class InterfaceView(attributes: ClassAttributes? = definedExternally /* null */, options: Any? = definedExternally /* null */) : ClassView
external open class Generalization(attributes: joint.dia.LinkAttributes? = definedExternally /* null */, options: Any? = definedExternally /* null */) : joint.dia.Link
external open class Implementation(attributes: joint.dia.LinkAttributes? = definedExternally /* null */, options: Any? = definedExternally /* null */) : joint.dia.Link
external open class Aggregation(attributes: joint.dia.LinkAttributes? = definedExternally /* null */, options: Any? = definedExternally /* null */) : joint.dia.Link
external open class Composition(attributes: joint.dia.LinkAttributes? = definedExternally /* null */, options: Any? = definedExternally /* null */) : joint.dia.Link
external open class Association(attributes: joint.dia.LinkAttributes? = definedExternally /* null */, options: Any? = definedExternally /* null */) : joint.dia.Link
external interface StateAttributes : GenericAttributes<ShapeAttrs> {
    var events: Array<String>? get() = definedExternally; set(value) = definedExternally
}
external open class State(attributes: GenericAttributes<joint.shapes.basic.CircleAttrs>? = definedExternally /* null */, options: Any? = definedExternally /* null */) : joint.shapes.basic.Generic {
    open fun updateName(): Unit = definedExternally
    open fun updateEvents(): Unit = definedExternally
    open fun updatePath(): Unit = definedExternally
}
external open class StartState(attributes: GenericAttributes<joint.shapes.basic.CircleAttrs>? = definedExternally /* null */, options: Any? = definedExternally /* null */) : joint.shapes.basic.Circle
external open class EndState(attributes: GenericAttributes<joint.dia.SVGAttributes>? = definedExternally /* null */, options: Any? = definedExternally /* null */) : joint.shapes.basic.Generic
external open class Transition(attributes: joint.dia.LinkAttributes? = definedExternally /* null */, options: Any? = definedExternally /* null */) : joint.dia.Link
