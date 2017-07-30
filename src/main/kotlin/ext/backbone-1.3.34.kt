@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS")
@file:[JsQualifier("Backbone") JsModule("Backbone")]

package Backbone

import jquery.*
import org.w3c.dom.HTMLElement
import org.w3c.dom.Location
import org.w3c.dom.Window
import kotlin.js.*

external interface AddOptions : Silenceable {
    var at: Number? get() = definedExternally; set(value) = definedExternally
    var merge: Boolean? get() = definedExternally; set(value) = definedExternally
}
external interface HistoryOptions : Silenceable {
    var pushState: Boolean? get() = definedExternally; set(value) = definedExternally
    var root: String? get() = definedExternally; set(value) = definedExternally
}
external interface NavigateOptions {
    var trigger: Boolean? get() = definedExternally; set(value) = definedExternally
    var replace: Boolean? get() = definedExternally; set(value) = definedExternally
}
external interface RouterOptions {
    var routes: Any
}
external interface Silenceable {
    var silent: Boolean? get() = definedExternally; set(value) = definedExternally
}
external interface Validable {
    var validate: Boolean? get() = definedExternally; set(value) = definedExternally
}
external interface Waitable {
    var wait: Boolean? get() = definedExternally; set(value) = definedExternally
}
external interface Parseable {
    var parse: Any? get() = definedExternally; set(value) = definedExternally
}
external interface PersistenceOptions {
    var url: String? get() = definedExternally; set(value) = definedExternally
    var data: Any? get() = definedExternally; set(value) = definedExternally
    var beforeSend: ((jqxhr: JQueryXHR) -> Unit)? get() = definedExternally; set(value) = definedExternally
    var success: ((modelOrCollection: Any? /*= null*/, response: Any? /*= null*/, options: Any? /*= null*/) -> Unit)? get() = definedExternally; set(value) = definedExternally
    var error: ((modelOrCollection: Any? /*= null*/, jqxhr: JQueryXHR? /*= null*/, options: Any? /*= null*/) -> Unit)? get() = definedExternally; set(value) = definedExternally
}
external interface ModelSetOptions : Silenceable, Validable
external interface ModelFetchOptions : PersistenceOptions, ModelSetOptions, Parseable
external interface ModelSaveOptions : Silenceable, Waitable, Validable, Parseable, PersistenceOptions {
    var patch: Boolean? get() = definedExternally; set(value) = definedExternally
}
external interface ModelDestroyOptions : Waitable, PersistenceOptions
external interface CollectionFetchOptions : PersistenceOptions, Parseable {
    var reset: Boolean? get() = definedExternally; set(value) = definedExternally
}
external interface ObjectHash {
    @nativeGetter
    operator fun get(key: String): Any?
    @nativeSetter
    operator fun set(key: String, value: Any)
}
external interface `T$0` {
    @nativeInvoke
    operator fun invoke(vararg urlParts: String)
}
external interface RoutesHash {
    @nativeGetter
    operator fun get(routePattern: String): dynamic /* String | `T$0` */
    @nativeSetter
    operator fun set(routePattern: String, value: String)
    @nativeSetter
    operator fun set(routePattern: String, value: `T$0`)
}
external interface `T$1` {
    @nativeInvoke
    operator fun invoke(eventObject: JQueryEventObject)
}
external interface EventsHash {
    @nativeGetter
    operator fun get(selector: String): dynamic /* String | `T$1` */
    @nativeSetter
    operator fun set(selector: String, value: String)
    @nativeSetter
    operator fun set(selector: String, value: `T$1`)
}
external open class Events {
    open fun on(eventName: String, callback: Function<*>? = definedExternally /* null */, context: Any? = definedExternally /* null */): Any = definedExternally
    fun on(eventMap: EventsHash): Any = definedExternally
    open fun off(eventName: String? = definedExternally /* null */, callback: Function<*>? = definedExternally /* null */, context: Any? = definedExternally /* null */): Any = definedExternally
    open fun trigger(eventName: String, vararg args: Any): Any = definedExternally
    open fun bind(eventName: String, callback: Function<*>, context: Any? = definedExternally /* null */): Any = definedExternally
    open fun unbind(eventName: String? = definedExternally /* null */, callback: Function<*>? = definedExternally /* null */, context: Any? = definedExternally /* null */): Any = definedExternally
    open fun once(events: String, callback: Function<*>, context: Any? = definedExternally /* null */): Any = definedExternally
    open fun listenTo(`object`: Any, events: String, callback: Function<*>): Any = definedExternally
    open fun listenToOnce(`object`: Any, events: String, callback: Function<*>): Any = definedExternally
    open fun stopListening(`object`: Any? = definedExternally /* null */, events: String? = definedExternally /* null */, callback: Function<*>? = definedExternally /* null */): Any = definedExternally
}
external open class ModelBase : Events {
    open var url: Any = definedExternally
    open fun parse(response: Any, options: Any? = definedExternally /* null */): Any = definedExternally
    open fun toJSON(options: Any? = definedExternally /* null */): Any = definedExternally
    open fun sync(vararg arg: Any): JQueryXHR = definedExternally
}
external open class Model(attributes: Any? = definedExternally /* null */, options: Any? = definedExternally /* null */) : ModelBase {
    open var attributes: Any = definedExternally
    open var changed: Array<Any> = definedExternally
    open var cidPrefix: String = definedExternally
    open var cid: String = definedExternally
    open var collection: Collection<Model> = definedExternally
    open var _changing: Boolean = definedExternally
    open var _previousAttributes: Any = definedExternally
    open var _pending: Boolean = definedExternally
    open fun defaults(): ObjectHash = definedExternally
    open var id: Any = definedExternally
    open var idAttribute: String = definedExternally
    open var validationError: Any = definedExternally
    open var urlRoot: Any = definedExternally
    open fun initialize(attributes: Any? = definedExternally /* null */, options: Any? = definedExternally /* null */): Unit = definedExternally
    open fun fetch(options: ModelFetchOptions? = definedExternally /* null */): JQueryXHR = definedExternally
    open fun get(attributeName: String): Any = definedExternally
    open fun set(attributeName: String, value: Any, options: ModelSetOptions? = definedExternally /* null */): Model = definedExternally
    fun set(obj: Any, options: ModelSetOptions? = definedExternally /* null */): Model = definedExternally
    open fun changedAttributes(attributes: Any? = definedExternally /* null */): Any = definedExternally
    open fun clear(options: Silenceable? = definedExternally /* null */): Any = definedExternally
    open fun clone(): Model = definedExternally
    open fun destroy(options: ModelDestroyOptions? = definedExternally /* null */): Any = definedExternally
    open fun escape(attribute: String): String = definedExternally
    open fun has(attribute: String): Boolean = definedExternally
    open fun hasChanged(attribute: String? = definedExternally /* null */): Boolean = definedExternally
    open fun isNew(): Boolean = definedExternally
    open fun isValid(options: Any? = definedExternally /* null */): Boolean = definedExternally
    open fun previous(attribute: String): Any = definedExternally
    open fun previousAttributes(): Array<Any> = definedExternally
    open fun save(attributes: Any? = definedExternally /* null */, options: ModelSaveOptions? = definedExternally /* null */): Any = definedExternally
    open fun unset(attribute: String, options: Silenceable? = definedExternally /* null */): Model = definedExternally
    open fun validate(attributes: Any, options: Any? = definedExternally /* null */): Any = definedExternally
    open fun _validate(attributes: Any, options: Any): Boolean = definedExternally
    open fun keys(): Array<String> = definedExternally
    open fun values(): Array<Any> = definedExternally
    open fun pairs(): Array<Any> = definedExternally
    open fun invert(): Any = definedExternally
    open fun pick(vararg keys: String): Any = definedExternally
    fun pick(fn: (value: Any, key: Any, obj: Any) -> Any): Any = definedExternally
    open fun omit(vararg keys: String): Any = definedExternally
    fun omit(fn: (value: Any, key: Any, obj: Any) -> Any): Any = definedExternally
    open fun chain(): Any = definedExternally
    open fun isEmpty(): Boolean = definedExternally
    open fun matches(attrs: Any): Boolean = definedExternally
    companion object {
        fun extend(properties: Any, classProperties: Any? = definedExternally /* null */): Any = definedExternally
    }
}
external open class Collection<TModel : Model> : ModelBase {
    constructor(models: Array<TModel>? = definedExternally /* null */, options: Any? = definedExternally /* null */)
    constructor(models: Array<Any>? = definedExternally /* null */, options: Any? = definedExternally /* null */)
    open var model: (args: Any) -> TModel = definedExternally
    open var models: Array<TModel> = definedExternally
    open var length: Number = definedExternally
    open fun initialize(models: Array<TModel>? = definedExternally /* null */, options: Any? = definedExternally /* null */): Unit = definedExternally
    open fun initialize(models: Array<Any>? = definedExternally /* null */, options: Any? = definedExternally /* null */): Unit = definedExternally
    open fun fetch(options: CollectionFetchOptions? = definedExternally /* null */): JQueryXHR = definedExternally
    open var comparator: dynamic /* String | (element: TModel) -> dynamic /* Number | String */ | (compare: TModel, to: TModel? /*= null*/) -> Number */ = definedExternally
    open fun add(model: Any, options: AddOptions? = definedExternally /* null */): TModel = definedExternally
    open fun add(model: TModel, options: AddOptions? = definedExternally /* null */): TModel = definedExternally
    open fun add(models: Array<dynamic /* Any | TModel */>, options: AddOptions? = definedExternally /* null */): Array<TModel> = definedExternally
    open fun at(index: Number): TModel = definedExternally
    open fun get(id: Number): TModel = definedExternally
    open fun get(id: String): TModel = definedExternally
    open fun get(id: Model): TModel = definedExternally
    open fun has(key: Number): Boolean = definedExternally
    open fun has(key: String): Boolean = definedExternally
    open fun has(key: Model): Boolean = definedExternally
    open fun create(attributes: Any, options: ModelSaveOptions? = definedExternally /* null */): TModel = definedExternally
    open fun pluck(attribute: String): Array<Any> = definedExternally
    open fun push(model: TModel, options: AddOptions? = definedExternally /* null */): TModel = definedExternally
    open fun pop(options: Silenceable? = definedExternally /* null */): TModel = definedExternally
    open fun remove(model: Any, options: Silenceable? = definedExternally /* null */): TModel = definedExternally
    open fun remove(model: TModel, options: Silenceable? = definedExternally /* null */): TModel = definedExternally
    open fun remove(models: Array<dynamic /* Any | TModel */>, options: Silenceable? = definedExternally /* null */): Array<TModel> = definedExternally
    open fun reset(models: Array<TModel>? = definedExternally /* null */, options: Silenceable? = definedExternally /* null */): Array<TModel> = definedExternally
    open fun set(models: Array<TModel>? = definedExternally /* null */, options: Silenceable? = definedExternally /* null */): Array<TModel> = definedExternally
    open fun shift(options: Silenceable? = definedExternally /* null */): TModel = definedExternally
    open fun sort(options: Silenceable? = definedExternally /* null */): Collection<TModel> = definedExternally
    open fun unshift(model: TModel, options: AddOptions? = definedExternally /* null */): TModel = definedExternally
    open fun where(properties: Any): Array<TModel> = definedExternally
    open fun findWhere(properties: Any): TModel = definedExternally
    open fun modelId(attrs: Any): Any = definedExternally
    open fun _prepareModel(attributes: Any? = definedExternally /* null */, options: Any? = definedExternally /* null */): Any = definedExternally
    open fun _removeReference(model: TModel): Unit = definedExternally
    open fun _onModelEvent(event: String, model: TModel, collection: Collection<TModel>, options: Any): Unit = definedExternally
    open fun _isModel(obj: Any): Boolean = definedExternally
    open fun slice(min: Number, max: Number? = definedExternally /* null */): Array<TModel> = definedExternally
    open fun all(iterator: lodash.ListIterator<TModel, Boolean>? = definedExternally /* null */, context: Any? = definedExternally /* null */): Boolean = definedExternally
    open fun any(iterator: lodash.ListIterator<TModel, Boolean>? = definedExternally /* null */, context: Any? = definedExternally /* null */): Boolean = definedExternally
    open fun chain(): Any = definedExternally
    open fun <TResult> collect(iterator: lodash.ListIterator<TModel, TResult>, context: Any? = definedExternally /* null */): Array<TResult> = definedExternally
    open fun contains(value: TModel): Boolean = definedExternally
    open fun countBy(iterator: lodash.ListIterator<TModel, Any>? = definedExternally /* null */): lodash.Dictionary<Number> = definedExternally
    open fun countBy(iterator: String): lodash.Dictionary<Number> = definedExternally
    open fun detect(iterator: lodash.ListIterator<TModel, Boolean>, context: Any? = definedExternally /* null */): TModel = definedExternally
    open fun difference(others: Array<TModel>): Array<TModel> = definedExternally
    open fun drop(n: Number? = definedExternally /* null */): Array<TModel> = definedExternally
    open fun each(iterator: lodash.ListIterator<TModel, Unit>, context: Any? = definedExternally /* null */): Array<TModel> = definedExternally
    open fun every(iterator: lodash.ListIterator<TModel, Boolean>, context: Any? = definedExternally /* null */): Boolean = definedExternally
    open fun filter(iterator: lodash.ListIterator<TModel, Boolean>, context: Any? = definedExternally /* null */): Array<TModel> = definedExternally
    open fun find(iterator: lodash.ListIterator<TModel, Boolean>, context: Any? = definedExternally /* null */): TModel = definedExternally
    open fun findIndex(predicate: lodash.ListIterator<TModel, Boolean>, context: Any? = definedExternally /* null */): Number = definedExternally
    open fun findLastIndex(predicate: lodash.ListIterator<TModel, Boolean>, context: Any? = definedExternally /* null */): Number = definedExternally
    open fun first(): TModel = definedExternally
    open fun first(n: Number): Array<TModel> = definedExternally
    open fun <TResult> foldl(iterator: lodash.MemoIterator<TModel, TResult>, memo: TResult? = definedExternally /* null */, context: Any? = definedExternally /* null */): TResult = definedExternally
    open fun <TResult> foldr(iterator: lodash.MemoIterator<TModel, TResult>, memo: TResult? = definedExternally /* null */, context: Any? = definedExternally /* null */): TResult = definedExternally
    open fun forEach(iterator: lodash.ListIterator<TModel, Unit>, context: Any? = definedExternally /* null */): Array<TModel> = definedExternally
    open fun groupBy(iterator: lodash.ListIterator<TModel, Any>, context: Any? = definedExternally /* null */): lodash.Dictionary<Array<TModel>> = definedExternally
    open fun groupBy(iterator: String, context: Any? = definedExternally /* null */): lodash.Dictionary<Array<TModel>> = definedExternally
    open fun head(): TModel = definedExternally
    open fun head(n: Number): Array<TModel> = definedExternally
    open fun include(value: TModel): Boolean = definedExternally
    open fun includes(value: TModel): Boolean = definedExternally
    open fun indexBy(iterator: lodash.ListIterator<TModel, Any>, context: Any? = definedExternally /* null */): lodash.Dictionary<TModel> = definedExternally
    open fun indexBy(iterator: String, context: Any? = definedExternally /* null */): lodash.Dictionary<TModel> = definedExternally
    open fun indexOf(value: TModel, isSorted: Boolean? = definedExternally /* null */): Number = definedExternally
    open fun initial(): TModel = definedExternally
    open fun initial(n: Number): Array<TModel> = definedExternally
    open fun <TResult> inject(iterator: lodash.MemoIterator<TModel, TResult>, memo: TResult? = definedExternally /* null */, context: Any? = definedExternally /* null */): TResult = definedExternally
    open fun invoke(methodName: String, vararg args: Any): Any = definedExternally
    open fun isEmpty(): Boolean = definedExternally
    open fun last(): TModel = definedExternally
    open fun last(n: Number): Array<TModel> = definedExternally
    open fun lastIndexOf(value: TModel, from: Number? = definedExternally /* null */): Number = definedExternally
    open fun <TResult> map(iterator: lodash.ListIterator<TModel, TResult>, context: Any? = definedExternally /* null */): Array<TResult> = definedExternally
    open fun max(iterator: lodash.ListIterator<TModel, Any>? = definedExternally /* null */, context: Any? = definedExternally /* null */): TModel = definedExternally
    open fun min(iterator: lodash.ListIterator<TModel, Any>? = definedExternally /* null */, context: Any? = definedExternally /* null */): TModel = definedExternally
    open fun partition(iterator: lodash.ListIterator<TModel, Boolean>): Array<Array<TModel>> = definedExternally
    open fun <TResult> reduce(iterator: lodash.MemoIterator<TModel, TResult>, memo: TResult? = definedExternally /* null */, context: Any? = definedExternally /* null */): TResult = definedExternally
    open fun <TResult> reduceRight(iterator: lodash.MemoIterator<TModel, TResult>, memo: TResult? = definedExternally /* null */, context: Any? = definedExternally /* null */): TResult = definedExternally
    open fun reject(iterator: lodash.ListIterator<TModel, Boolean>, context: Any? = definedExternally /* null */): Array<TModel> = definedExternally
    open fun rest(n: Number? = definedExternally /* null */): Array<TModel> = definedExternally
    open fun sample(): TModel = definedExternally
    open fun sample(n: Number): Array<TModel> = definedExternally
    open fun select(iterator: lodash.ListIterator<TModel, Boolean>, context: Any? = definedExternally /* null */): Array<TModel> = definedExternally
    open fun shuffle(): Array<TModel> = definedExternally
    open fun size(): Number = definedExternally
    open fun some(iterator: lodash.ListIterator<TModel, Boolean>? = definedExternally /* null */, context: Any? = definedExternally /* null */): Boolean = definedExternally
    open fun <TSort> sortBy(iterator: lodash.ListIterator<TModel, TSort>? = definedExternally /* null */, context: Any? = definedExternally /* null */): Array<TModel> = definedExternally
    open fun sortBy(iterator: String, context: Any? = definedExternally /* null */): Array<TModel> = definedExternally
    open fun tail(n: Number? = definedExternally /* null */): Array<TModel> = definedExternally
    open fun take(): TModel = definedExternally
    open fun take(n: Number): Array<TModel> = definedExternally
    open fun toArray(): Array<TModel> = definedExternally
    open fun without(vararg values: TModel): Array<TModel> = definedExternally
    companion object {
        fun extend(properties: Any, classProperties: Any? = definedExternally /* null */): Any = definedExternally
    }
}
external open class Router(options: RouterOptions? = definedExternally /* null */) : Events {
    open var routes: dynamic /* RoutesHash | Any */ = definedExternally
    open fun initialize(options: RouterOptions? = definedExternally /* null */): Unit = definedExternally
    open fun route(route: String, name: String, callback: Function<*>? = definedExternally /* null */): Router = definedExternally
    open fun route(route: RegExp, name: String, callback: Function<*>? = definedExternally /* null */): Router = definedExternally
    open fun navigate(fragment: String, options: NavigateOptions? = definedExternally /* null */): Router = definedExternally
    open fun navigate(fragment: String, trigger: Boolean? = definedExternally /* null */): Router = definedExternally
    open fun execute(callback: Function<*>, args: Array<Any>, name: String): Unit = definedExternally
    open fun _bindRoutes(): Unit = definedExternally
    open fun _routeToRegExp(route: String): RegExp = definedExternally
    open fun _extractParameters(route: RegExp, fragment: String): Array<String> = definedExternally
    companion object {
        fun extend(properties: Any, classProperties: Any? = definedExternally /* null */): Any = definedExternally
    }
}
external var history: History = definedExternally
external open class History : Events {
    open var handlers: Array<Any> = definedExternally
    open var interval: Number = definedExternally
    open fun start(options: HistoryOptions? = definedExternally /* null */): Boolean = definedExternally
    open fun getHash(window: Window? = definedExternally /* null */): String = definedExternally
    open fun getFragment(fragment: String? = definedExternally /* null */): String = definedExternally
    open fun decodeFragment(fragment: String): String = definedExternally
    open fun getSearch(): String = definedExternally
    open fun stop(): Unit = definedExternally
    open fun route(route: String, callback: Function<*>): Number = definedExternally
    open fun checkUrl(e: Any? = definedExternally /* null */): Unit = definedExternally
    open fun getPath(): String = definedExternally
    open fun matchRoot(): Boolean = definedExternally
    open fun atRoot(): Boolean = definedExternally
    open fun loadUrl(fragmentOverride: String? = definedExternally /* null */): Boolean = definedExternally
    open fun navigate(fragment: String, options: Any? = definedExternally /* null */): Boolean = definedExternally
    open var options: Any = definedExternally
    open fun _updateHash(location: Location, fragment: String, replace: Boolean): Unit = definedExternally
    companion object {
        var started: Boolean = definedExternally
    }
}
external interface ViewOptions<TModel : Model> {
    var model: TModel? get() = definedExternally; set(value) = definedExternally
    var collection: Backbone.Collection<Model>? get() = definedExternally; set(value) = definedExternally
    var el: Any? get() = definedExternally; set(value) = definedExternally
    var events: EventsHash? get() = definedExternally; set(value) = definedExternally
    var id: String? get() = definedExternally; set(value) = definedExternally
    var className: String? get() = definedExternally; set(value) = definedExternally
    var tagName: String? get() = definedExternally; set(value) = definedExternally
    var attributes: Json? get() = definedExternally; set(value) = definedExternally
}
external open class View<TModel : Model>(options: ViewOptions<TModel>? = definedExternally /* null */) : Events {
    open fun initialize(options: ViewOptions<TModel>? = definedExternally /* null */): Unit = definedExternally
    open fun events(): EventsHash = definedExternally
    open fun `$`(selector: String): JQuery = definedExternally
    open var model: TModel = definedExternally
    open var collection: Collection<TModel> = definedExternally
    open fun setElement(element: HTMLElement, delegate: Boolean? = definedExternally /* null */): View<TModel> = definedExternally
    open fun setElement(element: JQuery, delegate: Boolean? = definedExternally /* null */): View<TModel> = definedExternally
    open var id: String = definedExternally
    open var cid: String = definedExternally
    open var className: String = definedExternally
    open var tagName: String = definedExternally
    open var el: Any = definedExternally
    open var `$el`: JQuery = definedExternally
    open fun setElement(element: Any): View<TModel> = definedExternally
    open var attributes: Any = definedExternally
    open fun `$`(selector: Any): JQuery = definedExternally
    open fun render(): View<TModel> = definedExternally
    open fun remove(): View<TModel> = definedExternally
    open fun delegateEvents(events: EventsHash? = definedExternally /* null */): Any = definedExternally
    open fun delegate(eventName: String, selector: String, listener: Function<*>): View<TModel> = definedExternally
    open fun undelegateEvents(): Any = definedExternally
    open fun undelegate(eventName: String, selector: String? = definedExternally /* null */, listener: Function<*>? = definedExternally /* null */): View<TModel> = definedExternally
    open fun _ensureElement(): Unit = definedExternally
    companion object {
        fun extend(properties: Any, classProperties: Any? = definedExternally /* null */): Any = definedExternally
    }
}
external fun sync(method: String, model: Model, options: JQueryAjaxSettings? = definedExternally /* null */): Any = definedExternally
external fun ajax(options: JQueryAjaxSettings? = definedExternally /* null */): JQueryXHR = definedExternally
external var emulateHTTP: Boolean = definedExternally
external var emulateJSON: Boolean = definedExternally
external fun noConflict(): dynamic /* "TypeQuery" kind unsupported yet here! (index.d.ts:435:27 to 435:43) */ = definedExternally
external var `$`: JQueryStatic = definedExternally
