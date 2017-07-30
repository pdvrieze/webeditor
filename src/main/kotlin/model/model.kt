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

import es6.JSArray
import joint.dia.Cell
import joint.dia.CellView
import joint.dia.Graph
import joint.dia.Paper
import jquery.JQuery
import jquery.JQueryPromise


typealias NodeT = Any
/**
 * Kotlin interface for the model
 */

external interface ModelCount {
    var start:Int
    var activity:Int
    var join:Int
    var split:Int
    var end:Int
}

external interface ExtractResult {
    var title:String
    var value:String
}

external interface Model {
    var graph: Graph?
    var nodes: JSArray<NodeT>
    var selected: NodeT?
    var mode: Any?
    var paper: Paper?
    var nosave:Boolean

    fun click(cellView: CellView<out Cell>, edit: (Int, Int)->Unit)
    fun add(node:NodeT, offset:Coordinate, id:String)
    fun find(cellView: CellView<out Cell>)
    fun link(source: NodeT, target: NodeT)
    fun select(cellView: CellView<out Cell>, edit: (Int, Int) -> Unit)
    fun remove(cellView: CellView<out Cell>)
    fun extract(node:JQuery): Array<ExtractResult>
    fun storage(cellView: CellView<out Cell>): Array<ExtractResult>
    fun edit(cellView: CellView<out Cell>)
    fun connect(cellView: CellView<out Cell>)
    fun linkPossible(source: NodeT, target: NodeT)
    fun autoalign()
    fun fromXml(_xml:JQuery)
    fun toXml():String
    fun save():JQueryPromise<Unit>
    fun editXml()
    fun reload()
}

