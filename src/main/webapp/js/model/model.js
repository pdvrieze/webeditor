/**
 * Holds the list of nodes of the graph and allows some interactions in between
 * Basically a collection of convinient utilities to simplify jointjs
 * interactions
 * 
 * @module Model
 */
define(['jquery', 'joint', 'lodash', './node', 'store/model', 'webeditor',
        './dialogue', 'dagre'],
        function ($, joint, _, Nodes, storex, webeditor, Dialogue, dagre) {
    "use strict";

    var store = webeditor.store.model;

    var util = webeditor.util.util;
    // var Dialogue = webeditor.model.Dialogue;

    /** 
     * @class Model
     * @constructor
     * 
     * @param graph {Object} jointjs graph
     * @param paper {Object} jointjs paper
     */
    function Model(graph, paper) {
        this.graph = graph;
        this.nodes = [];
        this.selected = null;
        this.mode = null;
        this.paper = paper;
        this.nosave = true;

        // we count all the items
        this.count = {
            start: 0,
            activity: 0,
            join: 0,
            split: 0,
            end: 0
        };
    }

    /**
     * Since HTML is not case sensitive and jQuery does not support
     * namespaces in HTML, we generate HTML output, and then apply
     * namespaces as a hack + we fix the case
     *
     * @param str {String} big xml/html string to edit
     * @param items {Array} array of items to replace as namespaces
     *
     * @return {String} formatted string
     */
    function addNamespaces(str, items) {
        $.each(items, function (i, val) {
            var regex = new RegExp('(<\/?)' + val.split(':')[1], 'gi');
            str = str.replace(regex, '$1' + val);
        });
        return str;
    }

    Model.prototype = {
        /**
         * Cell is clicked by user
         * @method click
         *
         * @param cellView {Object} jointjs cell view
         * @param edit {Function} edit dialogue callback
         */
        click: function (cellView, edit) {
            var node = this.find(cellView);
            if (!node) return; // should not happen

            if (this.mode == 'connect' && this.selected != node) {
                // we are connecting two nodes
                this.link(this.selected, node);
                this.mode = null; // done
            }
            else this.select(cellView, edit); // regular selection
        },

        /**
         * Add new node on an offset
         * @method add
         *
         * @param node {Object} node to add
         * @param offset {Object} coordinate point
         * @param id {String} node ID
         */
        add: function (node, offset, id) {
            if (!node.cell) node.create(offset, id); // create if needed

            // add required references
            node.model = this;
            node.paper = this.paper;

            // add to graph and count
            this.graph.addCell(node.cell);
            this.nodes.push(node);
            this.count[node.type]++;

            // init if needed
            if (node.init) node.init();

            this.save(); // save model
        },

        /**
         * Find cell by its cellView
         * @method find
         *
         * @param cellView {Object} jointjs cell view
         *
         * @return {Object} node if found
         */
        find: function (cellView) {
            for (var i = 0; i < this.nodes.length; ++i) {
                if (this.nodes[i].cell.id == cellView.model.id) {
                    return this.nodes[i];
                }
            }
        },

        /**
         * Links two nodes together
         * @method link
         *
         * @param source {Object} source node
         * @param target {Object} target node
         */
        link: function (source, target) {
            if (!this.linkPossible(source, target)) {
                // this is not an error, stuff like that happens
                console.log('Impossible to connect');
                return;
            }

            // create arrow connector
            var cell = new joint.shapes.fsa.Arrow({
                source: { id: source.cell.id },
                target: { id: target.cell.id },
                router: { name: 'metro', args: {
                    startDirections: [ 'right' ],
                    endDirections: [ 'left' ],
                } },
                connector: { name: 'rounded', args: { radius: 4 } }
            });

            // add to graph
            this.graph.addCell(cell);

            this.save(); // save model
        },

        /**
         * Select node and delegate work to tooltip
         * @method select
         * 
         * @param cellView {Object} jointjs cell view
         * @param edit {Function} edit dialogue callback
         */
        select: function (cellView, edit) {
            this.selected = this.find(cellView);
            this.selected.tooltip(edit);
        },

        /**
         * Remove node
         * @method remove
         *
         * @param cellView {Object} jointjs cell view
         */
        remove: function (cellView) {
            var node = this.find(cellView);
            var index = this.nodes.indexOf(node);

            node.cell.remove();

            // count down nodes
            this.count[node.type]--;
            this.nodes.splice(index, 1);

            this.save(); // save model
        },

        /**
         * Extract resulable variable from node (activity)
         * @method extract
         *
         * @param node {Object} node to extract info from
         *
         * @return {Array} array of current node dynamic variables
         */
        extract: function (node) {
            var vars = [];
            if (node.attrs && node.attrs.elements) {
                for (var i = 0; i < node.attrs.elements.length; ++i) {
                    var element = node.attrs.elements[i];
                    var activity = node.attrs.label || '#' + node.eid;
                    if (element.type != 'label') {
                        vars.push({
                            title: activity + '.' + element.name,
                            value: '#' + node.eid + '.' + element.eid
                        });
                    }
                }
            }
            return vars;
        },

        /**
         * Extract resusable variables from all nodes behind the current one
         * Does not work for multiple links, because of ambiguities
         * @method storage
         *
         * @param cellView {Object} jointjs cell view
         *
         * @return {Array} array of previous dynamic variables
         */
        storage: function (cellView) {
            var cell = this.find(cellView).cell;
            var storage = [];

            var G = this.graph;
            var links = G.getConnectedLinks(cell, {inbound: true});
            while (links.length == 1) {
                cell = this.find({model: {id: links[0].attributes.source.id}});
                storage = storage.concat(this.extract(cell));
                links = G.getConnectedLinks(cell.cell, {inbound: true});
            }

            return storage;
        },

        /**
         * Open edit dialogue
         * @method edit
         *
         * @param cellView {Object} jointjs cell view
         */
        edit: function (cellView) {
            this.find(cellView).edit(this.storage(cellView));
        },

        /**
         * Enter connection mode
         * @method connect
         *
         * @param cellView {Object} jointjs cell view
         */
        connect: function (cellView) {
            this.mode = 'connect';
            this.selected = this.find(cellView);
        },

        /**
         * Check if link between two nodes is possible
         * Or if target is not specified, checks if links from this node are
         * possible at all
         * @method linkPossible
         *
         * @param source {Object} source node
         * @param target {Object} target node
         *
         * @return {Boolean}
         */
        linkPossible: function (source, target) {
            var G = this.graph;
            var sLinks = G.getConnectedLinks(source.cell, {outbound: true});
            if (target) {
                var tLinks = G.getConnectedLinks(target.cell, {inbound: true});
                if (tLinks.length == target.linkLimit.input) return false;
            }
            if (sLinks.length == source.linkLimit.output) return false;
            return true;
        },

        /**
         * Auto align model
         * @method autoalign
         */
        autoalign: function () {
            var SIZE = Nodes.SIZE; // spacing

            if (!this.nodes.length) return; // nothing to do

            // create graph for alignment
            var g = new dagre.graphlib.Graph();

            // set defaults
            g.setGraph({});
            g.setDefaultEdgeLabel(function() { return {}; });

            // add every node with sizes 60x60
            $.each(this.nodes, function (i, node) {
                g.setNode(node.cell.id, { width: SIZE, height: SIZE });
            });

            // add edges
            var G = this.graph;
            $.each(this.nodes, function (i, node) {
                var links = G.getConnectedLinks(node.cell, { inbound: true });
                $.each(links, function (i, link) {
                    var attrs = link.attributes;
                    g.setEdge(attrs.source.id, attrs.target.id);
                });
            });
            
            // relayout
            dagre.layout(g);

            // save new positions and calculate bounds
            var positions = {};
            var bounds = {
                x: { min: Infinity, max: -Infinity },
                y: { min: Infinity, max: -Infinity }
            };
            g.nodes().forEach(function(id) {
                positions[id] = g.node(id);
                var x = positions[id].y;
                var y = positions[id].x;
                if (x < bounds.x.min) bounds.x.min = x;
                if (x > bounds.x.max) bounds.x.max = x;
                if (y < bounds.y.min) bounds.y.min = y;
                if (y > bounds.y.max) bounds.y.max = y;
            });

            var width = bounds.x.max - bounds.x.min + Nodes.SIZE;
            var height = bounds.y.max - bounds.y.min + Nodes.SIZE;

            var offsetX = width / 2 - 100;
            var offsetY = height / 2 - 150;

            // apply new positions switching x and y
            this.nosave = true;
            $.each(this.nodes, function (i, node) {
                var pos = positions[node.cell.id];
                node.cell.position(pos.y - offsetX, pos.x - offsetY);
            });
            this.nosave = false;

            // save graph
            this.save();
        },

        /**
         * Convert from xml
         * @method fromXml
         *
         * @param $xml {Object} XML node
         */
        fromXml: function ($xml) {
            var self = this;

            // nodes will be stored here
            var nodes = {};

            // find root
            var $model = $xml.find(util.xmlSel('pe:processModel'));
            this.name = $model.attr('name');

            // traverse children
            $model.children().each(function () {
                var name = _.capitalize(this.tagName.replace(/^pe:/, ''));
                if (name == 'Activity') name = 'Activity'; // remap

                // find offset
                var offset = {
                    x: $(this).attr('x') - 0,
                    y: $(this).attr('y') - 0,
                };

                // create node
                var id = $(this).attr('id');
                var node = new Nodes[name]();
                node.fromXml($(this), $model);

                // save for reause
                nodes[id] = node;

                // add
                self.add(node, offset, $(this).attr('id'));

                // init
                if (node.init) node.init();
            }).each(function () {
                // additional pass to connect nodes to their counterparts
                var id = $(this).attr('id');
                var predecessor = $(this).attr('predecessor');

                if (predecessor) self.link(nodes[predecessor], nodes[id]);
                $(this).find(util.xmlSel('pe:predecessor')).each(function () {
                    self.link(nodes[this.textContent], nodes[id]);
                });
            });
        },

        /**
         * Convert model to XML
         * @method toXml
         *
         * @return {String}
         */
        toXml: function () {
            var self = this;
            var G = this.graph;

            // root element
            var $xml = $('<processModel>', {
                name: this.name,
                'xmlns:pe': 'http://adaptivity.nl/ProcessEngine/',
                'xmlns:umh': 'http://adaptivity.nl/userMessageHandler'
            });

            var model = store.getModel(this.handle);
            if (model) $xml.attr('uuid', model.uuid);

            // for every node find its predecessor and append using its own
            // toXml method
            $.each(this.nodes, function (i, val) {
                var links = G.getConnectedLinks(val.cell, {inbound: true});
                var cells = [];
                $.each(links, function (i, link) {
                    cells.push(self.find({
                        model: {id: link.attributes.source.id}
                    }));
                });
                $xml.append(val.toXml(cells));
            });

            // fix namespaces and case (see addNamespaces())
            return addNamespaces($('<div>').append($xml).html(), [
                'pe:processModel', 'pe:start', 'pe:end', 'pe:join', 'pe:split',
                'pe:activity', 'pe:predecessor', 'pe:define', 'pe:result',
                'pe:message', 'jbi:attribute', 'umh:task', 'umh:taskParam',
                'umh:repliesParam', 'env:Body', 'env:Envelope', 'umh:postTask',
                'jbi:element', 'umh:item'
            ]).replace(/servicens/g, 'serviceNS')
                .replace(/servicename/g, 'serviceName')
                .replace(/encodingstyle/g, 'encodingStyle')
                .replace(/taskparam/g, 'taskParam');
        },

        /**
         * Save model to server
         * @method fromXml
         *
         * @return {Promise}
         */
        save: function () {
            if (this.nosave) return; // do not save unless needed

            var xml = this.toXml();
            var handle = this.handle;
            return store.updateModel(handle, xml).fail(function () {
                alert('Connection Error');
            });
        },

        /**
         * Opens dialogue to edit xml
         * @method editXml
         */
        editXml: function () {
            new Dialogue.Xml(this);
        },

        /**
         * Reload model from store xml
         * @method reload
         */
        reload: function () {
            this.nosave = true;
            $.each(this.nodes, function (i, node) { node.cell.remove(); });
            this.nodes = [];
            this.fromXml($(store.getModel(this.handle).xml));
            this.nosave = false;
        }
    };

    // export
    return Model;
});
