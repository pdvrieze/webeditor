package es6

import org.w3c.dom.Element
import org.w3c.dom.events.EventTarget
import kotlin.js.*

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

@JsName("Function")
external interface JSFunction {
    /**
     * Calls the function, substituting the specified object for the this value of the function, and the specified array for the arguments of the function.
     * @param thisArg The object to be used as the this object.
     * @param argArray A set of arguments to be passed to the function.
     */
    fun apply(thisArg: dynamic, vararg argArray: dynamic): dynamic

    /**
     * Calls a method of an object, substituting another object for the current object.
     * @param thisArg The object to be used as the current object.
     * @param argArray A list of arguments to be passed to the method.
     */
    fun call(thisArg: dynamic, vararg argArray: dynamic): dynamic

    /**
     * For a given function, creates a bound function that has the same body as the original function.
     * The this object of the bound function is associated with the specified object, and has the specified initial parameters.
     * @param thisArg An object to which the this keyword can refer inside the new function.
     * @param argArray A list of arguments to be passed to the new function.
     */
    fun bind(thisArg: dynamic, vararg argArray: dynamic): dynamic

    var prototype: dynamic
    val length: Int
    val arguments: dynamic
    val caller: JSFunction
}

fun <T> jsArray():JSArray<T> = js("[]")

@JsName("Array")
external interface JSArray<T> {
    /**
     * Gets or sets the length of the array. This is a number one higher than the highest element defined in an array.
     */
    var length: Int

    /**
     * Returns a string representation of an array.
     */
    override fun toString(): String

    fun toLocaleString(): String
    /**
     * Appends new elements to an array, and returns the new length of the array.
     * @param items New elements of the Array.
     */
    fun push(vararg items: T): Int

    /**
     * Removes the last element from an array and returns it.
     */
    fun pop(): T?

    /**
     * Combines two or more arrays.
     * @param items Additional items to add to the end of array1.
     */
    fun concat(vararg items: JSArray<T>): JSArray<T>

    /**
     * Combines two or more arrays.
     * @param items Additional items to add to the end of array1.
     */
    fun concat(vararg items: T): JSArray<T>

    /**
     * Adds all the elements of an array separated by the specified separator string.
     * @param separator A string used to separate one element of an array from the next in the resulting String. If omitted, the array elements are separated with a comma.
     */
    fun join(separator: String? = definedExternally): String

    /**
     * Reverses the elements in an Array.
     */
    fun reverse(): JSArray<T>

    /**
     * Removes the first element from an array and returns it.
     */
    fun shift(): T?

    /**
     * Returns a section of an array.
     * @param start The beginning of the specified portion of the array.
     * @param end The end of the specified portion of the array.
     */
    fun slice(start: Int? = definedExternally, end: Int = definedExternally): JSArray<T>

    /**
     * Sorts an array.
     * @param compareFn The name of the function used to determine the order of the elements. If omitted, the elements are sorted in ascending, ASCII character order.
     */
    fun sort(compareFn: (a: T, b: T) -> Int = definedExternally): JSArray<T>

    /**
     * Removes elements from an array and, if necessary, inserts new elements in their place, returning the deleted elements.
     * @param start The zero-based location in the array from which to start removing elements.
     */
    fun splice(start: Int): JSArray<T>

    /**
     * Removes elements from an array and, if necessary, inserts new elements in their place, returning the deleted elements.
     * @param start The zero-based location in the array from which to start removing elements.
     * @param deleteCount The number of elements to remove.
     * @param items Elements to insert into the array in place of the deleted elements.
     */
    fun splice(start: Int, deleteCount: Int, vararg items: T = definedExternally): JSArray<T>

    /**
     * Inserts new elements at the start of an array.
     * @param items  Elements to insert at the start of the Array.
     */
    fun unshift(vararg items: T): Int

    /**
     * Returns the index of the first occurrence of a value in an array.
     * @param searchElement The value to locate in the array.
     * @param fromIndex The array index at which to begin the search. If fromIndex is omitted, the search starts at index 0.
     */
    fun indexOf(searchElement: T, fromIndex: Int = definedExternally): Int

    /**
     * Returns the index of the last occurrence of a specified value in an array.
     * @param searchElement The value to locate in the array.
     * @param fromIndex The array index at which to begin the search. If fromIndex is omitted, the search starts at the last index in the array.
     */
    fun lastIndexOf(searchElement: T, fromIndex: Int = definedExternally): Int

    /**
     * Determines whether all the members of an array satisfy the specified test.
     * @param callbackfn A function that accepts up to three arguments. The every method calls the callbackfn function for each element in array1 until the callbackfn returns false, or until the end of the array.
     * @param thisArg An object to which the this keyword can refer in the callbackfn function. If thisArg is omitted, undefined is used as the this value.
     */
    fun every(callbackfn: (value: T, index: Int, array: JSArray<T>) -> Boolean,
              thisArg: Any? = definedExternally): Boolean

    /**
     * Determines whether the specified callback function returns true for any element of an array.
     * @param callbackfn A function that accepts up to three arguments. The some method calls the callbackfn function for each element in array1 until the callbackfn returns true, or until the end of the array.
     * @param thisArg An object to which the this keyword can refer in the callbackfn function. If thisArg is omitted, undefined is used as the this value.
     */
    fun some(callbackfn: (value: T, index: Int, array: JSArray<T>) -> Boolean,
             thisArg: Any? = definedExternally): Boolean

    /**
     * Performs the specified action for each element in an array.
     * @param callbackfn  A function that accepts up to three arguments. forEach calls the callbackfn function one time for each element in the array.
     * @param thisArg  An object to which the this keyword can refer in the callbackfn function. If thisArg is omitted, undefined is used as the this value.
     */
    fun forEach(callbackfn: (value: T, index: Int, array: JSArray<T>) -> Unit, thisArg: Any? = definedExternally)

    /**
     * Calls a defined callback function on each element of an array, and returns an array that contains the results.
     * @param callbackfn A function that accepts up to three arguments. The map method calls the callbackfn function one time for each element in the array.
     * @param thisArg An object to which the this keyword can refer in the callbackfn function. If thisArg is omitted, undefined is used as the this value.
     */
    fun <U> map(callbackfn: (value: T, index: Int, array: JSArray<T>) -> U,
                thisArg: Any? = definedExternally): JSArray<U>

    /**
     * Returns the elements of an array that meet the condition specified in a callback function.
     * @param callbackfn A function that accepts up to three arguments. The filter method calls the callbackfn function one time for each element in the array.
     * @param thisArg An object to which the this keyword can refer in the callbackfn function. If thisArg is omitted, undefined is used as the this value.
     */
    fun filter(callbackfn: (value: T, index: Int, array: JSArray<T>) -> Any,
               thisArg: Any? = definedExternally): JSArray<T>

    /**
     * Calls the specified callback function for all the elements in an array. The return value of the callback function is the accumulated result, and is provided as an argument in the next call to the callback function.
     * @param callbackfn A function that accepts up to four arguments. The reduce method calls the callbackfn function one time for each element in the array.
     * @param initialValue If initialValue is specified, it is used as the initial value to start the accumulation. The first call to the callbackfn function provides this value as an argument instead of an array value.
     */
    fun reduce(callbackfn: (previousValue: T, currentValue: T, currentIndex: Int, array: JSArray<T>) -> T,
               initialValue: T? = definedExternally): T

    /**
     * Calls the specified callback function for all the elements in an array. The return value of the callback function is the accumulated result, and is provided as an argument in the next call to the callback function.
     * @param callbackfn A function that accepts up to four arguments. The reduce method calls the callbackfn function one time for each element in the array.
     * @param initialValue If initialValue is specified, it is used as the initial value to start the accumulation. The first call to the callbackfn function provides this value as an argument instead of an array value.
     */
    fun <U> reduce(callbackfn: (previousValue: U, currentValue: T, currentIndex: Int, array: JSArray<T>) -> U,
                   initialValue: U): U

    /**
     * Calls the specified callback function for all the elements in an array, in descending order. The return value of the callback function is the accumulated result, and is provided as an argument in the next call to the callback function.
     * @param callbackfn A function that accepts up to four arguments. The reduceRight method calls the callbackfn function one time for each element in the array.
     * @param initialValue If initialValue is specified, it is used as the initial value to start the accumulation. The first call to the callbackfn function provides this value as an argument instead of an array value.
     */
    fun reduceRight(callbackfn: (previousValue: T, currentValue: T, currentIndex: Int, array: JSArray<T>) -> T,
                    initialValue: T? = definedExternally): T

    /**
     * Calls the specified callback function for all the elements in an array, in descending order. The return value of the callback function is the accumulated result, and is provided as an argument in the next call to the callback function.
     * @param callbackfn A function that accepts up to four arguments. The reduceRight method calls the callbackfn function one time for each element in the array.
     * @param initialValue If initialValue is specified, it is used as the initial value to start the accumulation. The first call to the callbackfn function provides this value as an argument instead of an array value.
     */
    fun <U> reduceRight(callbackfn: (previousValue: U, currentValue: T, currentIndex: Int, array: JSArray<T>) -> U,
                        initialValue: U): U

    operator fun get(n: Int): T
}

//
//external interface Event {
//    val bubbles: Boolean
//    var cancelBubble: Boolean
//    val cancelable: Boolean
//    val currentTarget: EventTarget
//    val defaultPrevented: Boolean
//    val eventPhase: Number
//    val isTrusted: Boolean
//    var returnValue: Boolean
//    val srcElement: Element?
//    val target: EventTarget
//    val timeStamp: Number
//    val type: String
//    val scoped: Boolean
//    fun initEvent(eventTypeArg: String, canBubbleArg: Boolean, cancelableArg: Boolean)
//    fun preventDefault()
//    fun stopImmediatePropagation()
//    fun stopPropagation()
//    fun deepPath(): Array<EventTarget>
//    val AT_TARGET: Number
//    val BUBBLING_PHASE: Number
//    val CAPTURING_PHASE: Number
//}

fun json(input:Map<String, Any>):Json {
    return json().apply {
        for ((key, value) in input.entries) {
            this[key]=value
        }
    }
}