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

package model.dialogue

import BootstrapJQuery
import bootstrap
import jquery.JQuery
import jquery.jQuery
import simpleTemplate
import util.util

/**
 * Kotlin wrapper for the xml class
 */
external interface ModelType {
    var handle: Long
    fun  reload()
    fun  toXml(): String

}


object XmlStatic {
    operator fun invoke(model:ModelType): Xml = Xml(model)
}

class Xml(model: ModelType) {
    var model: ModelType = model
    @JsName("\$target")
    var _target: BootstrapJQuery = jQuery(selector = "#dialogue").bootstrap
    @JsName("\$body")
    var _body: JQuery = _target.find(".modal-body")!!

    init {
        simpleTemplate.renderTo(_body, "dialogue-xml");
        _target.apply {
            find("#save")?.off()?.click {
              store.model.updateModel(model.handle, _body.find("textarea")?.value() as String).then { _ ->
                  _target.modal("hide")
                  model.reload()
              }.fail({ e:Any ->
                  alert("could not save XML")
                  console.error("XML Saving Failed", e)
              })

            }
            modal("show")
        }
        _body.find("textarea")?.value(util.formatXml(this.model.toXml()))
    }
}

val xml:XmlStatic = XmlStatic