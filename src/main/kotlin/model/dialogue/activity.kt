package model.dialogue

import bootstrap
import ext.hasOwnProperty
import jquery.JQuery
import jquery.jQuery
import model.Model
import org.w3c.dom.Node
import simpleTemplate
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

/**
 * Activity set up, currently only small choice but nicely fits together
 *
 * @module Dialogue
 */

external interface ActivityStatic {
    operator fun invoke(node: JQuery, storage: Array<Any?>):Activity
}

external interface NodeClass {
    var position: dynamic
    var attrs : ActivityAttrs
    fun  setText(label: String?)
    val  model: Model
}

data class ElementT(@JsName("type") var type: String,
                    @JsName("eid") var eid: Int,
                    @JsName("value") var value: Boolean? = null,
                    @JsName("text") var text: String? = null,
                    @JsName("name") var name: String? = null)

class ActivityAttrs {
    var  elements: Array<ElementT> = emptyArray()
    var  label: String? = null
    var  type: String? = null

}

class Activity(var node: NodeClass, var storage: Array<Any?>) {
    var attrs:ActivityAttrs = jQuery.extend(true, json(), object1 = node.attrs).asDynamic()
    var _target = jQuery("#dialogue").bootstrap

    private val _body = _target.find(".modal-body")!!.bootstrap
    private var _content = this._body.find(".list-group")!!.bootstrap

    init {
        // default parameters
        js("this.attrs.elements = this.attrs.elements || []")

        // set title
        _target.find(".modal-title")?.html("Activity Settings")

        this.init()
        this.initChange()
        this.initListeners()
        this.initSave()
        this.initCollapse()

        // show modal
        _target.modal("show")

    }

    /**
     * Render element list
     * @param content
     */

    fun render(content: JQuery) {
        val views = js("[]") as Array<Any>
        jQuery.each(this.attrs.elements, { i:Number, value:ElementT ->
            // generate view for each element
            views.asDynamic().push(simpleTemplate.render("activity-row", toView(value, i.toInt())))
        })
        content.empty().append(views)

    }

    private fun init() {
        // render template
        simpleTemplate.renderTo(this._body, "dialogue-activity")

        // make elements sortable
        //TODO("Support reordering")
/*
        Sortable.create(this._content.get(0), {
            onSort: function () {
                var list = [];
                self._content.find(".collapse")?.each({
                    var id = jQuery(this).attr("id")
                    .replace(/^activity_item_/, '');
                    list.push(self.attrs.elements[id]);
                });
                self.attrs.elements = list;
                self.render(self._content);
            }
        });
*/

        // show label
        this._body.find("#label")?.value((this.attrs.label ?: ""))

        // render content
        this.render(this._content)

    }
    private fun initChange() {
        val self = this

        // type change
        this._body.find("#activity_type")!!.on("change", { _, _ ->
            val type = jQuery(js("this") as Node).value() as String

            if (type == "other") _content.hide()
            else _content.show()

            attrs.type = type
            Unit
        }).value((attrs.type ?: "human")).change() // select curernt

        // add element
        this._body.find("#add")!!.on("click", { _, _ ->
            val random = Math.floor(Math.random() * 64 * 1024)
            self.attrs.elements.asDynamic().push(ElementT(type="empty", eid = random, value = null))
            self.render(self._content)
            self._content.find(".collapse")?.apply { last().bootstrap.collapse("show"); }
            Unit
        })

    }
    private fun initListeners() {
        val self = this

        // listener for collapsing elements
        this._body.on("hide.bs.collapse", ".collapse", { _, _ ->
            jQuery(this).run {
                self._body.find("a[href=\"#${this.attr("id")}\"]")?.removeClass("active")
                empty()
            }
        })

        // listener for reusable variables
        this._body.on("click", "a.variable-reuse", { e , _ ->
            e.preventDefault()
            jQuery(this).run {
                val _text = parents(".form-group").find("input")!!
                val text = (_text.value() as String).trim() + ' ' + attr("value")
                _text.value(text.trim())
            }
        })

        // listener for collapsing modal
        _target.on("hide.bs.modal", { _, _ -> _body.off(); })

    }
    private fun initSave() {
        var self = this

        this._target.find("#save")!!.off().on("click", { _, _ ->
            self.attrs.label = self._body.find("#label")?.run { value() as String }

            self.node.attrs = self.attrs
            var ishuman = self.node.attrs.asDynamic().type == "human"
            if (!ishuman && self.node.attrs.asDynamic().elements) {
//                delete node.attrs.elements;
            }

            self.node.setText(self.attrs.label)
            self.node.model.save()

            self._target.modal("hide")
        })

    }
    private fun initCollapse() {
        var self = this

        this._body.on("show.bs.collapse", ".collapse", { _, _ ->
            var _this = jQuery(this).bootstrap

            // close other elements
            self._body.find(".collapse.in")?.apply { bootstrap.collapse("hide") }

            // make this element active
            var href = _this.attr("id")
            self._body.find("a[href=\"#$href\"]")?.addClass("active")

            // get index name and view, and render
            var index = href.replace(Regex("^activity_item_"), "").toInt()
            val view:ElementT? = self.attrs.elements[index]
            var name = "activity-selector"
            simpleTemplate.renderTo(_this, name, view)

            // setup lisetener for type change
            var _type = _this.find(".element-type")!!
            _type.change({
                var name = "activity-element-" + jQuery(this).value()
                             if (name != "empty") {
                    // as soon as type selected, 'empty' is disabled
                    _this.find("option[value=empty]")?.prop("disabled", true)
                }

                // add storage
                var _content = _this.find(".element-content")!!
                var newview = jQuery.extend(true, target = js("{}"), objectN = view!!)
                             newview.asDynamic().stor = getStorage(self.storage, self.attrs.elements)

                             // render
                             simpleTemplate.renderTo(_content, name, newview)

                             // initialise storage vars
                _content.find(".dropdown-button")!!.each({ i, elem ->
                    jQuery(this).attr("id", "menu_$i")
                                                             jQuery(this).next().attr("aria-labelledby", "menu_$i")
                                                         })
                         })

            // auto change type if possible
            if (view!=null && view.type != "empty") {
                _this.find(".element-type")!!.value(view.type).change()
            }

            // listener for discard
            _this.find("#element_discard")?.click({
                _this.collapse("hide")
                                                  })

            // listener for remove
            _this.find("#element_remove")?.click({
                self.attrs.elements.asDynamic().splice(index, 1)
                                                     self.render(self._content)
                                                 })

            // listener for save
            _this.find("#element_save")?.click {
                val element = ElementT(_type.value() as String, self.attrs.elements[index].eid)

                if (element.type != "empty") { // if empty can't save

                    // put attributes from html to attrs
                    _this.find(".element-content")?.find("input[name]")?.each { _, elem ->
                        val _this = jQuery(elem)
                        element.asDynamic()[_this.attr("name")] = _this.value()
                        Unit
                    }

                    // apply and rerender
                    self.attrs.elements[index] = element
                    self.render(self._content)
                } // if emtpy can't save
            }
        })

    }


    companion object {
        /**
         * Convert element to template view
         *
         * @param element element to be converted
         * @param index {Integer} elements position
         *
         * @return {Object} generated view
         */
        private fun toView(element:ElementT, index:Int): Json {
            var title = element.type

            var description = json()
            element.name?.also { description.set("name", it)}
            element.text?.also { description.set("Text", it) }
            element.value?.also { description.set("Default", it)}

            var descarray = js("[]")
            for (key:String in description.asDynamic()) {
                if (!description.hasOwnProperty(key)) continue
                descarray.push(key + ": '" + description[key] + "'")
            }

            return json("index" to index,
                "title" to title,
                "description" to  ((descarray as Array<String>).joinToString(", ")))

        }


        /**
         * Generate storage into a list of <li> elements for the template
         *
         * @param storage {Array} array of previous dynamic values
         * @param elements {Array} arry of current labels in this
         *
         * @return {String} HTML of set of <li> elements for the list
         */
        private fun getStorage(storage:Array<Any?>, elements: Array<ElementT>): String {
            // this is just a wrapper to get html
            var _ul = jQuery("<ul>")

            // first lets add current activity elements
            var added = false
            jQuery.each(elements, { i:Number, value ->
                if (value.value!=null) {
                    added = true
                    _ul.append(
                    makeLi("@${value.eid}", "this.${value.name}")
                )
                }
            })

            // if both groups exist we need to separate
            if (added && storage.isNotEmpty()) {
                _ul.append("<li role=\"separator\" class=\"divider\">")
            }

            // now we add elemetns from activities before
            jQuery.each(storage, { i: Number, value ->
                _ul.append(makeLi(value.asDynamic().value, value.asDynamic().title))
            })

            // return as a string for the template
            return _ul.html()
        }


        /**
         * Generates <li> element for the list of reusable variables
         *
         * @param value {String} underlying value
         * @param name {String} value to print
         *
         * @return {Object} jQuery <li> element for the list
         */
        fun makeLi(value:String, name:String): JQuery {
            return jQuery("<li>").also {
                jQuery("<a>", json("class" to "variable-reuse",
                                   "value" to "%$value",
                                   "href" to "#")).append(name).appendTo(it)
            }
        }


    }

}

@JsModule("model/dialogue/activity")
@JsNonModule
external val activity: ActivityStatic = definedExternally
