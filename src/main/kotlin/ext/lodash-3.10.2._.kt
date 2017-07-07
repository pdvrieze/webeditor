@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS")
@file:[JsQualifier("_") JsModule("lodash")]
package lodash

import es6.JSArray
import es6.JSFunction
import kotlin.js.*

external interface LoDashStatic {
    @nativeInvoke
    operator fun invoke(value: Number): LoDashImplicitWrapper<Number>
    @nativeInvoke
    operator fun invoke(value: String): LoDashImplicitStringWrapper
    @nativeInvoke
    operator fun invoke(value: Boolean): LoDashImplicitWrapper<Boolean>
    @nativeInvoke
    operator fun invoke(value: Array<Number>): LoDashImplicitNumberArrayWrapper
    @nativeInvoke
    operator fun <T> invoke(value: Array<T>): LoDashImplicitArrayWrapper<T>
    @nativeInvoke
    operator fun <T : Any> invoke(value: T): LoDashImplicitObjectWrapper<T>
    @nativeInvoke
    operator fun invoke(value: Any): LoDashImplicitWrapper<Any>
    var VERSION: String
    var support: Support
    var templateSettings: TemplateSettings
    fun <T> chunk(array: List<T>, size: Number? = definedExternally /* null */): Array<Array<T>>
    fun <T> compact(array: List<T>? = definedExternally /* null */): Array<T>
    fun <T> difference(array: Array<T>, vararg values: dynamic /* Array<T> | List<T> */): Array<T>
    fun <T> difference(array: List<T>, vararg values: dynamic /* Array<T> | List<T> */): Array<T>
    fun <T> drop(array: Array<T>, n: Number? = definedExternally /* null */): Array<T>
    fun <T> drop(array: List<T>, n: Number? = definedExternally /* null */): Array<T>
    fun <T> dropRight(array: List<T>, n: Number? = definedExternally /* null */): Array<T>
    fun <TValue> dropRightWhile(array: List<TValue>, predicate: ListIterator<TValue, Boolean>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Array<TValue>
    fun <TValue> dropRightWhile(array: List<TValue>, predicate: String? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Array<TValue>
    fun <TWhere, TValue> dropRightWhile(array: List<TValue>, predicate: TWhere? = definedExternally /* null */): Array<TValue>
    fun <TValue> dropWhile(array: List<TValue>, predicate: ListIterator<TValue, Boolean>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Array<TValue>
    fun <TValue> dropWhile(array: List<TValue>, predicate: String? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Array<TValue>
    fun <TWhere, TValue> dropWhile(array: List<TValue>, predicate: TWhere? = definedExternally /* null */): Array<TValue>
    fun <T> fill(array: Array<Any>, value: T, start: Number? = definedExternally /* null */, end: Number? = definedExternally /* null */): Array<T>
    fun <T> fill(array: List<Any>, value: T, start: Number? = definedExternally /* null */, end: Number? = definedExternally /* null */): List<T>
    fun <T> findIndex(array: List<T>, predicate: ListIterator<T, Boolean>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Number
    fun <T> findIndex(array: List<T>, predicate: String? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Number
    fun <W, T> findIndex(array: List<T>, predicate: W? = definedExternally /* null */): Number
    fun <T> findLastIndex(array: List<T>, predicate: ListIterator<T, Boolean>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Number
    fun <T> findLastIndex(array: List<T>, predicate: String? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Number
    fun <W, T> findLastIndex(array: List<T>, predicate: W? = definedExternally /* null */): Number
    fun <T> first(array: List<T>): T
    fun <T> flatten(array: ListOfRecursiveArraysOrValues<T>, isDeep: Boolean): Array<T>
    fun <T> flatten(dynarray: List<dynamic /* T | Array<T> */>): Array<T>
    fun <T> flatten(recarray: ListOfRecursiveArraysOrValues<T>): RecursiveArray<T>
    fun <T> flattenDeep(array: ListOfRecursiveArraysOrValues<T>): Array<T>
    fun <T> head(array: List<T>): T
    fun <T> indexOf(array: List<T>, value: T, fromIndex: Boolean? = definedExternally /* null */): Number
    fun <T> indexOf(array: List<T>, value: T, fromIndex: Number? = definedExternally /* null */): Number
    fun <T> initial(array: List<T>): Array<T>
    fun <T> intersection(vararg arrays: dynamic /* Array<T> | List<T> */): Array<T>
    fun <T> last(array: List<T>): T
    fun <T> lastIndexOf(array: List<T>, value: T, fromIndex: Boolean? = definedExternally /* null */): Number
    fun <T> lastIndexOf(array: List<T>, value: T, fromIndex: Number? = definedExternally /* null */): Number
    fun <TValues, TResult : Any> `object`(props: List<StringRepresentable>, values: List<TValues>? = definedExternally /* null */): TResult
    fun <TValues, TResult : Any> `object`(props: List<List<Any>>, values: List<TValues>? = definedExternally /* null */): TResult
    fun <TResult : Any> `object`(props: List<StringRepresentable>, values: List<Any>? = definedExternally /* null */): TResult
    fun <TResult : Any> `object`(props: List<List<Any>>, values: List<Any>? = definedExternally /* null */): TResult
    fun `object`(props: List<StringRepresentable>, values: List<Any>? = definedExternally /* null */): lodash.Dictionary<Any>
    fun `object`(props: List<List<Any>>, values: List<Any>? = definedExternally /* null */): lodash.Dictionary<Any>
    fun <T> pull(array: Array<T>, vararg values: T): Array<T>
    fun <T> pull(array: List<T>, vararg values: T): List<T>
    fun <T> pullAt(array: List<T>, vararg indexes: dynamic /* Number | Array<Number> */): Array<T>
    fun <T> remove(array: List<T>, predicate: ListIterator<T, Boolean>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Array<T>
    fun <T> remove(array: List<T>, predicate: String? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Array<T>
    fun <W, T> remove(array: List<T>, predicate: W? = definedExternally /* null */): Array<T>
    fun <T> rest(array: List<T>): Array<T>
    fun <T> slice(array: Array<T>, start: Number? = definedExternally /* null */, end: Number? = definedExternally /* null */): Array<T>
    fun <T, TSort> sortedIndex(array: List<T>, value: T, iteratee: ((x: T) -> TSort)? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Number
    fun <T> sortedIndex(array: List<T>, value: T, iteratee: ((x: T) -> Any)? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Number
    fun <T> sortedIndex(array: List<T>, value: T, iteratee: String): Number
    fun <W, T> sortedIndex(array: List<T>, value: T, iteratee: W): Number
    fun <T> sortedIndex(array: List<T>, value: T, iteratee: Any): Number
    fun <T, TSort> sortedLastIndex(array: List<T>, value: T, iteratee: ((x: T) -> TSort)? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Number
    fun <T> sortedLastIndex(array: List<T>, value: T, iteratee: ((x: T) -> Any)? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Number
    fun <T> sortedLastIndex(array: List<T>, value: T, iteratee: String): Number
    fun <W, T> sortedLastIndex(array: List<T>, value: T, iteratee: W): Number
    fun <T> sortedLastIndex(array: List<T>, value: T, iteratee: Any): Number
    fun <T> tail(array: List<T>): Array<T>
    fun <T> take(array: List<T>, n: Number? = definedExternally /* null */): Array<T>
    fun <T> takeRight(array: List<T>, n: Number? = definedExternally /* null */): Array<T>
    fun <TValue> takeRightWhile(array: List<TValue>, predicate: ListIterator<TValue, Boolean>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Array<TValue>
    fun <TValue> takeRightWhile(array: List<TValue>, predicate: String? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Array<TValue>
    fun <TWhere, TValue> takeRightWhile(array: List<TValue>, predicate: TWhere? = definedExternally /* null */): Array<TValue>
    fun <TValue> takeWhile(array: List<TValue>, predicate: ListIterator<TValue, Boolean>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Array<TValue>
    fun <TValue> takeWhile(array: List<TValue>, predicate: String? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Array<TValue>
    fun <TWhere, TValue> takeWhile(array: List<TValue>, predicate: TWhere? = definedExternally /* null */): Array<TValue>
    fun <T> union(vararg arrays: List<T>): Array<T>
    fun <T> uniq(array: List<T>, isSorted: Boolean? = definedExternally /* null */, iteratee: ListIterator<T, Any>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Array<T>
    fun <T, TSort> uniq(array: List<T>, isSorted: Boolean? = definedExternally /* null */, iteratee: ListIterator<T, TSort>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Array<T>
    fun <T> uniq(array: List<T>, iteratee: ListIterator<T, Any>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Array<T>
    fun <T, TSort> uniq(array: List<T>, iteratee: ListIterator<T, TSort>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Array<T>
    fun <T> uniq(array: List<T>, isSorted: Boolean? = definedExternally /* null */, iteratee: String? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Array<T>
    fun <T> uniq(array: List<T>, iteratee: String? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Array<T>
    fun <T> uniq(array: List<T>, isSorted: Boolean? = definedExternally /* null */, iteratee: Any? = definedExternally /* null */): Array<T>
    fun <TWhere : Any, T> uniq(array: List<T>, isSorted: Boolean? = definedExternally /* null */, iteratee: TWhere? = definedExternally /* null */): Array<T>
    fun <T> uniq(array: List<T>, iteratee: Any? = definedExternally /* null */): Array<T>
    fun <TWhere : Any, T> uniq(array: List<T>, iteratee: TWhere? = definedExternally /* null */): Array<T>
    fun <T> unique(array: List<T>, isSorted: Boolean? = definedExternally /* null */, iteratee: ListIterator<T, Any>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Array<T>
    fun <T, TSort> unique(array: List<T>, isSorted: Boolean? = definedExternally /* null */, iteratee: ListIterator<T, TSort>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Array<T>
    fun <T> unique(array: List<T>, iteratee: ListIterator<T, Any>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Array<T>
    fun <T, TSort> unique(array: List<T>, iteratee: ListIterator<T, TSort>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Array<T>
    fun <T> unique(array: List<T>, isSorted: Boolean? = definedExternally /* null */, iteratee: String? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Array<T>
    fun <T> unique(array: List<T>, iteratee: String? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Array<T>
    fun <T> unique(array: List<T>, isSorted: Boolean? = definedExternally /* null */, iteratee: Any? = definedExternally /* null */): Array<T>
    fun <TWhere : Any, T> unique(array: List<T>, isSorted: Boolean? = definedExternally /* null */, iteratee: TWhere? = definedExternally /* null */): Array<T>
    fun <T> unique(array: List<T>, iteratee: Any? = definedExternally /* null */): Array<T>
    fun <TWhere : Any, T> unique(array: List<T>, iteratee: TWhere? = definedExternally /* null */): Array<T>
    fun <T> unzip(array: List<List<T>>): Array<Array<T>>
    fun <TArray, TResult> unzipWith(array: List<List<TArray>>, iteratee: MemoIterator<TArray, TResult>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Array<TResult>
    fun <T> without(array: List<T>, vararg values: T): Array<T>
    fun <T> xor(vararg arrays: List<T>): Array<T>
    fun <T> zip(vararg arrays: List<T>): Array<Array<T>>
    fun <TValues, TResult : Any> zipObject(props: List<StringRepresentable>, values: List<TValues>? = definedExternally /* null */): TResult
    fun <TValues, TResult : Any> zipObject(props: List<List<Any>>, values: List<TValues>? = definedExternally /* null */): TResult
    fun <TResult : Any> zipObject(props: List<StringRepresentable>, values: List<Any>? = definedExternally /* null */): TResult
    fun <TResult : Any> zipObject(props: List<List<Any>>, values: List<Any>? = definedExternally /* null */): TResult
    fun zipObject(props: List<StringRepresentable>, values: List<Any>? = definedExternally /* null */): lodash.Dictionary<Any>
    fun zipObject(props: List<List<Any>>, values: List<Any>? = definedExternally /* null */): lodash.Dictionary<Any>
    fun <TResult> zipWith(vararg args: Any): Array<TResult>
    fun chain(value: Number): LoDashExplicitWrapper<Number>
    fun chain(value: String): LoDashExplicitWrapper<String>
    fun chain(value: Boolean): LoDashExplicitWrapper<Boolean>
    fun <T> chain(value: Array<T>): LoDashExplicitArrayWrapper<T>
    fun <T : Any> chain(value: T): LoDashExplicitObjectWrapper<T>
    fun chain(value: Any): LoDashExplicitWrapper<Any>
    fun <T> tap(value: T, interceptor: (value: T) -> Unit, thisArg: Any? = definedExternally /* null */): T
    fun <T, TResult> thru(value: T, interceptor: (value: T) -> TResult, thisArg: Any? = definedExternally /* null */): TResult
    fun <T> all(collection: List<T>, predicate: ListIterator<T, Boolean>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Boolean
    fun <T> all(collection: Dictionary<T>, predicate: DictionaryIterator<T, Boolean>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Boolean
    fun <T> all(collection: List<T>, predicate: String? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Boolean
    fun <T> all(collection: Dictionary<T>, predicate: String? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Boolean
    fun <TObject : Any, T> all(collection: List<T>, predicate: TObject? = definedExternally /* null */): Boolean
    fun <TObject : Any, T> all(collection: Dictionary<T>, predicate: TObject? = definedExternally /* null */): Boolean
    fun <T> any(collection: List<T>, predicate: ListIterator<T, Boolean>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Boolean
    fun <T> any(collection: Dictionary<T>, predicate: DictionaryIterator<T, Boolean>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Boolean
    fun <T> any(collection: NumericDictionary<T>, predicate: NumericDictionaryIterator<T, Boolean>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Boolean
    fun <T> any(collection: Any, predicate: ObjectIterator<Any, Boolean>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Boolean
    fun <T> any(collection: List<T>, predicate: String? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Boolean
    fun <T> any(collection: Dictionary<T>, predicate: String? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Boolean
    fun <T> any(collection: NumericDictionary<T>, predicate: String? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Boolean
    fun any(collection: Any, predicate: String? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Boolean
    fun <TObject : Any, T> any(collection: List<T>, predicate: TObject? = definedExternally /* null */): Boolean
    fun <TObject : Any, T> any(collection: Dictionary<T>, predicate: TObject? = definedExternally /* null */): Boolean
    fun <TObject : Any, T> any(collection: NumericDictionary<T>, predicate: TObject? = definedExternally /* null */): Boolean
    fun <T> any(collection: List<T>, predicate: Any? = definedExternally /* null */): Boolean
    fun <T> any(collection: Dictionary<T>, predicate: Any? = definedExternally /* null */): Boolean
    fun <T> any(collection: NumericDictionary<T>, predicate: Any? = definedExternally /* null */): Boolean
    fun <TObject : Any> any(collection: Any, predicate: TObject? = definedExternally /* null */): Boolean
    fun <T> at(collection: List<T>, vararg props: dynamic /* Number | String | Array<dynamic /* Number | String */> */): Array<T>
    fun <T> at(collection: Dictionary<T>, vararg props: dynamic /* Number | String | Array<dynamic /* Number | String */> */): Array<T>
    fun <T, TResult> collect(collection: List<T>, iteratee: ListIterator<T, TResult>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Array<TResult>
    fun <T : Any, TResult> collect(collection: Dictionary<T>, iteratee: DictionaryIterator<T, TResult>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Array<TResult>
    fun <T, TResult> collect(collection: List<T>, iteratee: String? = definedExternally /* null */): Array<TResult>
    fun <T, TResult> collect(collection: Dictionary<T>, iteratee: String? = definedExternally /* null */): Array<TResult>
    fun <T, TObject : Any> collect(collection: List<T>, iteratee: TObject? = definedExternally /* null */): Array<Boolean>
    fun <T, TObject : Any> collect(collection: Dictionary<T>, iteratee: TObject? = definedExternally /* null */): Array<Boolean>
    fun <T> contains(collection: List<T>, target: T, fromIndex: Number? = definedExternally /* null */): Boolean
    fun <T> contains(collection: Dictionary<T>, target: T, fromIndex: Number? = definedExternally /* null */): Boolean
    fun contains(collection: String, target: String, fromIndex: Number? = definedExternally /* null */): Boolean
    fun <T> countBy(collection: List<T>, iteratee: ListIterator<T, Any>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Dictionary<Number>
    fun <T> countBy(collection: Dictionary<T>, iteratee: DictionaryIterator<T, Any>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Dictionary<Number>
    fun <T> countBy(collection: NumericDictionary<T>, iteratee: NumericDictionaryIterator<T, Any>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Dictionary<Number>
    fun <T> countBy(collection: List<T>, iteratee: String? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Dictionary<Number>
    fun <T> countBy(collection: Dictionary<T>, iteratee: String? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Dictionary<Number>
    fun <T> countBy(collection: NumericDictionary<T>, iteratee: String? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Dictionary<Number>
    fun <W, T> countBy(collection: List<T>, iteratee: W? = definedExternally /* null */): Dictionary<Number>
    fun <W, T> countBy(collection: Dictionary<T>, iteratee: W? = definedExternally /* null */): Dictionary<Number>
    fun <W, T> countBy(collection: NumericDictionary<T>, iteratee: W? = definedExternally /* null */): Dictionary<Number>
    fun <T> countBy(collection: List<T>, iteratee: Any? = definedExternally /* null */): Dictionary<Number>
    fun <T> countBy(collection: Dictionary<T>, iteratee: Any? = definedExternally /* null */): Dictionary<Number>
    fun <T> countBy(collection: NumericDictionary<T>, iteratee: Any? = definedExternally /* null */): Dictionary<Number>
    fun <T> detect(collection: List<T>, predicate: ListIterator<T, Boolean>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): T
    fun <T> detect(collection: Dictionary<T>, predicate: DictionaryIterator<T, Boolean>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): T
    fun <T> detect(collection: List<T>, predicate: String? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): T
    fun <T> detect(collection: Dictionary<T>, predicate: String? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): T
    fun <TObject : Any, T> detect(collection: List<T>, predicate: TObject? = definedExternally /* null */): T
    fun <TObject : Any, T> detect(collection: Dictionary<T>, predicate: TObject? = definedExternally /* null */): T
    fun <T> each(collection: Array<T>, iteratee: ListIterator<T, Any>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Array<T>
    fun <T> each(collection: List<T>, iteratee: ListIterator<T, Any>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): List<T>
    fun <T> each(collection: Dictionary<T>, iteratee: DictionaryIterator<T, Any>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Dictionary<T>
    fun <T : Any> each(collection: T, iteratee: ObjectIterator<Any, Any>? = definedExternally /* null */, thisArgs: Any? = definedExternally /* null */): T
    fun <T : Any, TValue> each(collection: T, iteratee: ObjectIterator<TValue, Any>? = definedExternally /* null */, thisArgs: Any? = definedExternally /* null */): T
    fun <T> eachRight(collection: Array<T>, iteratee: ListIterator<T, Any>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Array<T>
    fun <T> eachRight(collection: List<T>, iteratee: ListIterator<T, Any>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): List<T>
    fun <T> eachRight(collection: Dictionary<T>, iteratee: DictionaryIterator<T, Any>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Dictionary<T>
    fun <T : Any> eachRight(collection: T, iteratee: ObjectIterator<Any, Any>? = definedExternally /* null */, thisArgs: Any? = definedExternally /* null */): T
    fun <T : Any, TValue> eachRight(collection: T, iteratee: ObjectIterator<TValue, Any>? = definedExternally /* null */, thisArgs: Any? = definedExternally /* null */): T
    fun <T> every(collection: List<T>, predicate: ListIterator<T, Boolean>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Boolean
    fun <T> every(collection: Dictionary<T>, predicate: DictionaryIterator<T, Boolean>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Boolean
    fun <T> every(collection: List<T>, predicate: String? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Boolean
    fun <T> every(collection: Dictionary<T>, predicate: String? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Boolean
    fun <TObject : Any, T> every(collection: List<T>, predicate: TObject? = definedExternally /* null */): Boolean
    fun <TObject : Any, T> every(collection: Dictionary<T>, predicate: TObject? = definedExternally /* null */): Boolean
    fun <T> filter(collection: List<T>, predicate: ListIterator<T, Boolean>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Array<T>
    fun <T> filter(collection: Dictionary<T>, predicate: DictionaryIterator<T, Boolean>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Array<T>
    fun filter(collection: String, predicate: StringIterator<Boolean>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Array<String>
    fun <T> filter(collection: List<T>, predicate: String, thisArg: Any? = definedExternally /* null */): Array<T>
    fun <T> filter(collection: Dictionary<T>, predicate: String, thisArg: Any? = definedExternally /* null */): Array<T>
    fun <W : Any, T> filter(collection: List<T>, predicate: W): Array<T>
    fun <W : Any, T> filter(collection: Dictionary<T>, predicate: W): Array<T>
    fun <T> find(collection: List<T>, predicate: ListIterator<T, Boolean>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): T?
    fun <T> find(collection: Dictionary<T>, predicate: DictionaryIterator<T, Boolean>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): T?
    fun <T> find(collection: List<T>, predicate: String? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): T?
    fun <T> find(collection: Dictionary<T>, predicate: String? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): T?
    fun <TObject : Any, T> find(collection: List<T>, predicate: TObject? = definedExternally /* null */): T?
    fun <TObject : Any, T> find(collection: Dictionary<T>, predicate: TObject? = definedExternally /* null */): T?
    fun <T> findWhere(collection: Array<T>, callback: ListIterator<T, Boolean>, thisArg: Any? = definedExternally /* null */): T?
    fun <T> findWhere(collection: List<T>, callback: ListIterator<T, Boolean>, thisArg: Any? = definedExternally /* null */): T?
    fun <T> findWhere(collection: Dictionary<T>, callback: DictionaryIterator<T, Boolean>, thisArg: Any? = definedExternally /* null */): T?
    fun <W, T> findWhere(collection: Array<T>, whereValue: W): T?
    fun <W, T> findWhere(collection: List<T>, whereValue: W): T?
    fun <W, T> findWhere(collection: Dictionary<T>, whereValue: W): T?
    fun <T> findWhere(collection: Array<T>, pluckValue: String): T?
    fun <T> findWhere(collection: List<T>, pluckValue: String): T?
    fun <T> findWhere(collection: Dictionary<T>, pluckValue: String): T?
    fun <T> findLast(collection: Array<T>, callback: ListIterator<T, Boolean>, thisArg: Any? = definedExternally /* null */): T?
    fun <T> findLast(collection: List<T>, callback: ListIterator<T, Boolean>, thisArg: Any? = definedExternally /* null */): T?
    fun <T> findLast(collection: Dictionary<T>, callback: DictionaryIterator<T, Boolean>, thisArg: Any? = definedExternally /* null */): T?
    fun <W, T> findLast(collection: Array<T>, whereValue: W): T?
    fun <W, T> findLast(collection: List<T>, whereValue: W): T?
    fun <W, T> findLast(collection: Dictionary<T>, whereValue: W): T?
    fun <T> findLast(collection: Array<T>, pluckValue: String): T?
    fun <T> findLast(collection: List<T>, pluckValue: String): T?
    fun <T> findLast(collection: Dictionary<T>, pluckValue: String): T?
    fun <T> forEach(collection: Array<T>, iteratee: ListIterator<T, Any>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Array<T>
    fun <T> forEach(collection: List<T>, iteratee: ListIterator<T, Any>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): List<T>
    fun <T> forEach(collection: Dictionary<T>, iteratee: DictionaryIterator<T, Any>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Dictionary<T>
    fun <T : Any> forEach(collection: T, iteratee: ObjectIterator<Any, Any>? = definedExternally /* null */, thisArgs: Any? = definedExternally /* null */): T
    fun <T : Any, TValue> forEach(collection: T, iteratee: ObjectIterator<TValue, Any>? = definedExternally /* null */, thisArgs: Any? = definedExternally /* null */): T
    fun <T> forEachRight(collection: Array<T>, iteratee: ListIterator<T, Any>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Array<T>
    fun <T> forEachRight(collection: List<T>, iteratee: ListIterator<T, Any>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): List<T>
    fun <T> forEachRight(collection: Dictionary<T>, iteratee: DictionaryIterator<T, Any>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Dictionary<T>
    fun <T : Any> forEachRight(collection: T, iteratee: ObjectIterator<Any, Any>? = definedExternally /* null */, thisArgs: Any? = definedExternally /* null */): T
    fun <T : Any, TValue> forEachRight(collection: T, iteratee: ObjectIterator<TValue, Any>? = definedExternally /* null */, thisArgs: Any? = definedExternally /* null */): T
    fun <T, TKey> groupBy(collection: List<T>, iteratee: ListIterator<T, TKey>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Dictionary<Array<T>>
    fun <T> groupBy(collection: List<Any>, iteratee: ListIterator<T, Any>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Dictionary<Array<T>>
    fun <T, TKey> groupBy(collection: Dictionary<T>, iteratee: DictionaryIterator<T, TKey>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Dictionary<Array<T>>
    fun <T> groupBy(collection: Dictionary<Any>, iteratee: DictionaryIterator<T, Any>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Dictionary<Array<T>>
    fun <T, TValue> groupBy(collection: List<T>, iteratee: String? = definedExternally /* null */, thisArg: TValue? = definedExternally /* null */): Dictionary<Array<T>>
    fun <T, TValue> groupBy(collection: Dictionary<T>, iteratee: String? = definedExternally /* null */, thisArg: TValue? = definedExternally /* null */): Dictionary<Array<T>>
    fun <T> groupBy(collection: List<T>, iteratee: String? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Dictionary<Array<T>>
    fun <T> groupBy(collection: Dictionary<T>, iteratee: String? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Dictionary<Array<T>>
    fun <TWhere, T> groupBy(collection: List<T>, iteratee: TWhere? = definedExternally /* null */): Dictionary<Array<T>>
    fun <TWhere, T> groupBy(collection: Dictionary<T>, iteratee: TWhere? = definedExternally /* null */): Dictionary<Array<T>>
    fun <T> groupBy(collection: List<T>, iteratee: Any? = definedExternally /* null */): Dictionary<Array<T>>
    fun <T> groupBy(collection: Dictionary<T>, iteratee: Any? = definedExternally /* null */): Dictionary<Array<T>>
    fun <T> include(collection: List<T>, target: T, fromIndex: Number? = definedExternally /* null */): Boolean
    fun <T> include(collection: Dictionary<T>, target: T, fromIndex: Number? = definedExternally /* null */): Boolean
    fun include(collection: String, target: String, fromIndex: Number? = definedExternally /* null */): Boolean
    fun <T> includes(collection: List<T>, target: T, fromIndex: Number? = definedExternally /* null */): Boolean
    fun <T> includes(collection: Dictionary<T>, target: T, fromIndex: Number? = definedExternally /* null */): Boolean
    fun includes(collection: String, target: String, fromIndex: Number? = definedExternally /* null */): Boolean
    fun <T> indexBy(collection: List<T>, iteratee: ListIterator<T, Any>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Dictionary<T>
    fun <T> indexBy(collection: NumericDictionary<T>, iteratee: NumericDictionaryIterator<T, Any>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Dictionary<T>
    fun <T> indexBy(collection: Dictionary<T>, iteratee: DictionaryIterator<T, Any>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Dictionary<T>
    fun <T> indexBy(collection: List<T>, iteratee: String? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Dictionary<T>
    fun <T> indexBy(collection: NumericDictionary<T>, iteratee: String? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Dictionary<T>
    fun <T> indexBy(collection: Dictionary<T>, iteratee: String? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Dictionary<T>
    fun <W : Any, T> indexBy(collection: List<T>, iteratee: W? = definedExternally /* null */): Dictionary<T>
    fun <W : Any, T> indexBy(collection: NumericDictionary<T>, iteratee: W? = definedExternally /* null */): Dictionary<T>
    fun <W : Any, T> indexBy(collection: Dictionary<T>, iteratee: W? = definedExternally /* null */): Dictionary<T>
    fun <T> indexBy(collection: List<T>, iteratee: Any? = definedExternally /* null */): Dictionary<T>
    fun <T> indexBy(collection: NumericDictionary<T>, iteratee: Any? = definedExternally /* null */): Dictionary<T>
    fun <T> indexBy(collection: Dictionary<T>, iteratee: Any? = definedExternally /* null */): Dictionary<T>
    fun <T : Any> invoke(collection: Array<T>, methodName: String, vararg args: Any): Any
    fun <T : Any> invoke(collection: List<T>, methodName: String, vararg args: Any): Any
    fun <T : Any> invoke(collection: Dictionary<T>, methodName: String, vararg args: Any): Any
    fun <T : Any> invoke(collection: Array<T>, method: Function<*>, vararg args: Any): Any
    fun <T : Any> invoke(collection: List<T>, method: Function<*>, vararg args: Any): Any
    fun <T : Any> invoke(collection: Dictionary<T>, method: Function<*>, vararg args: Any): Any
    fun <T, TResult> map(collection: List<T>, iteratee: ListIterator<T, TResult>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Array<TResult>
    fun <T : Any, TResult> map(collection: Dictionary<T>, iteratee: DictionaryIterator<T, TResult>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Array<TResult>
    fun <T, TResult> map(collection: List<T>, iteratee: String? = definedExternally /* null */): Array<TResult>
    fun <T, TResult> map(collection: Dictionary<T>, iteratee: String? = definedExternally /* null */): Array<TResult>
    fun <T, TObject : Any> map(collection: List<T>, iteratee: TObject? = definedExternally /* null */): Array<Boolean>
    fun <T, TObject : Any> map(collection: Dictionary<T>, iteratee: TObject? = definedExternally /* null */): Array<Boolean>
    fun <T> partition(collection: List<T>, callback: ListIterator<T, Boolean>, thisArg: Any? = definedExternally /* null */): Array<Array<T>>
    fun <T> partition(collection: Dictionary<T>, callback: DictionaryIterator<T, Boolean>, thisArg: Any? = definedExternally /* null */): Array<Array<T>>
    fun <W, T> partition(collection: List<T>, whereValue: W): Array<Array<T>>
    fun <W, T> partition(collection: Dictionary<T>, whereValue: W): Array<Array<T>>
    fun <T> partition(collection: List<T>, path: String, srcValue: Any): Array<Array<T>>
    fun <T> partition(collection: Dictionary<T>, path: String, srcValue: Any): Array<Array<T>>
    fun <T> partition(collection: List<T>, pluckValue: String): Array<Array<T>>
    fun <T> partition(collection: Dictionary<T>, pluckValue: String): Array<Array<T>>
    fun <T : Any> pluck(collection: List<T>, path: StringRepresentable): Array<Any>
    fun <T : Any> pluck(collection: List<T>, path: Array<StringRepresentable>): Array<Any>
    fun <T : Any> pluck(collection: Dictionary<T>, path: StringRepresentable): Array<Any>
    fun <T : Any> pluck(collection: Dictionary<T>, path: Array<StringRepresentable>): Array<Any>
    fun <T : Any, TResult> pluck(collection: List<T>, path: StringRepresentable): Array<TResult>
    fun <T : Any, TResult> pluck(collection: List<T>, path: Array<StringRepresentable>): Array<TResult>
    fun <T : Any, TResult> pluck(collection: Dictionary<T>, path: StringRepresentable): Array<TResult>
    fun <T : Any, TResult> pluck(collection: Dictionary<T>, path: Array<StringRepresentable>): Array<TResult>
    fun <T, TResult> reduce(collection: Array<T>, callback: MemoIterator<T, TResult>, accumulator: TResult, thisArg: Any? = definedExternally /* null */): TResult
    fun <T, TResult> reduce(collection: List<T>, callback: MemoIterator<T, TResult>, accumulator: TResult, thisArg: Any? = definedExternally /* null */): TResult
    fun <T, TResult> reduce(collection: Dictionary<T>, callback: MemoIterator<T, TResult>, accumulator: TResult, thisArg: Any? = definedExternally /* null */): TResult
    fun <T, TResult> reduce(collection: Array<T>, callback: MemoIterator<T, TResult>, thisArg: Any? = definedExternally /* null */): TResult
    fun <T, TResult> reduce(collection: List<T>, callback: MemoIterator<T, TResult>, thisArg: Any? = definedExternally /* null */): TResult
    fun <T, TResult> reduce(collection: Dictionary<T>, callback: MemoIterator<T, TResult>, thisArg: Any? = definedExternally /* null */): TResult
    fun <T, TResult> inject(collection: Array<T>, callback: MemoIterator<T, TResult>, accumulator: TResult, thisArg: Any? = definedExternally /* null */): TResult
    fun <T, TResult> inject(collection: List<T>, callback: MemoIterator<T, TResult>, accumulator: TResult, thisArg: Any? = definedExternally /* null */): TResult
    fun <T, TResult> inject(collection: Dictionary<T>, callback: MemoIterator<T, TResult>, accumulator: TResult, thisArg: Any? = definedExternally /* null */): TResult
    fun <T, TResult> inject(collection: Array<T>, callback: MemoIterator<T, TResult>, thisArg: Any? = definedExternally /* null */): TResult
    fun <T, TResult> inject(collection: List<T>, callback: MemoIterator<T, TResult>, thisArg: Any? = definedExternally /* null */): TResult
    fun <T, TResult> inject(collection: Dictionary<T>, callback: MemoIterator<T, TResult>, thisArg: Any? = definedExternally /* null */): TResult
    fun <T, TResult> foldl(collection: Array<T>, callback: MemoIterator<T, TResult>, accumulator: TResult, thisArg: Any? = definedExternally /* null */): TResult
    fun <T, TResult> foldl(collection: List<T>, callback: MemoIterator<T, TResult>, accumulator: TResult, thisArg: Any? = definedExternally /* null */): TResult
    fun <T, TResult> foldl(collection: Dictionary<T>, callback: MemoIterator<T, TResult>, accumulator: TResult, thisArg: Any? = definedExternally /* null */): TResult
    fun <T, TResult> foldl(collection: Array<T>, callback: MemoIterator<T, TResult>, thisArg: Any? = definedExternally /* null */): TResult
    fun <T, TResult> foldl(collection: List<T>, callback: MemoIterator<T, TResult>, thisArg: Any? = definedExternally /* null */): TResult
    fun <T, TResult> foldl(collection: Dictionary<T>, callback: MemoIterator<T, TResult>, thisArg: Any? = definedExternally /* null */): TResult
    fun <T, TResult> reduceRight(collection: Array<T>, callback: MemoIterator<T, TResult>, accumulator: TResult, thisArg: Any? = definedExternally /* null */): TResult
    fun <T, TResult> reduceRight(collection: List<T>, callback: MemoIterator<T, TResult>, accumulator: TResult, thisArg: Any? = definedExternally /* null */): TResult
    fun <T, TResult> reduceRight(collection: Dictionary<T>, callback: MemoIterator<T, TResult>, accumulator: TResult, thisArg: Any? = definedExternally /* null */): TResult
    fun <T, TResult> reduceRight(collection: Array<T>, callback: MemoIterator<T, TResult>, thisArg: Any? = definedExternally /* null */): TResult
    fun <T, TResult> reduceRight(collection: List<T>, callback: MemoIterator<T, TResult>, thisArg: Any? = definedExternally /* null */): TResult
    fun <T, TResult> reduceRight(collection: Dictionary<T>, callback: MemoIterator<T, TResult>, thisArg: Any? = definedExternally /* null */): TResult
    fun <T, TResult> foldr(collection: Array<T>, callback: MemoIterator<T, TResult>, accumulator: TResult, thisArg: Any? = definedExternally /* null */): TResult
    fun <T, TResult> foldr(collection: List<T>, callback: MemoIterator<T, TResult>, accumulator: TResult, thisArg: Any? = definedExternally /* null */): TResult
    fun <T, TResult> foldr(collection: Dictionary<T>, callback: MemoIterator<T, TResult>, accumulator: TResult, thisArg: Any? = definedExternally /* null */): TResult
    fun <T, TResult> foldr(collection: Array<T>, callback: MemoIterator<T, TResult>, thisArg: Any? = definedExternally /* null */): TResult
    fun <T, TResult> foldr(collection: List<T>, callback: MemoIterator<T, TResult>, thisArg: Any? = definedExternally /* null */): TResult
    fun <T, TResult> foldr(collection: Dictionary<T>, callback: MemoIterator<T, TResult>, thisArg: Any? = definedExternally /* null */): TResult
    fun <T> reject(collection: List<T>, predicate: ListIterator<T, Boolean>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Array<T>
    fun <T> reject(collection: Dictionary<T>, predicate: DictionaryIterator<T, Boolean>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Array<T>
    fun reject(collection: String, predicate: StringIterator<Boolean>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Array<String>
    fun <T> reject(collection: List<T>, predicate: String, thisArg: Any? = definedExternally /* null */): Array<T>
    fun <T> reject(collection: Dictionary<T>, predicate: String, thisArg: Any? = definedExternally /* null */): Array<T>
    fun <W : Any, T> reject(collection: List<T>, predicate: W): Array<T>
    fun <W : Any, T> reject(collection: Dictionary<T>, predicate: W): Array<T>
    fun <T> select(collection: List<T>, predicate: ListIterator<T, Boolean>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Array<T>
    fun <T> select(collection: Dictionary<T>, predicate: DictionaryIterator<T, Boolean>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Array<T>
    fun select(collection: String, predicate: StringIterator<Boolean>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Array<String>
    fun <T> select(collection: List<T>, predicate: String, thisArg: Any? = definedExternally /* null */): Array<T>
    fun <T> select(collection: Dictionary<T>, predicate: String, thisArg: Any? = definedExternally /* null */): Array<T>
    fun <W : Any, T> select(collection: List<T>, predicate: W): Array<T>
    fun <W : Any, T> select(collection: Dictionary<T>, predicate: W): Array<T>
    fun <T> sample(collection: List<T>, n: Number): Array<T>
    fun <T> sample(collection: Dictionary<T>, n: Number): Array<T>
    fun <T> sample(collection: NumericDictionary<T>, n: Number): Array<T>
    fun <O : Any, T> sample(collection: O, n: Number): Array<T>
    fun <T> sample(collection: Any, n: Number): Array<T>
    fun <T> sample(collection: List<T>): T
    fun <T> sample(collection: Dictionary<T>): T
    fun <T> sample(collection: NumericDictionary<T>): T
    fun <O : Any, T> sample(collection: O): T
    fun <T> sample(collection: Any): T
    fun <T> shuffle(collection: List<T>): Array<T>
    fun <T> shuffle(collection: Dictionary<T>): Array<T>
    fun shuffle(collection: String): Array<String>
    fun <T> size(collection: List<T>): Number
    fun <T> size(collection: Dictionary<T>): Number
    fun size(collection: String): Number
    fun <T> some(collection: List<T>, predicate: ListIterator<T, Boolean>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Boolean
    fun <T> some(collection: Dictionary<T>, predicate: DictionaryIterator<T, Boolean>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Boolean
    fun <T> some(collection: NumericDictionary<T>, predicate: NumericDictionaryIterator<T, Boolean>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Boolean
    fun some(collection: Any, predicate: ObjectIterator<Any, Boolean>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Boolean
    fun <T> some(collection: List<T>, predicate: String? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Boolean
    fun <T> some(collection: Dictionary<T>, predicate: String? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Boolean
    fun <T> some(collection: NumericDictionary<T>, predicate: String? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Boolean
    fun some(collection: Any, predicate: String? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Boolean
    fun <TObject : Any, T> some(collection: List<T>, predicate: TObject? = definedExternally /* null */): Boolean
    fun <TObject : Any, T> some(collection: Dictionary<T>, predicate: TObject? = definedExternally /* null */): Boolean
    fun <TObject : Any, T> some(collection: NumericDictionary<T>, predicate: TObject? = definedExternally /* null */): Boolean
    fun <T> some(collection: List<T>, predicate: Any? = definedExternally /* null */): Boolean
    fun <T> some(collection: Dictionary<T>, predicate: Any? = definedExternally /* null */): Boolean
    fun <T> some(collection: NumericDictionary<T>, predicate: Any? = definedExternally /* null */): Boolean
    fun <TObject : Any> some(collection: Any, predicate: TObject? = definedExternally /* null */): Boolean
    fun <T, TSort> sortBy(collection: List<T>, iteratee: ListIterator<T, TSort>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Array<T>
    fun <T, TSort> sortBy(collection: Dictionary<T>, iteratee: DictionaryIterator<T, TSort>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Array<T>
    fun <T> sortBy(collection: List<T>, iteratee: String): Array<T>
    fun <T> sortBy(collection: Dictionary<T>, iteratee: String): Array<T>
    fun <W : Any, T> sortBy(collection: List<T>, whereValue: W): Array<T>
    fun <W : Any, T> sortBy(collection: Dictionary<T>, whereValue: W): Array<T>
    fun <T> sortBy(collection: List<T>): Array<T>
    fun <T> sortBy(collection: Dictionary<T>): Array<T>
    fun <T> sortByAll(collection: Array<T>, iteratees: Array<dynamic /* ListIterator<T, Any> | String | Any */>): Array<T>
    fun <T> sortByAll(collection: List<T>, iteratees: Array<dynamic /* ListIterator<T, Any> | String | Any */>): Array<T>
    fun <T> sortByAll(collection: Array<T>, vararg iteratees: dynamic /* ListIterator<T, Any> | String | Any */): Array<T>
    fun <T> sortByAll(collection: List<T>, vararg iteratees: dynamic /* ListIterator<T, Any> | String | Any */): Array<T>
    fun <T> sortByAll(collection: dynamic /* Array<T> | List<T> */, vararg args: dynamic /* ListIterator<T, Boolean> | Any | String */): Array<T>
    fun <W : Any, T> sortByOrder(collection: List<T>, iteratees: ListIterator<T, Any>, orders: dynamic /* Boolean? | String? | Array<dynamic /* Boolean | String */>? */ = definedExternally /* null */): Array<T>
    fun <W : Any, T> sortByOrder(collection: List<T>, iteratees: String, orders: dynamic /* Boolean? | String? | Array<dynamic /* Boolean | String */>? */ = definedExternally /* null */): Array<T>
    fun <W : Any, T> sortByOrder(collection: List<T>, iteratees: W, orders: dynamic /* Boolean? | String? | Array<dynamic /* Boolean | String */>? */ = definedExternally /* null */): Array<T>
    fun <W : Any, T> sortByOrder(collection: List<T>, iteratees: Array<dynamic /* ListIterator<T, Any> | String | W */>, orders: dynamic /* Boolean? | String? | Array<dynamic /* Boolean | String */>? */ = definedExternally /* null */): Array<T>
    fun <T> sortByOrder(collection: List<T>, iteratees: ListIterator<T, Any>, orders: dynamic /* Boolean? | String? | Array<dynamic /* Boolean | String */>? */ = definedExternally /* null */): Array<T>
    fun <T> sortByOrder(collection: List<T>, iteratees: String, orders: dynamic /* Boolean? | String? | Array<dynamic /* Boolean | String */>? */ = definedExternally /* null */): Array<T>
    fun <T> sortByOrder(collection: List<T>, iteratees: Any, orders: dynamic /* Boolean? | String? | Array<dynamic /* Boolean | String */>? */ = definedExternally /* null */): Array<T>
    fun <T> sortByOrder(collection: List<T>, iteratees: Array<dynamic /* ListIterator<T, Any> | String | Any */>, orders: dynamic /* Boolean? | String? | Array<dynamic /* Boolean | String */>? */ = definedExternally /* null */): Array<T>
    fun <W : Any, T> sortByOrder(collection: NumericDictionary<T>, iteratees: NumericDictionaryIterator<T, Any>, orders: dynamic /* Boolean? | String? | Array<dynamic /* Boolean | String */>? */ = definedExternally /* null */): Array<T>
    fun <W : Any, T> sortByOrder(collection: NumericDictionary<T>, iteratees: String, orders: dynamic /* Boolean? | String? | Array<dynamic /* Boolean | String */>? */ = definedExternally /* null */): Array<T>
    fun <W : Any, T> sortByOrder(collection: NumericDictionary<T>, iteratees: W, orders: dynamic /* Boolean? | String? | Array<dynamic /* Boolean | String */>? */ = definedExternally /* null */): Array<T>
    fun <W : Any, T> sortByOrder(collection: NumericDictionary<T>, iteratees: Array<dynamic /* NumericDictionaryIterator<T, Any> | String | W */>, orders: dynamic /* Boolean? | String? | Array<dynamic /* Boolean | String */>? */ = definedExternally /* null */): Array<T>
    fun <T> sortByOrder(collection: NumericDictionary<T>, iteratees: NumericDictionaryIterator<T, Any>, orders: dynamic /* Boolean? | String? | Array<dynamic /* Boolean | String */>? */ = definedExternally /* null */): Array<T>
    fun <T> sortByOrder(collection: NumericDictionary<T>, iteratees: String, orders: dynamic /* Boolean? | String? | Array<dynamic /* Boolean | String */>? */ = definedExternally /* null */): Array<T>
    fun <T> sortByOrder(collection: NumericDictionary<T>, iteratees: Any, orders: dynamic /* Boolean? | String? | Array<dynamic /* Boolean | String */>? */ = definedExternally /* null */): Array<T>
    fun <T> sortByOrder(collection: NumericDictionary<T>, iteratees: Array<dynamic /* NumericDictionaryIterator<T, Any> | String | Any */>, orders: dynamic /* Boolean? | String? | Array<dynamic /* Boolean | String */>? */ = definedExternally /* null */): Array<T>
    fun <W : Any, T> sortByOrder(collection: Dictionary<T>, iteratees: DictionaryIterator<T, Any>, orders: dynamic /* Boolean? | String? | Array<dynamic /* Boolean | String */>? */ = definedExternally /* null */): Array<T>
    fun <W : Any, T> sortByOrder(collection: Dictionary<T>, iteratees: String, orders: dynamic /* Boolean? | String? | Array<dynamic /* Boolean | String */>? */ = definedExternally /* null */): Array<T>
    fun <W : Any, T> sortByOrder(collection: Dictionary<T>, iteratees: W, orders: dynamic /* Boolean? | String? | Array<dynamic /* Boolean | String */>? */ = definedExternally /* null */): Array<T>
    fun <W : Any, T> sortByOrder(collection: Dictionary<T>, iteratees: Array<dynamic /* DictionaryIterator<T, Any> | String | W */>, orders: dynamic /* Boolean? | String? | Array<dynamic /* Boolean | String */>? */ = definedExternally /* null */): Array<T>
    fun <T> sortByOrder(collection: Dictionary<T>, iteratees: DictionaryIterator<T, Any>, orders: dynamic /* Boolean? | String? | Array<dynamic /* Boolean | String */>? */ = definedExternally /* null */): Array<T>
    fun <T> sortByOrder(collection: Dictionary<T>, iteratees: String, orders: dynamic /* Boolean? | String? | Array<dynamic /* Boolean | String */>? */ = definedExternally /* null */): Array<T>
    fun <T> sortByOrder(collection: Dictionary<T>, iteratees: Any, orders: dynamic /* Boolean? | String? | Array<dynamic /* Boolean | String */>? */ = definedExternally /* null */): Array<T>
    fun <T> sortByOrder(collection: Dictionary<T>, iteratees: Array<dynamic /* DictionaryIterator<T, Any> | String | Any */>, orders: dynamic /* Boolean? | String? | Array<dynamic /* Boolean | String */>? */ = definedExternally /* null */): Array<T>
    fun <T, U : Any> where(list: Array<T>, properties: U): Array<T>
    fun <T, U : Any> where(list: List<T>, properties: U): Array<T>
    fun <T, U : Any> where(list: Dictionary<T>, properties: U): Array<T>
    fun now(): Number
    fun <TFunc : Function<*>> after(n: Number, func: TFunc): TFunc
    fun <TResult : Function<*>> ary(func: Function<*>, n: Number? = definedExternally /* null */): TResult
    fun <T : Function<*>, TResult : Function<*>> ary(func: T, n: Number? = definedExternally /* null */): TResult
    fun <TResult : Function<*>> backflow(vararg funcs: Function<*>): TResult
    fun <TFunc : Function<*>> before(n: Number, func: TFunc): TFunc
    var bind: FunctionBind
    fun <T> bindAll(`object`: T, vararg methodNames: dynamic /* String | Array<String> */): T
    var bindKey: FunctionBindKey
    fun <TResult : Function<*>> compose(vararg funcs: Function<*>): TResult
    fun createCallback(func: String, thisArg: Any? = definedExternally /* null */, argCount: Number? = definedExternally /* null */): () -> Any
    fun createCallback(func: Dictionary<Any>, thisArg: Any? = definedExternally /* null */, argCount: Number? = definedExternally /* null */): () -> Boolean
    fun <T1, R> curry(func: (t1: T1) -> R): CurriedFunction1<T1, R>
    fun <T1, T2, R> curry(func: (t1: T1, t2: T2) -> R): CurriedFunction2<T1, T2, R>
    fun <T1, T2, T3, R> curry(func: (t1: T1, t2: T2, t3: T3) -> R): CurriedFunction3<T1, T2, T3, R>
    fun <T1, T2, T3, T4, R> curry(func: (t1: T1, t2: T2, t3: T3, t4: T4) -> R): CurriedFunction4<T1, T2, T3, T4, R>
    fun <T1, T2, T3, T4, T5, R> curry(func: (t1: T1, t2: T2, t3: T3, t4: T4, t5: T5) -> R): CurriedFunction5<T1, T2, T3, T4, T5, R>
    fun <TResult : Function<*>> curry(func: Function<*>, arity: Number? = definedExternally /* null */): TResult
    fun <T1, R> curryRight(func: (t1: T1) -> R): CurriedFunction1<T1, R>
    fun <T1, T2, R> curryRight(func: (t1: T1, t2: T2) -> R): CurriedFunction2<T2, T1, R>
    fun <T1, T2, T3, R> curryRight(func: (t1: T1, t2: T2, t3: T3) -> R): CurriedFunction3<T3, T2, T1, R>
    fun <T1, T2, T3, T4, R> curryRight(func: (t1: T1, t2: T2, t3: T3, t4: T4) -> R): CurriedFunction4<T4, T3, T2, T1, R>
    fun <T1, T2, T3, T4, T5, R> curryRight(func: (t1: T1, t2: T2, t3: T3, t4: T4, t5: T5) -> R): CurriedFunction5<T5, T4, T3, T2, T1, R>
    fun <TResult : Function<*>> curryRight(func: Function<*>, arity: Number? = definedExternally /* null */): TResult
    fun <T : Function<*>> debounce(func: T, wait: Number? = definedExternally /* null */, options: DebounceSettings? = definedExternally /* null */): T /* T & Cancelable */
    fun <T : Function<*>> defer(func: T, vararg args: Any): Number
    fun <T : Function<*>> delay(func: T, wait: Number, vararg args: Any): Number
    fun <TResult : Function<*>> flow(vararg funcs: Function<*>): TResult
    fun <TResult : Function<*>> flowRight(vararg funcs: Function<*>): TResult
    var memoize: `T$0`
    fun <T : Function<*>, TResult : Function<*>> modArgs(func: T, vararg transforms: Function<*>): TResult
    fun <T : Function<*>, TResult : Function<*>> modArgs(func: T, transforms: Array<Function<*>>): TResult
    fun <T : Function<*>> negate(predicate: T): (args: Any) -> Boolean
    fun <T : Function<*>, TResult : Function<*>> negate(predicate: T): TResult
    fun <T : Function<*>> once(func: T): T
    var partial: Partial
    var partialRight: PartialRight
    fun <TResult : Function<*>> rearg(func: Function<*>, indexes: Array<Number>): TResult
    fun <TResult : Function<*>> rearg(func: Function<*>, vararg indexes: Number): TResult
    fun <TResult : Function<*>> restParam(func: Function<*>, start: Number? = definedExternally /* null */): TResult
    fun <TResult : Function<*>, TFunc : Function<*>> restParam(func: TFunc, start: Number? = definedExternally /* null */): TResult
    fun <F : Function<*>, T : Function<*>> spread(func: F): T
    fun <T : Function<*>> spread(func: Function<*>): T
    fun <T : Function<*>> throttle(func: T, wait: Number? = definedExternally /* null */, options: ThrottleSettings? = definedExternally /* null */): T /* T & Cancelable */
    fun <V, W : Function<*>, R : Function<*>> wrap(value: V, wrapper: W): R
    fun <V, R : Function<*>> wrap(value: V, wrapper: Function<*>): R
    fun <R : Function<*>> wrap(value: Any, wrapper: Function<*>): R
    fun <TResult> clone(value: Any, isDeep: Boolean, customizer: CloneCustomizer<Any, TResult>, thisArg: Any? = definedExternally /* null */): TResult
    fun <T, TResult> clone(value: T, isDeep: Boolean, customizer: CloneCustomizer<T, TResult>, thisArg: Any? = definedExternally /* null */): TResult
    fun <TResult> clone(value: Any, customizer: CloneCustomizer<Any, TResult>, thisArg: Any? = definedExternally /* null */): TResult
    fun <T, TResult> clone(value: T, customizer: CloneCustomizer<T, TResult>, thisArg: Any? = definedExternally /* null */): TResult
    fun <T> clone(value: T, isDeep: Boolean? = definedExternally /* null */): T
    fun <TResult> cloneDeep(value: Any, customizer: CloneDeepCustomizer<Any, TResult>, thisArg: Any? = definedExternally /* null */): TResult
    fun <T, TResult> cloneDeep(value: T, customizer: CloneDeepCustomizer<T, TResult>, thisArg: Any? = definedExternally /* null */): TResult
    fun <T> cloneDeep(value: T): T
    fun eq(value: Any, other: Any, customizer: IsEqualCustomizer? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Boolean
    fun gt(value: Any, other: Any): Boolean
    fun gte(value: Any, other: Any): Boolean
    fun isArguments(value: Any? = definedExternally /* null */): Boolean
    fun <T> isArray(value: Any? = definedExternally /* null */): Boolean
    fun isBoolean(value: Any? = definedExternally /* null */): Boolean
    fun isDate(value: Any? = definedExternally /* null */): Boolean
    fun isElement(value: Any? = definedExternally /* null */): Boolean
    fun isEmpty(value: Any? = definedExternally /* null */): Boolean
    fun isEqual(value: Any, other: Any, customizer: IsEqualCustomizer? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Boolean
    fun isError(value: Any): Boolean
    fun isFinite(value: Any? = definedExternally /* null */): Boolean
    fun isFunction(value: Any? = definedExternally /* null */): Boolean
    fun isMatch(`object`: Any, source: Any, customizer: isMatchCustomizer? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Boolean
    fun isNaN(value: Any? = definedExternally /* null */): Boolean
    fun isNative(value: Any): Boolean
    fun isNull(value: Any? = definedExternally /* null */): Boolean
    fun isNumber(value: Any? = definedExternally /* null */): Boolean
    fun isObject(value: Any? = definedExternally /* null */): Boolean
    fun isPlainObject(value: Any? = definedExternally /* null */): Boolean
    fun isRegExp(value: Any? = definedExternally /* null */): Boolean
    fun isString(value: Any? = definedExternally /* null */): Boolean
    fun isTypedArray(value: Any): Boolean
    fun isUndefined(value: Any): Boolean
    fun lt(value: Any, other: Any): Boolean
    fun lte(value: Any, other: Any): Boolean
    fun <T> toArray(value: List<T>): Array<T>
    fun <T> toArray(value: Dictionary<T>): Array<T>
    fun <T> toArray(value: NumericDictionary<T>): Array<T>
    fun <TValue, TResult> toArray(value: TValue): Array<TResult>
    fun <TResult> toArray(value: Any? = definedExternally /* null */): Array<TResult>
    fun <TResult : Any> toPlainObject(value: Any? = definedExternally /* null */): TResult
    fun add(augend: Number, addend: Number): Number
    fun ceil(n: Number, precision: Number? = definedExternally /* null */): Number
    fun floor(n: Number, precision: Number? = definedExternally /* null */): Number
    fun <T> max(collection: List<T>, iteratee: ListIterator<T, Any>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): T
    fun <T> max(collection: Dictionary<T>, iteratee: DictionaryIterator<T, Any>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): T
    fun <T> max(collection: List<T>, iteratee: String? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): T
    fun <T> max(collection: Dictionary<T>, iteratee: String? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): T
    fun <TObject : Any, T> max(collection: List<T>, whereValue: TObject? = definedExternally /* null */): T
    fun <TObject : Any, T> max(collection: Dictionary<T>, whereValue: TObject? = definedExternally /* null */): T
    fun <T> min(collection: List<T>, iteratee: ListIterator<T, Any>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): T
    fun <T> min(collection: Dictionary<T>, iteratee: DictionaryIterator<T, Any>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): T
    fun <T> min(collection: List<T>, iteratee: String? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): T
    fun <T> min(collection: Dictionary<T>, iteratee: String? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): T
    fun <TObject : Any, T> min(collection: List<T>, whereValue: TObject? = definedExternally /* null */): T
    fun <TObject : Any, T> min(collection: Dictionary<T>, whereValue: TObject? = definedExternally /* null */): T
    fun round(n: Number, precision: Number? = definedExternally /* null */): Number
    fun <T> sum(collection: List<T>, iteratee: ListIterator<T, Number>, thisArg: Any? = definedExternally /* null */): Number
    fun <T> sum(collection: Dictionary<T>, iteratee: DictionaryIterator<T, Number>, thisArg: Any? = definedExternally /* null */): Number
    fun sum(collection: List<Any>, iteratee: String): Number
    fun sum(collection: Dictionary<Any>, iteratee: String): Number
    fun <T> sum(collection: List<T>): Number
    fun <T> sum(collection: Dictionary<T>): Number
    fun sum(collection: List<Number>): Number
    fun sum(collection: Dictionary<Number>): Number
    fun inRange(n: Number, start: Number, end: Number): Boolean
    fun inRange(n: Number, end: Number): Boolean
    fun random(min: Number? = definedExternally /* null */, max: Number? = definedExternally /* null */, floating: Boolean? = definedExternally /* null */): Number
    fun random(min: Number? = definedExternally /* null */, floating: Boolean? = definedExternally /* null */): Number
    fun random(floating: Boolean? = definedExternally /* null */): Number
    fun <TObject : Any, TSource : Any, TResult : Any> assign(`object`: TObject, source: TSource, customizer: AssignCustomizer? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): TResult
    fun <TObject : Any, TSource1 : Any, TSource2 : Any, TResult : Any> assign(`object`: TObject, source1: TSource1, source2: TSource2, customizer: AssignCustomizer? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): TResult
    fun <TObject : Any, TSource1 : Any, TSource2 : Any, TSource3 : Any, TResult : Any> assign(`object`: TObject, source1: TSource1, source2: TSource2, source3: TSource3, customizer: AssignCustomizer? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): TResult
    fun <TObject : Any, TSource1 : Any, TSource2 : Any, TSource3 : Any, TSource4 : Any, TResult : Any> assign(`object`: TObject, source1: TSource1, source2: TSource2, source3: TSource3, source4: TSource4, customizer: AssignCustomizer? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): TResult
    fun <TObject : Any> assign(`object`: TObject): TObject
    fun <TObject : Any, TResult : Any> assign(`object`: TObject, vararg otherArgs: Any): TResult
    fun <T : Any, U : Any> create(prototype: T, properties: U? = definedExternally /* null */): T /* T & U */
    fun <Obj : Any, TResult : Any> defaults(`object`: Obj, vararg sources: Any): TResult
    fun <Obj : Any, S1 : Any, TResult : Any> defaults(`object`: Obj, source1: S1, vararg sources: Any): TResult
    fun <Obj : Any, S1 : Any, S2 : Any, TResult : Any> defaults(`object`: Obj, source1: S1, source2: S2, vararg sources: Any): TResult
    fun <Obj : Any, S1 : Any, S2 : Any, S3 : Any, TResult : Any> defaults(`object`: Obj, source1: S1, source2: S2, source3: S3, vararg sources: Any): TResult
    fun <Obj : Any, S1 : Any, S2 : Any, S3 : Any, S4 : Any, TResult : Any> defaults(`object`: Obj, source1: S1, source2: S2, source3: S3, source4: S4, vararg sources: Any): TResult
    fun <TResult : Any> defaults(`object`: Any, vararg sources: Any): TResult
    fun <T, TResult> defaultsDeep(`object`: T, vararg sources: Any): TResult
    fun <TObject : Any, TSource : Any, TResult : Any> extend(`object`: TObject, source: TSource, customizer: AssignCustomizer? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): TResult
    fun <TObject : Any, TSource1 : Any, TSource2 : Any, TResult : Any> extend(`object`: TObject, source1: TSource1, source2: TSource2, customizer: AssignCustomizer? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): TResult
    fun <TObject : Any, TSource1 : Any, TSource2 : Any, TSource3 : Any, TResult : Any> extend(`object`: TObject, source1: TSource1, source2: TSource2, source3: TSource3, customizer: AssignCustomizer? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): TResult
    fun <TObject : Any, TSource1 : Any, TSource2 : Any, TSource3 : Any, TSource4 : Any, TResult : Any> extend(`object`: TObject, source1: TSource1, source2: TSource2, source3: TSource3, source4: TSource4, customizer: AssignCustomizer? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): TResult
    fun <TObject : Any> extend(`object`: TObject): TObject
    fun <TObject : Any, TResult : Any> extend(`object`: TObject, vararg otherArgs: Any): TResult
    fun <TValues, TObject> findKey(`object`: TObject, predicate: DictionaryIterator<TValues, Boolean>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): String
    fun <TObject> findKey(`object`: TObject, predicate: ObjectIterator<Any, Boolean>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): String
    fun <TObject> findKey(`object`: TObject, predicate: String? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): String
    fun <TWhere : Dictionary<Any>, TObject> findKey(`object`: TObject, predicate: TWhere? = definedExternally /* null */): String
    fun <TValues, TObject> findLastKey(`object`: TObject, predicate: DictionaryIterator<TValues, Boolean>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): String
    fun <TObject> findLastKey(`object`: TObject, predicate: ObjectIterator<Any, Boolean>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): String
    fun <TObject> findLastKey(`object`: TObject, predicate: String? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): String
    fun <TWhere : Dictionary<Any>, TObject> findLastKey(`object`: TObject, predicate: TWhere? = definedExternally /* null */): String
    fun <T> forIn(`object`: Dictionary<T>, iteratee: DictionaryIterator<T, Any>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Dictionary<T>
    fun <T : Any> forIn(`object`: T, iteratee: ObjectIterator<Any, Any>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): T
    fun <T> forInRight(`object`: Dictionary<T>, iteratee: DictionaryIterator<T, Any>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Dictionary<T>
    fun <T : Any> forInRight(`object`: T, iteratee: ObjectIterator<Any, Any>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): T
    fun <T> forOwn(`object`: Dictionary<T>, iteratee: DictionaryIterator<T, Any>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Dictionary<T>
    fun <T : Any> forOwn(`object`: T, iteratee: ObjectIterator<Any, Any>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): T
    fun <T> forOwnRight(`object`: Dictionary<T>, iteratee: DictionaryIterator<T, Any>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Dictionary<T>
    fun <T : Any> forOwnRight(`object`: T, iteratee: ObjectIterator<Any, Any>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): T
    fun <T : Any> functions(`object`: Any): Array<String>
    fun <TObject, TResult> get(`object`: TObject, path: StringRepresentable, defaultValue: TResult? = definedExternally /* null */): TResult
    fun <TObject, TResult> get(`object`: TObject, path: Array<StringRepresentable>, defaultValue: TResult? = definedExternally /* null */): TResult
    fun <TResult> get(`object`: Any, path: StringRepresentable, defaultValue: TResult? = definedExternally /* null */): TResult
    fun <TResult> get(`object`: Any, path: Array<StringRepresentable>, defaultValue: TResult? = definedExternally /* null */): TResult
    fun <T : Any> has(`object`: T, path: StringRepresentable): Boolean
    fun <T : Any> has(`object`: T, path: Array<StringRepresentable>): Boolean
    fun <T : Any, TResult : Any> invert(`object`: T, multiValue: Boolean? = definedExternally /* null */): TResult
    fun <TResult : Any> invert(`object`: Any, multiValue: Boolean? = definedExternally /* null */): TResult
    fun keys(`object`: Any? = definedExternally /* null */): Array<String>
    fun keysIn(`object`: Any? = definedExternally /* null */): Array<String>
    fun <T, TKey> mapKeys(`object`: List<T>, iteratee: ListIterator<T, TKey>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Dictionary<T>
    fun <T, TKey> mapKeys(`object`: Dictionary<T>, iteratee: DictionaryIterator<T, TKey>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Dictionary<T>
    fun <T, TObject : Any> mapKeys(`object`: List<T>, iteratee: TObject? = definedExternally /* null */): Dictionary<T>
    fun <T, TObject : Any> mapKeys(`object`: Dictionary<T>, iteratee: TObject? = definedExternally /* null */): Dictionary<T>
    fun <T> mapKeys(`object`: List<T>, iteratee: String? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Dictionary<T>
    fun <T> mapKeys(`object`: Dictionary<T>, iteratee: String? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Dictionary<T>
    fun <T, TResult> mapValues(obj: Dictionary<T>, callback: ObjectIterator<T, TResult>, thisArg: Any? = definedExternally /* null */): Dictionary<TResult>
    fun <T> mapValues(obj: Dictionary<T>, where: Dictionary<T>): Dictionary<Boolean>
    fun <T, TMapped> mapValues(obj: T, pluck: String): TMapped
    fun <T> mapValues(obj: T, callback: ObjectIterator<Any, Any>, thisArg: Any? = definedExternally /* null */): T
    fun <TObject, TSource> merge(`object`: TObject, source: TSource, customizer: MergeCustomizer? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): TObject /* TObject & TSource */
    fun <TObject, TSource1, TSource2> merge(`object`: TObject, source1: TSource1, source2: TSource2, customizer: MergeCustomizer? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): TObject /* TObject & TSource1 & TSource2 */
    fun <TObject, TSource1, TSource2, TSource3> merge(`object`: TObject, source1: TSource1, source2: TSource2, source3: TSource3, customizer: MergeCustomizer? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): TObject /* TObject & TSource1 & TSource2 & TSource3 */
    fun <TObject, TSource1, TSource2, TSource3, TSource4> merge(`object`: TObject, source1: TSource1, source2: TSource2, source3: TSource3, source4: TSource4, customizer: MergeCustomizer? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): TObject /* TObject & TSource1 & TSource2 & TSource3 & TSource4 */
    fun <TResult> merge(`object`: Any, vararg otherArgs: Any): TResult
    fun <T : Any> methods(`object`: Any): Array<String>
    fun <TResult : Any, T : Any> omit(`object`: T, predicate: ObjectIterator<Any, Boolean>, thisArg: Any? = definedExternally /* null */): TResult
    fun <TResult : Any, T : Any> omit(`object`: T, vararg predicate: dynamic /* StringRepresentable | Array<StringRepresentable> */): TResult
    fun <T : Any> pairs(`object`: T? = definedExternally /* null */): Array<Array<Any>>
    fun <T : Any, TResult> pairs(`object`: T? = definedExternally /* null */): Array<Array<TResult>>
    fun <TResult : Any, T : Any> pick(`object`: T, predicate: ObjectIterator<Any, Boolean>, thisArg: Any? = definedExternally /* null */): TResult
    fun <TResult : Any, T : Any> pick(`object`: T, vararg predicate: dynamic /* StringRepresentable | Array<StringRepresentable> */): TResult
    fun <TObject, TResult> result(`object`: TObject, path: StringRepresentable, defaultValue: TResult? = definedExternally /* null */): TResult
    fun <TObject, TResult> result(`object`: TObject, path: StringRepresentable, defaultValue: (args: Any) -> TResult? = definedExternally /* null */): TResult
    fun <TObject, TResult> result(`object`: TObject, path: Array<StringRepresentable>, defaultValue: TResult? = definedExternally /* null */): TResult
    fun <TObject, TResult> result(`object`: TObject, path: Array<StringRepresentable>, defaultValue: (args: Any) -> TResult? = definedExternally /* null */): TResult
    fun <TResult> result(`object`: Any, path: StringRepresentable, defaultValue: TResult? = definedExternally /* null */): TResult
    fun <TResult> result(`object`: Any, path: StringRepresentable, defaultValue: (args: Any) -> TResult? = definedExternally /* null */): TResult
    fun <TResult> result(`object`: Any, path: Array<StringRepresentable>, defaultValue: TResult? = definedExternally /* null */): TResult
    fun <TResult> result(`object`: Any, path: Array<StringRepresentable>, defaultValue: (args: Any) -> TResult? = definedExternally /* null */): TResult
    fun <TResult> set(`object`: Any, path: StringRepresentable, value: Any): TResult
    fun <TResult> set(`object`: Any, path: Array<StringRepresentable>, value: Any): TResult
    fun <V, TResult> set(`object`: Any, path: StringRepresentable, value: V): TResult
    fun <V, TResult> set(`object`: Any, path: Array<StringRepresentable>, value: V): TResult
    fun <O, V, TResult> set(`object`: O, path: StringRepresentable, value: V): TResult
    fun <O, V, TResult> set(`object`: O, path: Array<StringRepresentable>, value: V): TResult
    fun <T, TResult> transform(`object`: Array<T>, iteratee: MemoVoidArrayIterator<T, Array<TResult>>? = definedExternally /* null */, accumulator: Array<TResult>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Array<TResult>
    fun <T, TResult> transform(`object`: Array<T>, iteratee: MemoVoidArrayIterator<T, Dictionary<TResult>>? = definedExternally /* null */, accumulator: Dictionary<TResult>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Dictionary<TResult>
    fun <T, TResult> transform(`object`: Dictionary<T>, iteratee: MemoVoidDictionaryIterator<T, Dictionary<TResult>>? = definedExternally /* null */, accumulator: Dictionary<TResult>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Dictionary<TResult>
    fun <T, TResult> transform(`object`: Dictionary<T>, iteratee: MemoVoidDictionaryIterator<T, Array<TResult>>? = definedExternally /* null */, accumulator: Array<TResult>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Array<TResult>
    fun <T> values(`object`: Any? = definedExternally /* null */): Array<T>
    fun <T> valuesIn(`object`: Any? = definedExternally /* null */): Array<T>
    fun camelCase(string: String? = definedExternally /* null */): String
    fun capitalize(string: String? = definedExternally /* null */): String
    fun deburr(string: String? = definedExternally /* null */): String
    fun endsWith(string: String? = definedExternally /* null */, target: String? = definedExternally /* null */, position: Number? = definedExternally /* null */): Boolean
    fun escape(string: String? = definedExternally /* null */): String
    fun escapeRegExp(string: String? = definedExternally /* null */): String
    fun kebabCase(string: String? = definedExternally /* null */): String
    fun pad(string: String? = definedExternally /* null */, length: Number? = definedExternally /* null */, chars: String? = definedExternally /* null */): String
    fun padLeft(string: String? = definedExternally /* null */, length: Number? = definedExternally /* null */, chars: String? = definedExternally /* null */): String
    fun padRight(string: String? = definedExternally /* null */, length: Number? = definedExternally /* null */, chars: String? = definedExternally /* null */): String
    fun parseInt(string: String, radix: Number? = definedExternally /* null */): Number
    fun repeat(string: String? = definedExternally /* null */, n: Number? = definedExternally /* null */): String
    fun snakeCase(string: String? = definedExternally /* null */): String
    fun startCase(string: String? = definedExternally /* null */): String
    fun startsWith(string: String? = definedExternally /* null */, target: String? = definedExternally /* null */, position: Number? = definedExternally /* null */): Boolean
    fun template(string: String, options: TemplateOptions? = definedExternally /* null */): TemplateExecutor
    fun trim(string: String? = definedExternally /* null */, chars: String? = definedExternally /* null */): String
    fun trimLeft(string: String? = definedExternally /* null */, chars: String? = definedExternally /* null */): String
    fun trimRight(string: String? = definedExternally /* null */, chars: String? = definedExternally /* null */): String
    fun trunc(string: String? = definedExternally /* null */, options: TruncOptions? = definedExternally /* null */): String
    fun trunc(string: String? = definedExternally /* null */, options: Number? = definedExternally /* null */): String
    fun unescape(string: String? = definedExternally /* null */): String
    fun words(string: String? = definedExternally /* null */, pattern: String? = definedExternally /* null */): Array<String>
    fun words(string: String? = definedExternally /* null */, pattern: RegExp? = definedExternally /* null */): Array<String>
    fun <TResult> attempt(func: (args: Any) -> TResult, vararg args: Any): dynamic /* TResult | Error */
    fun <TResult> callback(func: Function<*>, thisArg: Any? = definedExternally /* null */): (args: Any) -> TResult
    fun <TResult> callback(func: String, thisArg: Any? = definedExternally /* null */): (obj: Any) -> TResult
    fun callback(func: Any, thisArg: Any? = definedExternally /* null */): (obj: Any) -> Boolean
    fun <TResult> callback(): (value: TResult) -> TResult
    fun <T> constant(value: T): () -> T
    fun <T> identity(value: T? = definedExternally /* null */): T
    fun <TResult> iteratee(func: Function<*>, thisArg: Any? = definedExternally /* null */): (args: Any) -> TResult
    fun <TResult> iteratee(func: String, thisArg: Any? = definedExternally /* null */): (obj: Any) -> TResult
    fun iteratee(func: Any, thisArg: Any? = definedExternally /* null */): (obj: Any) -> Boolean
    fun <TResult> iteratee(): (value: TResult) -> TResult
    fun <T> matches(source: T): (value: Any) -> Boolean
    fun <T, V> matches(source: T): (value: V) -> Boolean
    fun <T> matchesProperty(path: StringRepresentable, srcValue: T): (value: Any) -> Boolean
    fun <T> matchesProperty(path: Array<StringRepresentable>, srcValue: T): (value: Any) -> Boolean
    fun <T, V> matchesProperty(path: StringRepresentable, srcValue: T): (value: V) -> Boolean
    fun <T, V> matchesProperty(path: Array<StringRepresentable>, srcValue: T): (value: V) -> Boolean
    fun <TObject, TResult> method(path: String, vararg args: Any): (obj: TObject) -> TResult
    fun <TObject, TResult> method(path: Array<StringRepresentable>, vararg args: Any): (obj: TObject) -> TResult
    fun <TResult> method(path: String, vararg args: Any): (obj: Any) -> TResult
    fun <TResult> method(path: Array<StringRepresentable>, vararg args: Any): (obj: Any) -> TResult
    fun <TObject : Any, TResult> methodOf(`object`: TObject, vararg args: Any): (path: dynamic /* StringRepresentable | Array<StringRepresentable> */) -> TResult
    fun <TResult> methodOf(`object`: Any, vararg args: Any): (path: dynamic /* StringRepresentable | Array<StringRepresentable> */) -> TResult
    fun <TResult, TObject> mixin(`object`: TObject, source: Dictionary<Function<*>>, options: MixinOptions? = definedExternally /* null */): TResult
    fun <TResult> mixin(source: Dictionary<Function<*>>, options: MixinOptions? = definedExternally /* null */): TResult
    fun noConflict(): dynamic /* "TypeQuery" kind unsupported yet here! (index.d.ts:15584:22 to 15584:31) */
    fun noop(vararg args: Any)
    fun <TObj, TResult> property(path: StringRepresentable): (obj: TObj) -> TResult
    fun <TObj, TResult> property(path: Array<StringRepresentable>): (obj: TObj) -> TResult
    fun <T : Any> propertyOf(`object`: T): (path: dynamic /* String | Array<String> */) -> Any
    fun range(start: Number, end: Number, step: Number? = definedExternally /* null */): Array<Number>
    fun range(end: Number, step: Number? = definedExternally /* null */): Array<Number>
    fun runInContext(context: Any? = definedExternally /* null */): dynamic /* "TypeQuery" kind unsupported yet here! (index.d.ts:15745:40 to 15745:49) */
    fun <TResult> times(n: Number, iteratee: (num: Number) -> TResult, thisArg: Any? = definedExternally /* null */): Array<TResult>
    fun times(n: Number): Array<Number>
    fun uniqueId(prefix: String? = definedExternally /* null */): String
}
external interface TemplateSettings {
    var escape: RegExp? get() = definedExternally; set(value) = definedExternally
    var evaluate: RegExp? get() = definedExternally; set(value) = definedExternally
    var imports: Dictionary<Any>? get() = definedExternally; set(value) = definedExternally
    var interpolate: RegExp? get() = definedExternally; set(value) = definedExternally
    var variable: String? get() = definedExternally; set(value) = definedExternally
}
external interface MapCache {
    fun delete(key: String): Boolean
    fun get(key: String): Any
    fun has(key: String): Boolean
    fun set(key: String, value: Any): lodash.Dictionary<Any>
}
external interface Support {
    var argsClass: Boolean
    var argsObject: Boolean
    var enumErrorProps: Boolean
    var enumPrototypes: Boolean
    var fastBind: Boolean
    var funcDecomp: Boolean
    var funcNames: Boolean
    var nonEnumArgs: Boolean
    var nonEnumShadows: Boolean
    var ownLast: Boolean
    var spliceObjects: Boolean
    var unindexedChars: Boolean
}
external interface LoDashWrapperBase<T, TWrapper> {
    fun run(): T
    fun toJSON(): T
    override fun toString(): String
    fun value(): T
    fun valueOf(): T
}
external interface LoDashImplicitWrapperBase<T, TWrapper> : LoDashWrapperBase<T, TWrapper> {
    fun tap(interceptor: (value: T) -> Unit, thisArg: Any? = definedExternally /* null */): TWrapper
    fun <TResult : Number> thru(interceptor: (value: T) -> TResult, thisArg: Any? = definedExternally /* null */): LoDashImplicitWrapper<TResult>
    fun <TResult : String> thru(interceptor: (value: T) -> TResult, thisArg: Any? = definedExternally /* null */): LoDashImplicitWrapper<TResult>
    fun <TResult : Boolean> thru(interceptor: (value: T) -> TResult, thisArg: Any? = definedExternally /* null */): LoDashImplicitWrapper<TResult>
    fun <TResult : Any> thru(interceptor: (value: T) -> TResult, thisArg: Any? = definedExternally /* null */): LoDashImplicitObjectWrapper<TResult>
    fun <TResult> thru(interceptor: (value: T) -> Array<TResult>, thisArg: Any? = definedExternally /* null */): LoDashImplicitArrayWrapper<TResult>
    fun commit(): TWrapper
    fun <TItem> concat(vararg items: TItem): LoDashImplicitArrayWrapper<TItem>
    fun <TItem> concat(vararg items: Array<TItem>): LoDashImplicitArrayWrapper<TItem>
    fun concat(vararg items: T): LoDashImplicitArrayWrapper<T>
    fun concat(vararg items: Array<T>): LoDashImplicitArrayWrapper<T>
    fun plant(value: Number): LoDashImplicitWrapper<Number>
    fun plant(value: String): LoDashImplicitStringWrapper
    fun plant(value: Boolean): LoDashImplicitWrapper<Boolean>
    fun plant(value: Array<Number>): LoDashImplicitNumberArrayWrapper
    fun <T> plant(value: Array<T>): LoDashImplicitArrayWrapper<T>
    fun <T : Any> plant(value: T): LoDashImplicitObjectWrapper<T>
    fun plant(value: Any): LoDashImplicitWrapper<Any>
    fun now(): Number
    fun eq(other: Any, customizer: IsEqualCustomizer? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Boolean
    fun gt(other: Any): Boolean
    fun gte(other: Any): Boolean
    fun isArguments(): Boolean
    fun isArray(): Boolean
    fun isBoolean(): Boolean
    fun isDate(): Boolean
    fun isElement(): Boolean
    fun isEmpty(): Boolean
    fun isEqual(other: Any, customizer: IsEqualCustomizer? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Boolean
    fun isError(): Boolean
    fun isFinite(): Boolean
    fun isFunction(): Boolean
    fun isNaN(): Boolean
    fun isNative(): Boolean
    fun isNull(): Boolean
    fun isNumber(): Boolean
    fun isObject(): Boolean
    fun isPlainObject(): Boolean
    fun isRegExp(): Boolean
    fun isString(): Boolean
    fun isTypedArray(): Boolean
    fun isUndefined(): Boolean
    fun lt(other: Any): Boolean
    fun lte(other: Any): Boolean
    fun <TResult : Any> toPlainObject(): LoDashImplicitObjectWrapper<TResult>
    fun <TResult> constant(): LoDashImplicitObjectWrapper<() -> TResult>
    fun <V> matches(): LoDashImplicitObjectWrapper<(value: V) -> Boolean>
    fun <SrcValue> matchesProperty(srcValue: SrcValue): LoDashImplicitObjectWrapper<(value: Any) -> Boolean>
    fun <SrcValue, Value> matchesProperty(srcValue: SrcValue): LoDashImplicitObjectWrapper<(value: Value) -> Boolean>
    fun noConflict(): dynamic /* "TypeQuery" kind unsupported yet here! (index.d.ts:15591:22 to 15591:31) */
    fun noop(vararg args: Any)
}
external interface LoDashExplicitWrapperBase<T, TWrapper> : LoDashWrapperBase<T, TWrapper> {
    fun chain(): TWrapper
    fun tap(interceptor: (value: T) -> Unit, thisArg: Any? = definedExternally /* null */): TWrapper
    fun <TResult : Number> thru(interceptor: (value: T) -> TResult, thisArg: Any? = definedExternally /* null */): LoDashExplicitWrapper<TResult>
    fun <TResult : String> thru(interceptor: (value: T) -> TResult, thisArg: Any? = definedExternally /* null */): LoDashExplicitWrapper<TResult>
    fun <TResult : Boolean> thru(interceptor: (value: T) -> TResult, thisArg: Any? = definedExternally /* null */): LoDashExplicitWrapper<TResult>
    fun <TResult : Any> thru(interceptor: (value: T) -> TResult, thisArg: Any? = definedExternally /* null */): LoDashExplicitObjectWrapper<TResult>
    fun <TResult> thru(interceptor: (value: T) -> Array<TResult>, thisArg: Any? = definedExternally /* null */): LoDashExplicitArrayWrapper<TResult>
    fun commit(): TWrapper
    fun <TItem> concat(vararg items: TItem): LoDashExplicitArrayWrapper<TItem>
    fun <TItem> concat(vararg items: Array<TItem>): LoDashExplicitArrayWrapper<TItem>
    fun concat(vararg items: T): LoDashExplicitArrayWrapper<T>
    fun concat(vararg items: Array<T>): LoDashExplicitArrayWrapper<T>
    fun plant(value: Number): LoDashExplicitWrapper<Number>
    fun plant(value: String): LoDashExplicitStringWrapper
    fun plant(value: Boolean): LoDashExplicitWrapper<Boolean>
    fun plant(value: Array<Number>): LoDashExplicitNumberArrayWrapper
    fun <T> plant(value: Array<T>): LoDashExplicitArrayWrapper<T>
    fun <T : Any> plant(value: T): LoDashExplicitObjectWrapper<T>
    fun plant(value: Any): LoDashExplicitWrapper<Any>
    fun now(): LoDashExplicitWrapper<Number>
    fun eq(other: Any, customizer: IsEqualCustomizer? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashExplicitWrapper<Boolean>
    fun gt(other: Any): LoDashExplicitWrapper<Boolean>
    fun gte(other: Any): LoDashExplicitWrapper<Boolean>
    fun isArguments(): LoDashExplicitWrapper<Boolean>
    fun isArray(): LoDashExplicitWrapper<Boolean>
    fun isBoolean(): LoDashExplicitWrapper<Boolean>
    fun isDate(): LoDashExplicitWrapper<Boolean>
    fun isElement(): LoDashExplicitWrapper<Boolean>
    fun isEmpty(): LoDashExplicitWrapper<Boolean>
    fun isEqual(other: Any, customizer: IsEqualCustomizer? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashExplicitWrapper<Boolean>
    fun isError(): LoDashExplicitWrapper<Boolean>
    fun isFinite(): LoDashExplicitWrapper<Boolean>
    fun isFunction(): LoDashExplicitWrapper<Boolean>
    fun isNaN(): LoDashExplicitWrapper<Boolean>
    fun isNative(): LoDashExplicitWrapper<Boolean>
    fun isNull(): LoDashExplicitWrapper<Boolean>
    fun isNumber(): LoDashExplicitWrapper<Boolean>
    fun isObject(): LoDashExplicitWrapper<Boolean>
    fun isPlainObject(): LoDashExplicitWrapper<Boolean>
    fun isRegExp(): LoDashExplicitWrapper<Boolean>
    fun isString(): LoDashExplicitWrapper<Boolean>
    fun isTypedArray(): LoDashExplicitWrapper<Boolean>
    fun isUndefined(): LoDashExplicitWrapper<Boolean>
    fun lt(other: Any): LoDashExplicitWrapper<Boolean>
    fun lte(other: Any): LoDashExplicitWrapper<Boolean>
    fun <TResult> constant(): LoDashExplicitObjectWrapper<() -> TResult>
    fun <V> matches(): LoDashExplicitObjectWrapper<(value: V) -> Boolean>
    fun <SrcValue> matchesProperty(srcValue: SrcValue): LoDashExplicitObjectWrapper<(value: Any) -> Boolean>
    fun <SrcValue, Value> matchesProperty(srcValue: SrcValue): LoDashExplicitObjectWrapper<(value: Value) -> Boolean>
    fun noConflict(): LoDashExplicitObjectWrapper<dynamic /* "TypeQuery" kind unsupported yet here! (index.d.ts:15598:51 to 15598:59) */>
    fun noop(vararg args: Any): lodash.LoDashExplicitWrapper<Unit>
}
external interface LoDashImplicitWrapper<T> : LoDashImplicitWrapperBase<T, LoDashImplicitWrapper<T>> {
    fun join(separator: String? = definedExternally /* null */): String
    fun first(): String
    fun flatten(): LoDashImplicitArrayWrapper<String>
    fun flattenDeep(): LoDashImplicitArrayWrapper<String>
    fun head(): String
    fun last(): String
    fun <TSort> sortedIndex(value: String, iteratee: ((x: String) -> TSort)? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Number
    fun <TSort> sortedLastIndex(value: String, iteratee: ((x: String) -> TSort)? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Number
    fun <TSort> uniq(isSorted: Boolean? = definedExternally /* null */, iteratee: ListIterator<T, TSort>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashImplicitArrayWrapper<T>
    fun <TSort> uniq(iteratee: ListIterator<T, TSort>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashImplicitArrayWrapper<T>
    fun <TSort> unique(isSorted: Boolean? = definedExternally /* null */, iteratee: ListIterator<T, TSort>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashImplicitArrayWrapper<T>
    fun <TSort> unique(iteratee: ListIterator<T, TSort>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashImplicitArrayWrapper<T>
    fun chain(): LoDashExplicitWrapper<T>
    fun contains(target: String, fromIndex: Number? = definedExternally /* null */): Boolean
    fun countBy(iteratee: ListIterator<T, Any>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashImplicitObjectWrapper<Dictionary<Number>>
    fun each(iteratee: ListIterator<String, Any>, thisArg: Any? = definedExternally /* null */): LoDashImplicitWrapper<String>
    fun eachRight(iteratee: ListIterator<String, Any>, thisArg: Any? = definedExternally /* null */): LoDashImplicitWrapper<String>
    fun filter(predicate: StringIterator<Boolean>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashImplicitArrayWrapper<String>
    fun forEach(iteratee: ListIterator<String, Any>, thisArg: Any? = definedExternally /* null */): LoDashImplicitWrapper<String>
    fun forEachRight(iteratee: ListIterator<String, Any>, thisArg: Any? = definedExternally /* null */): LoDashImplicitWrapper<String>
    fun <TKey> groupBy(iteratee: ListIterator<T, TKey>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashImplicitObjectWrapper<Dictionary<Array<T>>>
    fun include(target: String, fromIndex: Number? = definedExternally /* null */): Boolean
    fun includes(target: String, fromIndex: Number? = definedExternally /* null */): Boolean
    fun indexBy(iteratee: ListIterator<T, Any>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashImplicitObjectWrapper<Dictionary<T>>
    fun reject(predicate: StringIterator<Boolean>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashImplicitArrayWrapper<String>
    fun select(predicate: StringIterator<Boolean>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashImplicitArrayWrapper<String>
    fun sample(n: Number): LoDashImplicitArrayWrapper<String>
    fun sample(): String
    fun shuffle(): LoDashImplicitArrayWrapper<String>
    fun size(): Number
    fun sortByOrder(iteratees: ListIterator<T, Any>, orders: Boolean? = definedExternally /* null */): LoDashImplicitArrayWrapper<T>
    fun sortByOrder(iteratees: ListIterator<T, Any>, orders: String? = definedExternally /* null */): LoDashImplicitArrayWrapper<T>
    fun sortByOrder(iteratees: ListIterator<T, Any>, orders: Array<dynamic /* Boolean | String */>? = definedExternally /* null */): LoDashImplicitArrayWrapper<T>
    fun sortByOrder(iteratees: String, orders: Boolean? = definedExternally /* null */): LoDashImplicitArrayWrapper<T>
    fun sortByOrder(iteratees: String, orders: String? = definedExternally /* null */): LoDashImplicitArrayWrapper<T>
    fun sortByOrder(iteratees: String, orders: Array<dynamic /* Boolean | String */>? = definedExternally /* null */): LoDashImplicitArrayWrapper<T>
    fun sortByOrder(iteratees: Array<dynamic /* ListIterator<T, Any> | String */>, orders: Boolean? = definedExternally /* null */): LoDashImplicitArrayWrapper<T>
    fun sortByOrder(iteratees: Array<dynamic /* ListIterator<T, Any> | String */>, orders: String? = definedExternally /* null */): LoDashImplicitArrayWrapper<T>
    fun sortByOrder(iteratees: Array<dynamic /* ListIterator<T, Any> | String */>, orders: Array<dynamic /* Boolean | String */>? = definedExternally /* null */): LoDashImplicitArrayWrapper<T>
    fun <TFunc : Function<*>> after(func: TFunc): LoDashImplicitObjectWrapper<TFunc>
    fun <TFunc : Function<*>> before(func: TFunc): LoDashImplicitObjectWrapper<TFunc>
    fun createCallback(thisArg: Any? = definedExternally /* null */, argCount: Number? = definedExternally /* null */): LoDashImplicitObjectWrapper<() -> Any>
    fun <W : Function<*>, R : Function<*>> wrap(wrapper: W): LoDashImplicitObjectWrapper<R>
    fun <R : Function<*>> wrap(wrapper: Function<*>): LoDashImplicitObjectWrapper<R>
    fun <TResult> clone(isDeep: Boolean, customizer: CloneCustomizer<T, TResult>, thisArg: Any? = definedExternally /* null */): TResult
    fun <TResult> clone(customizer: CloneCustomizer<T, TResult>, thisArg: Any? = definedExternally /* null */): TResult
    fun clone(isDeep: Boolean? = definedExternally /* null */): T
    fun <TResult> cloneDeep(customizer: CloneDeepCustomizer<T, TResult>, thisArg: Any? = definedExternally /* null */): TResult
    fun cloneDeep(): T
    fun <TResult> toArray(): LoDashImplicitArrayWrapper<TResult>
    fun add(addend: Number): Number
    fun ceil(precision: Number? = definedExternally /* null */): Number
    fun floor(precision: Number? = definedExternally /* null */): Number
    fun round(precision: Number? = definedExternally /* null */): Number
    fun inRange(start: Number, end: Number): Boolean
    fun inRange(end: Number): Boolean
    fun random(max: Number? = definedExternally /* null */, floating: Boolean? = definedExternally /* null */): Number
    fun random(floating: Boolean? = definedExternally /* null */): Number
    fun <TResult> get(path: StringRepresentable, defaultValue: TResult? = definedExternally /* null */): TResult
    fun <TResult> get(path: Array<StringRepresentable>, defaultValue: TResult? = definedExternally /* null */): TResult
    fun <TResult> result(path: StringRepresentable, defaultValue: TResult? = definedExternally /* null */): TResult
    fun <TResult> result(path: StringRepresentable, defaultValue: (args: Any) -> TResult? = definedExternally /* null */): TResult
    fun <TResult> result(path: Array<StringRepresentable>, defaultValue: TResult? = definedExternally /* null */): TResult
    fun <TResult> result(path: Array<StringRepresentable>, defaultValue: (args: Any) -> TResult? = definedExternally /* null */): TResult
    fun camelCase(): String
    fun capitalize(): String
    fun deburr(): String
    fun endsWith(target: String? = definedExternally /* null */, position: Number? = definedExternally /* null */): Boolean
    fun escape(): String
    fun escapeRegExp(): String
    fun kebabCase(): String
    fun pad(length: Number? = definedExternally /* null */, chars: String? = definedExternally /* null */): String
    fun padLeft(length: Number? = definedExternally /* null */, chars: String? = definedExternally /* null */): String
    fun padRight(length: Number? = definedExternally /* null */, chars: String? = definedExternally /* null */): String
    fun parseInt(radix: Number? = definedExternally /* null */): Number
    fun repeat(n: Number? = definedExternally /* null */): String
    fun snakeCase(): String
    fun split(separator: RegExp? = definedExternally /* null */, limit: Number? = definedExternally /* null */): LoDashImplicitArrayWrapper<String>
    fun split(separator: String? = definedExternally /* null */, limit: Number? = definedExternally /* null */): LoDashImplicitArrayWrapper<String>
    fun startCase(): String
    fun startsWith(target: String? = definedExternally /* null */, position: Number? = definedExternally /* null */): Boolean
    fun template(options: TemplateOptions? = definedExternally /* null */): TemplateExecutor
    fun trim(chars: String? = definedExternally /* null */): String
    fun trimLeft(chars: String? = definedExternally /* null */): String
    fun trimRight(chars: String? = definedExternally /* null */): String
    fun trunc(options: TruncOptions? = definedExternally /* null */): String
    fun trunc(options: Number? = definedExternally /* null */): String
    fun unescape(): String
    fun words(pattern: String? = definedExternally /* null */): Array<String>
    fun words(pattern: RegExp? = definedExternally /* null */): Array<String>
    fun <TResult> callback(thisArg: Any? = definedExternally /* null */): LoDashImplicitObjectWrapper<(obj: Any) -> TResult>
    fun identity(): T
    fun <TResult> iteratee(thisArg: Any? = definedExternally /* null */): LoDashImplicitObjectWrapper<(obj: Any) -> TResult>
    fun <TObject, TResult> method(vararg args: Any): LoDashImplicitObjectWrapper<(obj: TObject) -> TResult>
    fun <TResult> method(vararg args: Any): LoDashImplicitObjectWrapper<(obj: Any) -> TResult>
    fun <TObj, TResult> property(): LoDashImplicitObjectWrapper<(obj: TObj) -> TResult>
    fun range(end: Number? = definedExternally /* null */, step: Number? = definedExternally /* null */): LoDashImplicitArrayWrapper<Number>
    fun <TResult> times(iteratee: (num: Number) -> TResult, thisArgs: Any? = definedExternally /* null */): LoDashImplicitArrayWrapper<TResult>
    fun times(): LoDashImplicitArrayWrapper<Number>
    fun uniqueId(): String
}
external interface LoDashExplicitWrapper<T> : LoDashExplicitWrapperBase<T, LoDashExplicitWrapper<T>> {
    fun join(separator: String? = definedExternally /* null */): LoDashExplicitWrapper<String>
    fun first(): LoDashExplicitWrapper<String>
    fun flatten(): LoDashExplicitArrayWrapper<String>
    fun flattenDeep(): LoDashExplicitArrayWrapper<String>
    fun head(): LoDashExplicitWrapper<String>
    fun last(): LoDashExplicitWrapper<String>
    fun <TSort> sortedIndex(value: String, iteratee: ((x: String) -> TSort)? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashExplicitWrapper<Number>
    fun <TSort> sortedLastIndex(value: String, iteratee: ((x: String) -> TSort)? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashExplicitWrapper<Number>
    fun <TSort> uniq(isSorted: Boolean? = definedExternally /* null */, iteratee: ListIterator<T, TSort>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashExplicitArrayWrapper<T>
    fun <TSort> uniq(iteratee: ListIterator<T, TSort>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashExplicitArrayWrapper<T>
    fun <TSort> unique(isSorted: Boolean? = definedExternally /* null */, iteratee: ListIterator<T, TSort>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashExplicitArrayWrapper<T>
    fun <TSort> unique(iteratee: ListIterator<T, TSort>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashExplicitArrayWrapper<T>
    fun contains(target: String, fromIndex: Number? = definedExternally /* null */): LoDashExplicitWrapper<Boolean>
    fun countBy(iteratee: ListIterator<T, Any>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashExplicitObjectWrapper<Dictionary<Number>>
    fun each(iteratee: ListIterator<String, Any>, thisArg: Any? = definedExternally /* null */): LoDashExplicitWrapper<String>
    fun eachRight(iteratee: ListIterator<String, Any>, thisArg: Any? = definedExternally /* null */): LoDashExplicitWrapper<String>
    fun filter(predicate: StringIterator<Boolean>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashExplicitArrayWrapper<String>
    fun forEach(iteratee: ListIterator<String, Any>, thisArg: Any? = definedExternally /* null */): LoDashExplicitWrapper<String>
    fun forEachRight(iteratee: ListIterator<String, Any>, thisArg: Any? = definedExternally /* null */): LoDashExplicitWrapper<String>
    fun <TKey> groupBy(iteratee: ListIterator<T, TKey>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashExplicitObjectWrapper<Dictionary<Array<T>>>
    fun include(target: String, fromIndex: Number? = definedExternally /* null */): LoDashExplicitWrapper<Boolean>
    fun includes(target: String, fromIndex: Number? = definedExternally /* null */): LoDashExplicitWrapper<Boolean>
    fun indexBy(iteratee: ListIterator<T, Any>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashExplicitObjectWrapper<Dictionary<T>>
    fun reject(predicate: StringIterator<Boolean>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashExplicitArrayWrapper<String>
    fun select(predicate: StringIterator<Boolean>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashExplicitArrayWrapper<String>
    fun sample(n: Number): LoDashExplicitArrayWrapper<String>
    fun sample(): LoDashExplicitWrapper<String>
    fun shuffle(): LoDashExplicitArrayWrapper<String>
    fun size(): LoDashExplicitWrapper<Number>
    fun sortByOrder(iteratees: ListIterator<T, Any>, orders: Boolean? = definedExternally /* null */): LoDashExplicitArrayWrapper<T>
    fun sortByOrder(iteratees: ListIterator<T, Any>, orders: String? = definedExternally /* null */): LoDashExplicitArrayWrapper<T>
    fun sortByOrder(iteratees: ListIterator<T, Any>, orders: Array<dynamic /* Boolean | String */>? = definedExternally /* null */): LoDashExplicitArrayWrapper<T>
    fun sortByOrder(iteratees: String, orders: Boolean? = definedExternally /* null */): LoDashExplicitArrayWrapper<T>
    fun sortByOrder(iteratees: String, orders: String? = definedExternally /* null */): LoDashExplicitArrayWrapper<T>
    fun sortByOrder(iteratees: String, orders: Array<dynamic /* Boolean | String */>? = definedExternally /* null */): LoDashExplicitArrayWrapper<T>
    fun sortByOrder(iteratees: Array<dynamic /* ListIterator<T, Any> | String */>, orders: Boolean? = definedExternally /* null */): LoDashExplicitArrayWrapper<T>
    fun sortByOrder(iteratees: Array<dynamic /* ListIterator<T, Any> | String */>, orders: String? = definedExternally /* null */): LoDashExplicitArrayWrapper<T>
    fun sortByOrder(iteratees: Array<dynamic /* ListIterator<T, Any> | String */>, orders: Array<dynamic /* Boolean | String */>? = definedExternally /* null */): LoDashExplicitArrayWrapper<T>
    fun <TFunc : Function<*>> after(func: TFunc): LoDashExplicitObjectWrapper<TFunc>
    fun <TFunc : Function<*>> before(func: TFunc): LoDashExplicitObjectWrapper<TFunc>
    fun <W : Function<*>, R : Function<*>> wrap(wrapper: W): LoDashExplicitObjectWrapper<R>
    fun <R : Function<*>> wrap(wrapper: Function<*>): LoDashExplicitObjectWrapper<R>
    fun <TResult /* Number | String | Boolean */> clone(isDeep: Boolean, customizer: CloneCustomizer<T, TResult>, thisArg: Any? = definedExternally /* null */): LoDashExplicitWrapper<TResult>
    fun <TResult> clone(isDeep: Boolean, customizer: CloneCustomizer<T, Array<TResult>>, thisArg: Any? = definedExternally /* null */): LoDashExplicitArrayWrapper<TResult>
    fun <TResult : Any> clone(isDeep: Boolean, customizer: CloneCustomizer<T, TResult>, thisArg: Any? = definedExternally /* null */): LoDashExplicitObjectWrapper<TResult>
    fun <TResult /* Number | String | Boolean */> clone(customizer: CloneCustomizer<T, TResult>, thisArg: Any? = definedExternally /* null */): LoDashExplicitWrapper<TResult>
    fun <TResult> clone(customizer: CloneCustomizer<T, Array<TResult>>, thisArg: Any? = definedExternally /* null */): LoDashExplicitArrayWrapper<TResult>
    fun <TResult : Any> clone(customizer: CloneCustomizer<T, TResult>, thisArg: Any? = definedExternally /* null */): LoDashExplicitObjectWrapper<TResult>
    fun clone(isDeep: Boolean? = definedExternally /* null */): LoDashExplicitWrapper<T>
    fun <TResult /* Number | String | Boolean */> cloneDeep(customizer: CloneDeepCustomizer<T, TResult>, thisArg: Any? = definedExternally /* null */): LoDashExplicitWrapper<TResult>
    fun <TResult> cloneDeep(customizer: CloneDeepCustomizer<T, Array<TResult>>, thisArg: Any? = definedExternally /* null */): LoDashExplicitArrayWrapper<TResult>
    fun <TResult : Any> cloneDeep(customizer: CloneDeepCustomizer<T, TResult>, thisArg: Any? = definedExternally /* null */): LoDashExplicitObjectWrapper<TResult>
    fun cloneDeep(): LoDashExplicitWrapper<T>
    fun <TResult> toArray(): LoDashExplicitArrayWrapper<TResult>
    fun add(addend: Number): LoDashExplicitWrapper<Number>
    fun ceil(precision: Number? = definedExternally /* null */): LoDashExplicitWrapper<Number>
    fun floor(precision: Number? = definedExternally /* null */): LoDashExplicitWrapper<Number>
    fun round(precision: Number? = definedExternally /* null */): LoDashExplicitWrapper<Number>
    fun inRange(start: Number, end: Number): LoDashExplicitWrapper<Boolean>
    fun inRange(end: Number): LoDashExplicitWrapper<Boolean>
    fun random(max: Number? = definedExternally /* null */, floating: Boolean? = definedExternally /* null */): LoDashExplicitWrapper<Number>
    fun random(floating: Boolean? = definedExternally /* null */): LoDashExplicitWrapper<Number>
    fun <TResultWrapper> get(path: StringRepresentable, defaultValue: Any? = definedExternally /* null */): TResultWrapper
    fun <TResultWrapper> get(path: Array<StringRepresentable>, defaultValue: Any? = definedExternally /* null */): TResultWrapper
    fun <TResultWrapper> result(path: StringRepresentable, defaultValue: Any? = definedExternally /* null */): TResultWrapper
    fun <TResultWrapper> result(path: Array<StringRepresentable>, defaultValue: Any? = definedExternally /* null */): TResultWrapper
    fun camelCase(): LoDashExplicitWrapper<String>
    fun capitalize(): LoDashExplicitWrapper<String>
    fun deburr(): LoDashExplicitWrapper<String>
    fun endsWith(target: String? = definedExternally /* null */, position: Number? = definedExternally /* null */): LoDashExplicitWrapper<Boolean>
    fun escape(): LoDashExplicitWrapper<String>
    fun escapeRegExp(): LoDashExplicitWrapper<String>
    fun kebabCase(): LoDashExplicitWrapper<String>
    fun pad(length: Number? = definedExternally /* null */, chars: String? = definedExternally /* null */): LoDashExplicitWrapper<String>
    fun padLeft(length: Number? = definedExternally /* null */, chars: String? = definedExternally /* null */): LoDashExplicitWrapper<String>
    fun padRight(length: Number? = definedExternally /* null */, chars: String? = definedExternally /* null */): LoDashExplicitWrapper<String>
    fun parseInt(radix: Number? = definedExternally /* null */): LoDashExplicitWrapper<Number>
    fun repeat(n: Number? = definedExternally /* null */): LoDashExplicitWrapper<String>
    fun snakeCase(): LoDashExplicitWrapper<String>
    fun split(separator: RegExp? = definedExternally /* null */, limit: Number? = definedExternally /* null */): LoDashImplicitArrayWrapper<String>
    fun split(separator: String? = definedExternally /* null */, limit: Number? = definedExternally /* null */): LoDashImplicitArrayWrapper<String>
    fun startCase(): LoDashExplicitWrapper<String>
    fun startsWith(target: String? = definedExternally /* null */, position: Number? = definedExternally /* null */): LoDashExplicitWrapper<Boolean>
    fun template(options: TemplateOptions? = definedExternally /* null */): LoDashExplicitObjectWrapper<TemplateExecutor>
    fun trim(chars: String? = definedExternally /* null */): LoDashExplicitWrapper<String>
    fun trimLeft(chars: String? = definedExternally /* null */): LoDashExplicitWrapper<String>
    fun trimRight(chars: String? = definedExternally /* null */): LoDashExplicitWrapper<String>
    fun trunc(options: TruncOptions? = definedExternally /* null */): LoDashExplicitWrapper<String>
    fun trunc(options: Number? = definedExternally /* null */): LoDashExplicitWrapper<String>
    fun unescape(): LoDashExplicitWrapper<String>
    fun words(pattern: String? = definedExternally /* null */): LoDashExplicitArrayWrapper<String>
    fun words(pattern: RegExp? = definedExternally /* null */): LoDashExplicitArrayWrapper<String>
    fun <TResult> callback(thisArg: Any? = definedExternally /* null */): LoDashExplicitObjectWrapper<(obj: Any) -> TResult>
    fun identity(): LoDashExplicitWrapper<T>
    fun <TResult> iteratee(thisArg: Any? = definedExternally /* null */): LoDashExplicitObjectWrapper<(obj: Any) -> TResult>
    fun <TObject, TResult> method(vararg args: Any): LoDashExplicitObjectWrapper<(obj: TObject) -> TResult>
    fun <TResult> method(vararg args: Any): LoDashExplicitObjectWrapper<(obj: Any) -> TResult>
    fun <TObj, TResult> property(): LoDashExplicitObjectWrapper<(obj: TObj) -> TResult>
    fun range(end: Number? = definedExternally /* null */, step: Number? = definedExternally /* null */): LoDashExplicitArrayWrapper<Number>
    fun <TResult> times(iteratee: (num: Number) -> TResult, thisArgs: Any? = definedExternally /* null */): LoDashExplicitArrayWrapper<TResult>
    fun times(): LoDashExplicitArrayWrapper<Number>
    fun uniqueId(): LoDashExplicitWrapper<String>
}
external interface LoDashImplicitStringWrapper : LoDashImplicitWrapper<String> {
    fun partition(callback: ListIterator<String, Boolean>, thisArg: Any? = definedExternally /* null */): LoDashImplicitArrayWrapper<Array<String>>
}
external interface LoDashExplicitStringWrapper : LoDashExplicitWrapper<String>
external interface LoDashImplicitObjectWrapper<T> : LoDashImplicitWrapperBase<T, LoDashImplicitObjectWrapper<T>> {
    fun join(separator: String? = definedExternally /* null */): String
    fun <TResult> chunk(size: Number? = definedExternally /* null */): LoDashImplicitArrayWrapper<Array<TResult>>
    fun <TResult> compact(): LoDashImplicitArrayWrapper<TResult>
    fun <TValue> difference(vararg values: dynamic /* Array<TValue> | List<TValue> */): LoDashImplicitArrayWrapper<TValue>
    fun <T> drop(n: Number? = definedExternally /* null */): LoDashImplicitArrayWrapper<T>
    fun <TResult> dropRight(n: Number? = definedExternally /* null */): LoDashImplicitArrayWrapper<TResult>
    fun <TValue> dropRightWhile(predicate: ListIterator<TValue, Boolean>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashImplicitArrayWrapper<TValue>
    fun <TValue> dropRightWhile(predicate: String? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashImplicitArrayWrapper<TValue>
    fun <TWhere, TValue> dropRightWhile(predicate: TWhere? = definedExternally /* null */): LoDashImplicitArrayWrapper<TValue>
    fun <TValue> dropWhile(predicate: ListIterator<TValue, Boolean>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashImplicitArrayWrapper<TValue>
    fun <TValue> dropWhile(predicate: String? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashImplicitArrayWrapper<TValue>
    fun <TWhere, TValue> dropWhile(predicate: TWhere? = definedExternally /* null */): LoDashImplicitArrayWrapper<TValue>
    fun <T> fill(value: T, start: Number? = definedExternally /* null */, end: Number? = definedExternally /* null */): LoDashImplicitObjectWrapper<List<T>>
    fun <TResult> findIndex(predicate: ListIterator<TResult, Boolean>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Number
    fun findIndex(predicate: String? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Number
    fun <W> findIndex(predicate: W? = definedExternally /* null */): Number
    fun <TResult> findLastIndex(predicate: ListIterator<TResult, Boolean>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Number
    fun findLastIndex(predicate: String? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Number
    fun <W> findLastIndex(predicate: W? = definedExternally /* null */): Number
    fun <T> first(): T
    fun <TResult> flatten(isDeep: Boolean? = definedExternally /* null */): LoDashImplicitArrayWrapper<TResult>
    fun <T> flattenDeep(): LoDashImplicitArrayWrapper<T>
    fun <T> head(): T
    fun <TValue> indexOf(value: TValue, fromIndex: Boolean? = definedExternally /* null */): Number
    fun <TValue> indexOf(value: TValue, fromIndex: Number? = definedExternally /* null */): Number
    fun <T> initial(): LoDashImplicitArrayWrapper<T>
    fun <TResult> intersection(vararg arrays: dynamic /* Array<TResult> | List<TResult> */): LoDashImplicitArrayWrapper<TResult>
    fun <T> last(): T
    fun <TResult> lastIndexOf(value: TResult, fromIndex: Boolean? = definedExternally /* null */): Number
    fun <TResult> lastIndexOf(value: TResult, fromIndex: Number? = definedExternally /* null */): Number
    fun <TValues, TResult : Any> `object`(values: List<TValues>? = definedExternally /* null */): lodash.LoDashImplicitObjectWrapper<TResult>
    fun <TResult : Any> `object`(values: List<Any>? = definedExternally /* null */): lodash.LoDashImplicitObjectWrapper<TResult>
    fun `object`(values: List<Any>? = definedExternally /* null */): lodash.LoDashImplicitObjectWrapper<lodash.Dictionary<Any>>
    fun <TValue> pull(vararg values: TValue): LoDashImplicitObjectWrapper<List<TValue>>
    fun <T> pullAt(vararg indexes: dynamic /* Number | Array<Number> */): LoDashImplicitArrayWrapper<T>
    fun <TResult> remove(predicate: ListIterator<TResult, Boolean>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashImplicitArrayWrapper<TResult>
    fun <TResult> remove(predicate: String? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashImplicitArrayWrapper<TResult>
    fun <W, TResult> remove(predicate: W? = definedExternally /* null */): LoDashImplicitArrayWrapper<TResult>
    fun <T> rest(): LoDashImplicitArrayWrapper<T>
    fun <T, TSort> sortedIndex(value: T, iteratee: ((x: T) -> TSort)? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Number
    fun <T> sortedIndex(value: T, iteratee: ((x: T) -> Any)? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Number
    fun <T> sortedIndex(value: T, iteratee: String): Number
    fun <W, T> sortedIndex(value: T, iteratee: W): Number
    fun <T> sortedIndex(value: T, iteratee: Any): Number
    fun <T, TSort> sortedLastIndex(value: T, iteratee: ((x: T) -> TSort)? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Number
    fun <T> sortedLastIndex(value: T, iteratee: ((x: T) -> Any)? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Number
    fun <T> sortedLastIndex(value: T, iteratee: String): Number
    fun <W, T> sortedLastIndex(value: T, iteratee: W): Number
    fun <T> sortedLastIndex(value: T, iteratee: Any): Number
    fun <T> tail(): LoDashImplicitArrayWrapper<T>
    fun <TResult> take(n: Number? = definedExternally /* null */): LoDashImplicitArrayWrapper<TResult>
    fun <TResult> takeRight(n: Number? = definedExternally /* null */): LoDashImplicitArrayWrapper<TResult>
    fun <TValue> takeRightWhile(predicate: ListIterator<TValue, Boolean>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashImplicitArrayWrapper<TValue>
    fun <TValue> takeRightWhile(predicate: String? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashImplicitArrayWrapper<TValue>
    fun <TWhere, TValue> takeRightWhile(predicate: TWhere? = definedExternally /* null */): LoDashImplicitArrayWrapper<TValue>
    fun <TValue> takeWhile(predicate: ListIterator<TValue, Boolean>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashImplicitArrayWrapper<TValue>
    fun <TValue> takeWhile(predicate: String? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashImplicitArrayWrapper<TValue>
    fun <TWhere, TValue> takeWhile(predicate: TWhere? = definedExternally /* null */): LoDashImplicitArrayWrapper<TValue>
    fun <T> union(vararg arrays: List<T>): LoDashImplicitArrayWrapper<T>
    fun <T> uniq(isSorted: Boolean? = definedExternally /* null */, iteratee: ListIterator<T, Any>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashImplicitArrayWrapper<T>
    fun <T, TSort> uniq(isSorted: Boolean? = definedExternally /* null */, iteratee: ListIterator<T, TSort>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashImplicitArrayWrapper<T>
    fun <T> uniq(iteratee: ListIterator<T, Any>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashImplicitArrayWrapper<T>
    fun <T, TSort> uniq(iteratee: ListIterator<T, TSort>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashImplicitArrayWrapper<T>
    fun <T> uniq(isSorted: Boolean? = definedExternally /* null */, iteratee: String? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashImplicitArrayWrapper<T>
    fun <T> uniq(iteratee: String? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashImplicitArrayWrapper<T>
    fun <T> uniq(isSorted: Boolean? = definedExternally /* null */, iteratee: Any? = definedExternally /* null */): LoDashImplicitArrayWrapper<T>
    fun <TWhere : Any, T> uniq(isSorted: Boolean? = definedExternally /* null */, iteratee: TWhere? = definedExternally /* null */): LoDashImplicitArrayWrapper<T>
    fun <T> uniq(iteratee: Any? = definedExternally /* null */): LoDashImplicitArrayWrapper<T>
    fun <TWhere : Any, T> uniq(iteratee: TWhere? = definedExternally /* null */): LoDashImplicitArrayWrapper<T>
    fun <T> unique(isSorted: Boolean? = definedExternally /* null */, iteratee: ListIterator<T, Any>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashImplicitArrayWrapper<T>
    fun <T, TSort> unique(isSorted: Boolean? = definedExternally /* null */, iteratee: ListIterator<T, TSort>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashImplicitArrayWrapper<T>
    fun <T> unique(iteratee: ListIterator<T, Any>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashImplicitArrayWrapper<T>
    fun <T, TSort> unique(iteratee: ListIterator<T, TSort>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashImplicitArrayWrapper<T>
    fun <T> unique(isSorted: Boolean? = definedExternally /* null */, iteratee: String? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashImplicitArrayWrapper<T>
    fun <T> unique(iteratee: String? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashImplicitArrayWrapper<T>
    fun <T> unique(isSorted: Boolean? = definedExternally /* null */, iteratee: Any? = definedExternally /* null */): LoDashImplicitArrayWrapper<T>
    fun <TWhere : Any, T> unique(isSorted: Boolean? = definedExternally /* null */, iteratee: TWhere? = definedExternally /* null */): LoDashImplicitArrayWrapper<T>
    fun <T> unique(iteratee: Any? = definedExternally /* null */): LoDashImplicitArrayWrapper<T>
    fun <TWhere : Any, T> unique(iteratee: TWhere? = definedExternally /* null */): LoDashImplicitArrayWrapper<T>
    fun <T> unzip(): LoDashImplicitArrayWrapper<Array<T>>
    fun <TArr, TResult> unzipWith(iteratee: MemoIterator<TArr, TResult>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashImplicitArrayWrapper<TResult>
    fun <T> without(vararg values: T): LoDashImplicitArrayWrapper<T>
    fun <T> xor(vararg arrays: List<T>): LoDashImplicitArrayWrapper<T>
    fun <T> zip(vararg arrays: List<T>): lodash.LoDashImplicitArrayWrapper<Array<T>>
    fun <TValues, TResult : Any> zipObject(values: List<TValues>? = definedExternally /* null */): lodash.LoDashImplicitObjectWrapper<TResult>
    fun <TResult : Any> zipObject(values: List<Any>? = definedExternally /* null */): lodash.LoDashImplicitObjectWrapper<TResult>
    fun zipObject(values: List<Any>? = definedExternally /* null */): lodash.LoDashImplicitObjectWrapper<lodash.Dictionary<Any>>
    fun chain(): LoDashExplicitObjectWrapper<T>
    fun <TResult> all(predicate: ListIterator<TResult, Boolean>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Boolean
    fun <TResult> all(predicate: DictionaryIterator<TResult, Boolean>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Boolean
    fun all(predicate: String? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Boolean
    fun <TObject : Any> all(predicate: TObject? = definedExternally /* null */): Boolean
    fun <TResult> any(predicate: ListIterator<TResult, Boolean>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Boolean
    fun <TResult> any(predicate: DictionaryIterator<TResult, Boolean>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Boolean
    fun <TResult> any(predicate: NumericDictionaryIterator<T, Boolean>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Boolean
    fun <TResult> any(predicate: ObjectIterator<Any, Boolean>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Boolean
    fun any(predicate: String? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Boolean
    fun <TObject : Any> any(predicate: TObject? = definedExternally /* null */): Boolean
    fun <T> at(vararg props: dynamic /* Number | String | Array<dynamic /* Number | String */> */): LoDashImplicitArrayWrapper<T>
    fun <TValue, TResult> collect(iteratee: ListIterator<TValue, TResult>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashImplicitArrayWrapper<TResult>
    fun <TValue, TResult> collect(iteratee: DictionaryIterator<TValue, TResult>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashImplicitArrayWrapper<TResult>
    fun <TValue, TResult> collect(iteratee: String? = definedExternally /* null */): LoDashImplicitArrayWrapper<TResult>
    fun <TObject : Any> collect(iteratee: TObject? = definedExternally /* null */): LoDashImplicitArrayWrapper<Boolean>
    fun <TValue> contains(target: TValue, fromIndex: Number? = definedExternally /* null */): Boolean
    fun <T> countBy(iteratee: ListIterator<T, Any>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashImplicitObjectWrapper<Dictionary<Number>>
    fun <T> countBy(iteratee: DictionaryIterator<T, Any>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashImplicitObjectWrapper<Dictionary<Number>>
    fun <T> countBy(iteratee: NumericDictionaryIterator<T, Any>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashImplicitObjectWrapper<Dictionary<Number>>
    fun countBy(iteratee: String? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashImplicitObjectWrapper<Dictionary<Number>>
    fun <W> countBy(iteratee: W? = definedExternally /* null */): LoDashImplicitObjectWrapper<Dictionary<Number>>
    fun <TResult> detect(predicate: ListIterator<TResult, Boolean>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): TResult
    fun <TResult> detect(predicate: DictionaryIterator<TResult, Boolean>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): TResult
    fun <TResult> detect(predicate: String? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): TResult
    fun <TObject : Any, TResult> detect(predicate: TObject? = definedExternally /* null */): TResult
    fun <TValue> each(iteratee: ListIterator<TValue, Any>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashImplicitObjectWrapper<T>
    fun <TValue> each(iteratee: DictionaryIterator<TValue, Any>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashImplicitObjectWrapper<T>
    fun <TValue> eachRight(iteratee: ListIterator<TValue, Any>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashImplicitObjectWrapper<T>
    fun <TValue> eachRight(iteratee: DictionaryIterator<TValue, Any>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashImplicitObjectWrapper<T>
    fun <TResult> every(predicate: ListIterator<TResult, Boolean>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Boolean
    fun <TResult> every(predicate: DictionaryIterator<TResult, Boolean>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Boolean
    fun every(predicate: String? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Boolean
    fun <TObject : Any> every(predicate: TObject? = definedExternally /* null */): Boolean
    fun <T> filter(predicate: ListIterator<T, Boolean>, thisArg: Any? = definedExternally /* null */): LoDashImplicitArrayWrapper<T>
    fun <T> filter(predicate: DictionaryIterator<T, Boolean>, thisArg: Any? = definedExternally /* null */): LoDashImplicitArrayWrapper<T>
    fun <T> filter(predicate: String, thisArg: Any? = definedExternally /* null */): LoDashImplicitArrayWrapper<T>
    fun <W, T> filter(predicate: W): LoDashImplicitArrayWrapper<T>
    fun <TResult> find(predicate: ListIterator<TResult, Boolean>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): TResult?
    fun <TResult> find(predicate: DictionaryIterator<TResult, Boolean>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): TResult?
    fun <TResult> find(predicate: String? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): TResult?
    fun <TObject : Any, TResult> find(predicate: TObject? = definedExternally /* null */): TResult?
    fun <TValue> forEach(iteratee: ListIterator<TValue, Any>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashImplicitObjectWrapper<T>
    fun <TValue> forEach(iteratee: DictionaryIterator<TValue, Any>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashImplicitObjectWrapper<T>
    fun <TValue> forEachRight(iteratee: ListIterator<TValue, Any>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashImplicitObjectWrapper<T>
    fun <TValue> forEachRight(iteratee: DictionaryIterator<TValue, Any>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashImplicitObjectWrapper<T>
    fun <T, TKey> groupBy(iteratee: ListIterator<T, TKey>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashImplicitObjectWrapper<Dictionary<Array<T>>>
    fun <T, TKey> groupBy(iteratee: DictionaryIterator<T, TKey>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashImplicitObjectWrapper<Dictionary<Array<T>>>
    fun <T> groupBy(iteratee: ListIterator<T, Any>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashImplicitObjectWrapper<Dictionary<Array<T>>>
    fun <T> groupBy(iteratee: DictionaryIterator<T, Any>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashImplicitObjectWrapper<Dictionary<Array<T>>>
    fun <T, TValue> groupBy(iteratee: String? = definedExternally /* null */, thisArg: TValue? = definedExternally /* null */): LoDashImplicitObjectWrapper<Dictionary<Array<T>>>
    fun <T> groupBy(iteratee: String? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashImplicitObjectWrapper<Dictionary<Array<T>>>
    fun <TWhere, T> groupBy(iteratee: TWhere? = definedExternally /* null */): LoDashImplicitObjectWrapper<Dictionary<Array<T>>>
    fun <T> groupBy(iteratee: Any? = definedExternally /* null */): LoDashImplicitObjectWrapper<Dictionary<Array<T>>>
    fun <TValue> include(target: TValue, fromIndex: Number? = definedExternally /* null */): Boolean
    fun <TValue> includes(target: TValue, fromIndex: Number? = definedExternally /* null */): Boolean
    fun <T> indexBy(iteratee: ListIterator<T, Any>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashImplicitObjectWrapper<Dictionary<T>>
    fun <T> indexBy(iteratee: NumericDictionaryIterator<T, Any>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashImplicitObjectWrapper<Dictionary<T>>
    fun <T> indexBy(iteratee: DictionaryIterator<T, Any>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashImplicitObjectWrapper<Dictionary<T>>
    fun <T> indexBy(iteratee: String? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashImplicitObjectWrapper<Dictionary<T>>
    fun <W : Any, T> indexBy(iteratee: W? = definedExternally /* null */): LoDashImplicitObjectWrapper<Dictionary<T>>
    fun <T> indexBy(iteratee: Any? = definedExternally /* null */): LoDashImplicitObjectWrapper<Dictionary<T>>
    fun <TValue, TResult> map(iteratee: ListIterator<TValue, TResult>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashImplicitArrayWrapper<TResult>
    fun <TValue, TResult> map(iteratee: DictionaryIterator<TValue, TResult>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashImplicitArrayWrapper<TResult>
    fun <TValue, TResult> map(iteratee: String? = definedExternally /* null */): LoDashImplicitArrayWrapper<TResult>
    fun <TObject : Any> map(iteratee: TObject? = definedExternally /* null */): LoDashImplicitArrayWrapper<Boolean>
    fun <TResult> partition(callback: ListIterator<TResult, Boolean>, thisArg: Any? = definedExternally /* null */): LoDashImplicitArrayWrapper<Array<TResult>>
    fun <TResult> partition(callback: DictionaryIterator<TResult, Boolean>, thisArg: Any? = definedExternally /* null */): LoDashImplicitArrayWrapper<Array<TResult>>
    fun <W, TResult> partition(whereValue: W): LoDashImplicitArrayWrapper<Array<TResult>>
    fun <TResult> partition(path: String, srcValue: Any): LoDashImplicitArrayWrapper<Array<TResult>>
    fun <TResult> partition(pluckValue: String): LoDashImplicitArrayWrapper<Array<TResult>>
    fun <TResult> pluck(path: StringRepresentable): LoDashImplicitArrayWrapper<TResult>
    fun <TResult> pluck(path: Array<StringRepresentable>): LoDashImplicitArrayWrapper<TResult>
    fun <TValue, TResult> reduce(callback: MemoIterator<TValue, TResult>, accumulator: TResult, thisArg: Any? = definedExternally /* null */): TResult
    fun <TValue, TResult> reduce(callback: MemoIterator<TValue, TResult>, thisArg: Any? = definedExternally /* null */): TResult
    fun <TValue, TResult> inject(callback: MemoIterator<TValue, TResult>, accumulator: TResult, thisArg: Any? = definedExternally /* null */): TResult
    fun <TValue, TResult> inject(callback: MemoIterator<TValue, TResult>, thisArg: Any? = definedExternally /* null */): TResult
    fun <TValue, TResult> foldl(callback: MemoIterator<TValue, TResult>, accumulator: TResult, thisArg: Any? = definedExternally /* null */): TResult
    fun <TValue, TResult> foldl(callback: MemoIterator<TValue, TResult>, thisArg: Any? = definedExternally /* null */): TResult
    fun <T> reject(predicate: ListIterator<T, Boolean>, thisArg: Any? = definedExternally /* null */): LoDashImplicitArrayWrapper<T>
    fun <T> reject(predicate: DictionaryIterator<T, Boolean>, thisArg: Any? = definedExternally /* null */): LoDashImplicitArrayWrapper<T>
    fun <T> reject(predicate: String, thisArg: Any? = definedExternally /* null */): LoDashImplicitArrayWrapper<T>
    fun <W, T> reject(predicate: W): LoDashImplicitArrayWrapper<T>
    fun <T> select(predicate: ListIterator<T, Boolean>, thisArg: Any? = definedExternally /* null */): LoDashImplicitArrayWrapper<T>
    fun <T> select(predicate: DictionaryIterator<T, Boolean>, thisArg: Any? = definedExternally /* null */): LoDashImplicitArrayWrapper<T>
    fun <T> select(predicate: String, thisArg: Any? = definedExternally /* null */): LoDashImplicitArrayWrapper<T>
    fun <W, T> select(predicate: W): LoDashImplicitArrayWrapper<T>
    fun <T> sample(n: Number): LoDashImplicitArrayWrapper<T>
    fun <T> sample(): T
    fun <T> shuffle(): LoDashImplicitArrayWrapper<T>
    fun size(): Number
    fun <TResult> some(predicate: ListIterator<TResult, Boolean>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Boolean
    fun <TResult> some(predicate: DictionaryIterator<TResult, Boolean>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Boolean
    fun <TResult> some(predicate: NumericDictionaryIterator<T, Boolean>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Boolean
    fun <TResult> some(predicate: ObjectIterator<Any, Boolean>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Boolean
    fun some(predicate: String? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Boolean
    fun <TObject : Any> some(predicate: TObject? = definedExternally /* null */): Boolean
    fun <T, TSort> sortBy(iteratee: ListIterator<T, TSort>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashImplicitArrayWrapper<T>
    fun <T, TSort> sortBy(iteratee: DictionaryIterator<T, TSort>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashImplicitArrayWrapper<T>
    fun <T> sortBy(iteratee: String): LoDashImplicitArrayWrapper<T>
    fun <W : Any, T> sortBy(whereValue: W): LoDashImplicitArrayWrapper<T>
    fun <T> sortBy(): LoDashImplicitArrayWrapper<T>
    fun <W : Any, T> sortByOrder(iteratees: ListIterator<T, Any>, orders: dynamic /* Boolean? | String? | Array<dynamic /* Boolean | String */>? */ = definedExternally /* null */): LoDashImplicitArrayWrapper<T>
    fun <W : Any, T> sortByOrder(iteratees: String, orders: dynamic /* Boolean? | String? | Array<dynamic /* Boolean | String */>? */ = definedExternally /* null */): LoDashImplicitArrayWrapper<T>
    fun <W : Any, T> sortByOrder(iteratees: W, orders: dynamic /* Boolean? | String? | Array<dynamic /* Boolean | String */>? */ = definedExternally /* null */): LoDashImplicitArrayWrapper<T>
    fun <W : Any, T> sortByOrder(iteratees: Array<dynamic /* ListIterator<T, Any> | String | W */>, orders: dynamic /* Boolean? | String? | Array<dynamic /* Boolean | String */>? */ = definedExternally /* null */): LoDashImplicitArrayWrapper<T>
    fun <T> sortByOrder(iteratees: ListIterator<T, Any>, orders: dynamic /* Boolean? | String? | Array<dynamic /* Boolean | String */>? */ = definedExternally /* null */): LoDashImplicitArrayWrapper<T>
    fun <T> sortByOrder(iteratees: String, orders: dynamic /* Boolean? | String? | Array<dynamic /* Boolean | String */>? */ = definedExternally /* null */): LoDashImplicitArrayWrapper<T>
    fun <T> sortByOrder(iteratees: Any, orders: dynamic /* Boolean? | String? | Array<dynamic /* Boolean | String */>? */ = definedExternally /* null */): LoDashImplicitArrayWrapper<T>
    fun <T> sortByOrder(iteratees: Array<dynamic /* ListIterator<T, Any> | String | Any */>, orders: dynamic /* Boolean? | String? | Array<dynamic /* Boolean | String */>? */ = definedExternally /* null */): LoDashImplicitArrayWrapper<T>
    fun <W : Any, T> sortByOrder(iteratees: NumericDictionaryIterator<T, Any>, orders: dynamic /* Boolean? | String? | Array<dynamic /* Boolean | String */>? */ = definedExternally /* null */): LoDashImplicitArrayWrapper<T>
    fun <W : Any, T> sortByOrder(iteratees: String, orders: dynamic /* Boolean? | String? | Array<dynamic /* Boolean | String */>? */ = definedExternally /* null */): LoDashImplicitArrayWrapper<T>
    fun <W : Any, T> sortByOrder(iteratees: W, orders: dynamic /* Boolean? | String? | Array<dynamic /* Boolean | String */>? */ = definedExternally /* null */): LoDashImplicitArrayWrapper<T>
    fun <W : Any, T> sortByOrder(iteratees: Array<dynamic /* NumericDictionaryIterator<T, Any> | String | W */>, orders: dynamic /* Boolean? | String? | Array<dynamic /* Boolean | String */>? */ = definedExternally /* null */): LoDashImplicitArrayWrapper<T>
    fun <T> sortByOrder(iteratees: NumericDictionaryIterator<T, Any>, orders: dynamic /* Boolean? | String? | Array<dynamic /* Boolean | String */>? */ = definedExternally /* null */): LoDashImplicitArrayWrapper<T>
    fun <T> sortByOrder(iteratees: String, orders: dynamic /* Boolean? | String? | Array<dynamic /* Boolean | String */>? */ = definedExternally /* null */): LoDashImplicitArrayWrapper<T>
    fun <T> sortByOrder(iteratees: Any, orders: dynamic /* Boolean? | String? | Array<dynamic /* Boolean | String */>? */ = definedExternally /* null */): LoDashImplicitArrayWrapper<T>
    fun <T> sortByOrder(iteratees: Array<dynamic /* NumericDictionaryIterator<T, Any> | String | Any */>, orders: dynamic /* Boolean? | String? | Array<dynamic /* Boolean | String */>? */ = definedExternally /* null */): LoDashImplicitArrayWrapper<T>
    fun <W : Any, T> sortByOrder(iteratees: DictionaryIterator<T, Any>, orders: dynamic /* Boolean? | String? | Array<dynamic /* Boolean | String */>? */ = definedExternally /* null */): LoDashImplicitArrayWrapper<T>
    fun <W : Any, T> sortByOrder(iteratees: String, orders: dynamic /* Boolean? | String? | Array<dynamic /* Boolean | String */>? */ = definedExternally /* null */): LoDashImplicitArrayWrapper<T>
    fun <W : Any, T> sortByOrder(iteratees: W, orders: dynamic /* Boolean? | String? | Array<dynamic /* Boolean | String */>? */ = definedExternally /* null */): LoDashImplicitArrayWrapper<T>
    fun <W : Any, T> sortByOrder(iteratees: Array<dynamic /* DictionaryIterator<T, Any> | String | W */>, orders: dynamic /* Boolean? | String? | Array<dynamic /* Boolean | String */>? */ = definedExternally /* null */): LoDashImplicitArrayWrapper<T>
    fun <T> sortByOrder(iteratees: DictionaryIterator<T, Any>, orders: dynamic /* Boolean? | String? | Array<dynamic /* Boolean | String */>? */ = definedExternally /* null */): LoDashImplicitArrayWrapper<T>
    fun <T> sortByOrder(iteratees: String, orders: dynamic /* Boolean? | String? | Array<dynamic /* Boolean | String */>? */ = definedExternally /* null */): LoDashImplicitArrayWrapper<T>
    fun <T> sortByOrder(iteratees: Any, orders: dynamic /* Boolean? | String? | Array<dynamic /* Boolean | String */>? */ = definedExternally /* null */): LoDashImplicitArrayWrapper<T>
    fun <T> sortByOrder(iteratees: Array<dynamic /* DictionaryIterator<T, Any> | String | Any */>, orders: dynamic /* Boolean? | String? | Array<dynamic /* Boolean | String */>? */ = definedExternally /* null */): LoDashImplicitArrayWrapper<T>
    fun <TResult : Function<*>> ary(n: Number? = definedExternally /* null */): LoDashImplicitObjectWrapper<TResult>
    fun <TResult : Function<*>> backflow(vararg funcs: Function<*>): LoDashImplicitObjectWrapper<TResult>
    fun <TResult : Function<*>> bind(thisArg: Any, vararg partials: Any): LoDashImplicitObjectWrapper<TResult>
    fun bindAll(vararg methodNames: dynamic /* String | Array<String> */): LoDashImplicitObjectWrapper<T>
    fun <TResult : Function<*>> bindKey(key: Any, vararg partials: Any): LoDashImplicitObjectWrapper<TResult>
    fun <TResult : Function<*>> compose(vararg funcs: Function<*>): LoDashImplicitObjectWrapper<TResult>
    fun createCallback(thisArg: Any? = definedExternally /* null */, argCount: Number? = definedExternally /* null */): LoDashImplicitObjectWrapper<() -> Any>
    fun <TResult : Function<*>> curry(arity: Number? = definedExternally /* null */): LoDashImplicitObjectWrapper<TResult>
    fun <TResult : Function<*>> curryRight(arity: Number? = definedExternally /* null */): LoDashImplicitObjectWrapper<TResult>
    fun debounce(wait: Number? = definedExternally /* null */, options: DebounceSettings? = definedExternally /* null */): LoDashImplicitObjectWrapper<T /* T & Cancelable */>
    fun defer(vararg args: Any): LoDashImplicitWrapper<Number>
    fun delay(wait: Number, vararg args: Any): LoDashImplicitWrapper<Number>
    fun <TResult : Function<*>> flow(vararg funcs: Function<*>): LoDashImplicitObjectWrapper<TResult>
    fun <TResult : Function<*>> flowRight(vararg funcs: Function<*>): LoDashImplicitObjectWrapper<TResult>
    fun memoize(resolver: Function<*>? = definedExternally /* null */): LoDashImplicitObjectWrapper<T /* T & MemoizedFunction */>
    fun <TResult : Function<*>> modArgs(vararg transforms: Function<*>): LoDashImplicitObjectWrapper<TResult>
    fun <TResult : Function<*>> modArgs(transforms: Array<Function<*>>): LoDashImplicitObjectWrapper<TResult>
    fun negate(): LoDashImplicitObjectWrapper<(args: Any) -> Boolean>
    fun <TResult : Function<*>> negate(): LoDashImplicitObjectWrapper<TResult>
    fun once(): LoDashImplicitObjectWrapper<T>
    fun <TResult : Function<*>> rearg(indexes: Array<Number>): LoDashImplicitObjectWrapper<TResult>
    fun <TResult : Function<*>> rearg(vararg indexes: Number): LoDashImplicitObjectWrapper<TResult>
    fun <TResult : Function<*>> restParam(start: Number? = definedExternally /* null */): LoDashImplicitObjectWrapper<TResult>
    fun <T : Function<*>> spread(): LoDashImplicitObjectWrapper<T>
    fun throttle(wait: Number? = definedExternally /* null */, options: ThrottleSettings? = definedExternally /* null */): LoDashImplicitObjectWrapper<T /* T & Cancelable */>
    fun <W : Function<*>, R : Function<*>> wrap(wrapper: W): LoDashImplicitObjectWrapper<R>
    fun <R : Function<*>> wrap(wrapper: Function<*>): LoDashImplicitObjectWrapper<R>
    fun <TResult> clone(isDeep: Boolean, customizer: CloneCustomizer<T, TResult>, thisArg: Any? = definedExternally /* null */): TResult
    fun <TResult> clone(customizer: CloneCustomizer<T, TResult>, thisArg: Any? = definedExternally /* null */): TResult
    fun clone(isDeep: Boolean? = definedExternally /* null */): T
    fun <TResult> cloneDeep(customizer: CloneDeepCustomizer<T, TResult>, thisArg: Any? = definedExternally /* null */): TResult
    fun cloneDeep(): T
    fun isMatch(source: Any, customizer: isMatchCustomizer? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Boolean
    fun <TResult> toArray(): LoDashImplicitArrayWrapper<TResult>
    fun <T> max(iteratee: ListIterator<T, Any>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): T
    fun <T> max(iteratee: DictionaryIterator<T, Any>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): T
    fun <T> max(iteratee: String? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): T
    fun <TObject : Any, T> max(whereValue: TObject? = definedExternally /* null */): T
    fun <T> min(iteratee: ListIterator<T, Any>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): T
    fun <T> min(iteratee: DictionaryIterator<T, Any>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): T
    fun <T> min(iteratee: String? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): T
    fun <TObject : Any, T> min(whereValue: TObject? = definedExternally /* null */): T
    fun <TValue> sum(iteratee: ListIterator<TValue, Number>, thisArg: Any? = definedExternally /* null */): Number
    fun <TValue> sum(iteratee: DictionaryIterator<TValue, Number>, thisArg: Any? = definedExternally /* null */): Number
    fun sum(iteratee: String): Number
    fun sum(): Number
    fun <TSource : Any, TResult : Any> assign(source: TSource, customizer: AssignCustomizer? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashImplicitObjectWrapper<TResult>
    fun <TSource1 : Any, TSource2 : Any, TResult : Any> assign(source1: TSource1, source2: TSource2, customizer: AssignCustomizer? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashImplicitObjectWrapper<TResult>
    fun <TSource1 : Any, TSource2 : Any, TSource3 : Any, TResult : Any> assign(source1: TSource1, source2: TSource2, source3: TSource3, customizer: AssignCustomizer? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashImplicitObjectWrapper<TResult>
    fun <TSource1 : Any, TSource2 : Any, TSource3 : Any, TSource4 : Any, TResult : Any> assign(source1: TSource1, source2: TSource2, source3: TSource3, source4: TSource4, customizer: AssignCustomizer? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashImplicitObjectWrapper<TResult>
    fun assign(): LoDashImplicitObjectWrapper<T>
    fun <TResult : Any> assign(vararg otherArgs: Any): LoDashImplicitObjectWrapper<TResult>
    fun <U : Any> create(properties: U? = definedExternally /* null */): LoDashImplicitObjectWrapper<T /* T & U */>
    fun <S1 : Any, TResult : Any> defaults(source1: S1, vararg sources: Any): LoDashImplicitObjectWrapper<TResult>
    fun <S1 : Any, S2 : Any, TResult : Any> defaults(source1: S1, source2: S2, vararg sources: Any): LoDashImplicitObjectWrapper<TResult>
    fun <S1 : Any, S2 : Any, S3 : Any, TResult : Any> defaults(source1: S1, source2: S2, source3: S3, vararg sources: Any): LoDashImplicitObjectWrapper<TResult>
    fun <S1 : Any, S2 : Any, S3 : Any, S4 : Any, TResult : Any> defaults(source1: S1, source2: S2, source3: S3, source4: S4, vararg sources: Any): LoDashImplicitObjectWrapper<TResult>
    fun defaults(): LoDashImplicitObjectWrapper<T>
    fun <TResult> defaults(vararg sources: Any): LoDashImplicitObjectWrapper<TResult>
    fun <TResult> defaultsDeep(vararg sources: Any): LoDashImplicitObjectWrapper<TResult>
    fun <TSource : Any, TResult : Any> extend(source: TSource, customizer: AssignCustomizer? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashImplicitObjectWrapper<TResult>
    fun <TSource1 : Any, TSource2 : Any, TResult : Any> extend(source1: TSource1, source2: TSource2, customizer: AssignCustomizer? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashImplicitObjectWrapper<TResult>
    fun <TSource1 : Any, TSource2 : Any, TSource3 : Any, TResult : Any> extend(source1: TSource1, source2: TSource2, source3: TSource3, customizer: AssignCustomizer? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashImplicitObjectWrapper<TResult>
    fun <TSource1 : Any, TSource2 : Any, TSource3 : Any, TSource4 : Any, TResult : Any> extend(source1: TSource1, source2: TSource2, source3: TSource3, source4: TSource4, customizer: AssignCustomizer? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashImplicitObjectWrapper<TResult>
    fun extend(): LoDashImplicitObjectWrapper<T>
    fun <TResult : Any> extend(vararg otherArgs: Any): LoDashImplicitObjectWrapper<TResult>
    fun <TValues> findKey(predicate: DictionaryIterator<TValues, Boolean>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): String
    fun findKey(predicate: ObjectIterator<Any, Boolean>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): String
    fun findKey(predicate: String? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): String
    fun <TWhere : Dictionary<Any>> findKey(predicate: TWhere? = definedExternally /* null */): String
    fun <TValues> findLastKey(predicate: DictionaryIterator<TValues, Boolean>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): String
    fun findLastKey(predicate: ObjectIterator<Any, Boolean>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): String
    fun findLastKey(predicate: String? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): String
    fun <TWhere : Dictionary<Any>> findLastKey(predicate: TWhere? = definedExternally /* null */): String
    fun <TValue> forIn(iteratee: DictionaryIterator<TValue, Any>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): lodash.LoDashImplicitObjectWrapper<T>
    fun <TValue> forInRight(iteratee: DictionaryIterator<TValue, Any>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): lodash.LoDashImplicitObjectWrapper<T>
    fun <TValue> forOwn(iteratee: DictionaryIterator<TValue, Any>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): lodash.LoDashImplicitObjectWrapper<T>
    fun <TValue> forOwnRight(iteratee: DictionaryIterator<TValue, Any>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): lodash.LoDashImplicitObjectWrapper<T>
    fun functions(): lodash.LoDashImplicitArrayWrapper<String>
    fun <TResult> get(path: StringRepresentable, defaultValue: TResult? = definedExternally /* null */): TResult
    fun <TResult> get(path: Array<StringRepresentable>, defaultValue: TResult? = definedExternally /* null */): TResult
    fun has(path: StringRepresentable): Boolean
    fun has(path: Array<StringRepresentable>): Boolean
    fun <TResult : Any> invert(multiValue: Boolean? = definedExternally /* null */): LoDashImplicitObjectWrapper<TResult>
    fun keys(): LoDashImplicitArrayWrapper<String>
    fun keysIn(): LoDashImplicitArrayWrapper<String>
    fun <TResult, TKey> mapKeys(iteratee: ListIterator<TResult, TKey>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashImplicitObjectWrapper<Dictionary<TResult>>
    fun <TResult, TKey> mapKeys(iteratee: DictionaryIterator<TResult, TKey>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashImplicitObjectWrapper<Dictionary<TResult>>
    fun <TResult, TObject : Any> mapKeys(iteratee: TObject? = definedExternally /* null */): LoDashImplicitObjectWrapper<Dictionary<TResult>>
    fun <TResult> mapKeys(iteratee: String? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashImplicitObjectWrapper<Dictionary<TResult>>
    fun <TValue, TResult> mapValues(callback: ObjectIterator<TValue, TResult>, thisArg: Any? = definedExternally /* null */): LoDashImplicitObjectWrapper<Dictionary<TResult>>
    fun <TResult> mapValues(pluck: String): LoDashImplicitObjectWrapper<Dictionary<TResult>>
    fun <TResult> mapValues(pluck: String, where: Dictionary<TResult>): LoDashImplicitArrayWrapper<Dictionary<Boolean>>
    fun <TResult> mapValues(where: Dictionary<TResult>): LoDashImplicitArrayWrapper<Boolean>
    fun <TSource> merge(source: TSource, customizer: MergeCustomizer? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashImplicitObjectWrapper<T /* T & TSource */>
    fun <TSource1, TSource2> merge(source1: TSource1, source2: TSource2, customizer: MergeCustomizer? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashImplicitObjectWrapper<T /* T & TSource1 & TSource2 */>
    fun <TSource1, TSource2, TSource3> merge(source1: TSource1, source2: TSource2, source3: TSource3, customizer: MergeCustomizer? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashImplicitObjectWrapper<T /* T & TSource1 & TSource2 & TSource3 */>
    fun <TSource1, TSource2, TSource3, TSource4> merge(source1: TSource1, source2: TSource2, source3: TSource3, source4: TSource4, customizer: MergeCustomizer? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashImplicitObjectWrapper<T /* T & TSource1 & TSource2 & TSource3 & TSource4 */>
    fun <TResult> merge(vararg otherArgs: Any): LoDashImplicitObjectWrapper<TResult>
    fun methods(): lodash.LoDashImplicitArrayWrapper<String>
    fun <TResult : Any> omit(predicate: ObjectIterator<Any, Boolean>, thisArg: Any? = definedExternally /* null */): LoDashImplicitObjectWrapper<TResult>
    fun <TResult : Any> omit(vararg predicate: dynamic /* StringRepresentable | Array<StringRepresentable> */): LoDashImplicitObjectWrapper<TResult>
    fun <TResult> pairs(): LoDashImplicitArrayWrapper<Array<TResult>>
    fun <TResult : Any> pick(predicate: ObjectIterator<Any, Boolean>, thisArg: Any? = definedExternally /* null */): LoDashImplicitObjectWrapper<TResult>
    fun <TResult : Any> pick(vararg predicate: dynamic /* StringRepresentable | Array<StringRepresentable> */): LoDashImplicitObjectWrapper<TResult>
    fun <TResult> result(path: StringRepresentable, defaultValue: TResult? = definedExternally /* null */): TResult
    fun <TResult> result(path: StringRepresentable, defaultValue: (args: Any) -> TResult? = definedExternally /* null */): TResult
    fun <TResult> result(path: Array<StringRepresentable>, defaultValue: TResult? = definedExternally /* null */): TResult
    fun <TResult> result(path: Array<StringRepresentable>, defaultValue: (args: Any) -> TResult? = definedExternally /* null */): TResult
    fun <TResult> set(path: StringRepresentable, value: Any): LoDashImplicitObjectWrapper<TResult>
    fun <TResult> set(path: Array<StringRepresentable>, value: Any): LoDashImplicitObjectWrapper<TResult>
    fun <V, TResult> set(path: StringRepresentable, value: V): LoDashImplicitObjectWrapper<TResult>
    fun <V, TResult> set(path: Array<StringRepresentable>, value: V): LoDashImplicitObjectWrapper<TResult>
    fun <T, TResult> transform(iteratee: MemoVoidDictionaryIterator<T, Dictionary<TResult>>? = definedExternally /* null */, accumulator: Dictionary<TResult>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashImplicitObjectWrapper<Dictionary<TResult>>
    fun <T, TResult> transform(iteratee: MemoVoidDictionaryIterator<T, Array<TResult>>? = definedExternally /* null */, accumulator: Array<TResult>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashImplicitArrayWrapper<TResult>
    fun <T> values(): LoDashImplicitArrayWrapper<T>
    fun <T> valuesIn(): LoDashImplicitArrayWrapper<T>
    fun <TResult> attempt(vararg args: Any): dynamic /* TResult | Error */
    fun callback(thisArg: Any? = definedExternally /* null */): LoDashImplicitObjectWrapper<(obj: Any) -> Boolean>
    fun <TResult> callback(thisArg: Any? = definedExternally /* null */): LoDashImplicitObjectWrapper<(args: Any) -> TResult>
    fun identity(): T
    fun iteratee(thisArg: Any? = definedExternally /* null */): LoDashImplicitObjectWrapper<(obj: Any) -> Boolean>
    fun <TResult> iteratee(thisArg: Any? = definedExternally /* null */): LoDashImplicitObjectWrapper<(args: Any) -> TResult>
    fun <TResult> methodOf(vararg args: Any): LoDashImplicitObjectWrapper<(path: dynamic /* StringRepresentable | Array<StringRepresentable> */) -> TResult>
    fun <TResult> mixin(source: Dictionary<Function<*>>, options: MixinOptions? = definedExternally /* null */): LoDashImplicitObjectWrapper<TResult>
    fun <TResult> mixin(options: MixinOptions? = definedExternally /* null */): LoDashImplicitObjectWrapper<TResult>
    fun propertyOf(): LoDashImplicitObjectWrapper<(path: dynamic /* String | Array<String> */) -> Any>
    fun runInContext(): dynamic /* "TypeQuery" kind unsupported yet here! (index.d.ts:15752:24 to 15752:33) */
}
external interface LoDashExplicitObjectWrapper<T> : LoDashExplicitWrapperBase<T, LoDashExplicitObjectWrapper<T>> {
    fun join(separator: String? = definedExternally /* null */): LoDashExplicitWrapper<String>
    fun <TResult> chunk(size: Number? = definedExternally /* null */): LoDashExplicitArrayWrapper<Array<TResult>>
    fun <TResult> compact(): LoDashExplicitArrayWrapper<TResult>
    fun <TValue> difference(vararg values: dynamic /* Array<TValue> | List<TValue> */): LoDashExplicitArrayWrapper<TValue>
    fun <T> drop(n: Number? = definedExternally /* null */): LoDashExplicitArrayWrapper<T>
    fun <TResult> dropRight(n: Number? = definedExternally /* null */): LoDashExplicitArrayWrapper<TResult>
    fun <TValue> dropRightWhile(predicate: ListIterator<TValue, Boolean>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashExplicitArrayWrapper<TValue>
    fun <TValue> dropRightWhile(predicate: String? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashExplicitArrayWrapper<TValue>
    fun <TWhere, TValue> dropRightWhile(predicate: TWhere? = definedExternally /* null */): LoDashExplicitArrayWrapper<TValue>
    fun <TValue> dropWhile(predicate: ListIterator<TValue, Boolean>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashExplicitArrayWrapper<TValue>
    fun <TValue> dropWhile(predicate: String? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashExplicitArrayWrapper<TValue>
    fun <TWhere, TValue> dropWhile(predicate: TWhere? = definedExternally /* null */): LoDashExplicitArrayWrapper<TValue>
    fun <T> fill(value: T, start: Number? = definedExternally /* null */, end: Number? = definedExternally /* null */): LoDashExplicitObjectWrapper<List<T>>
    fun <TResult> findIndex(predicate: ListIterator<TResult, Boolean>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashExplicitWrapper<Number>
    fun findIndex(predicate: String? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashExplicitWrapper<Number>
    fun <W> findIndex(predicate: W? = definedExternally /* null */): LoDashExplicitWrapper<Number>
    fun <TResult> findLastIndex(predicate: ListIterator<TResult, Boolean>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashExplicitWrapper<Number>
    fun findLastIndex(predicate: String? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashExplicitWrapper<Number>
    fun <W> findLastIndex(predicate: W? = definedExternally /* null */): LoDashExplicitWrapper<Number>
    fun <T> first(): T
    fun <TResult> flatten(isDeep: Boolean? = definedExternally /* null */): LoDashExplicitArrayWrapper<TResult>
    fun <T> flattenDeep(): LoDashExplicitArrayWrapper<T>
    fun <T> head(): T
    fun <TValue> indexOf(value: TValue, fromIndex: Boolean? = definedExternally /* null */): LoDashExplicitWrapper<Number>
    fun <TValue> indexOf(value: TValue, fromIndex: Number? = definedExternally /* null */): LoDashExplicitWrapper<Number>
    fun <T> initial(): LoDashExplicitArrayWrapper<T>
    fun <TResult> intersection(vararg arrays: dynamic /* Array<TResult> | List<TResult> */): LoDashExplicitArrayWrapper<TResult>
    fun <T> last(): T
    fun <TResult> lastIndexOf(value: TResult, fromIndex: Boolean? = definedExternally /* null */): LoDashExplicitWrapper<Number>
    fun <TResult> lastIndexOf(value: TResult, fromIndex: Number? = definedExternally /* null */): LoDashExplicitWrapper<Number>
    fun <TValues, TResult : Any> `object`(values: List<TValues>? = definedExternally /* null */): lodash.LoDashExplicitObjectWrapper<TResult>
    fun <TResult : Any> `object`(values: List<Any>? = definedExternally /* null */): lodash.LoDashExplicitObjectWrapper<TResult>
    fun `object`(values: List<Any>? = definedExternally /* null */): lodash.LoDashExplicitObjectWrapper<lodash.Dictionary<Any>>
    fun <TValue> pull(vararg values: TValue): LoDashExplicitObjectWrapper<List<TValue>>
    fun <T> pullAt(vararg indexes: dynamic /* Number | Array<Number> */): LoDashExplicitArrayWrapper<T>
    fun <TResult> remove(predicate: ListIterator<TResult, Boolean>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashExplicitArrayWrapper<TResult>
    fun <TResult> remove(predicate: String? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashExplicitArrayWrapper<TResult>
    fun <W, TResult> remove(predicate: W? = definedExternally /* null */): LoDashExplicitArrayWrapper<TResult>
    fun <T> rest(): LoDashExplicitArrayWrapper<T>
    fun <T, TSort> sortedIndex(value: T, iteratee: ((x: T) -> TSort)? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashExplicitWrapper<Number>
    fun <T> sortedIndex(value: T, iteratee: ((x: T) -> Any)? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashExplicitWrapper<Number>
    fun <T> sortedIndex(value: T, iteratee: String): LoDashExplicitWrapper<Number>
    fun <W, T> sortedIndex(value: T, iteratee: W): LoDashExplicitWrapper<Number>
    fun <T> sortedIndex(value: T, iteratee: Any): LoDashExplicitWrapper<Number>
    fun <T, TSort> sortedLastIndex(value: T, iteratee: ((x: T) -> TSort)? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashExplicitWrapper<Number>
    fun <T> sortedLastIndex(value: T, iteratee: ((x: T) -> Any)? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashExplicitWrapper<Number>
    fun <T> sortedLastIndex(value: T, iteratee: String): LoDashExplicitWrapper<Number>
    fun <W, T> sortedLastIndex(value: T, iteratee: W): LoDashExplicitWrapper<Number>
    fun <T> sortedLastIndex(value: T, iteratee: Any): LoDashExplicitWrapper<Number>
    fun <T> tail(): LoDashExplicitArrayWrapper<T>
    fun <TResult> take(n: Number? = definedExternally /* null */): LoDashExplicitArrayWrapper<TResult>
    fun <TResult> takeRight(n: Number? = definedExternally /* null */): LoDashExplicitArrayWrapper<TResult>
    fun <TValue> takeRightWhile(predicate: ListIterator<TValue, Boolean>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashExplicitArrayWrapper<TValue>
    fun <TValue> takeRightWhile(predicate: String? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashExplicitArrayWrapper<TValue>
    fun <TWhere, TValue> takeRightWhile(predicate: TWhere? = definedExternally /* null */): LoDashExplicitArrayWrapper<TValue>
    fun <TValue> takeWhile(predicate: ListIterator<TValue, Boolean>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashExplicitArrayWrapper<TValue>
    fun <TValue> takeWhile(predicate: String? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashExplicitArrayWrapper<TValue>
    fun <TWhere, TValue> takeWhile(predicate: TWhere? = definedExternally /* null */): LoDashExplicitArrayWrapper<TValue>
    fun <T> union(vararg arrays: List<T>): LoDashExplicitArrayWrapper<T>
    fun <T> uniq(isSorted: Boolean? = definedExternally /* null */, iteratee: ListIterator<T, Any>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashExplicitArrayWrapper<T>
    fun <T, TSort> uniq(isSorted: Boolean? = definedExternally /* null */, iteratee: ListIterator<T, TSort>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashExplicitArrayWrapper<T>
    fun <T> uniq(iteratee: ListIterator<T, Any>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashExplicitArrayWrapper<T>
    fun <T, TSort> uniq(iteratee: ListIterator<T, TSort>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashExplicitArrayWrapper<T>
    fun <T> uniq(isSorted: Boolean? = definedExternally /* null */, iteratee: String? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashExplicitArrayWrapper<T>
    fun <T> uniq(iteratee: String? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashExplicitArrayWrapper<T>
    fun <T> uniq(isSorted: Boolean? = definedExternally /* null */, iteratee: Any? = definedExternally /* null */): LoDashExplicitArrayWrapper<T>
    fun <TWhere : Any, T> uniq(isSorted: Boolean? = definedExternally /* null */, iteratee: TWhere? = definedExternally /* null */): LoDashExplicitArrayWrapper<T>
    fun <T> uniq(iteratee: Any? = definedExternally /* null */): LoDashExplicitArrayWrapper<T>
    fun <TWhere : Any, T> uniq(iteratee: TWhere? = definedExternally /* null */): LoDashExplicitArrayWrapper<T>
    fun <T> unique(isSorted: Boolean? = definedExternally /* null */, iteratee: ListIterator<T, Any>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashExplicitArrayWrapper<T>
    fun <T, TSort> unique(isSorted: Boolean? = definedExternally /* null */, iteratee: ListIterator<T, TSort>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashExplicitArrayWrapper<T>
    fun <T> unique(iteratee: ListIterator<T, Any>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashExplicitArrayWrapper<T>
    fun <T, TSort> unique(iteratee: ListIterator<T, TSort>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashExplicitArrayWrapper<T>
    fun <T> unique(isSorted: Boolean? = definedExternally /* null */, iteratee: String? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashExplicitArrayWrapper<T>
    fun <T> unique(iteratee: String? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashExplicitArrayWrapper<T>
    fun <T> unique(isSorted: Boolean? = definedExternally /* null */, iteratee: Any? = definedExternally /* null */): LoDashExplicitArrayWrapper<T>
    fun <TWhere : Any, T> unique(isSorted: Boolean? = definedExternally /* null */, iteratee: TWhere? = definedExternally /* null */): LoDashExplicitArrayWrapper<T>
    fun <T> unique(iteratee: Any? = definedExternally /* null */): LoDashExplicitArrayWrapper<T>
    fun <TWhere : Any, T> unique(iteratee: TWhere? = definedExternally /* null */): LoDashExplicitArrayWrapper<T>
    fun <T> unzip(): LoDashExplicitArrayWrapper<Array<T>>
    fun <T> without(vararg values: T): LoDashExplicitArrayWrapper<T>
    fun <T> xor(vararg arrays: List<T>): LoDashExplicitArrayWrapper<T>
    fun <T> zip(vararg arrays: List<T>): lodash.LoDashExplicitArrayWrapper<Array<T>>
    fun <TValues, TResult : Any> zipObject(values: List<TValues>? = definedExternally /* null */): lodash.LoDashExplicitObjectWrapper<TResult>
    fun <TResult : Any> zipObject(values: List<Any>? = definedExternally /* null */): lodash.LoDashExplicitObjectWrapper<TResult>
    fun zipObject(values: List<Any>? = definedExternally /* null */): lodash.LoDashExplicitObjectWrapper<lodash.Dictionary<Any>>
    fun <TResult> all(predicate: ListIterator<TResult, Boolean>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashExplicitWrapper<Boolean>
    fun <TResult> all(predicate: DictionaryIterator<TResult, Boolean>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashExplicitWrapper<Boolean>
    fun all(predicate: String? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashExplicitWrapper<Boolean>
    fun <TObject : Any> all(predicate: TObject? = definedExternally /* null */): LoDashExplicitWrapper<Boolean>
    fun <TResult> any(predicate: ListIterator<TResult, Boolean>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashExplicitWrapper<Boolean>
    fun <TResult> any(predicate: DictionaryIterator<TResult, Boolean>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashExplicitWrapper<Boolean>
    fun <TResult> any(predicate: NumericDictionaryIterator<T, Boolean>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashExplicitWrapper<Boolean>
    fun <TResult> any(predicate: ObjectIterator<Any, Boolean>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashExplicitWrapper<Boolean>
    fun any(predicate: String? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashExplicitWrapper<Boolean>
    fun <TObject : Any> any(predicate: TObject? = definedExternally /* null */): LoDashExplicitWrapper<Boolean>
    fun <T> at(vararg props: dynamic /* Number | String | Array<dynamic /* Number | String */> */): LoDashExplicitArrayWrapper<T>
    fun <TValue, TResult> collect(iteratee: ListIterator<TValue, TResult>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashExplicitArrayWrapper<TResult>
    fun <TValue, TResult> collect(iteratee: DictionaryIterator<TValue, TResult>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashExplicitArrayWrapper<TResult>
    fun <TValue, TResult> collect(iteratee: String? = definedExternally /* null */): LoDashExplicitArrayWrapper<TResult>
    fun <TObject : Any> collect(iteratee: TObject? = definedExternally /* null */): LoDashExplicitArrayWrapper<Boolean>
    fun <TValue> contains(target: TValue, fromIndex: Number? = definedExternally /* null */): LoDashExplicitWrapper<Boolean>
    fun <T> countBy(iteratee: ListIterator<T, Any>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashExplicitObjectWrapper<Dictionary<Number>>
    fun <T> countBy(iteratee: DictionaryIterator<T, Any>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashExplicitObjectWrapper<Dictionary<Number>>
    fun <T> countBy(iteratee: NumericDictionaryIterator<T, Any>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashExplicitObjectWrapper<Dictionary<Number>>
    fun countBy(iteratee: String? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashExplicitObjectWrapper<Dictionary<Number>>
    fun <W> countBy(iteratee: W? = definedExternally /* null */): LoDashExplicitObjectWrapper<Dictionary<Number>>
    fun <TValue> each(iteratee: ListIterator<TValue, Any>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashExplicitObjectWrapper<T>
    fun <TValue> each(iteratee: DictionaryIterator<TValue, Any>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashExplicitObjectWrapper<T>
    fun <TValue> eachRight(iteratee: ListIterator<TValue, Any>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashExplicitObjectWrapper<T>
    fun <TValue> eachRight(iteratee: DictionaryIterator<TValue, Any>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashExplicitObjectWrapper<T>
    fun <TResult> every(predicate: ListIterator<TResult, Boolean>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashExplicitWrapper<Boolean>
    fun <TResult> every(predicate: DictionaryIterator<TResult, Boolean>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashExplicitWrapper<Boolean>
    fun every(predicate: String? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashExplicitWrapper<Boolean>
    fun <TObject : Any> every(predicate: TObject? = definedExternally /* null */): LoDashExplicitWrapper<Boolean>
    fun <T> filter(predicate: ListIterator<T, Boolean>, thisArg: Any? = definedExternally /* null */): LoDashExplicitArrayWrapper<T>
    fun <T> filter(predicate: DictionaryIterator<T, Boolean>, thisArg: Any? = definedExternally /* null */): LoDashExplicitArrayWrapper<T>
    fun <T> filter(predicate: String, thisArg: Any? = definedExternally /* null */): LoDashExplicitArrayWrapper<T>
    fun <W, T> filter(predicate: W): LoDashExplicitArrayWrapper<T>
    fun <TValue> forEach(iteratee: ListIterator<TValue, Any>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashExplicitObjectWrapper<T>
    fun <TValue> forEach(iteratee: DictionaryIterator<TValue, Any>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashExplicitObjectWrapper<T>
    fun <TValue> forEachRight(iteratee: ListIterator<TValue, Any>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashExplicitObjectWrapper<T>
    fun <TValue> forEachRight(iteratee: DictionaryIterator<TValue, Any>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashExplicitObjectWrapper<T>
    fun <T, TKey> groupBy(iteratee: ListIterator<T, TKey>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashExplicitObjectWrapper<Dictionary<Array<T>>>
    fun <T, TKey> groupBy(iteratee: DictionaryIterator<T, TKey>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashExplicitObjectWrapper<Dictionary<Array<T>>>
    fun <T> groupBy(iteratee: ListIterator<T, Any>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashExplicitObjectWrapper<Dictionary<Array<T>>>
    fun <T> groupBy(iteratee: DictionaryIterator<T, Any>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashExplicitObjectWrapper<Dictionary<Array<T>>>
    fun <T, TValue> groupBy(iteratee: String? = definedExternally /* null */, thisArg: TValue? = definedExternally /* null */): LoDashExplicitObjectWrapper<Dictionary<Array<T>>>
    fun <T> groupBy(iteratee: String? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashExplicitObjectWrapper<Dictionary<Array<T>>>
    fun <TWhere, T> groupBy(iteratee: TWhere? = definedExternally /* null */): LoDashExplicitObjectWrapper<Dictionary<Array<T>>>
    fun <T> groupBy(iteratee: Any? = definedExternally /* null */): LoDashExplicitObjectWrapper<Dictionary<Array<T>>>
    fun <TValue> include(target: TValue, fromIndex: Number? = definedExternally /* null */): LoDashExplicitWrapper<Boolean>
    fun <TValue> includes(target: TValue, fromIndex: Number? = definedExternally /* null */): LoDashExplicitWrapper<Boolean>
    fun <T> indexBy(iteratee: ListIterator<T, Any>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashExplicitObjectWrapper<Dictionary<T>>
    fun <T> indexBy(iteratee: NumericDictionaryIterator<T, Any>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashExplicitObjectWrapper<Dictionary<T>>
    fun <T> indexBy(iteratee: DictionaryIterator<T, Any>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashExplicitObjectWrapper<Dictionary<T>>
    fun <T> indexBy(iteratee: String? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashExplicitObjectWrapper<Dictionary<T>>
    fun <W : Any, T> indexBy(iteratee: W? = definedExternally /* null */): LoDashExplicitObjectWrapper<Dictionary<T>>
    fun <T> indexBy(iteratee: Any? = definedExternally /* null */): LoDashExplicitObjectWrapper<Dictionary<T>>
    fun <TValue, TResult> map(iteratee: ListIterator<TValue, TResult>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashExplicitArrayWrapper<TResult>
    fun <TValue, TResult> map(iteratee: DictionaryIterator<TValue, TResult>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashExplicitArrayWrapper<TResult>
    fun <TValue, TResult> map(iteratee: String? = definedExternally /* null */): LoDashExplicitArrayWrapper<TResult>
    fun <TObject : Any> map(iteratee: TObject? = definedExternally /* null */): LoDashExplicitArrayWrapper<Boolean>
    fun <TResult> pluck(path: StringRepresentable): LoDashExplicitArrayWrapper<TResult>
    fun <TResult> pluck(path: Array<StringRepresentable>): LoDashExplicitArrayWrapper<TResult>
    fun <TValue, TResult> reduce(callback: MemoIterator<TValue, TResult>, accumulator: TResult, thisArg: Any? = definedExternally /* null */): LoDashExplicitObjectWrapper<TResult>
    fun <TValue, TResult> reduce(callback: MemoIterator<TValue, TResult>, thisArg: Any? = definedExternally /* null */): LoDashExplicitObjectWrapper<TResult>
    fun <TValue, TResult> inject(callback: MemoIterator<TValue, TResult>, accumulator: TResult, thisArg: Any? = definedExternally /* null */): LoDashExplicitObjectWrapper<TResult>
    fun <TValue, TResult> inject(callback: MemoIterator<TValue, TResult>, thisArg: Any? = definedExternally /* null */): LoDashExplicitObjectWrapper<TResult>
    fun <TValue, TResult> foldl(callback: MemoIterator<TValue, TResult>, accumulator: TResult, thisArg: Any? = definedExternally /* null */): LoDashExplicitObjectWrapper<TResult>
    fun <TValue, TResult> foldl(callback: MemoIterator<TValue, TResult>, thisArg: Any? = definedExternally /* null */): LoDashExplicitObjectWrapper<TResult>
    fun <T> reject(predicate: ListIterator<T, Boolean>, thisArg: Any? = definedExternally /* null */): LoDashExplicitArrayWrapper<T>
    fun <T> reject(predicate: DictionaryIterator<T, Boolean>, thisArg: Any? = definedExternally /* null */): LoDashExplicitArrayWrapper<T>
    fun <T> reject(predicate: String, thisArg: Any? = definedExternally /* null */): LoDashExplicitArrayWrapper<T>
    fun <W, T> reject(predicate: W): LoDashExplicitArrayWrapper<T>
    fun <T> select(predicate: ListIterator<T, Boolean>, thisArg: Any? = definedExternally /* null */): LoDashExplicitArrayWrapper<T>
    fun <T> select(predicate: DictionaryIterator<T, Boolean>, thisArg: Any? = definedExternally /* null */): LoDashExplicitArrayWrapper<T>
    fun <T> select(predicate: String, thisArg: Any? = definedExternally /* null */): LoDashExplicitArrayWrapper<T>
    fun <W, T> select(predicate: W): LoDashExplicitArrayWrapper<T>
    fun <T> sample(n: Number): LoDashExplicitArrayWrapper<T>
    fun <TWrapper> sample(): TWrapper
    fun <T> shuffle(): LoDashExplicitArrayWrapper<T>
    fun size(): LoDashExplicitWrapper<Number>
    fun <TResult> some(predicate: ListIterator<TResult, Boolean>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashExplicitWrapper<Boolean>
    fun <TResult> some(predicate: DictionaryIterator<TResult, Boolean>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashExplicitWrapper<Boolean>
    fun <TResult> some(predicate: NumericDictionaryIterator<T, Boolean>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashExplicitWrapper<Boolean>
    fun <TResult> some(predicate: ObjectIterator<Any, Boolean>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashExplicitWrapper<Boolean>
    fun some(predicate: String? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashExplicitWrapper<Boolean>
    fun <TObject : Any> some(predicate: TObject? = definedExternally /* null */): LoDashExplicitWrapper<Boolean>
    fun <T, TSort> sortBy(iteratee: ListIterator<T, TSort>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashExplicitArrayWrapper<T>
    fun <T, TSort> sortBy(iteratee: DictionaryIterator<T, TSort>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashExplicitArrayWrapper<T>
    fun <T> sortBy(iteratee: String): LoDashExplicitArrayWrapper<T>
    fun <W : Any, T> sortBy(whereValue: W): LoDashExplicitArrayWrapper<T>
    fun <T> sortBy(): LoDashExplicitArrayWrapper<T>
    fun <W : Any, T> sortByOrder(iteratees: ListIterator<T, Any>, orders: dynamic /* Boolean? | String? | Array<dynamic /* Boolean | String */>? */ = definedExternally /* null */): LoDashExplicitArrayWrapper<T>
    fun <W : Any, T> sortByOrder(iteratees: String, orders: dynamic /* Boolean? | String? | Array<dynamic /* Boolean | String */>? */ = definedExternally /* null */): LoDashExplicitArrayWrapper<T>
    fun <W : Any, T> sortByOrder(iteratees: W, orders: dynamic /* Boolean? | String? | Array<dynamic /* Boolean | String */>? */ = definedExternally /* null */): LoDashExplicitArrayWrapper<T>
    fun <W : Any, T> sortByOrder(iteratees: Array<dynamic /* ListIterator<T, Any> | String | W */>, orders: dynamic /* Boolean? | String? | Array<dynamic /* Boolean | String */>? */ = definedExternally /* null */): LoDashExplicitArrayWrapper<T>
    fun <T> sortByOrder(iteratees: ListIterator<T, Any>, orders: dynamic /* Boolean? | String? | Array<dynamic /* Boolean | String */>? */ = definedExternally /* null */): LoDashExplicitArrayWrapper<T>
    fun <T> sortByOrder(iteratees: String, orders: dynamic /* Boolean? | String? | Array<dynamic /* Boolean | String */>? */ = definedExternally /* null */): LoDashExplicitArrayWrapper<T>
    fun <T> sortByOrder(iteratees: Any, orders: dynamic /* Boolean? | String? | Array<dynamic /* Boolean | String */>? */ = definedExternally /* null */): LoDashExplicitArrayWrapper<T>
    fun <T> sortByOrder(iteratees: Array<dynamic /* ListIterator<T, Any> | String | Any */>, orders: dynamic /* Boolean? | String? | Array<dynamic /* Boolean | String */>? */ = definedExternally /* null */): LoDashExplicitArrayWrapper<T>
    fun <W : Any, T> sortByOrder(iteratees: NumericDictionaryIterator<T, Any>, orders: dynamic /* Boolean? | String? | Array<dynamic /* Boolean | String */>? */ = definedExternally /* null */): LoDashExplicitArrayWrapper<T>
    fun <W : Any, T> sortByOrder(iteratees: String, orders: dynamic /* Boolean? | String? | Array<dynamic /* Boolean | String */>? */ = definedExternally /* null */): LoDashExplicitArrayWrapper<T>
    fun <W : Any, T> sortByOrder(iteratees: W, orders: dynamic /* Boolean? | String? | Array<dynamic /* Boolean | String */>? */ = definedExternally /* null */): LoDashExplicitArrayWrapper<T>
    fun <W : Any, T> sortByOrder(iteratees: Array<dynamic /* NumericDictionaryIterator<T, Any> | String | W */>, orders: dynamic /* Boolean? | String? | Array<dynamic /* Boolean | String */>? */ = definedExternally /* null */): LoDashExplicitArrayWrapper<T>
    fun <T> sortByOrder(iteratees: NumericDictionaryIterator<T, Any>, orders: dynamic /* Boolean? | String? | Array<dynamic /* Boolean | String */>? */ = definedExternally /* null */): LoDashExplicitArrayWrapper<T>
    fun <T> sortByOrder(iteratees: String, orders: dynamic /* Boolean? | String? | Array<dynamic /* Boolean | String */>? */ = definedExternally /* null */): LoDashExplicitArrayWrapper<T>
    fun <T> sortByOrder(iteratees: Any, orders: dynamic /* Boolean? | String? | Array<dynamic /* Boolean | String */>? */ = definedExternally /* null */): LoDashExplicitArrayWrapper<T>
    fun <T> sortByOrder(iteratees: Array<dynamic /* NumericDictionaryIterator<T, Any> | String | Any */>, orders: dynamic /* Boolean? | String? | Array<dynamic /* Boolean | String */>? */ = definedExternally /* null */): LoDashExplicitArrayWrapper<T>
    fun <W : Any, T> sortByOrder(iteratees: DictionaryIterator<T, Any>, orders: dynamic /* Boolean? | String? | Array<dynamic /* Boolean | String */>? */ = definedExternally /* null */): LoDashExplicitArrayWrapper<T>
    fun <W : Any, T> sortByOrder(iteratees: String, orders: dynamic /* Boolean? | String? | Array<dynamic /* Boolean | String */>? */ = definedExternally /* null */): LoDashExplicitArrayWrapper<T>
    fun <W : Any, T> sortByOrder(iteratees: W, orders: dynamic /* Boolean? | String? | Array<dynamic /* Boolean | String */>? */ = definedExternally /* null */): LoDashExplicitArrayWrapper<T>
    fun <W : Any, T> sortByOrder(iteratees: Array<dynamic /* DictionaryIterator<T, Any> | String | W */>, orders: dynamic /* Boolean? | String? | Array<dynamic /* Boolean | String */>? */ = definedExternally /* null */): LoDashExplicitArrayWrapper<T>
    fun <T> sortByOrder(iteratees: DictionaryIterator<T, Any>, orders: dynamic /* Boolean? | String? | Array<dynamic /* Boolean | String */>? */ = definedExternally /* null */): LoDashExplicitArrayWrapper<T>
    fun <T> sortByOrder(iteratees: String, orders: dynamic /* Boolean? | String? | Array<dynamic /* Boolean | String */>? */ = definedExternally /* null */): LoDashExplicitArrayWrapper<T>
    fun <T> sortByOrder(iteratees: Any, orders: dynamic /* Boolean? | String? | Array<dynamic /* Boolean | String */>? */ = definedExternally /* null */): LoDashExplicitArrayWrapper<T>
    fun <T> sortByOrder(iteratees: Array<dynamic /* DictionaryIterator<T, Any> | String | Any */>, orders: dynamic /* Boolean? | String? | Array<dynamic /* Boolean | String */>? */ = definedExternally /* null */): LoDashExplicitArrayWrapper<T>
    fun <TResult : Function<*>> ary(n: Number? = definedExternally /* null */): LoDashExplicitObjectWrapper<TResult>
    fun <TResult : Function<*>> backflow(vararg funcs: Function<*>): LoDashExplicitObjectWrapper<TResult>
    fun <TResult : Function<*>> bind(thisArg: Any, vararg partials: Any): LoDashExplicitObjectWrapper<TResult>
    fun bindAll(vararg methodNames: dynamic /* String | Array<String> */): LoDashExplicitObjectWrapper<T>
    fun <TResult : Function<*>> bindKey(key: Any, vararg partials: Any): LoDashExplicitObjectWrapper<TResult>
    fun <TResult : Function<*>> compose(vararg funcs: Function<*>): LoDashExplicitObjectWrapper<TResult>
    fun debounce(wait: Number? = definedExternally /* null */, options: DebounceSettings? = definedExternally /* null */): LoDashExplicitObjectWrapper<T /* T & Cancelable */>
    fun defer(vararg args: Any): LoDashExplicitWrapper<Number>
    fun delay(wait: Number, vararg args: Any): LoDashExplicitWrapper<Number>
    fun <TResult : Function<*>> flow(vararg funcs: Function<*>): LoDashExplicitObjectWrapper<TResult>
    fun <TResult : Function<*>> flowRight(vararg funcs: Function<*>): LoDashExplicitObjectWrapper<TResult>
    fun memoize(resolver: Function<*>? = definedExternally /* null */): LoDashExplicitObjectWrapper<T /* T & MemoizedFunction */>
    fun <TResult : Function<*>> modArgs(vararg transforms: Function<*>): LoDashExplicitObjectWrapper<TResult>
    fun <TResult : Function<*>> modArgs(transforms: Array<Function<*>>): LoDashExplicitObjectWrapper<TResult>
    fun negate(): LoDashExplicitObjectWrapper<(args: Any) -> Boolean>
    fun <TResult : Function<*>> negate(): LoDashExplicitObjectWrapper<TResult>
    fun once(): LoDashExplicitObjectWrapper<T>
    fun <TResult : Function<*>> restParam(start: Number? = definedExternally /* null */): LoDashExplicitObjectWrapper<TResult>
    fun <T : Function<*>> spread(): LoDashExplicitObjectWrapper<T>
    fun throttle(wait: Number? = definedExternally /* null */, options: ThrottleSettings? = definedExternally /* null */): LoDashExplicitObjectWrapper<T /* T & Cancelable */>
    fun <W : Function<*>, R : Function<*>> wrap(wrapper: W): LoDashExplicitObjectWrapper<R>
    fun <R : Function<*>> wrap(wrapper: Function<*>): LoDashExplicitObjectWrapper<R>
    fun <TResult /* Number | String | Boolean */> clone(isDeep: Boolean, customizer: CloneCustomizer<T, TResult>, thisArg: Any? = definedExternally /* null */): LoDashExplicitWrapper<TResult>
    fun <TResult> clone(isDeep: Boolean, customizer: CloneCustomizer<T, Array<TResult>>, thisArg: Any? = definedExternally /* null */): LoDashExplicitArrayWrapper<TResult>
    fun <TResult : Any> clone(isDeep: Boolean, customizer: CloneCustomizer<T, TResult>, thisArg: Any? = definedExternally /* null */): LoDashExplicitObjectWrapper<TResult>
    fun <TResult /* Number | String | Boolean */> clone(customizer: CloneCustomizer<T, TResult>, thisArg: Any? = definedExternally /* null */): LoDashExplicitWrapper<TResult>
    fun <TResult> clone(customizer: CloneCustomizer<T, Array<TResult>>, thisArg: Any? = definedExternally /* null */): LoDashExplicitArrayWrapper<TResult>
    fun <TResult : Any> clone(customizer: CloneCustomizer<T, TResult>, thisArg: Any? = definedExternally /* null */): LoDashExplicitObjectWrapper<TResult>
    fun clone(isDeep: Boolean? = definedExternally /* null */): LoDashExplicitObjectWrapper<T>
    fun <TResult /* Number | String | Boolean */> cloneDeep(customizer: CloneDeepCustomizer<T, TResult>, thisArg: Any? = definedExternally /* null */): LoDashExplicitWrapper<TResult>
    fun <TResult> cloneDeep(customizer: CloneDeepCustomizer<T, Array<TResult>>, thisArg: Any? = definedExternally /* null */): LoDashExplicitArrayWrapper<TResult>
    fun <TResult : Any> cloneDeep(customizer: CloneDeepCustomizer<T, TResult>, thisArg: Any? = definedExternally /* null */): LoDashExplicitObjectWrapper<TResult>
    fun cloneDeep(): LoDashExplicitObjectWrapper<T>
    fun <TResult> toArray(): LoDashExplicitArrayWrapper<TResult>
    fun <TValue> sum(iteratee: ListIterator<TValue, Number>, thisArg: Any? = definedExternally /* null */): LoDashExplicitWrapper<Number>
    fun <TValue> sum(iteratee: DictionaryIterator<TValue, Number>, thisArg: Any? = definedExternally /* null */): LoDashExplicitWrapper<Number>
    fun sum(iteratee: String): LoDashExplicitWrapper<Number>
    fun sum(): LoDashExplicitWrapper<Number>
    fun <TSource : Any, TResult : Any> assign(source: TSource, customizer: AssignCustomizer? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashExplicitObjectWrapper<TResult>
    fun <TSource1 : Any, TSource2 : Any, TResult : Any> assign(source1: TSource1, source2: TSource2, customizer: AssignCustomizer? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashExplicitObjectWrapper<TResult>
    fun <TSource1 : Any, TSource2 : Any, TSource3 : Any, TResult : Any> assign(source1: TSource1, source2: TSource2, source3: TSource3, customizer: AssignCustomizer? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashExplicitObjectWrapper<TResult>
    fun <TSource1 : Any, TSource2 : Any, TSource3 : Any, TSource4 : Any, TResult : Any> assign(source1: TSource1, source2: TSource2, source3: TSource3, source4: TSource4, customizer: AssignCustomizer? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashExplicitObjectWrapper<TResult>
    fun assign(): LoDashExplicitObjectWrapper<T>
    fun <TResult : Any> assign(vararg otherArgs: Any): LoDashExplicitObjectWrapper<TResult>
    fun <U : Any> create(properties: U? = definedExternally /* null */): LoDashExplicitObjectWrapper<T /* T & U */>
    fun <S1 : Any, TResult : Any> defaults(source1: S1, vararg sources: Any): LoDashExplicitObjectWrapper<TResult>
    fun <S1 : Any, S2 : Any, TResult : Any> defaults(source1: S1, source2: S2, vararg sources: Any): LoDashExplicitObjectWrapper<TResult>
    fun <S1 : Any, S2 : Any, S3 : Any, TResult : Any> defaults(source1: S1, source2: S2, source3: S3, vararg sources: Any): LoDashExplicitObjectWrapper<TResult>
    fun <S1 : Any, S2 : Any, S3 : Any, S4 : Any, TResult : Any> defaults(source1: S1, source2: S2, source3: S3, source4: S4, vararg sources: Any): LoDashExplicitObjectWrapper<TResult>
    fun defaults(): LoDashExplicitObjectWrapper<T>
    fun <TResult> defaults(vararg sources: Any): LoDashExplicitObjectWrapper<TResult>
    fun <TSource : Any, TResult : Any> extend(source: TSource, customizer: AssignCustomizer? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashExplicitObjectWrapper<TResult>
    fun <TSource1 : Any, TSource2 : Any, TResult : Any> extend(source1: TSource1, source2: TSource2, customizer: AssignCustomizer? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashExplicitObjectWrapper<TResult>
    fun <TSource1 : Any, TSource2 : Any, TSource3 : Any, TResult : Any> extend(source1: TSource1, source2: TSource2, source3: TSource3, customizer: AssignCustomizer? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashExplicitObjectWrapper<TResult>
    fun <TSource1 : Any, TSource2 : Any, TSource3 : Any, TSource4 : Any, TResult : Any> extend(source1: TSource1, source2: TSource2, source3: TSource3, source4: TSource4, customizer: AssignCustomizer? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashExplicitObjectWrapper<TResult>
    fun extend(): LoDashExplicitObjectWrapper<T>
    fun <TResult : Any> extend(vararg otherArgs: Any): LoDashExplicitObjectWrapper<TResult>
    fun <TValues> findKey(predicate: DictionaryIterator<TValues, Boolean>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashExplicitWrapper<String>
    fun findKey(predicate: ObjectIterator<Any, Boolean>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashExplicitWrapper<String>
    fun findKey(predicate: String? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashExplicitWrapper<String>
    fun <TWhere : Dictionary<Any>> findKey(predicate: TWhere? = definedExternally /* null */): LoDashExplicitWrapper<String>
    fun <TValues> findLastKey(predicate: DictionaryIterator<TValues, Boolean>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashExplicitWrapper<String>
    fun findLastKey(predicate: ObjectIterator<Any, Boolean>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashExplicitWrapper<String>
    fun findLastKey(predicate: String? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashExplicitWrapper<String>
    fun <TWhere : Dictionary<Any>> findLastKey(predicate: TWhere? = definedExternally /* null */): LoDashExplicitWrapper<String>
    fun <TValue> forIn(iteratee: DictionaryIterator<TValue, Any>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): lodash.LoDashExplicitObjectWrapper<T>
    fun <TValue> forInRight(iteratee: DictionaryIterator<TValue, Any>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): lodash.LoDashExplicitObjectWrapper<T>
    fun <TValue> forOwn(iteratee: DictionaryIterator<TValue, Any>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): lodash.LoDashExplicitObjectWrapper<T>
    fun <TValue> forOwnRight(iteratee: DictionaryIterator<TValue, Any>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): lodash.LoDashExplicitObjectWrapper<T>
    fun functions(): lodash.LoDashExplicitArrayWrapper<String>
    fun <TResultWrapper> get(path: StringRepresentable, defaultValue: Any? = definedExternally /* null */): TResultWrapper
    fun <TResultWrapper> get(path: Array<StringRepresentable>, defaultValue: Any? = definedExternally /* null */): TResultWrapper
    fun has(path: StringRepresentable): LoDashExplicitWrapper<Boolean>
    fun has(path: Array<StringRepresentable>): LoDashExplicitWrapper<Boolean>
    fun <TResult : Any> invert(multiValue: Boolean? = definedExternally /* null */): LoDashExplicitObjectWrapper<TResult>
    fun keys(): LoDashExplicitArrayWrapper<String>
    fun keysIn(): LoDashExplicitArrayWrapper<String>
    fun <TResult, TKey> mapKeys(iteratee: ListIterator<TResult, TKey>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashExplicitObjectWrapper<Dictionary<TResult>>
    fun <TResult, TKey> mapKeys(iteratee: DictionaryIterator<TResult, TKey>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashExplicitObjectWrapper<Dictionary<TResult>>
    fun <TResult, TObject : Any> mapKeys(iteratee: TObject? = definedExternally /* null */): LoDashExplicitObjectWrapper<Dictionary<TResult>>
    fun <TResult> mapKeys(iteratee: String? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashExplicitObjectWrapper<Dictionary<TResult>>
    fun methods(): lodash.LoDashExplicitArrayWrapper<String>
    fun <TResult : Any> omit(predicate: ObjectIterator<Any, Boolean>, thisArg: Any? = definedExternally /* null */): LoDashExplicitObjectWrapper<TResult>
    fun <TResult : Any> omit(vararg predicate: dynamic /* StringRepresentable | Array<StringRepresentable> */): LoDashExplicitObjectWrapper<TResult>
    fun <TResult> pairs(): LoDashExplicitArrayWrapper<Array<TResult>>
    fun <TResult : Any> pick(predicate: ObjectIterator<Any, Boolean>, thisArg: Any? = definedExternally /* null */): LoDashExplicitObjectWrapper<TResult>
    fun <TResult : Any> pick(vararg predicate: dynamic /* StringRepresentable | Array<StringRepresentable> */): LoDashExplicitObjectWrapper<TResult>
    fun <TResultWrapper> result(path: StringRepresentable, defaultValue: Any? = definedExternally /* null */): TResultWrapper
    fun <TResultWrapper> result(path: Array<StringRepresentable>, defaultValue: Any? = definedExternally /* null */): TResultWrapper
    fun <TResult> set(path: StringRepresentable, value: Any): LoDashExplicitObjectWrapper<TResult>
    fun <TResult> set(path: Array<StringRepresentable>, value: Any): LoDashExplicitObjectWrapper<TResult>
    fun <V, TResult> set(path: StringRepresentable, value: V): LoDashExplicitObjectWrapper<TResult>
    fun <V, TResult> set(path: Array<StringRepresentable>, value: V): LoDashExplicitObjectWrapper<TResult>
    fun <T> values(): LoDashExplicitArrayWrapper<T>
    fun <T> valuesIn(): LoDashExplicitArrayWrapper<T>
    fun <TResult> attempt(vararg args: Any): LoDashExplicitObjectWrapper<dynamic /* TResult | Error */>
    fun callback(thisArg: Any? = definedExternally /* null */): LoDashExplicitObjectWrapper<(obj: Any) -> Boolean>
    fun <TResult> callback(thisArg: Any? = definedExternally /* null */): LoDashExplicitObjectWrapper<(args: Any) -> TResult>
    fun identity(): LoDashExplicitObjectWrapper<T>
    fun iteratee(thisArg: Any? = definedExternally /* null */): LoDashExplicitObjectWrapper<(obj: Any) -> Boolean>
    fun <TResult> iteratee(thisArg: Any? = definedExternally /* null */): LoDashExplicitObjectWrapper<(args: Any) -> TResult>
    fun <TResult> methodOf(vararg args: Any): LoDashExplicitObjectWrapper<(path: dynamic /* StringRepresentable | Array<StringRepresentable> */) -> TResult>
    fun <TResult> mixin(source: Dictionary<Function<*>>, options: MixinOptions? = definedExternally /* null */): LoDashExplicitObjectWrapper<TResult>
    fun <TResult> mixin(options: MixinOptions? = definedExternally /* null */): LoDashExplicitObjectWrapper<TResult>
    fun propertyOf(): LoDashExplicitObjectWrapper<(path: dynamic /* String | Array<String> */) -> Any>
}
external interface LoDashImplicitArrayWrapper<T> : LoDashImplicitWrapperBase<Array<T>, LoDashImplicitArrayWrapper<T>> {
    fun pop(): T
    fun push(vararg items: T): LoDashImplicitArrayWrapper<T>
    fun shift(): T
    fun sort(compareFn: ((a: T, b: T) -> Number)? = definedExternally /* null */): LoDashImplicitArrayWrapper<T>
    fun splice(start: Number): LoDashImplicitArrayWrapper<T>
    fun splice(start: Number, deleteCount: Number, vararg items: Any): LoDashImplicitArrayWrapper<T>
    fun unshift(vararg items: T): LoDashImplicitArrayWrapper<T>
    fun join(separator: String? = definedExternally /* null */): String
    fun chunk(size: Number? = definedExternally /* null */): LoDashImplicitArrayWrapper<Array<T>>
    fun compact(): LoDashImplicitArrayWrapper<T>
    fun difference(vararg values: dynamic /* Array<T> | List<T> */): LoDashImplicitArrayWrapper<T>
    fun drop(n: Number? = definedExternally /* null */): LoDashImplicitArrayWrapper<T>
    fun dropRight(n: Number? = definedExternally /* null */): LoDashImplicitArrayWrapper<T>
    fun dropRightWhile(predicate: ListIterator<T, Boolean>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashImplicitArrayWrapper<T>
    fun dropRightWhile(predicate: String? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashImplicitArrayWrapper<T>
    fun <TWhere> dropRightWhile(predicate: TWhere? = definedExternally /* null */): LoDashImplicitArrayWrapper<T>
    fun dropWhile(predicate: ListIterator<T, Boolean>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashImplicitArrayWrapper<T>
    fun dropWhile(predicate: String? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashImplicitArrayWrapper<T>
    fun <TWhere> dropWhile(predicate: TWhere? = definedExternally /* null */): LoDashImplicitArrayWrapper<T>
    fun <T> fill(value: T, start: Number? = definedExternally /* null */, end: Number? = definedExternally /* null */): LoDashImplicitArrayWrapper<T>
    fun findIndex(predicate: ListIterator<T, Boolean>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Number
    fun findIndex(predicate: String? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Number
    fun <W> findIndex(predicate: W? = definedExternally /* null */): Number
    fun findLastIndex(predicate: ListIterator<T, Boolean>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Number
    fun findLastIndex(predicate: String? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Number
    fun <W> findLastIndex(predicate: W? = definedExternally /* null */): Number
    fun first(): T
    fun <TResult> flatten(isDeep: Boolean? = definedExternally /* null */): LoDashImplicitArrayWrapper<TResult>
    fun <T> flattenDeep(): LoDashImplicitArrayWrapper<T>
    fun head(): T
    fun indexOf(value: T, fromIndex: Boolean? = definedExternally /* null */): Number
    fun indexOf(value: T, fromIndex: Number? = definedExternally /* null */): Number
    fun initial(): LoDashImplicitArrayWrapper<T>
    fun <TResult> intersection(vararg arrays: dynamic /* Array<TResult> | List<TResult> */): LoDashImplicitArrayWrapper<TResult>
    fun last(): T
    fun lastIndexOf(value: T, fromIndex: Boolean? = definedExternally /* null */): Number
    fun lastIndexOf(value: T, fromIndex: Number? = definedExternally /* null */): Number
    fun <TValues, TResult : Any> `object`(values: List<TValues>? = definedExternally /* null */): lodash.LoDashImplicitObjectWrapper<TResult>
    fun <TResult : Any> `object`(values: List<Any>? = definedExternally /* null */): lodash.LoDashImplicitObjectWrapper<TResult>
    fun `object`(values: List<Any>? = definedExternally /* null */): lodash.LoDashImplicitObjectWrapper<lodash.Dictionary<Any>>
    fun pull(vararg values: T): LoDashImplicitArrayWrapper<T>
    fun pullAt(vararg indexes: dynamic /* Number | Array<Number> */): LoDashImplicitArrayWrapper<T>
    fun remove(predicate: ListIterator<T, Boolean>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashImplicitArrayWrapper<T>
    fun remove(predicate: String? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashImplicitArrayWrapper<T>
    fun <W> remove(predicate: W? = definedExternally /* null */): LoDashImplicitArrayWrapper<T>
    fun rest(): LoDashImplicitArrayWrapper<T>
    fun slice(start: Number? = definedExternally /* null */, end: Number? = definedExternally /* null */): LoDashImplicitArrayWrapper<T>
    fun <TSort> sortedIndex(value: T, iteratee: ((x: T) -> TSort)? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Number
    fun sortedIndex(value: T, iteratee: String): Number
    fun <W> sortedIndex(value: T, iteratee: W): Number
    fun <TSort> sortedLastIndex(value: T, iteratee: ((x: T) -> TSort)? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Number
    fun sortedLastIndex(value: T, iteratee: String): Number
    fun <W> sortedLastIndex(value: T, iteratee: W): Number
    fun tail(): LoDashImplicitArrayWrapper<T>
    fun take(n: Number? = definedExternally /* null */): LoDashImplicitArrayWrapper<T>
    fun takeRight(n: Number? = definedExternally /* null */): LoDashImplicitArrayWrapper<T>
    fun takeRightWhile(predicate: ListIterator<T, Boolean>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashImplicitArrayWrapper<T>
    fun takeRightWhile(predicate: String? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashImplicitArrayWrapper<T>
    fun <TWhere> takeRightWhile(predicate: TWhere? = definedExternally /* null */): LoDashImplicitArrayWrapper<T>
    fun takeWhile(predicate: ListIterator<T, Boolean>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashImplicitArrayWrapper<T>
    fun takeWhile(predicate: String? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashImplicitArrayWrapper<T>
    fun <TWhere> takeWhile(predicate: TWhere? = definedExternally /* null */): LoDashImplicitArrayWrapper<T>
    fun union(vararg arrays: List<T>): LoDashImplicitArrayWrapper<T>
    fun <T> union(vararg arrays: List<T>): LoDashImplicitArrayWrapper<T>
    fun <TSort> uniq(isSorted: Boolean? = definedExternally /* null */, iteratee: ListIterator<T, TSort>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashImplicitArrayWrapper<T>
    fun <TSort> uniq(iteratee: ListIterator<T, TSort>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashImplicitArrayWrapper<T>
    fun uniq(isSorted: Boolean? = definedExternally /* null */, iteratee: String? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashImplicitArrayWrapper<T>
    fun uniq(iteratee: String? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashImplicitArrayWrapper<T>
    fun <TWhere : Any> uniq(isSorted: Boolean? = definedExternally /* null */, iteratee: TWhere? = definedExternally /* null */): LoDashImplicitArrayWrapper<T>
    fun <TWhere : Any> uniq(iteratee: TWhere? = definedExternally /* null */): LoDashImplicitArrayWrapper<T>
    fun <TSort> unique(isSorted: Boolean? = definedExternally /* null */, iteratee: ListIterator<T, TSort>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashImplicitArrayWrapper<T>
    fun <TSort> unique(iteratee: ListIterator<T, TSort>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashImplicitArrayWrapper<T>
    fun unique(isSorted: Boolean? = definedExternally /* null */, iteratee: String? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashImplicitArrayWrapper<T>
    fun unique(iteratee: String? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashImplicitArrayWrapper<T>
    fun <TWhere : Any> unique(isSorted: Boolean? = definedExternally /* null */, iteratee: TWhere? = definedExternally /* null */): LoDashImplicitArrayWrapper<T>
    fun <TWhere : Any> unique(iteratee: TWhere? = definedExternally /* null */): LoDashImplicitArrayWrapper<T>
    fun <T> unzip(): LoDashImplicitArrayWrapper<Array<T>>
    fun <TArr, TResult> unzipWith(iteratee: MemoIterator<TArr, TResult>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashImplicitArrayWrapper<TResult>
    fun without(vararg values: T): LoDashImplicitArrayWrapper<T>
    fun xor(vararg arrays: List<T>): LoDashImplicitArrayWrapper<T>
    fun <T> zip(vararg arrays: List<T>): lodash.LoDashImplicitArrayWrapper<Array<T>>
    fun <TValues, TResult : Any> zipObject(values: List<TValues>? = definedExternally /* null */): lodash.LoDashImplicitObjectWrapper<TResult>
    fun <TResult : Any> zipObject(values: List<Any>? = definedExternally /* null */): lodash.LoDashImplicitObjectWrapper<TResult>
    fun zipObject(values: List<Any>? = definedExternally /* null */): lodash.LoDashImplicitObjectWrapper<lodash.Dictionary<Any>>
    fun <TResult> zipWith(vararg args: Any): LoDashImplicitArrayWrapper<TResult>
    fun chain(): LoDashExplicitArrayWrapper<T>
    fun reverse(): LoDashImplicitArrayWrapper<T>
    fun all(predicate: ListIterator<T, Boolean>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Boolean
    fun all(predicate: String? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Boolean
    fun <TObject : Any> all(predicate: TObject? = definedExternally /* null */): Boolean
    fun any(predicate: ListIterator<T, Boolean>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Boolean
    fun any(predicate: NumericDictionaryIterator<T, Boolean>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Boolean
    fun any(predicate: String? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Boolean
    fun <TObject : Any> any(predicate: TObject? = definedExternally /* null */): Boolean
    fun at(vararg props: dynamic /* Number | String | Array<dynamic /* Number | String */> */): LoDashImplicitArrayWrapper<T>
    fun <TResult> collect(iteratee: ListIterator<T, TResult>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashImplicitArrayWrapper<TResult>
    fun <TResult> collect(iteratee: String? = definedExternally /* null */): LoDashImplicitArrayWrapper<TResult>
    fun <TObject : Any> collect(iteratee: TObject? = definedExternally /* null */): LoDashImplicitArrayWrapper<Boolean>
    fun contains(target: T, fromIndex: Number? = definedExternally /* null */): Boolean
    fun countBy(iteratee: ListIterator<T, Any>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashImplicitObjectWrapper<Dictionary<Number>>
    fun countBy(iteratee: String? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashImplicitObjectWrapper<Dictionary<Number>>
    fun <W> countBy(iteratee: W? = definedExternally /* null */): LoDashImplicitObjectWrapper<Dictionary<Number>>
    fun detect(predicate: ListIterator<T, Boolean>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): T
    fun detect(predicate: String? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): T
    fun <TObject : Any> detect(predicate: TObject? = definedExternally /* null */): T
    fun each(iteratee: ListIterator<T, Any>, thisArg: Any? = definedExternally /* null */): LoDashImplicitArrayWrapper<T>
    fun eachRight(iteratee: ListIterator<T, Any>, thisArg: Any? = definedExternally /* null */): LoDashImplicitArrayWrapper<T>
    fun every(predicate: ListIterator<T, Boolean>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Boolean
    fun every(predicate: String? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Boolean
    fun <TObject : Any> every(predicate: TObject? = definedExternally /* null */): Boolean
    fun filter(predicate: ListIterator<T, Boolean>, thisArg: Any? = definedExternally /* null */): LoDashImplicitArrayWrapper<T>
    fun filter(predicate: String, thisArg: Any? = definedExternally /* null */): LoDashImplicitArrayWrapper<T>
    fun <W> filter(predicate: W): LoDashImplicitArrayWrapper<T>
    fun find(predicate: ListIterator<T, Boolean>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): T?
    fun find(predicate: String? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): T?
    fun <TObject : Any> find(predicate: TObject? = definedExternally /* null */): T?
    fun findLast(callback: ListIterator<T, Boolean>, thisArg: Any? = definedExternally /* null */): T?
    fun <W> findLast(whereValue: W): T?
    fun findLast(pluckValue: String): T?
    fun forEach(iteratee: ListIterator<T, Any>, thisArg: Any? = definedExternally /* null */): LoDashImplicitArrayWrapper<T>
    fun forEachRight(iteratee: ListIterator<T, Any>, thisArg: Any? = definedExternally /* null */): LoDashImplicitArrayWrapper<T>
    fun <TKey> groupBy(iteratee: ListIterator<T, TKey>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashImplicitObjectWrapper<Dictionary<Array<T>>>
    fun <TValue> groupBy(iteratee: String? = definedExternally /* null */, thisArg: TValue? = definedExternally /* null */): LoDashImplicitObjectWrapper<Dictionary<Array<T>>>
    fun <TWhere> groupBy(iteratee: TWhere? = definedExternally /* null */): LoDashImplicitObjectWrapper<Dictionary<Array<T>>>
    fun include(target: T, fromIndex: Number? = definedExternally /* null */): Boolean
    fun includes(target: T, fromIndex: Number? = definedExternally /* null */): Boolean
    fun indexBy(iteratee: ListIterator<T, Any>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashImplicitObjectWrapper<Dictionary<T>>
    fun indexBy(iteratee: String? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashImplicitObjectWrapper<Dictionary<T>>
    fun <W : Any> indexBy(iteratee: W? = definedExternally /* null */): LoDashImplicitObjectWrapper<Dictionary<T>>
    fun <TResult> map(iteratee: ListIterator<T, TResult>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashImplicitArrayWrapper<TResult>
    fun <TResult> map(iteratee: String? = definedExternally /* null */): LoDashImplicitArrayWrapper<TResult>
    fun <TObject : Any> map(iteratee: TObject? = definedExternally /* null */): LoDashImplicitArrayWrapper<Boolean>
    fun partition(callback: ListIterator<T, Boolean>, thisArg: Any? = definedExternally /* null */): LoDashImplicitArrayWrapper<Array<T>>
    fun <W> partition(whereValue: W): LoDashImplicitArrayWrapper<Array<T>>
    fun partition(path: String, srcValue: Any): LoDashImplicitArrayWrapper<Array<T>>
    fun partition(pluckValue: String): LoDashImplicitArrayWrapper<Array<T>>
    fun <TResult> pluck(path: StringRepresentable): LoDashImplicitArrayWrapper<TResult>
    fun <TResult> pluck(path: Array<StringRepresentable>): LoDashImplicitArrayWrapper<TResult>
    fun <TResult> reduce(callback: MemoIterator<T, TResult>, accumulator: TResult, thisArg: Any? = definedExternally /* null */): TResult
    fun <TResult> reduce(callback: MemoIterator<T, TResult>, thisArg: Any? = definedExternally /* null */): TResult
    fun <TResult> inject(callback: MemoIterator<T, TResult>, accumulator: TResult, thisArg: Any? = definedExternally /* null */): TResult
    fun <TResult> inject(callback: MemoIterator<T, TResult>, thisArg: Any? = definedExternally /* null */): TResult
    fun <TResult> foldl(callback: MemoIterator<T, TResult>, accumulator: TResult, thisArg: Any? = definedExternally /* null */): TResult
    fun <TResult> foldl(callback: MemoIterator<T, TResult>, thisArg: Any? = definedExternally /* null */): TResult
    fun reject(predicate: ListIterator<T, Boolean>, thisArg: Any? = definedExternally /* null */): LoDashImplicitArrayWrapper<T>
    fun reject(predicate: String, thisArg: Any? = definedExternally /* null */): LoDashImplicitArrayWrapper<T>
    fun <W> reject(predicate: W): LoDashImplicitArrayWrapper<T>
    fun select(predicate: ListIterator<T, Boolean>, thisArg: Any? = definedExternally /* null */): LoDashImplicitArrayWrapper<T>
    fun select(predicate: String, thisArg: Any? = definedExternally /* null */): LoDashImplicitArrayWrapper<T>
    fun <W> select(predicate: W): LoDashImplicitArrayWrapper<T>
    fun sample(n: Number): LoDashImplicitArrayWrapper<T>
    fun sample(): T
    fun shuffle(): LoDashImplicitArrayWrapper<T>
    fun size(): Number
    fun some(predicate: ListIterator<T, Boolean>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Boolean
    fun some(predicate: NumericDictionaryIterator<T, Boolean>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Boolean
    fun some(predicate: String? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): Boolean
    fun <TObject : Any> some(predicate: TObject? = definedExternally /* null */): Boolean
    fun <TSort> sortBy(iteratee: ListIterator<T, TSort>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashImplicitArrayWrapper<T>
    fun sortBy(iteratee: String): LoDashImplicitArrayWrapper<T>
    fun <W : Any> sortBy(whereValue: W): LoDashImplicitArrayWrapper<T>
    fun sortBy(): LoDashImplicitArrayWrapper<T>
    fun sortByAll(vararg args: dynamic /* ListIterator<T, Boolean> | Any | String */): LoDashImplicitArrayWrapper<T>
    fun sortByAll(iteratees: Array<dynamic /* ListIterator<T, Any> | String | Any */>): LoDashImplicitArrayWrapper<T>
    fun sortByAll(vararg iteratees: dynamic /* ListIterator<T, Any> | String | Any */): LoDashImplicitArrayWrapper<T>
    fun <W : Any> sortByOrder(iteratees: ListIterator<T, Any>, orders: dynamic /* Boolean? | String? | Array<dynamic /* Boolean | String */>? */ = definedExternally /* null */): LoDashImplicitArrayWrapper<T>
    fun <W : Any> sortByOrder(iteratees: String, orders: dynamic /* Boolean? | String? | Array<dynamic /* Boolean | String */>? */ = definedExternally /* null */): LoDashImplicitArrayWrapper<T>
    fun <W : Any> sortByOrder(iteratees: W, orders: dynamic /* Boolean? | String? | Array<dynamic /* Boolean | String */>? */ = definedExternally /* null */): LoDashImplicitArrayWrapper<T>
    fun <W : Any> sortByOrder(iteratees: Array<dynamic /* ListIterator<T, Any> | String | W */>, orders: dynamic /* Boolean? | String? | Array<dynamic /* Boolean | String */>? */ = definedExternally /* null */): LoDashImplicitArrayWrapper<T>
    fun <U : Any> where(properties: U): LoDashImplicitArrayWrapper<T>
    fun <W : Function<*>, R : Function<*>> wrap(wrapper: W): LoDashImplicitObjectWrapper<R>
    fun <R : Function<*>> wrap(wrapper: Function<*>): LoDashImplicitObjectWrapper<R>
    fun <TResult> clone(isDeep: Boolean, customizer: CloneCustomizer<Array<T>, TResult>, thisArg: Any? = definedExternally /* null */): TResult
    fun <TResult> clone(customizer: CloneCustomizer<Array<T>, TResult>, thisArg: Any? = definedExternally /* null */): TResult
    fun clone(isDeep: Boolean? = definedExternally /* null */): Array<T>
    fun <TResult> cloneDeep(customizer: CloneDeepCustomizer<Array<T>, TResult>, thisArg: Any? = definedExternally /* null */): TResult
    fun cloneDeep(): Array<T>
    fun toArray(): LoDashImplicitArrayWrapper<T>
    fun max(iteratee: ListIterator<T, Any>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): T
    fun max(iteratee: String? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): T
    fun <TObject : Any> max(whereValue: TObject? = definedExternally /* null */): T
    fun min(iteratee: ListIterator<T, Any>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): T
    fun min(iteratee: String? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): T
    fun <TObject : Any> min(whereValue: TObject? = definedExternally /* null */): T
    fun sum(iteratee: ListIterator<T, Number>, thisArg: Any? = definedExternally /* null */): Number
    fun sum(iteratee: String): Number
    fun sum(): Number
    fun <TResult> get(path: StringRepresentable, defaultValue: TResult? = definedExternally /* null */): TResult
    fun <TResult> get(path: Array<StringRepresentable>, defaultValue: TResult? = definedExternally /* null */): TResult
    fun <TKey> mapKeys(iteratee: ListIterator<T, TKey>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashImplicitObjectWrapper<Dictionary<T>>
    fun <TObject : Any> mapKeys(iteratee: TObject? = definedExternally /* null */): LoDashImplicitObjectWrapper<Dictionary<T>>
    fun mapKeys(iteratee: String? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashImplicitObjectWrapper<Dictionary<T>>
    fun <TResult> result(path: StringRepresentable, defaultValue: TResult? = definedExternally /* null */): TResult
    fun <TResult> result(path: StringRepresentable, defaultValue: (args: Any) -> TResult? = definedExternally /* null */): TResult
    fun <TResult> result(path: Array<StringRepresentable>, defaultValue: TResult? = definedExternally /* null */): TResult
    fun <TResult> result(path: Array<StringRepresentable>, defaultValue: (args: Any) -> TResult? = definedExternally /* null */): TResult
    fun <TResult> transform(iteratee: MemoVoidArrayIterator<T, Array<TResult>>? = definedExternally /* null */, accumulator: Array<TResult>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashImplicitArrayWrapper<TResult>
    fun <TResult> transform(iteratee: MemoVoidArrayIterator<T, Dictionary<TResult>>? = definedExternally /* null */, accumulator: Dictionary<TResult>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashImplicitObjectWrapper<Dictionary<TResult>>
    fun identity(): Array<T>
    fun <TObject, TResult> method(vararg args: Any): LoDashImplicitObjectWrapper<(obj: TObject) -> TResult>
    fun <TResult> method(vararg args: Any): LoDashImplicitObjectWrapper<(obj: Any) -> TResult>
    fun <TObj, TResult> property(): LoDashImplicitObjectWrapper<(obj: TObj) -> TResult>
}
external interface LoDashExplicitArrayWrapper<T> : LoDashExplicitWrapperBase<Array<T>, LoDashExplicitArrayWrapper<T>> {
    fun join(separator: String? = definedExternally /* null */): LoDashExplicitWrapper<String>
    fun chunk(size: Number? = definedExternally /* null */): LoDashExplicitArrayWrapper<Array<T>>
    fun compact(): LoDashExplicitArrayWrapper<T>
    fun difference(vararg values: dynamic /* Array<T> | List<T> */): LoDashExplicitArrayWrapper<T>
    fun drop(n: Number? = definedExternally /* null */): LoDashExplicitArrayWrapper<T>
    fun dropRight(n: Number? = definedExternally /* null */): LoDashExplicitArrayWrapper<T>
    fun dropRightWhile(predicate: ListIterator<T, Boolean>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashExplicitArrayWrapper<T>
    fun dropRightWhile(predicate: String? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashExplicitArrayWrapper<T>
    fun <TWhere> dropRightWhile(predicate: TWhere? = definedExternally /* null */): LoDashExplicitArrayWrapper<T>
    fun dropWhile(predicate: ListIterator<T, Boolean>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashExplicitArrayWrapper<T>
    fun dropWhile(predicate: String? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashExplicitArrayWrapper<T>
    fun <TWhere> dropWhile(predicate: TWhere? = definedExternally /* null */): LoDashExplicitArrayWrapper<T>
    fun <T> fill(value: T, start: Number? = definedExternally /* null */, end: Number? = definedExternally /* null */): LoDashExplicitArrayWrapper<T>
    fun findIndex(predicate: ListIterator<T, Boolean>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashExplicitWrapper<Number>
    fun findIndex(predicate: String? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashExplicitWrapper<Number>
    fun <W> findIndex(predicate: W? = definedExternally /* null */): LoDashExplicitWrapper<Number>
    fun findLastIndex(predicate: ListIterator<T, Boolean>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashExplicitWrapper<Number>
    fun findLastIndex(predicate: String? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashExplicitWrapper<Number>
    fun <W> findLastIndex(predicate: W? = definedExternally /* null */): LoDashExplicitWrapper<Number>
    fun <T> first(): T
    fun <TResult> flatten(isDeep: Boolean? = definedExternally /* null */): LoDashExplicitArrayWrapper<TResult>
    fun <T> flattenDeep(): LoDashExplicitArrayWrapper<T>
    fun <T> head(): T
    fun indexOf(value: T, fromIndex: Boolean? = definedExternally /* null */): LoDashExplicitWrapper<Number>
    fun indexOf(value: T, fromIndex: Number? = definedExternally /* null */): LoDashExplicitWrapper<Number>
    fun initial(): LoDashExplicitArrayWrapper<T>
    fun <TResult> intersection(vararg arrays: dynamic /* Array<TResult> | List<TResult> */): LoDashExplicitArrayWrapper<TResult>
    fun <T> last(): T
    fun lastIndexOf(value: T, fromIndex: Boolean? = definedExternally /* null */): LoDashExplicitWrapper<Number>
    fun lastIndexOf(value: T, fromIndex: Number? = definedExternally /* null */): LoDashExplicitWrapper<Number>
    fun <TValues, TResult : Any> `object`(values: List<TValues>? = definedExternally /* null */): lodash.LoDashExplicitObjectWrapper<TResult>
    fun <TResult : Any> `object`(values: List<Any>? = definedExternally /* null */): lodash.LoDashExplicitObjectWrapper<TResult>
    fun `object`(values: List<Any>? = definedExternally /* null */): lodash.LoDashExplicitObjectWrapper<lodash.Dictionary<Any>>
    fun pull(vararg values: T): LoDashExplicitArrayWrapper<T>
    fun pullAt(vararg indexes: dynamic /* Number | Array<Number> */): LoDashExplicitArrayWrapper<T>
    fun remove(predicate: ListIterator<T, Boolean>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashExplicitArrayWrapper<T>
    fun remove(predicate: String? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashExplicitArrayWrapper<T>
    fun <W> remove(predicate: W? = definedExternally /* null */): LoDashExplicitArrayWrapper<T>
    fun rest(): LoDashExplicitArrayWrapper<T>
    fun slice(start: Number? = definedExternally /* null */, end: Number? = definedExternally /* null */): LoDashExplicitArrayWrapper<T>
    fun <TSort> sortedIndex(value: T, iteratee: ((x: T) -> TSort)? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashExplicitWrapper<Number>
    fun sortedIndex(value: T, iteratee: String): LoDashExplicitWrapper<Number>
    fun <W> sortedIndex(value: T, iteratee: W): LoDashExplicitWrapper<Number>
    fun <TSort> sortedLastIndex(value: T, iteratee: ((x: T) -> TSort)? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashExplicitWrapper<Number>
    fun sortedLastIndex(value: T, iteratee: String): LoDashExplicitWrapper<Number>
    fun <W> sortedLastIndex(value: T, iteratee: W): LoDashExplicitWrapper<Number>
    fun tail(): LoDashExplicitArrayWrapper<T>
    fun take(n: Number? = definedExternally /* null */): LoDashExplicitArrayWrapper<T>
    fun takeRight(n: Number? = definedExternally /* null */): LoDashExplicitArrayWrapper<T>
    fun takeRightWhile(predicate: ListIterator<T, Boolean>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashExplicitArrayWrapper<T>
    fun takeRightWhile(predicate: String? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashExplicitArrayWrapper<T>
    fun <TWhere> takeRightWhile(predicate: TWhere? = definedExternally /* null */): LoDashExplicitArrayWrapper<T>
    fun takeWhile(predicate: ListIterator<T, Boolean>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashExplicitArrayWrapper<T>
    fun takeWhile(predicate: String? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashExplicitArrayWrapper<T>
    fun <TWhere> takeWhile(predicate: TWhere? = definedExternally /* null */): LoDashExplicitArrayWrapper<T>
    fun union(vararg arrays: List<T>): LoDashExplicitArrayWrapper<T>
    fun <T> union(vararg arrays: List<T>): LoDashExplicitArrayWrapper<T>
    fun <TSort> uniq(isSorted: Boolean? = definedExternally /* null */, iteratee: ListIterator<T, TSort>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashExplicitArrayWrapper<T>
    fun <TSort> uniq(iteratee: ListIterator<T, TSort>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashExplicitArrayWrapper<T>
    fun uniq(isSorted: Boolean? = definedExternally /* null */, iteratee: String? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashExplicitArrayWrapper<T>
    fun uniq(iteratee: String? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashExplicitArrayWrapper<T>
    fun <TWhere : Any> uniq(isSorted: Boolean? = definedExternally /* null */, iteratee: TWhere? = definedExternally /* null */): LoDashExplicitArrayWrapper<T>
    fun <TWhere : Any> uniq(iteratee: TWhere? = definedExternally /* null */): LoDashExplicitArrayWrapper<T>
    fun <TSort> unique(isSorted: Boolean? = definedExternally /* null */, iteratee: ListIterator<T, TSort>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashExplicitArrayWrapper<T>
    fun <TSort> unique(iteratee: ListIterator<T, TSort>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashExplicitArrayWrapper<T>
    fun unique(isSorted: Boolean? = definedExternally /* null */, iteratee: String? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashExplicitArrayWrapper<T>
    fun unique(iteratee: String? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashExplicitArrayWrapper<T>
    fun <TWhere : Any> unique(isSorted: Boolean? = definedExternally /* null */, iteratee: TWhere? = definedExternally /* null */): LoDashExplicitArrayWrapper<T>
    fun <TWhere : Any> unique(iteratee: TWhere? = definedExternally /* null */): LoDashExplicitArrayWrapper<T>
    fun <T> unzip(): LoDashExplicitArrayWrapper<Array<T>>
    fun without(vararg values: T): LoDashExplicitArrayWrapper<T>
    fun xor(vararg arrays: List<T>): LoDashExplicitArrayWrapper<T>
    fun <T> zip(vararg arrays: List<T>): lodash.LoDashExplicitArrayWrapper<Array<T>>
    fun <TValues, TResult : Any> zipObject(values: List<TValues>? = definedExternally /* null */): lodash.LoDashExplicitObjectWrapper<TResult>
    fun <TResult : Any> zipObject(values: List<Any>? = definedExternally /* null */): lodash.LoDashExplicitObjectWrapper<TResult>
    fun zipObject(values: List<Any>? = definedExternally /* null */): lodash.LoDashExplicitObjectWrapper<lodash.Dictionary<Any>>
    fun reverse(): LoDashExplicitArrayWrapper<T>
    fun all(predicate: ListIterator<T, Boolean>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashExplicitWrapper<Boolean>
    fun all(predicate: String? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashExplicitWrapper<Boolean>
    fun <TObject : Any> all(predicate: TObject? = definedExternally /* null */): LoDashExplicitWrapper<Boolean>
    fun any(predicate: ListIterator<T, Boolean>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashExplicitWrapper<Boolean>
    fun any(predicate: NumericDictionaryIterator<T, Boolean>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashExplicitWrapper<Boolean>
    fun any(predicate: String? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashExplicitWrapper<Boolean>
    fun <TObject : Any> any(predicate: TObject? = definedExternally /* null */): LoDashExplicitWrapper<Boolean>
    fun at(vararg props: dynamic /* Number | String | Array<dynamic /* Number | String */> */): LoDashExplicitArrayWrapper<T>
    fun <TResult> collect(iteratee: ListIterator<T, TResult>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashExplicitArrayWrapper<TResult>
    fun <TResult> collect(iteratee: String? = definedExternally /* null */): LoDashExplicitArrayWrapper<TResult>
    fun <TObject : Any> collect(iteratee: TObject? = definedExternally /* null */): LoDashExplicitArrayWrapper<Boolean>
    fun contains(target: T, fromIndex: Number? = definedExternally /* null */): LoDashExplicitWrapper<Boolean>
    fun countBy(iteratee: ListIterator<T, Any>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashExplicitObjectWrapper<Dictionary<Number>>
    fun countBy(iteratee: String? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashExplicitObjectWrapper<Dictionary<Number>>
    fun <W> countBy(iteratee: W? = definedExternally /* null */): LoDashExplicitObjectWrapper<Dictionary<Number>>
    fun each(iteratee: ListIterator<T, Any>, thisArg: Any? = definedExternally /* null */): LoDashExplicitArrayWrapper<T>
    fun eachRight(iteratee: ListIterator<T, Any>, thisArg: Any? = definedExternally /* null */): LoDashExplicitArrayWrapper<T>
    fun every(predicate: ListIterator<T, Boolean>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashExplicitWrapper<Boolean>
    fun every(predicate: String? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashExplicitWrapper<Boolean>
    fun <TObject : Any> every(predicate: TObject? = definedExternally /* null */): LoDashExplicitWrapper<Boolean>
    fun filter(predicate: ListIterator<T, Boolean>, thisArg: Any? = definedExternally /* null */): LoDashExplicitArrayWrapper<T>
    fun filter(predicate: String, thisArg: Any? = definedExternally /* null */): LoDashExplicitArrayWrapper<T>
    fun <W> filter(predicate: W): LoDashExplicitArrayWrapper<T>
    fun forEach(iteratee: ListIterator<T, Any>, thisArg: Any? = definedExternally /* null */): LoDashExplicitArrayWrapper<T>
    fun forEachRight(iteratee: ListIterator<T, Any>, thisArg: Any? = definedExternally /* null */): LoDashExplicitArrayWrapper<T>
    fun <TKey> groupBy(iteratee: ListIterator<T, TKey>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashExplicitObjectWrapper<Dictionary<Array<T>>>
    fun <TValue> groupBy(iteratee: String? = definedExternally /* null */, thisArg: TValue? = definedExternally /* null */): LoDashExplicitObjectWrapper<Dictionary<Array<T>>>
    fun <TWhere> groupBy(iteratee: TWhere? = definedExternally /* null */): LoDashExplicitObjectWrapper<Dictionary<Array<T>>>
    fun include(target: T, fromIndex: Number? = definedExternally /* null */): LoDashExplicitWrapper<Boolean>
    fun includes(target: T, fromIndex: Number? = definedExternally /* null */): LoDashExplicitWrapper<Boolean>
    fun indexBy(iteratee: ListIterator<T, Any>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashExplicitObjectWrapper<Dictionary<T>>
    fun indexBy(iteratee: String? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashExplicitObjectWrapper<Dictionary<T>>
    fun <W : Any> indexBy(iteratee: W? = definedExternally /* null */): LoDashExplicitObjectWrapper<Dictionary<T>>
    fun <TResult> map(iteratee: ListIterator<T, TResult>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashExplicitArrayWrapper<TResult>
    fun <TResult> map(iteratee: String? = definedExternally /* null */): LoDashExplicitArrayWrapper<TResult>
    fun <TObject : Any> map(iteratee: TObject? = definedExternally /* null */): LoDashExplicitArrayWrapper<Boolean>
    fun <TResult> pluck(path: StringRepresentable): LoDashExplicitArrayWrapper<TResult>
    fun <TResult> pluck(path: Array<StringRepresentable>): LoDashExplicitArrayWrapper<TResult>
    fun reject(predicate: ListIterator<T, Boolean>, thisArg: Any? = definedExternally /* null */): LoDashExplicitArrayWrapper<T>
    fun reject(predicate: String, thisArg: Any? = definedExternally /* null */): LoDashExplicitArrayWrapper<T>
    fun <W> reject(predicate: W): LoDashExplicitArrayWrapper<T>
    fun select(predicate: ListIterator<T, Boolean>, thisArg: Any? = definedExternally /* null */): LoDashExplicitArrayWrapper<T>
    fun select(predicate: String, thisArg: Any? = definedExternally /* null */): LoDashExplicitArrayWrapper<T>
    fun <W> select(predicate: W): LoDashExplicitArrayWrapper<T>
    fun sample(n: Number): LoDashExplicitArrayWrapper<T>
    fun <TWrapper> sample(): TWrapper
    fun shuffle(): LoDashExplicitArrayWrapper<T>
    fun size(): LoDashExplicitWrapper<Number>
    fun some(predicate: ListIterator<T, Boolean>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashExplicitWrapper<Boolean>
    fun some(predicate: NumericDictionaryIterator<T, Boolean>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashExplicitWrapper<Boolean>
    fun some(predicate: String? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashExplicitWrapper<Boolean>
    fun <TObject : Any> some(predicate: TObject? = definedExternally /* null */): LoDashExplicitWrapper<Boolean>
    fun <TSort> sortBy(iteratee: ListIterator<T, TSort>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashExplicitArrayWrapper<T>
    fun sortBy(iteratee: String): LoDashExplicitArrayWrapper<T>
    fun <W : Any> sortBy(whereValue: W): LoDashExplicitArrayWrapper<T>
    fun sortBy(): LoDashExplicitArrayWrapper<T>
    fun <W : Any> sortByOrder(iteratees: ListIterator<T, Any>, orders: dynamic /* Boolean? | String? | Array<dynamic /* Boolean | String */>? */ = definedExternally /* null */): LoDashExplicitArrayWrapper<T>
    fun <W : Any> sortByOrder(iteratees: String, orders: dynamic /* Boolean? | String? | Array<dynamic /* Boolean | String */>? */ = definedExternally /* null */): LoDashExplicitArrayWrapper<T>
    fun <W : Any> sortByOrder(iteratees: W, orders: dynamic /* Boolean? | String? | Array<dynamic /* Boolean | String */>? */ = definedExternally /* null */): LoDashExplicitArrayWrapper<T>
    fun <W : Any> sortByOrder(iteratees: Array<dynamic /* ListIterator<T, Any> | String | W */>, orders: dynamic /* Boolean? | String? | Array<dynamic /* Boolean | String */>? */ = definedExternally /* null */): LoDashExplicitArrayWrapper<T>
    fun <W : Function<*>, R : Function<*>> wrap(wrapper: W): LoDashExplicitObjectWrapper<R>
    fun <R : Function<*>> wrap(wrapper: Function<*>): LoDashExplicitObjectWrapper<R>
    fun <TResult /* Number | String | Boolean */> clone(isDeep: Boolean, customizer: CloneCustomizer<Array<T>, TResult>, thisArg: Any? = definedExternally /* null */): LoDashExplicitWrapper<TResult>
    fun <TResult> clone(isDeep: Boolean, customizer: CloneCustomizer<Array<T>, Array<TResult>>, thisArg: Any? = definedExternally /* null */): LoDashExplicitArrayWrapper<TResult>
    fun <TResult : Any> clone(isDeep: Boolean, customizer: CloneCustomizer<Array<T>, TResult>, thisArg: Any? = definedExternally /* null */): LoDashExplicitObjectWrapper<TResult>
    fun <TResult /* Number | String | Boolean */> clone(customizer: CloneCustomizer<Array<T>, TResult>, thisArg: Any? = definedExternally /* null */): LoDashExplicitWrapper<TResult>
    fun <TResult> clone(customizer: CloneCustomizer<Array<T>, Array<TResult>>, thisArg: Any? = definedExternally /* null */): LoDashExplicitArrayWrapper<TResult>
    fun <TResult : Any> clone(customizer: CloneCustomizer<Array<T>, TResult>, thisArg: Any? = definedExternally /* null */): LoDashExplicitObjectWrapper<TResult>
    fun clone(isDeep: Boolean? = definedExternally /* null */): LoDashExplicitArrayWrapper<T>
    fun <TResult /* Number | String | Boolean */> cloneDeep(customizer: CloneDeepCustomizer<Array<T>, TResult>, thisArg: Any? = definedExternally /* null */): LoDashExplicitWrapper<TResult>
    fun <TResult> cloneDeep(customizer: CloneDeepCustomizer<Array<T>, Array<TResult>>, thisArg: Any? = definedExternally /* null */): LoDashExplicitArrayWrapper<TResult>
    fun <TResult : Any> cloneDeep(customizer: CloneDeepCustomizer<Array<T>, TResult>, thisArg: Any? = definedExternally /* null */): LoDashExplicitObjectWrapper<TResult>
    fun cloneDeep(): LoDashExplicitArrayWrapper<T>
    fun toArray(): LoDashExplicitArrayWrapper<T>
    fun sum(iteratee: ListIterator<T, Number>, thisArg: Any? = definedExternally /* null */): LoDashExplicitWrapper<Number>
    fun sum(iteratee: String): LoDashExplicitWrapper<Number>
    fun sum(): LoDashExplicitWrapper<Number>
    fun <TResultWrapper> get(path: StringRepresentable, defaultValue: Any? = definedExternally /* null */): TResultWrapper
    fun <TResultWrapper> get(path: Array<StringRepresentable>, defaultValue: Any? = definedExternally /* null */): TResultWrapper
    fun <TKey> mapKeys(iteratee: ListIterator<T, TKey>? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashExplicitObjectWrapper<Dictionary<T>>
    fun <TObject : Any> mapKeys(iteratee: TObject? = definedExternally /* null */): LoDashExplicitObjectWrapper<Dictionary<T>>
    fun mapKeys(iteratee: String? = definedExternally /* null */, thisArg: Any? = definedExternally /* null */): LoDashExplicitObjectWrapper<Dictionary<T>>
    fun <TResultWrapper> result(path: StringRepresentable, defaultValue: Any? = definedExternally /* null */): TResultWrapper
    fun <TResultWrapper> result(path: Array<StringRepresentable>, defaultValue: Any? = definedExternally /* null */): TResultWrapper
    fun identity(): LoDashExplicitArrayWrapper<T>
    fun <TObject, TResult> method(vararg args: Any): LoDashExplicitObjectWrapper<(obj: TObject) -> TResult>
    fun <TResult> method(vararg args: Any): LoDashExplicitObjectWrapper<(obj: Any) -> TResult>
    fun <TObj, TResult> property(): LoDashExplicitObjectWrapper<(obj: TObj) -> TResult>
}
external interface LoDashImplicitNumberArrayWrapper : LoDashImplicitArrayWrapper<Number>
external interface LoDashExplicitNumberArrayWrapper : LoDashExplicitArrayWrapper<Number>
external interface RecursiveArray<T> : JSArray<dynamic>
external interface ListOfRecursiveArraysOrValues<T> : List<dynamic /* T | RecursiveArray<T> */>
external interface FunctionBind {
    var placeholder: Any
    @nativeInvoke
    operator fun <T : Function<*>, TResult : Function<*>> invoke(func: T, thisArg: Any, vararg partials: Any): TResult
    @nativeInvoke
    operator fun <TResult : Function<*>> invoke(func: Function<*>, thisArg: Any, vararg partials: Any): TResult
}
external interface FunctionBindKey {
    var placeholder: Any
    @nativeInvoke
    operator fun <T : Any, TResult : Function<*>> invoke(`object`: T, key: Any, vararg partials: Any): TResult
    @nativeInvoke
    operator fun <TResult : Function<*>> invoke(`object`: Any, key: Any, vararg partials: Any): TResult
}
external interface CurriedFunction1<T1, R> {
    @nativeInvoke
    operator fun invoke(): CurriedFunction1<T1, R>
    @nativeInvoke
    operator fun invoke(t1: T1): R
}
external interface CurriedFunction2<T1, T2, R> {
    @nativeInvoke
    operator fun invoke(): CurriedFunction2<T1, T2, R>
    @nativeInvoke
    operator fun invoke(t1: T1): CurriedFunction1<T2, R>
    @nativeInvoke
    operator fun invoke(t1: T1, t2: T2): R
}
external interface CurriedFunction3<T1, T2, T3, R> {
    @nativeInvoke
    operator fun invoke(): CurriedFunction3<T1, T2, T3, R>
    @nativeInvoke
    operator fun invoke(t1: T1): CurriedFunction2<T2, T3, R>
    @nativeInvoke
    operator fun invoke(t1: T1, t2: T2): CurriedFunction1<T3, R>
    @nativeInvoke
    operator fun invoke(t1: T1, t2: T2, t3: T3): R
}
external interface CurriedFunction4<T1, T2, T3, T4, R> {
    @nativeInvoke
    operator fun invoke(): CurriedFunction4<T1, T2, T3, T4, R>
    @nativeInvoke
    operator fun invoke(t1: T1): CurriedFunction3<T2, T3, T4, R>
    @nativeInvoke
    operator fun invoke(t1: T1, t2: T2): CurriedFunction2<T3, T4, R>
    @nativeInvoke
    operator fun invoke(t1: T1, t2: T2, t3: T3): CurriedFunction1<T4, R>
    @nativeInvoke
    operator fun invoke(t1: T1, t2: T2, t3: T3, t4: T4): R
}
external interface CurriedFunction5<T1, T2, T3, T4, T5, R> {
    @nativeInvoke
    operator fun invoke(): CurriedFunction5<T1, T2, T3, T4, T5, R>
    @nativeInvoke
    operator fun invoke(t1: T1): CurriedFunction4<T2, T3, T4, T5, R>
    @nativeInvoke
    operator fun invoke(t1: T1, t2: T2): CurriedFunction3<T3, T4, T5, R>
    @nativeInvoke
    operator fun invoke(t1: T1, t2: T2, t3: T3): CurriedFunction2<T4, T5, R>
    @nativeInvoke
    operator fun invoke(t1: T1, t2: T2, t3: T3, t4: T4): CurriedFunction1<T5, R>
    @nativeInvoke
    operator fun invoke(t1: T1, t2: T2, t3: T3, t4: T4, t5: T5): R
}
external interface DebounceSettings {
    var leading: Boolean? get() = definedExternally; set(value) = definedExternally
    var maxWait: Number? get() = definedExternally; set(value) = definedExternally
    var trailing: Boolean? get() = definedExternally; set(value) = definedExternally
}

external interface MemoizedFunction : JSFunction {
    var cache: MapCache
}
external interface `T$0` {
    @nativeInvoke
    operator fun <T : Function<*>> invoke(func: T, resolver: Function<*>? = definedExternally /* null */): T /* T & MemoizedFunction */
    var Cache: MapCache
}
external interface Function0<R> {
    @nativeInvoke
    operator fun invoke(): R
}
external interface Function1<T1, R> {
    @nativeInvoke
    operator fun invoke(t1: T1): R
}
external interface Function2<T1, T2, R> {
    @nativeInvoke
    operator fun invoke(t1: T1, t2: T2): R
}
external interface Function3<T1, T2, T3, R> {
    @nativeInvoke
    operator fun invoke(t1: T1, t2: T2, t3: T3): R
}
external interface Function4<T1, T2, T3, T4, R> {
    @nativeInvoke
    operator fun invoke(t1: T1, t2: T2, t3: T3, t4: T4): R
}
external interface Partial {
    @nativeInvoke
    operator fun <R> invoke(func: Function0<R>): Function0<R>
    @nativeInvoke
    operator fun <T1, R> invoke(func: Function1<T1, R>): Function1<T1, R>
    @nativeInvoke
    operator fun <T1, R> invoke(func: Function1<T1, R>, arg1: T1): Function0<R>
    @nativeInvoke
    operator fun <T1, T2, R> invoke(func: Function2<T1, T2, R>): Function2<T1, T2, R>
    @nativeInvoke
    operator fun <T1, T2, R> invoke(func: Function2<T1, T2, R>, arg1: T1): Function1<T2, R>
    @nativeInvoke
    operator fun <T1, T2, R> invoke(func: Function2<T1, T2, R>, plc1: LoDashStatic, arg2: T2): Function1<T1, R>
    @nativeInvoke
    operator fun <T1, T2, R> invoke(func: Function2<T1, T2, R>, arg1: T1, arg2: T2): Function0<R>
    @nativeInvoke
    operator fun <T1, T2, T3, R> invoke(func: Function3<T1, T2, T3, R>): Function3<T1, T2, T3, R>
    @nativeInvoke
    operator fun <T1, T2, T3, R> invoke(func: Function3<T1, T2, T3, R>, arg1: T1): Function2<T2, T3, R>
    @nativeInvoke
    operator fun <T1, T2, T3, R> invoke(func: Function3<T1, T2, T3, R>, plc1: LoDashStatic, arg2: T2): Function2<T1, T3, R>
    @nativeInvoke
    operator fun <T1, T2, T3, R> invoke(func: Function3<T1, T2, T3, R>, arg1: T1, arg2: T2): Function1<T3, R>
    @nativeInvoke
    operator fun <T1, T2, T3, R> invoke(func: Function3<T1, T2, T3, R>, plc1: LoDashStatic, plc2: LoDashStatic, arg3: T3): Function2<T1, T2, R>
    @nativeInvoke
    operator fun <T1, T2, T3, R> invoke(func: Function3<T1, T2, T3, R>, arg1: T1, plc2: LoDashStatic, arg3: T3): Function1<T2, R>
    @nativeInvoke
    operator fun <T1, T2, T3, R> invoke(func: Function3<T1, T2, T3, R>, plc1: LoDashStatic, arg2: T2, arg3: T3): Function1<T1, R>
    @nativeInvoke
    operator fun <T1, T2, T3, R> invoke(func: Function3<T1, T2, T3, R>, arg1: T1, arg2: T2, arg3: T3): Function0<R>
    @nativeInvoke
    operator fun <T1, T2, T3, T4, R> invoke(func: Function4<T1, T2, T3, T4, R>): Function4<T1, T2, T3, T4, R>
    @nativeInvoke
    operator fun <T1, T2, T3, T4, R> invoke(func: Function4<T1, T2, T3, T4, R>, arg1: T1): Function3<T2, T3, T4, R>
    @nativeInvoke
    operator fun <T1, T2, T3, T4, R> invoke(func: Function4<T1, T2, T3, T4, R>, plc1: LoDashStatic, arg2: T2): Function3<T1, T3, T4, R>
    @nativeInvoke
    operator fun <T1, T2, T3, T4, R> invoke(func: Function4<T1, T2, T3, T4, R>, arg1: T1, arg2: T2): Function2<T3, T4, R>
    @nativeInvoke
    operator fun <T1, T2, T3, T4, R> invoke(func: Function4<T1, T2, T3, T4, R>, plc1: LoDashStatic, plc2: LoDashStatic, arg3: T3): Function3<T1, T2, T4, R>
    @nativeInvoke
    operator fun <T1, T2, T3, T4, R> invoke(func: Function4<T1, T2, T3, T4, R>, arg1: T1, plc2: LoDashStatic, arg3: T3): Function2<T2, T4, R>
    @nativeInvoke
    operator fun <T1, T2, T3, T4, R> invoke(func: Function4<T1, T2, T3, T4, R>, plc1: LoDashStatic, arg2: T2, arg3: T3): Function2<T1, T4, R>
    @nativeInvoke
    operator fun <T1, T2, T3, T4, R> invoke(func: Function4<T1, T2, T3, T4, R>, arg1: T1, arg2: T2, arg3: T3): Function1<T4, R>
    @nativeInvoke
    operator fun <T1, T2, T3, T4, R> invoke(func: Function4<T1, T2, T3, T4, R>, plc1: LoDashStatic, plc2: LoDashStatic, plc3: LoDashStatic, arg4: T4): Function3<T1, T2, T3, R>
    @nativeInvoke
    operator fun <T1, T2, T3, T4, R> invoke(func: Function4<T1, T2, T3, T4, R>, arg1: T1, plc2: LoDashStatic, plc3: LoDashStatic, arg4: T4): Function2<T2, T3, R>
    @nativeInvoke
    operator fun <T1, T2, T3, T4, R> invoke(func: Function4<T1, T2, T3, T4, R>, plc1: LoDashStatic, arg2: T2, plc3: LoDashStatic, arg4: T4): Function2<T1, T3, R>
    @nativeInvoke
    operator fun <T1, T2, T3, T4, R> invoke(func: Function4<T1, T2, T3, T4, R>, arg1: T1, arg2: T2, plc3: LoDashStatic, arg4: T4): Function1<T3, R>
    @nativeInvoke
    operator fun <T1, T2, T3, T4, R> invoke(func: Function4<T1, T2, T3, T4, R>, plc1: LoDashStatic, plc2: LoDashStatic, arg3: T3, arg4: T4): Function2<T1, T2, R>
    @nativeInvoke
    operator fun <T1, T2, T3, T4, R> invoke(func: Function4<T1, T2, T3, T4, R>, arg1: T1, plc2: LoDashStatic, arg3: T3, arg4: T4): Function1<T2, R>
    @nativeInvoke
    operator fun <T1, T2, T3, T4, R> invoke(func: Function4<T1, T2, T3, T4, R>, plc1: LoDashStatic, arg2: T2, arg3: T3, arg4: T4): Function1<T1, R>
    @nativeInvoke
    operator fun <T1, T2, T3, T4, R> invoke(func: Function4<T1, T2, T3, T4, R>, arg1: T1, arg2: T2, arg3: T3, arg4: T4): Function0<R>
    @nativeInvoke
    operator fun invoke(func: Function<*>, vararg args: Any): Function<*>
}
external interface PartialRight {
    @nativeInvoke
    operator fun <R> invoke(func: Function0<R>): Function0<R>
    @nativeInvoke
    operator fun <T1, R> invoke(func: Function1<T1, R>): Function1<T1, R>
    @nativeInvoke
    operator fun <T1, R> invoke(func: Function1<T1, R>, arg1: T1): Function0<R>
    @nativeInvoke
    operator fun <T1, T2, R> invoke(func: Function2<T1, T2, R>): Function2<T1, T2, R>
    @nativeInvoke
    operator fun <T1, T2, R> invoke(func: Function2<T1, T2, R>, arg1: T1, plc2: LoDashStatic): Function1<T2, R>
    @nativeInvoke
    operator fun <T1, T2, R> invoke(func: Function2<T1, T2, R>, arg2: T2): Function1<T1, R>
    @nativeInvoke
    operator fun <T1, T2, R> invoke(func: Function2<T1, T2, R>, arg1: T1, arg2: T2): Function0<R>
    @nativeInvoke
    operator fun <T1, T2, T3, R> invoke(func: Function3<T1, T2, T3, R>): Function3<T1, T2, T3, R>
    @nativeInvoke
    operator fun <T1, T2, T3, R> invoke(func: Function3<T1, T2, T3, R>, arg1: T1, plc2: LoDashStatic, plc3: LoDashStatic): Function2<T2, T3, R>
    @nativeInvoke
    operator fun <T1, T2, T3, R> invoke(func: Function3<T1, T2, T3, R>, arg2: T2, plc3: LoDashStatic): Function2<T1, T3, R>
    @nativeInvoke
    operator fun <T1, T2, T3, R> invoke(func: Function3<T1, T2, T3, R>, arg1: T1, arg2: T2, plc3: LoDashStatic): Function1<T3, R>
    @nativeInvoke
    operator fun <T1, T2, T3, R> invoke(func: Function3<T1, T2, T3, R>, arg3: T3): Function2<T1, T2, R>
    @nativeInvoke
    operator fun <T1, T2, T3, R> invoke(func: Function3<T1, T2, T3, R>, arg1: T1, plc2: LoDashStatic, arg3: T3): Function1<T2, R>
    @nativeInvoke
    operator fun <T1, T2, T3, R> invoke(func: Function3<T1, T2, T3, R>, arg2: T2, arg3: T3): Function1<T1, R>
    @nativeInvoke
    operator fun <T1, T2, T3, R> invoke(func: Function3<T1, T2, T3, R>, arg1: T1, arg2: T2, arg3: T3): Function0<R>
    @nativeInvoke
    operator fun <T1, T2, T3, T4, R> invoke(func: Function4<T1, T2, T3, T4, R>): Function4<T1, T2, T3, T4, R>
    @nativeInvoke
    operator fun <T1, T2, T3, T4, R> invoke(func: Function4<T1, T2, T3, T4, R>, arg1: T1, plc2: LoDashStatic, plc3: LoDashStatic, plc4: LoDashStatic): Function3<T2, T3, T4, R>
    @nativeInvoke
    operator fun <T1, T2, T3, T4, R> invoke(func: Function4<T1, T2, T3, T4, R>, arg2: T2, plc3: LoDashStatic, plc4: LoDashStatic): Function3<T1, T3, T4, R>
    @nativeInvoke
    operator fun <T1, T2, T3, T4, R> invoke(func: Function4<T1, T2, T3, T4, R>, arg1: T1, arg2: T2, plc3: LoDashStatic, plc4: LoDashStatic): Function2<T3, T4, R>
    @nativeInvoke
    operator fun <T1, T2, T3, T4, R> invoke(func: Function4<T1, T2, T3, T4, R>, arg3: T3, plc4: LoDashStatic): Function3<T1, T2, T4, R>
    @nativeInvoke
    operator fun <T1, T2, T3, T4, R> invoke(func: Function4<T1, T2, T3, T4, R>, arg1: T1, plc2: LoDashStatic, arg3: T3, plc4: LoDashStatic): Function2<T2, T4, R>
    @nativeInvoke
    operator fun <T1, T2, T3, T4, R> invoke(func: Function4<T1, T2, T3, T4, R>, arg2: T2, arg3: T3, plc4: LoDashStatic): Function2<T1, T4, R>
    @nativeInvoke
    operator fun <T1, T2, T3, T4, R> invoke(func: Function4<T1, T2, T3, T4, R>, arg1: T1, arg2: T2, arg3: T3, plc4: LoDashStatic): Function1<T4, R>
    @nativeInvoke
    operator fun <T1, T2, T3, T4, R> invoke(func: Function4<T1, T2, T3, T4, R>, arg4: T4): Function3<T1, T2, T3, R>
    @nativeInvoke
    operator fun <T1, T2, T3, T4, R> invoke(func: Function4<T1, T2, T3, T4, R>, arg1: T1, plc2: LoDashStatic, plc3: LoDashStatic, arg4: T4): Function2<T2, T3, R>
    @nativeInvoke
    operator fun <T1, T2, T3, T4, R> invoke(func: Function4<T1, T2, T3, T4, R>, arg2: T2, plc3: LoDashStatic, arg4: T4): Function2<T1, T3, R>
    @nativeInvoke
    operator fun <T1, T2, T3, T4, R> invoke(func: Function4<T1, T2, T3, T4, R>, arg1: T1, arg2: T2, plc3: LoDashStatic, arg4: T4): Function1<T3, R>
    @nativeInvoke
    operator fun <T1, T2, T3, T4, R> invoke(func: Function4<T1, T2, T3, T4, R>, arg3: T3, arg4: T4): Function2<T1, T2, R>
    @nativeInvoke
    operator fun <T1, T2, T3, T4, R> invoke(func: Function4<T1, T2, T3, T4, R>, arg1: T1, plc2: LoDashStatic, arg3: T3, arg4: T4): Function1<T2, R>
    @nativeInvoke
    operator fun <T1, T2, T3, T4, R> invoke(func: Function4<T1, T2, T3, T4, R>, arg2: T2, arg3: T3, arg4: T4): Function1<T1, R>
    @nativeInvoke
    operator fun <T1, T2, T3, T4, R> invoke(func: Function4<T1, T2, T3, T4, R>, arg1: T1, arg2: T2, arg3: T3, arg4: T4): Function0<R>
    @nativeInvoke
    operator fun invoke(func: Function<*>, vararg args: Any): Function<*>
}
external interface ThrottleSettings {
    var leading: Boolean? get() = definedExternally; set(value) = definedExternally
    var trailing: Boolean? get() = definedExternally; set(value) = definedExternally
}
external interface CloneCustomizer<TValue, TResult> {
    @nativeInvoke
    operator fun invoke(value: TValue): TResult
}
external interface CloneDeepCustomizer<TValue, TResult> {
    @nativeInvoke
    operator fun invoke(value: TValue): TResult
}
external interface IsEqualCustomizer {
    @nativeInvoke
    operator fun invoke(value: Any, other: Any, indexOrKey: Number? = definedExternally /* null */): Boolean
    @nativeInvoke
    operator fun invoke(value: Any, other: Any, indexOrKey: String? = definedExternally /* null */): Boolean
}
external interface isMatchCustomizer {
    @nativeInvoke
    operator fun invoke(value: Any, other: Any, indexOrKey: Number? = definedExternally /* null */): Boolean
    @nativeInvoke
    operator fun invoke(value: Any, other: Any, indexOrKey: String? = definedExternally /* null */): Boolean
}
external interface AssignCustomizer {
    @nativeInvoke
    operator fun invoke(objectValue: Any, sourceValue: Any, key: String? = definedExternally /* null */, `object`: Any? = definedExternally /* null */, source: Any? = definedExternally /* null */): Any
}
external interface MergeCustomizer {
    @nativeInvoke
    operator fun invoke(value: Any, srcValue: Any, key: String? = definedExternally /* null */, `object`: Any? = definedExternally /* null */, source: Any? = definedExternally /* null */): Any
}
external interface TemplateOptions : TemplateSettings {
    var sourceURL: String? get() = definedExternally; set(value) = definedExternally
}
external interface TemplateExecutor {
    @nativeInvoke
    operator fun invoke(data: Any? = definedExternally /* null */): String
    var source: String
}
external interface TruncOptions {
    var length: Number? get() = definedExternally; set(value) = definedExternally
    var omission: String? get() = definedExternally; set(value) = definedExternally
    var separator: dynamic /* String | RegExp */ get() = definedExternally; set(value) = definedExternally
}
external interface MixinOptions {
    var chain: Boolean? get() = definedExternally; set(value) = definedExternally
}
external interface ListIterator<T, TResult> {
    @nativeInvoke
    operator fun invoke(value: T, index: Number, collection: List<T>): TResult
}
external interface DictionaryIterator<T, TResult> {
    @nativeInvoke
    operator fun invoke(value: T, key: String? = definedExternally /* null */, collection: Dictionary<T>? = definedExternally /* null */): TResult
}
external interface NumericDictionaryIterator<T, TResult> {
    @nativeInvoke
    operator fun invoke(value: T, key: Number? = definedExternally /* null */, collection: Dictionary<T>? = definedExternally /* null */): TResult
}
external interface ObjectIterator<T, TResult> {
    @nativeInvoke
    operator fun invoke(element: T, key: String? = definedExternally /* null */, collection: Any? = definedExternally /* null */): TResult
}
external interface StringIterator<TResult> {
    @nativeInvoke
    operator fun invoke(char: String, index: Number? = definedExternally /* null */, string: String? = definedExternally /* null */): TResult
}
external interface MemoVoidIterator<T, TResult> {
    @nativeInvoke
    operator fun invoke(prev: TResult, curr: T, indexOrKey: Any? = definedExternally /* null */, list: Array<T>? = definedExternally /* null */)
}
external interface MemoIterator<T, TResult> {
    @nativeInvoke
    operator fun invoke(prev: TResult, curr: T, indexOrKey: Any? = definedExternally /* null */, list: Array<T>? = definedExternally /* null */): TResult
}
external interface MemoVoidArrayIterator<T, TResult> {
    @nativeInvoke
    operator fun invoke(acc: TResult, curr: T, index: Number? = definedExternally /* null */, arr: Array<T>? = definedExternally /* null */)
}
external interface MemoVoidDictionaryIterator<T, TResult> {
    @nativeInvoke
    operator fun invoke(acc: TResult, curr: T, key: String? = definedExternally /* null */, dict: Dictionary<T>? = definedExternally /* null */)
}
external interface List<T> {
    @nativeGetter
    operator fun get(index: Number): T?
    @nativeSetter
    operator fun set(index: Number, value: T)
    var length: Number
}
external interface Dictionary<T> {
    @nativeGetter
    operator fun get(index: String): T?
    @nativeSetter
    operator fun set(index: String, value: T)
}
external interface NumericDictionary<T> {
    @nativeGetter
    operator fun get(index: Number): T?
    @nativeSetter
    operator fun set(index: Number, value: T)
}
external interface StringRepresentable {
    override fun toString(): String
}
external interface Cancelable {
    fun cancel()
}
