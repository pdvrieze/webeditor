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

@file:JsQualifier("dia")
package joint.dia

import org.w3c.dom.events.Event
import ext.JsMap
import joint.Padding
import jquery.JQuery
import org.w3c.dom.HTMLElement
import org.w3c.dom.svg.SVGDefsElement
import org.w3c.dom.svg.SVGElement
import org.w3c.dom.svg.SVGGElement
import kotlin.js.Json

external interface Size {
    var width: Number
    var height: Number
}

external interface Point {
    var x: Number
    var y: Number
}

external interface BBox : Point, Size

external interface TranslateOptions {
    var restrictedArea: BBox?
    var transition: TransitionOptions?
}

external interface TransitionOptions {
    var delay: Number?
    var duration: Number?
    var timingFunction: ((t: Number) -> Number)?
    var valueFunction: ((a: Any, b: Any) -> (t: Number) -> Any)?
}

external interface ExploreOptions: DeepOptions { var breadthFirst: Boolean? }
external interface DfsBfsOptions : InOutBoundOptions, DeepOptions

external interface StrictOptions { var strict: Boolean }
external interface GraphOptions { var cellNamespace:Any }
external interface InOutBoundOptions { var inbound: Boolean?; var outbound: Boolean?}
external interface DeepOptions { var deep:Boolean? }
external interface BreathFirstOptions { var breadthFirst: Boolean? }
external interface SearchByOptions { var searchBy: String? /* 'bbox' | 'center' | 'origin' | 'corner' | 'topRight' | 'bottomLeft'*/ }
external interface DisconnectOptions { var disconnectLinks:Boolean? }

external interface ParentRelativeOptions { var parentRelative: Boolean }
external interface DirectionOptions { var direction: String? /*'left' | 'right' | 'top' | 'bottom' | 'top-right' | 'top-left' | 'bottom-left' | 'bottom-right'*/}
external interface FitEmbedOptions: DeepOptions { var padding: Padding? }

external class Graph : Backbone.Model {
    constructor(attributes: Any? = definedExternally, options: GradientOptions? = definedExternally)
    fun addCell(cell: Cell): Graph
    fun addCell(cell: Array<Cell>): Graph
    fun addCells(cells: Array<Cell>): Graph
    fun resetCells(cells: Array<Cell>, options: Any? = definedExternally): Graph
    fun getCell(id: String): Cell
    fun getElements(): Array<Element>
    fun getLinks(): Array<Link>
    fun getCells(): Array<Cell>
    fun getFirstCell(): Cell
    fun getLastCell(): Cell
    fun getConnectedLinks(element: Cell, options: DfsBfsOptions? = definedExternally): Array<Link>
    fun disconnectLinks(cell: Cell, options: Any? = definedExternally): Unit
    fun removeLinks(cell: Cell, options: Any? = definedExternally): Unit
    fun translate(tx: Number, ty: Number?, options: TranslateOptions? = definedExternally): Unit
    fun cloneCells(cells: Array<Cell>): JsMap<Cell>//{ [id: String]: Cell }
    fun getSubgraph(cells: Array<Cell>, options: DeepOptions? = definedExternally): Array<Cell>
    fun cloneSubgraph(cells: Array<Cell>, options: DeepOptions? = definedExternally): JsMap<Cell>//{ [id: String]: Cell }
    fun dfs(element: Element, iteratee: (element: Element, distance: Number) -> Boolean, options: DfsBfsOptions? = definedExternally, visited: Any? = definedExternally, distance: Number? = definedExternally): Unit
    fun bfs(element: Element, iteratee: (element: Element, distance: Number) -> Boolean, options: DfsBfsOptions? = definedExternally): Unit
    fun search(element: Element, iteratee: (element: Element, distance: Number) -> Boolean, options: BreathFirstOptions? = definedExternally): Unit
    fun getSuccessors(element: Element, options: ExploreOptions? = definedExternally): Array<Element>
    fun getPredecessors(element: Element, options: ExploreOptions? = definedExternally): Array<Element>
    fun isSuccessor(elementA: Element, elementB: Element): Boolean
    fun isPredecessor(elementA: Element, elementB: Element): Boolean
    fun isSource(element: Element): Boolean
    fun isSink(element: Element): Boolean
    fun getSources(): Array<Element>
    fun getSinks(): Array<Element>
    fun getNeighbors(element: Element, options: DfsBfsOptions? = definedExternally): Array<Element>
    fun isNeighbor(elementA: Element, elementB: Element, options: InOutBoundOptions? = definedExternally): Boolean
    fun getCommonAncestor(vararg cells: Cell): Element
    fun toJSON(): Json
    fun fromJSON(json: Json, options: Any? = definedExternally): Graph
    fun clear(options: Any? = definedExternally): Graph
    fun findModelsFromPoint(rect: BBox): Array<Element>
    fun findModelsUnderElement(element: Element, options: SearchByOptions? = definedExternally): Array<Element>
    fun getBBox(elements: Array<Element>, options: Any? = definedExternally): BBox
    fun toGraphLib(): Any; // graphlib graph objec
    fun findModelsInArea(rect: BBox, options: Any? = definedExternally): Any //BBox | Boolean
    fun getCellsBBox(cells: Array<Cell>, options: Any? = definedExternally): BBox
    fun getInboundEdges(node: String): Any
    fun getOutboundEdges(node: String): Any
    fun hasActiveBatch(name: String? = definedExternally): Any //Number | Boolean
    fun maxZIndex(): Number
    fun removeCells(cells: Array<Cell>, options: Any?): Graph
    fun resize(width: Number, height: Number, options: Number? = definedExternally): Graph
    fun resizeCells(width: Number, height: Number, cells: Array<Cell>, options: Number? = definedExternally): Graph
    fun set(key: Any, value: Any, options: Any? = definedExternally): Graph
    fun set(key: String, value: Any, options: Any? = definedExternally): Graph
    fun startBatch(name: String, data: Any? = definedExternally): Any
    fun stopBatch(name: String, data: Any? = definedExternally): Any
}

external open class Cell : Backbone.Model {
    @JsName("id")
    var strid: String
    fun toJSON(): Json
    fun remove(options: DisconnectOptions? = definedExternally): Cell
    fun toFront(options: DeepOptions? = definedExternally): Cell
    fun toBack(options: DeepOptions? = definedExternally): Cell
    fun getAncestors(): Array<Cell>
    fun isEmbeddedIn(element: Element, options: DeepOptions? = definedExternally): Boolean
    fun prop(key: String): Any
    fun prop(obj: Any): Cell
    fun prop(key: String, value: Any, options: Any?): Cell
    fun removeProp(path: String, options: Any?): Cell
    fun attr(key: String): Any
    fun attr(obj: SVGAttributes): Cell
    fun attr(key: String, value: Any): Cell
    override fun clone(): Cell
    fun clone(opt: DeepOptions?): Any //Cell | Array<Cell>
    fun removeAttr(path: String, options: Any? = definedExternally): Cell
    fun removeAttr(path: Array<String>, options: Any? = definedExternally): Cell
    fun transition(path: String, value: Any? = definedExternally, options: TransitionOptions? = definedExternally, delim: String? = definedExternally): Number
    fun getTransitions(): Array<String>
    fun stopTransitions(path: String? = definedExternally, delim: String? = definedExternally): Cell
    fun addTo(graph: Graph, options: Any? = definedExternally): Cell
    fun isLink(): Boolean
    fun embed(cell: Cell, options: Any? = definedExternally): Cell
    open fun findView(paper: Paper): CellView<out Cell>
    fun getEmbeddedCells(options: Any? = definedExternally): Array<Cell>
    fun initialize(options: Any? = definedExternally): Unit
    fun isElement(): Boolean
    fun isEmbedded(): Boolean
    fun processPorts(): Unit
    fun startBatch(name: String, options: Any? = definedExternally): Cell
    fun stopBatch(name: String, options: Any? = definedExternally): Cell
    fun unembed(cell: Cell, options: Any? = definedExternally): Cell
}

external open class Element : Cell {
    fun translate(tx: Number, ty: Number? = definedExternally, options: TranslateOptions? = definedExternally): Element
    fun position(options: ParentRelativeOptions? = definedExternally): Point
    fun position(x: Number, y: Number, options: ParentRelativeOptions? = definedExternally): Element
    fun resize(width: Number, height: Number, options: DisconnectOptions? = definedExternally): Element
    fun rotate(deg: Number, absolute: Boolean = definedExternally, origin: Point? = definedExternally): Element
    fun embed(cell: Cell): Element
    fun unembed(cell: Cell): Element
    fun getEmbeddedCells(options: ExploreOptions? = definedExternally): Array<Cell>
    fun fitEmbeds(options: FitEmbedOptions? = definedExternally): Element
    fun getBBox(options: Any?): BBox
    override fun findView(paper: Paper): ElementView
    fun scale(scaleX: Number, scaleY: Number, origin: Point?, options: Any?): Element
    fun addPort(port: Any, opt: Any?): Element
    fun addPorts(ports: Array<Any>, opt: Any?): Element
    fun removePort(port: Any, opt: Any?): Element
    fun hasPorts(): Boolean
    fun hasPort(id: String): Boolean
    fun getPorts(): Array<Any>
    fun getPort(id: String): Any
    fun getPortIndex(port: Any): Number
    fun portProp(portId: String, path: Any, value: Any?, opt: Any?): joint.dia.Element
}

external interface CSSSelector:Json {
//    [key: String]: String | Number | Any; // Any added to support special attributes like filter http://jointjs.com/api#SpecialAttributes:filter
}

external interface SVGAttributes:Json {
//    [selector: String]: CSSSelector
}

external interface CellAttributes:Json {
//    [key: String]: Any
}

external interface TextT: Json {
    var text: String?
}
external interface TextAttrs : SVGAttributes {
    var text: TextT? //
}

external interface Label {
    var position: Number
    var attrs: TextAttrs?
}
external interface LinkAttributes : CellAttributes {
    var source: Any? //Point? | { id: String, selector: String?, port: String? }
    var target: Any? // Point? | { id: String, selector: String?, port: String? }
    var labels: Array<Label>?
    var vertices: Array<Point>?
    var smooth: Boolean?
    var attrs: TextAttrs?
    var z: Number?
}

external open class Link : Cell {
    var markup: String
    var labelMarkup: String
    var toolMakup: String
    var vertexMarkup: String
    var arrowHeadMarkup: String

    constructor(attributes: LinkAttributes?, options: Any?)
    fun disconnect(): Link
    fun label(index: Number?): Any
    fun label(index: Number, value: Label): Link
    fun reparent(options: Any?): Element
    override fun findView(paper: Paper): LinkView
    fun getSourceElement(): Element
    fun getTargetElement(): Element
    fun hasLoop(options: DeepOptions? = definedExternally): Boolean
    fun applyToPoints(fn: ()->Any?, options: Any?): Link
    fun getRelationshipAncestor(): Element
    fun isRelationshipEmbeddedIn(element: Element): Boolean
    fun scale(sx: Number, sy: Number, origin: Point, optionts: Any?): Link
    fun translate(tx: Number, ty: Number, options: Any?): Link
}

external interface ManhattanRouterArgs {
    var excludeTypes: Array<String>?
    var excludeEnds:String? //?: 'source' | 'target'
    var startDirections: String? //?: ['left' | 'right' | 'top' | 'bottom']
    var endDirections: String?//?: ['left' | 'right' | 'top' | 'bottom']
}

external interface PaperOptions : Backbone.ViewOptions<Graph> {
//    override var el: Any? //String? | JQuery? | HTMLElement?
    var width: Number?
    var height: Number?
    var origin: Point?
    var gridSize: Number?
    var perpendicularLinks: Boolean?
    var elementView: Any? // (element: Element) -> ElementView | ElementView
    var linkView: Any?// (link: Link) -> LinkView | LinkView
    var defaultLink: Any?// ((cellView: CellView, magnet: SVGElement) -> Link) | Link
    var defaultRouter: Any?// ((vertices: Array<Point>, args: Any, linkView: LinkView) -> Array<Point>) | { name: String, args: ManhattanRouterArgs? }
    var defaultConnector: Any?// ((sourcePoint: Point, targetPoint: Point, vertices: Array<Point>, args: Any, linkView: LinkView) -> String) | { name: String, args?: { radius: Number? } }
    var interactive: Any?//((cellView: CellView, event: String) -> Boolean) | Boolean | { vertexAdd: Boolean?, vertexMove: Boolean?, vertexRemove: Boolean?, arrowheadMove: Boolean? }
    var validateMagnet: ((cellView: CellView<out Cell>, magnet: SVGElement) -> Boolean)?
    var validateConnection: Any?// (cellViewS: CellView, magnetS: SVGElement, cellViewT: CellView, magnetT: SVGElement, end: 'source' | 'target', linkView: LinkView) -> Boolean
    var linkConnectionPoint: ((linkView: LinkView, view: ElementView, magnet: SVGElement, reference: Point) -> Point)?
    var snapLinks: Any? //Boolean? | { radius: Number }
    var linkPinning: Boolean?
    var markAvailable: Boolean?
    var async: Any? //Boolean? | { batchZise: Number }
    var embeddingMode: Boolean?
    var validateEmbedding: ((childView: ElementView, parentView: ElementView) -> Boolean)?
    var restrictTranslate: Any? //((elementView: ElementView) -> BBox) | Boolean
    var guard: ((evt: Event, view: CellView<out Cell>) -> Boolean)?
    var multiLinks: Boolean?
    var cellViewNamespace: Any?
    /** useful undocumented option */
    var clickThreshold: Number?
    var highlighting: Any?
}

external interface ScaleContentOptions {
    var padding: Number?
    var preserveAspectRatio: Boolean?
    var minScale: Number?
    var minScaleX: Number?
    var minScaleY: Number?
    var maxScale: Number?
    var maxScaleX: Number?
    var maxScaleY: Number?
    var scaleGrid: Number?
    var fittingBBox: BBox?
}

external interface FitToContentOptions {
    var gridWidth: Number?
    var gridHeight: Number?
    var padding: Padding?
    var allowNewOrigin: String?// 'negative' | 'positive' | 'Any'
    var minWidth: Number?
    var minHeight: Number?
    var maxWidth: Number?
    var maxHeight: Number?
}

external class Paper : Backbone.View<Graph> {
    constructor(options: PaperOptions?)
    var options: PaperOptions
    var svg: SVGElement
    var viewport: SVGGElement
    var defs: SVGDefsElement
    fun setDimensions(width: Number, height: Number): Unit
    fun setOrigin(x: Number, y: Number): Unit
    fun scale(sx: Number, sy: Number?, ox: Number?, oy: Number?): Paper
    fun findView(element: Any): CellView<out Cell>
    fun findViewByModel(model: Cell): CellView<out Cell>
    fun findViewByModel(model: String): CellView<out Cell>
    fun findViewsFromPoint(point: Point): Array<ElementView>
    fun findViewsInArea(rect: BBox, options: StrictOptions?): Array<CellView<out Cell>>
    fun fitToContent(options: FitToContentOptions?): Unit
    fun scaleContentToFit(options: ScaleContentOptions?): Unit
    fun getContentBBox(): BBox
    fun clientToLocalPoint(p: Point): Point

    fun rotate(deg: Number, ox: Number?, oy: Number?): Paper;      // @todo not released yet though it's in the source code already

    fun afterRenderViews(): Unit
    fun asyncRenderViews(cells: Array<Cell>, options: Any?): Unit
    fun beforeRenderViews(cells: Array<Cell>): Array<Cell>
    fun cellMouseout(evt: Event): Unit
    fun cellMouseover(evt: Event): Unit
    fun clearGrid(): Paper
    fun contextmenu(evt: Event): Unit
    fun createViewForModel(cell: Cell): CellView<out Cell>
    fun drawGrid(options: Any?): Paper
    fun fitToContent(gridWidth: Number?, gridHeight: Number?, padding: Number?, options: Any?): Unit
    fun getArea(): BBox
    fun getDefaultLink(cellView: CellView<out Cell>, magnet: HTMLElement): Link
    fun getModelById(id: String): Cell
    fun getRestrictedArea(): BBox
    fun guard(evt: Event, view: CellView<out Cell>): Boolean
    fun linkAllowed(linkView: LinkView): Boolean
    fun linkAllowed(model: Link): Boolean
    fun mouseclick(evt: Event): Unit
    fun mousedblclick(evt: Event): Unit
    fun mousewheel(evt: Event): Unit
    fun onCellAdded(cell: Cell, graph: Graph, options: Any): Unit
    fun onCellHighlight(cellView: CellView<out Cell>, magnetEl: HTMLElement, options: Any?): Unit
    fun onCellUnhighlight(cellView: CellView<out Cell>, magnetEl: HTMLElement, options: Any?): Unit
    fun onRemove(): Unit
    fun pointerdown(evt: Event): Unit
    fun pointermove(evt: Event): Unit
    fun pointerup(evt: Event): Unit
    override fun remove(): Paper
    fun removeView(cell: Cell): CellView<out Cell>
    fun removeViews(): Unit
    fun renderView(cell: Cell): CellView<out Cell>
    fun resetViews(cellsCollection: Array<Cell>, options: Any): Unit
    fun resolveHighlighter(options: Any?): Any //Boolean | Any
    fun setGridSize(gridSize: Number): Paper
    fun setInteractivity(value: Any): Unit
    fun snapToGrid(p: Point): Point
    fun sortViews(): Unit
}


external class GradientStop {
    var offset: String
    var color: String
    var opacity: Number?
}
external interface GradientOptions {
    var type: String //'linearGradient' | 'radialGradient'
    var stops: Array<GradientStop>
}

external class GeometryOptions {var useModelGeometry: Boolean?}
external open class CellViewGeneric<T:Backbone.Model> : Backbone.View<T> {
    fun getBBox(options: GeometryOptions?): BBox
    fun highlight(el: Any?, options: Any?): CellViewGeneric<T>
    fun unhighlight(el: Any?, options: Any?): CellViewGeneric<T>
    fun applyFilter(selector: String, filter: Any): Unit
    fun applyFilter(selector: HTMLElement, filter: Any): Unit
    fun applyGradient(selector: String, attr: String /*'fill' | 'stroke'*/, gradient: GradientOptions): Unit
    fun applyGradient(selector: HTMLElement, attr: String /*'fill' | 'stroke'*/, gradient: GradientOptions): Unit
    fun can(feature: String): Boolean
    fun findBySelector(selector: String): JQuery
    fun findMagnet(el: Any): HTMLElement
    fun getSelector(el: HTMLElement, prevSelector: String): String
    fun getStrokeBBox(el: Any): BBox; // String|HTMLElement|Vectorizer
    fun mouseout(evt: Event): Unit
    fun mouseover(evt: Event): Unit
    fun mousewheel(evt: Event, x: Number, y: Number, delta: Number): Unit
    fun notify(eventName: String): Unit
    fun onChangeAttrs(cell: Cell, attrs: Backbone.ViewOptions<T>, options: Any?): CellViewGeneric<T>
    fun onSetTheme(oldTheme: String, newTheme: String): Unit
    fun pointerclick(evt: Event, x: Number, y: Number): Unit
    fun pointerdblclick(evt: Event, x: Number, y: Number): Unit
    fun pointerdown(evt: Event, x: Number, y: Number): Unit
    fun pointermove(evt: Event, x: Number, y: Number): Unit
    fun pointerup(evt: Event, x: Number, y: Number): Unit
    override fun remove(): CellViewGeneric<T>
    fun setInteractivity(value: Any): Unit
    fun setTheme(theme: String, options: Any?): CellViewGeneric<T>
}

external open class CellView<T:Cell> : CellViewGeneric<T> { }

external interface ElementViewAttributes: Json {
    var style: String?
    var text: String?
    var html: String?
//    "ref-y": String? | Number?
//    "ref-dx": Number?
//    "ref-dy": Number?
//    "ref-width": String? | Number?
//    "ref-height": String? | Number?
    var ref: String?
//    "x-alignment"?: 'middle' | 'right' | Number
//    "y-alignment"?: 'middle' | 'bottom' | Number
    var port: String?
}
external open class ElementView : CellView<Element> {
    fun scale(sx: Number, sy: Number): Unit; // @todo Documented in source but not released
    fun finalizeEmbedding(options: Any?): Unit
    fun getBBox(options: Any?): BBox
    fun positionRelative(vel: Any, bbox: BBox, attributes: ElementViewAttributes, nodesBySelector: Any?): Unit; // Vectorizer
    fun prepareEmbedding(options: Any?): Unit
    fun processEmbedding(options: Any?): Unit
    override fun render(): ElementView
    fun renderMarkup(): Unit
    fun resize(): Unit
    fun rotate(): Unit
    fun translate(model: Backbone.Model, changes: Any?, options: Any?): Unit
    fun update(cell: Cell, renderingOnlyAttrs: Any?): Unit
}

external class LinkViewOptions {
    var shortLinkLength: Number?
    var doubleLinkTools: Boolean?
    var longLinkLength: Number?
    var linkToolsOffset: Number?
    var doubleLinkToolsOffset: Number?
    var sampleInterval: Number

}

external interface IdObject {
    var id:String
}
external class LinkView : CellView<Link> {
    var options: LinkViewOptions
    fun getConnectionLength(): Number
    fun sendToken(token: SVGElement, duration: Number?, callback: (() -> Unit)? = definedExternally): Unit
    fun addVertex(vertex: Point): Number
    fun getPointAtLength(length: Number): Point; // Marked as public api in source but not in the documents
    fun createWatcher(endType: IdObject): ()->Any?
    fun findRoute(oldVertices: Array<Point>): Array<Point>
    fun getConnectionPoint(end: String /*'source' | 'target'*/, selector: Element, referenceSelector: Element): Point
    fun getConnectionPoint(end: String /*'source' | 'target'*/, selector: Element, referencePoint: Point): Point
    fun getConnectionPoint(end: String /*'source' | 'target'*/, point: Point, referenceSelector: Element): Point
    fun getConnectionPoint(end: String /*'source' | 'target'*/, point: Point, referencePoint: Point): Point
    fun getPathData(vertices: Array<Point>): Any
    fun onEndModelChange(endType: String/*'source' | 'target'*/, endModel: Element? = definedExternally, opt: Any? = definedExternally): Unit
    fun onLabelsChange(): Unit
    fun onSourceChange(cell: Cell, sourceEnd: IdObject, options: Any = definedExternally): Unit
    fun onTargetChange(cell: Cell, targetEnd: IdObject, options: Any = definedExternally): Unit
    fun onToolsChange(): Unit
    fun onVerticesChange(cell: Cell, changed: Any, options: Any): Unit
    fun removeVertex(idx: Number): LinkView
    override fun render(): LinkView
    fun renderArrowheadMarkers(): LinkView
    fun renderLabels(): LinkView
    fun renderTools(): LinkView
    fun renderVertexMarkers(): LinkView
    fun startArrowheadMove(end: String/*'source' | 'target'*/, options: Any?): Unit
    fun startListening(): Unit
    fun update(model: Any, attributes: Any, options: Any?): LinkView
    fun updateArrowheadMarkers(): LinkView
    fun updateAttributes(): Unit
    fun updateConnection(options: Any?): Unit
    fun updateLabelPositions(): LinkView
    fun updateToolsPosition(): LinkView
}
