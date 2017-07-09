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

package store

import es6.json
import jquery.*
import org.w3c.dom.Document
import org.w3c.dom.XMLDocument
import org.w3c.dom.parsing.XMLSerializer
import util.util
import kotlin.js.Json

/**
 * Model Store
 *
 * Handles the interaction between server and client, and stores the
 * intermediate results
 *
 * @module Store
 */

data class ModelData(var handle:Long, var xml: Document, var name: String?, var uuid: String?)

object model {

    private var models = mutableMapOf<String, ModelData>() // model storage
    private var _def: JQueryDeferred<Json>? = null // global deferred object

    const val URL = "/ProcessEngine/processModels" // base URL

    /**
     * Clone model as a new one with a given name
     *
     * @param handle model handle
     * @param name new model name
     *
     * @return {Promise}
     */
    @JsName("cloneModel")
    fun cloneModel(handle:Long, name:String): JQueryDeferred<Long> {
        val model = models[handle.toString()]!! // get model

        // convert to string, replace name and remove meta info
        var str = XMLSerializer().serializeToString(model.xml)
        str = util.replaceAttr(str, "name", model.name, name)
        str = util.replaceAttr(str, "handle")
        str = util.replaceAttr(str, "owner")
        str = util.replaceAttr(str, "uuid")

        return createModel(str) // delegate to creation function
    }

    /**
     * Rename existing model
     *
     * @param handle {Integer} model handle
     * @param name {String} new model name
     *
     * @return {Promise}
     */
    @JsName("renameModel")
    fun renameModel(handle:Long, name:String):JQueryPromise<Unit> {
        val model = models[handle.toString()]!! // get model

        // convert to string, replace name and remove meta info
        var str = XMLSerializer().serializeToString(model.xml)
        str = util.replaceAttr(str, "name", model.name, name)

        val url = URL + '/' + handle
        return util.upload(url, "processUpload", str).then(doneFilter = { _ ->
            val m = models[handle.toString()]!!
            m.name = name
            m.xml.documentElement?.setAttribute("name", name)
            Unit
        })
    }

    /**
     * Update existing model with XML
     * Does not change the name
     *
     * @param handle {Integer} model handle
     * @param str {String} model xml
     *
     * @return {Promise}
     */
    @JsName("updateModel")
    fun updateModel(handle:Long, str:String):JQueryPromise<Unit> {
        val url = URL + '/' + handle
        return util.upload(url, "processUpload", str).then({ _->
            val xml = jQuery.parseXML(str)
            val m = models[handle.toString()]!!
            m.xml = xml
            m.uuid = xml.documentElement?.getAttribute("uuid")
            Unit
        })
    }

    /**
     * Create new model with given XML
     *
     * @param xml {String} model xml
     *
     * @return {Promise}
     */
    @JsName("CreateModel")
    fun createModel(xml:String):JQueryDeferred<Long> {
        val _def = jQuery.Deferred<Long>() // sucess on model re-fetch
        util.upload(URL, "processUpload", xml).thenPromise<Long>({ model ->
            val _xml = jQuery(model as Any) // newly fetched model
            val handle = _xml.attr("handle").toLong() // now we know its handle
            fetchModel(handle, _xml.attr("name")).thenPromise<Long>({ _ ->
                _def.resolve(handle) // success
            })
        }).fail({ e:Any -> _def.reject(e); })
        return _def

    }

    /**
     * Download model
     *
     * @param handle {Integer} model handle
     * @param name {String} model name
     *
     * @return {Promise}
     */
    @JsName("fetchModel")
    fun fetchModel(handle:Long, name:String):JQueryPromise<ModelData> {
        val url = URL + '/' + handle
        return jQuery.get(url).then(doneFilter = { _xml: Any? ->
            // test framework workaround

            val xml: XMLDocument = if (_xml is String) jQuery.parseXML(
                _xml) else _xml as XMLDocument
            ModelData(handle = handle, name = name,
                      uuid = xml.documentElement?.getAttribute("uuid"),
                      xml = xml).also {
                models[handle.toString()] = it
            }
        })
    }

    /**
     * Remove existing model
     *
     * @param handle {Integer} model handle
     *
     * @return {Promise}
     */
    @JsName("deleteModel")
    fun deleteModel(handle:Long): JQueryPromise<Any?> {
        return jQuery.ajax(object:JQueryAjaxSettings{}.apply { // special REST method delete, so only $.ajax
            url= URL + '/' + handle
            type= "DELETE"
        }).then(doneFilter = { _-> models.remove(handle.toString()); })
    }

    /**
     * Update the whole list
     *
     * @return {Promise}
     */
    @JsName("update")
    fun update(): JQueryGenericPromise<Json> {
        // we need list of deferreds
        _def = jQuery.Deferred()
        val deferreds = mutableListOf<JQueryGenericPromise<out Any?>>()

        jQuery.get(URL).then(doneFilter = { xml ->
            models = mutableMapOf() // empty list
            jQuery(`obj`=xml!!).find("processModel")!!.each({ _, elem ->
                val _elem: JQuery = jQuery(elem)
                val handle = _elem.attr("handle").toLong() // get handle
                // fetch model
                deferreds.add(fetchModel(handle, _elem.attr("name")))
            })
            jQuery.`when`(*deferreds.toTypedArray()).thenPromise(doneFilterPromise = {
                // notify that everything is resolved
                _def!!.resolve(json(models))
            }).fail({ e:Any ->
                _def!!.reject(e)
                        console.error("Cannot update models", e)
                    })
        }).fail({ e:Any ->
            _def!!.reject(e)
            console.error("Cannot update models", e)
                })

        return _def!!
    }

    @JsName("getList")
    fun getList() = _def ?: update()
    @JsName("getModel")
    fun getModel(handle:Long) = models[handle.toString()]
    @JsName("reset")
    fun reset() {
        _def = null
        models.clear()
    }
}
