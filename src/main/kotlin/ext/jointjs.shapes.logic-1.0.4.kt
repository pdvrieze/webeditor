@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS")
@file:JsQualifier("shapes.logic")
package joint.shapes.logic

import joint.shapes.GenericAttributes
import joint.shapes.ShapeAttrs

external interface LogicAttrs : ShapeAttrs {
    override var ref: String? get() = definedExternally; set(value) = definedExternally
    var magnet: Boolean? get() = definedExternally; set(value) = definedExternally
    var port: String? get() = definedExternally; set(value) = definedExternally
}
external interface IOAttrs : joint.dia.TextAttrs {
    var circle: LogicAttrs? get() = definedExternally; set(value) = definedExternally
}
external open class Gate(attributes: GenericAttributes<IOAttrs>? = definedExternally /* null */, options: Any? = definedExternally /* null */) : joint.shapes.basic.Generic
external open class IO(attributes: GenericAttributes<IOAttrs>? = definedExternally /* null */, options: Any? = definedExternally /* null */) : Gate
external open class Input(attributes: GenericAttributes<IOAttrs>? = definedExternally /* null */, options: Any? = definedExternally /* null */) : IO
external open class Output(attributes: GenericAttributes<IOAttrs>? = definedExternally /* null */, options: Any? = definedExternally /* null */) : IO
external open class Gate11(attributes: GenericAttributes<IOAttrs>? = definedExternally /* null */, options: Any? = definedExternally /* null */) : Gate
external open class Gate21(attributes: GenericAttributes<IOAttrs>? = definedExternally /* null */, options: Any? = definedExternally /* null */) : Gate
external interface Image
external interface ImageAttrs : LogicAttrs {
    var image: Image? get() = definedExternally; set(value) = definedExternally
}
external open class Repeater(attributes: GenericAttributes<ImageAttrs>? = definedExternally /* null */, options: Any? = definedExternally /* null */) : Gate11 {
    open fun operation(input: Any): Any = definedExternally
}
external open class Note(attributes: GenericAttributes<ImageAttrs>? = definedExternally /* null */, options: Any? = definedExternally /* null */) : Gate11 {
    open fun operation(input: Any): Boolean = definedExternally
}
external open class Or(attributes: GenericAttributes<ImageAttrs>? = definedExternally /* null */, options: Any? = definedExternally /* null */) : Gate21 {
    open fun operation(input1: Any, input2: Any): Boolean = definedExternally
}
external open class And(attributes: GenericAttributes<ImageAttrs>? = definedExternally /* null */, options: Any? = definedExternally /* null */) : Gate21 {
    open fun operation(input1: Any, input2: Any): Boolean = definedExternally
}
external open class Nor(attributes: GenericAttributes<ImageAttrs>? = definedExternally /* null */, options: Any? = definedExternally /* null */) : Gate21 {
    open fun operation(input1: Any, input2: Any): Boolean = definedExternally
}
external open class Nand(attributes: GenericAttributes<ImageAttrs>? = definedExternally /* null */, options: Any? = definedExternally /* null */) : Gate21 {
    open fun operation(input1: Any, input2: Any): Boolean = definedExternally
}
external open class Xor(attributes: GenericAttributes<ImageAttrs>? = definedExternally /* null */, options: Any? = definedExternally /* null */) : Gate21 {
    open fun operation(input1: Any, input2: Any): Boolean = definedExternally
}
external open class Xnor(attributes: GenericAttributes<ImageAttrs>? = definedExternally /* null */, options: Any? = definedExternally /* null */) : Gate21 {
    open fun operation(input1: Any, input2: Any): Boolean = definedExternally
}
external interface WireArgs : joint.dia.LinkAttributes {
    var router: Any? get() = definedExternally; set(value) = definedExternally
    var connector: Any? get() = definedExternally; set(value) = definedExternally
}
external open class Wire(attributes: WireArgs? = definedExternally /* null */, options: Any? = definedExternally /* null */) : joint.dia.Link
