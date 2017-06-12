import org.w3c.dom.Element
import org.w3c.dom.Node

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
 * Shows the gate dialogue and allows the interactions with it
 *
 * @module Dialogue
 */
object gate {

    /**
     * Gate Dialogue class
     *
     * @class Gate
     * @constructor
     *
     * @param node {Object} calling node
     */
    class Gate(node: Element) {
        var node: dynamic = null
        var attrs: dynamic = null

        var _target: BootstrapJQuery? = null

        private var _body: JQuery? = null
        private var _in: JQuery? = null
        private var _out: JQuery? = null
        private var _controls: JQuery? = null

        init {

            this.node = node // save the node

            // initialise the attributes with default values if needed
            this.attrs = js("node.attrs || { min: 2, max: 2 }")

            // find dialogue output
            this._target = jQuery(selector = "#dialogue").bootstrap

            // set title
            this._target?.find(".modal-title")?.html("Gate Settings")

            // render content
            this._body = this._target?.find(".modal-body")
            simpleTemplate.renderTo(this._body!!, "dialogue-gate", null)

            // initialise values
            this.init()
            this.initChange()
            this.initSave()

            // show dialogue
            this._target?.let { it.modal("show") }
        }


        /**
         * Initialise elements
         * @method init
         */
        fun init() {
            // find all controls
            this._in = this._body?.find("#gate_in")
            this._out = this._body?.find("#gate_out")
            this._controls = this._body?.find("#gate_controls")

            // initialise label
            if (this.attrs.label) {
                this._body?.find("#label")?.`val`(this.attrs.label as String)
            }

            // initialise gates
            this._in?.`val`(this.attrs.min as Number)
            this._out?.`val`(this.attrs.max as Number)
        }

        /**
         * Initialise change listener for buttons
         * @method initChange
         */
        fun initChange() {
            var self = this
            // selector for current choice
            var selector = "[min=${this.attrs.min}]" +
                           "[max=${this.attrs.max}]"
            this._body?.find("button")?.on("click", { eventObj, args ->
                var _this = jQuery(eventObj)

                // select this button, deselecting others
                self._body?.find("button")?.removeClass("active")
                _this.addClass("active")

                // if button has hardcoded values save them to attributes
                // and propogate to inputs
                if (_this.attr("min").isNotBlank()) {
                    self.attrs.min = _this.attr("min")
                    self.attrs.max = _this.attr("max")
                    self._in?.`val`(self.attrs.min as Number)
                    self._out?.`val`(self.attrs.max as Number)
                }

                // collapse or unhide the controls if other button is selected
                if (_this.`val`() != "other") self._controls?.hide()
                else self._controls?.show()
            })?.filter(selector)?.click() // click on current selection
        }

        /**
         * Setup listeners for saving this modal
         * @method initSave
         */
        fun initSave() {
            var self = this

            self._target?.find("#save")?.off()?.on("click", { _, _ ->
                // if label is set add it to attributes
                self._body?.find("#label")?.`val`()?.let { self.attrs.label = it }

                // find what button is selected
                var text = self._body?.find(".active")?.`val`()
                if (self._body?.find("[value=other].active")?.asDynamic()?.size() ?: false >0) {
                    // manually apply attributes when other is selected
                    self.attrs.min = self._in?.`val`()
                    self.attrs.max = self._out?.`val`()
                    text = ""
                }

                // apply attriutes on node
                self.node.attrs = self.attrs
                self.node.setText(text)
                self.node.model.save()

                // hide modal
                self._target?.modal("hide")
            })
        }
    }
}
