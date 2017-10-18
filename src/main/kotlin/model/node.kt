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

package model

import joint.DfsBfsOptions
import joint.dia.Element
import joint.dia.Paper
import joint.dia.Point
import joint.dia.SVGAttributes
import jquery.JQuery
import jquery.jQuery
import util.util
import kotlin.js.*
import model.dialogue.activity as ActivityDialogue
import model.dialogue.gate as GateDialogue

/**
 * Helper interface for node
 */

external interface Coordinate {
    var x: Int
    var y: Int
}

@Deprecated("Replace with nodeinstancestate")
enum class NodeState {
    Complete,
    Acknowledged,
    Taken,

    Started
}

object node {

    /**
     * Single element size on the graph
     *
     * @final
     * @type Integer
     */
    val SIZE = 30

    // half the size
    val HSIZE = SIZE / 2

    fun NodeAttrs(image: Json) = (js("{}") as NodeAttrs).apply { }
    interface NodeAttrs : Json {
        var image: Json?
            get() = asDynamic().image
            set(value: Json?) {
                asDynamic().image = value
            }
    }

    /**
     * Base Node class to be used as a cell descroption
     *
     * @class NodeClass
     * @extends joint.dia.Element
     */
    class NodeClass : Element {
        val markup = "<g transform=\"translate(-$HSIZE, -$HSIZE)\">" +
                     "<image width=\"$SIZE\" height=\"$SIZE\" xlink:href=\"svg/end.svg\"/>" +
                     "<text x=\"15\" y=\"17\" font-family=\"sans-serif\" font-size=\"7\" text-anchor=\"middle\" />" +
                     "<rect x=\"0\" y=\"0\" width=\"30\" height=\"30\" stroke=\"transparent\" fill=\"transparent\" stroke-width=\"2\"/>" +
                     "</g>"

        init {
            asDynamic().defaults = joint.util.deepSupplement(kotlin.js.json("type" to "node"),
                                                             js("joint.dia.Element.prototype.defaults"))
        }

        var attrs: NodeAttrs

        constructor(position: Point, attrs: NodeAttrs) {
            asDynamic().position = position
            this.attrs = attrs
        }
    }

    // TODO a very random number to be used in node naming
    var random = 1


    interface LinkLimit {
        @JsName("input")
        var input: Int
        @JsName("output")
        var output: Int
    }

    fun LinkLimit(input: Int = 1, output: Int = 1): LinkLimit {
        return object : LinkLimit {
            override var input: Int = input
            override var output: Int = output
        }
    }


    /**
     * Base Node class that contains base functions
     *
     * @class Base
     * @extends Backbone.Model
     */
    abstract class Base : backbone.Model {
        /**
         * Node Type name
         */
        var type: String? = null

        /**
         * Node unique ID
         */
        var eid: String? = null // to be specified

        /**
         * Link limit setup
         */
        var linkLimit: LinkLimit = LinkLimit() // 1 in 1 out default

        /**
         * Set to true if node has edit dialogue
         *
         * @property canEdit
         * @type Boolean
         * @default false
         */
        var canEdit: Boolean = false // by default node is not to be edited

        var label: String? = null
        var cell: NodeClass? = null
        var paper: Paper? = null
        var attrs: dynamic = null

        private val model: Model? = null

        constructor(offset: Point, id: String? = null) {
            this.eid = id ?: this.type + random++; // random ID if needed
            this.label = null; // label is missing by default
            this.cell = NodeClass(offset, NodeAttrs(image = json("xlink:href" to "svg/${type}.svg"))/*{
                position: offset, // node position
                attrs: { image: { 'xlink:href': 'svg/' + this.type + '.svg' } }
            }*/)
        }

        /**
         * Sets text in the centre of a node
         *
         * @method setText
         * @param text {String} text to set
         */
        fun setText(text: String) {
            var id = cell!!.findView(paper!!).id
            var _node = jQuery('#' + id).find("text")
            _node?.get(0)?.run { textContent = text }
            styleText(_node!!)
        }

        fun String.toState(): NodeState? = when (this) {
            "Complete"     -> NodeState.Complete
            "Acknowledged" -> NodeState.Acknowledged
            "Started"      -> NodeState.Started
            "Taken"        -> NodeState.Taken
            else           -> null
        }

        /**
         * Allows to set state to the node
         *
         * @param state {String} state
         */
        fun setState(state: String) {
            setState(state.toState())
        }

        fun setState(state: NodeState?) {
            var id = cell!!.findView(this.paper!!).id
            var _node = jQuery('#' + id).find("rect")

            // choose colour depending on state
            val colour = when (state) {
                NodeState.Complete     -> "green"
                NodeState.Acknowledged -> "red"
                NodeState.Started      -> "orange"
                NodeState.Taken        -> "orange"
                else                   -> "transparent"
            }

            // colour the links on graph
            var G = model?.graph
            var links = G?.getConnectedLinks(cell!!, DfsBfsOptions(inbound = true)) ?: emptyArray()
            var stroke = if (colour == "transparent") "black" else colour
            for (link in links) {
                link.attr(json(".connection" to json("stroke" to stroke),
                               ".marker-target" to json("fill" to stroke)) as SVGAttributes)
            }

            // apply to node
            _node?.attr(json("stroke" to colour))
        }

        /**
         * Override to style text
         *
         * @method styleText
         */
        open fun styleText(_node: JQuery) {}

        /**
         * Returns cell position for the tooltip hence the name
         *
         * @method tooltip
         * @param callback {Function} callback to pass arguments to
         */
        fun tooltip(callback: (Number, Number) -> Unit) {
            callback(cell!!.position().x, cell!!.position().y)
        }

        /**
         * Override in to output advanced XML
         *
         * @method fromXml
         */

        open fun fromXml(_xml: JQuery) {}

        abstract fun toXml(predecessors: dynamic = null): JQuery
    }

    class Start : Base {
        constructor(offset: Point, id: String? = null) : super(offset, id) {
            type = "start"
            linkLimit = LinkLimit(0)
        }

        override fun toXml(predecessors: dynamic): JQuery {
            return jQuery(html = "<start>",
                          attributes = json("id" to this.eid,
                                            "x" to cell?.attributes?.asDynamic()?.position.x,
                                            "y" to cell?.attributes?.asDynamic()?.position.y))
        }

    }

    /**
     * Format define to reference other activity or labels in current one
     *
     * @param _define {Object} jQuery define element
     * @param text {String} string to include in define
     */
    private fun formatDefine(_define: JQuery, _text: String) {
        // uglyass url
        var url = "http://adaptivity.nl/ProcessEngine/activity"

        // replace local labels
        val replace = "<jbi:value xmlns:jbi=\"$url\" value=\"d_$1\"/>"
        var text: String = _text.replace(Regex("%@(\\w+)"), replace)

        // find global values
        var match = text.match(js("/%#(\\w+)\\.(\\w+)/"))
        if (match != null && match.isNotEmpty()) {
            var activity = match[1]
            var ref = match[2]

            // make define reference another activity
            _define.attr("xpath", ".")
            _define.attr("refnode", activity)
            _define.attr("refname", "r_" + ref)

            // replace all occurances
            var replace2 = "<jbi:value xmlns:jbi=\"$url\"/>"
            text = text.replace(Regex.fromLiteral(match[0]), replace2)
        }

        // apply
        _define.append(text)
    }

    /**
     * Add result tag
     *
     * @param _cell {Object} jQuery cell to add tag to
     * @param value {Object} element that needs to be added
     */
    fun addResult(_cell: JQuery, value: Json) {
        // add result
        var rname = "r_" + value.asDynamic().eid
        jQuery("<result>", json(
            "xpath" to "/umh:result/umh:value[@name='" + value.asDynamic().name + "']/text()",
            "name" to rname
                               )).appendTo(_cell)
    }

    /**
     * Added define and result items to the xml
     *
     * @param _cell {Object} jQuery activity cell
     * @param _item {Object} jQuery attribute item
     * @param value {Object} element that needs to be added
     * @param name {String} attibute name
     */
    fun addItem(_cell: JQuery, _item: JQuery, value: Json, name: String) {
        // add define
        var dname = "d_${value.asDynamic().eid}_$name"
        var _define = jQuery("<define>", json("name" to dname)).appendTo(_cell)
        formatDefine(_define, (value[name] as String).trim())

        // append everything else as attributes
        jQuery("<attribute>", json("xmlns:jbi" to "http://adaptivity.nl/ProcessEngine/activity",
                                   "value" to dname,
                                   "name" to name
                                  )).appendTo(_item)
    }

    /**
     * Parse define from xml into text
     *
     * @param define {String} text of define element to parse
     *
     * @return {String}
     */
    fun parseDefine(define: String): String {
        var _define = jQuery(define)
        var text = jQuery(define).html()
        text = text.replace(Regex("< *jbi:value. * ?value=\"d_(.+?)\".*?/>", RegexOption.IGNORE_CASE), "%@$1")

        var refnode = _define.attr("refnode")
        var refname = _define.attr("refname")
        if (refnode != null && refname != null) {
            refname = refname.substring(2)
            var replace = "%#$refnode.$refname"
            text = text.replace(Regex("<jbi:value.*?>"), replace)
        }

        return text
    }

    /**
     * Activity Class
     *
     * @class Activity
     * @extends Base
     */
    class Activity(offset: Point, id: String? = null) : Base(offset, id) {
        init {
            type = "activity"
            canEdit = true
        }

        fun edit(storage: Array<Any?>) {
            ActivityDialogue(jQuery(this), storage)
        }

        fun init() {
            val self = this.asDynamic()

            if (self.attrs && self.attrs.label) {
                setText(self.attrs.label)
            }
        }

        fun fromXml(_xml: JQuery, _model: JQuery) {
            val attrs = json("elements" to js("[]"))


            // set label if exists
            _xml.attr("label")?.let { attrs["label"] = it }

            // defines will be stored here
            var defines = json()
            _model.find(util.xmlSel("pe:define"))?.each { _: Number, elem: org.w3c.dom.Element ->
                defines[jQuery(elem).attr("name")!!] = parseDefine(
                    elem.textContent!!)
                Unit
            }

            val elements = js("[]")

            // elements will be stored here
            _xml.find(util.xmlSel("umh:item"))?.each { _, elem ->
                var element = js("{}")

                element.name = jQuery(this).attr("name")
                element.type = jQuery(this).attr("type")

                jQuery(this).find(util.xmlSel("jbi:attribute"))?.each { _, elem ->
                    val attribute = jQuery(this).attr("name")?.trim()
                    var value = jQuery(this).attr("value")?.trim() ?: ""

                    // substitute with defines
                    if (defines[value] != null) {
                        element.eid = value.removePrefix("d_").replace("_", "")
                        value = defines[value] as String
                    }

                    element[attribute] = value
                    Unit
                }

                // add to the list
                elements.push(element)
            }

            // apply
            attrs["elements"] = elements
            this.attrs = attrs
        }

        val JSON_POSTTASK = json("type" to "application/soap+xml",
                                 "serviceNS" to "http://adaptivity.nl/userMessageHandler",
                                 "serviceName" to "userMessageHandler",
                                 "endpoint" to "internal",
                                 "operation" to "postTask")

        /** @override */
        override fun toXml(predecessors: dynamic): JQuery {
            var params = json("id" to eid,
                              "x" to cell!!.attributes.asDynamic().position.x,
                              "y" to cell!!.attributes.asDynamic().position.y)

            // set default attributes
            if (!this.attrs) this.attrs = {}
            if (!this.attrs.elements) this.attrs.elements = js("[]")

            // set label and predecessor
            if (this.attrs.label) params["label"] = this.attrs.label
            if (predecessors.length == 1) {
                params["predecessor"] = predecessors[0].eid
            }

            // big nothing start --------------------
            var _cell = jQuery(html = "<activity>", attributes = params)

            if (!this.attrs.elements.length) return _cell

            var _message = createTag("message",
                                     "type" to "application/soap+xml",
                                     "serviceNS" to "http://adaptivity.nl/userMessageHandler",
                                     "serviceName" to "userMessageHandler",
                                     "endpoint" to "internal",
                                     "operation" to "postTask").appendTo(_cell)

            var _envelope = createTag("Envelope", "xmlns:env" to "http://www.w3.org/2003/05/soap-envelope",
                                      "encodingStyle" to "http://www.w3.org/2003/05/soap-encoding"
                                     ).appendTo(_message)

            var _body = createTag("Body").appendTo(_envelope)
            var _postTask = createTag("postTask",  "xmlns:umh" to "http://adaptivity.nl/userMessageHandler").appendTo(_body)

            var _repliesParam = jQuery("<repliesParam>").appendTo(_postTask)
            createTag("<element>", "xmlns:jbi" to "http://adaptivity.nl/ProcessEngine/activity",
                      "value" to "endpoint").appendTo(_repliesParam)

            var _taskParam = jQuery("<taskParam>").appendTo(_postTask)
            var _task = jQuery("<task>").appendTo(_taskParam)
            createTag("<attribute>",
                            "xmlns:jbi" to "http://adaptivity.nl/ProcessEngine/activity",
                            "value" to "instancehandle",
                            "name" to "instancehandle"
                   ).appendTo(_task)
            createTag("<attribute>",
                      "xmlns:jbi" to "http://adaptivity.nl/ProcessEngine/activity",
                      "value" to "handle",
                      "name" to "remotehandle"
                     ).appendTo(_task)
            createTag("<attribute>",
                      "xmlns:jbi" to "http://adaptivity.nl/ProcessEngine/activity",
                      "value" to "owner",
                      "name" to "owner"
                     ).appendTo(_task)
            // big nothing end --------------------------

            // add elements
            jQuery.each(this.attrs.elements, { i: Number, value: dynamic ->
                var _item = jQuery("<item>", json("name" to value.name, "type" to value.type)).appendTo(_task)

                for (key in value) {
//               if (!value[key]) continue
//               if (['name', 'type', 'eid'].indexOf(key) !== -1) continue
//               addItem(_cell, _item, value, key)
                    when (value[key]) {
                        "name", "type", "eid" -> addItem(_cell, _item, value, key)
                    }
                }

                // add defines and results
                if (value.type != "label") addResult(_cell, value)
            })

            return _cell
        }
    }

    /**
     * Gate Node
     *
     * @class Gate
     * @extends Base
     */
    abstract class Gate : Base {

        constructor(type: String, offset: Point, id: String? = null) : super(offset, id) {
            this.type = type
            linkLimit = LinkLimit()
            canEdit = true
        }


        /**
         * Open edit dialogue
         */
        fun edit(): model.dialogue.gate.Gate {
            return GateDialogue.Gate(this as org.w3c.dom.Element)
        }

        /** @override */
        override fun styleText(_text: JQuery) {
            _text.get(0)?.run { textContent = textContent?.toUpperCase() }
            _text.attr("style", "font-weight: bold;")
        }

        /**
         * Initialisation in graph
         */
        fun init() {
            if (!this.attrs) this.attrs = js("{ min: 2; max: 2 }")

            // and ?
            if (this.attrs.min == 2 && this.attrs.max == 2) {
                this.setText("and")
            }

            // or ?
            else if (this.attrs.min == 1 && this.attrs.max == 2) {
                this.setText("or")
            }

            // xor ?
            else if (this.attrs.min == 1 && this.attrs.max == 1) {
                this.setText("xor")
            }
        }

        /** @override */
        override fun fromXml(_xml: JQuery) {
            this.attrs = json("min" to -1, "max" to -1)
            _xml.attr("label")?.let { this.attrs.label = it }
            _xml.attr("min")?.let { this.attrs.min = it.toInt() }
            _xml.attr("max")?.let { this.attrs.max = it.toInt() }
        }

        /** @override */
        override fun toXml(predecessors: dynamic): JQuery {
            var params = json(
               "id" to this.eid,
               "x" to this.cell!!.attributes.asDynamic().position.x,
               "y" to this.cell!!.attributes.asDynamic().position.y
           )

            // default attributes
            if (!this.attrs) this.attrs = {}
            if (this.attrs.min >= 0) params["min"] = this.attrs.min
            if (this.attrs.max >= 0) params["max"] = this.attrs.max

            // predecessors
            if (this.type == "split" && predecessors.length) {
                params["predecessor"] = predecessors[0].eid
            }

            // create cell
            var _cell = jQuery("<${this.type}>", attributes = params)

            // join special predecessors
            if (this.type == "join" && predecessors.length) {
                jQuery.each(predecessors, { i: Number, value: dynamic ->
                    jQuery("<predecessor>${value.eid}</predecessor>")
                        .appendTo(_cell)
                })
            }

            return _cell
        }
    }

    /**
     * Split class
     *
     * @class Split
     * @extends Gate
     */
    class Split : Gate {
        constructor(offset: Point, id: String?) : super("split", offset, id)

        init {
            linkLimit = LinkLimit(1, -1)
        }
    }

    /**
     * Join class
     *
     * @class Join
     * @extends Gate
     */
    class Join : Gate {
        constructor(offset: Point, id: String?) : super("join", offset, id)

        init {
            linkLimit = LinkLimit(-1, 1)
        }
    }

    /**
     * End Node
     *
     * @class End
     * @extends Base
     */
    class End : Base {

        init {
            type = "end"
            linkLimit = LinkLimit(1, 0)
        }

        constructor(offset: Point, id: String?) : super(offset, id)


        override fun toXml(predecessors: dynamic): JQuery {
            var params = json(
                  "id" to this.eid,
                  "x" to this.cell!!.attributes.asDynamic().position.x,
                  "y" to this.cell!!.attributes.asDynamic().position.y
              )

            // predecessors
            if (predecessors.length) {
                params["predecessor"] = predecessors[0].eid
            }

            return jQuery("<end>", attributes = params)
        }
    }

}

fun createTag(name: String, vararg attrs: Pair<String, String>) = jQuery("<$name>", json(*attrs))
