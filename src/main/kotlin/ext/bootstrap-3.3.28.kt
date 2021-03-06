@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS")

import jquery.JQuery

external interface ModalOptions {
    var backdrop: dynamic /* Boolean | String */ get() = definedExternally; set(value) = definedExternally
    var keyboard: Boolean? get() = definedExternally; set(value) = definedExternally
    var show: Boolean? get() = definedExternally; set(value) = definedExternally
    var remote: String? get() = definedExternally; set(value) = definedExternally
}
external interface ModalOptionsBackdropString {
    var backdrop: String? get() = definedExternally; set(value) = definedExternally
    var keyboard: Boolean? get() = definedExternally; set(value) = definedExternally
    var show: Boolean? get() = definedExternally; set(value) = definedExternally
    var remote: String? get() = definedExternally; set(value) = definedExternally
}
external interface ScrollSpyOptions {
    var offset: Number? get() = definedExternally; set(value) = definedExternally
    var target: String? get() = definedExternally; set(value) = definedExternally
}
external interface TooltipOptions {
    var animation: Boolean? get() = definedExternally; set(value) = definedExternally
    var html: Boolean? get() = definedExternally; set(value) = definedExternally
    var placement: dynamic /* String | Function<*> */ get() = definedExternally; set(value) = definedExternally
    var selector: String? get() = definedExternally; set(value) = definedExternally
    var title: dynamic /* String | Function<*> */ get() = definedExternally; set(value) = definedExternally
    var trigger: String? get() = definedExternally; set(value) = definedExternally
    var template: String? get() = definedExternally; set(value) = definedExternally
    var delay: dynamic /* Number | Any */ get() = definedExternally; set(value) = definedExternally
    var container: dynamic /* String | Boolean */ get() = definedExternally; set(value) = definedExternally
    var viewport: dynamic /* String | Function<*> | Any */ get() = definedExternally; set(value) = definedExternally
}
external interface PopoverOptions {
    var animation: Boolean? get() = definedExternally; set(value) = definedExternally
    var html: Boolean? get() = definedExternally; set(value) = definedExternally
    var placement: dynamic /* String | Function<*> */ get() = definedExternally; set(value) = definedExternally
    var selector: String? get() = definedExternally; set(value) = definedExternally
    var trigger: String? get() = definedExternally; set(value) = definedExternally
    var title: dynamic /* String | Function<*> */ get() = definedExternally; set(value) = definedExternally
    var template: String? get() = definedExternally; set(value) = definedExternally
    var content: Any? get() = definedExternally; set(value) = definedExternally
    var delay: dynamic /* Number | Any */ get() = definedExternally; set(value) = definedExternally
    var container: dynamic /* String | Boolean */ get() = definedExternally; set(value) = definedExternally
    var viewport: dynamic /* String | Function<*> | Any */ get() = definedExternally; set(value) = definedExternally
}
external interface CollapseOptions {
    var parent: Any? get() = definedExternally; set(value) = definedExternally
    var toggle: Boolean? get() = definedExternally; set(value) = definedExternally
}
external interface CarouselOptions {
    var interval: Number? get() = definedExternally; set(value) = definedExternally
    var pause: String? get() = definedExternally; set(value) = definedExternally
    var wrap: Boolean? get() = definedExternally; set(value) = definedExternally
    var keybord: Boolean? get() = definedExternally; set(value) = definedExternally
}
external interface TypeaheadOptions {
    var source: Any? get() = definedExternally; set(value) = definedExternally
    var items: Number? get() = definedExternally; set(value) = definedExternally
    var minLength: Number? get() = definedExternally; set(value) = definedExternally
    var matcher: ((item: Any) -> Boolean)? get() = definedExternally; set(value) = definedExternally
    var sorter: ((items: Array<Any>) -> Array<Any>)? get() = definedExternally; set(value) = definedExternally
    var updater: ((item: Any) -> Any)? get() = definedExternally; set(value) = definedExternally
    var highlighter: ((item: Any) -> String)? get() = definedExternally; set(value) = definedExternally
}
external interface AffixOptions {
    var offset: dynamic /* Number | Function<*> | Any */ get() = definedExternally; set(value) = definedExternally
    var target: Any? get() = definedExternally; set(value) = definedExternally
}
external interface BootstrapJQuery: JQuery {
    fun modal(options: ModalOptions? = definedExternally /* null */): JQuery
    fun modal(options: ModalOptionsBackdropString? = definedExternally /* null */): JQuery
    fun modal(command: String): JQuery
    fun dropdown(): JQuery
    fun dropdown(command: String): JQuery
    fun scrollspy(command: String): JQuery
    fun scrollspy(options: ScrollSpyOptions? = definedExternally /* null */): JQuery
    fun tab(): JQuery
    fun tab(command: String): JQuery
    fun tooltip(options: TooltipOptions? = definedExternally /* null */): JQuery
    fun tooltip(command: String): JQuery
    fun popover(options: PopoverOptions? = definedExternally /* null */): JQuery
    fun popover(command: String): JQuery
    fun alert(): JQuery
    fun alert(command: String): JQuery
    fun button(): JQuery
    fun button(command: String): JQuery
    fun collapse(options: CollapseOptions? = definedExternally /* null */): JQuery
    fun collapse(command: String): JQuery
    fun carousel(options: CarouselOptions? = definedExternally /* null */): JQuery
    fun carousel(command: String): JQuery
    fun typeahead(options: TypeaheadOptions? = definedExternally /* null */): JQuery
    fun affix(options: AffixOptions? = definedExternally /* null */): JQuery
}

@Suppress("UNCHECKED_CAST_TO_NATIVE_INTERFACE")
val JQuery.bootstrap inline get() = this as BootstrapJQuery