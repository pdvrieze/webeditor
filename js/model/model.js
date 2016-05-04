/*
 * Model class
 *
 * Holds the list of nodes of the graph and allows some interactions in between
 * Basically a collection of convinient utilities to simplify jointjs
 * interactions
 */
define(['jquery', 'joint', 'lodash', './node', 'store/model', 'util/util'],
       function ($, joint, _, Nodes, store, util) {
    "use strict";

    /*
     * Constructor
     *
     * simply sets default values
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
            block: 0,
            join: 0,
            split: 0,
            end: 0
        };
    }

    /*
     * Since HTML is not case sensitive and jQuery does not support
     * namespaces in HTML, we generate HTML output, and then apply
     * namespaces as a hack + we fix the case
     */
    function addNamespaces(str, items) {
        $.each(items, function (i, val) {
            var regex = new RegExp('(<\/?)' + val.split(':')[1], 'gi');
            str = str.replace(regex, '$1' + val);
        });
        return str;
    }

    /*
     * Model class
     */
    Model.prototype = {
        /*
         * Cell is clicked by user
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

        /*
         * Add new node on an offset
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

        /*
         * Find cell by its cellView
         */
        find: function (cellView) {
            for (var i = 0; i < this.nodes.length; ++i) {
                if (this.nodes[i].cell.id == cellView.model.id) {
                    return this.nodes[i];
                }
            }
        },

        /*
         * Links two nodes together
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
                router: { name: 'manhattan', args: {
                    startDirections: [ 'right' ],
                    endDirections: [ 'left' ],
                } },
                connector: { name: 'rounded', args: { radius: 4 } }
            });

            // add to graph
            this.graph.addCell(cell);

            this.save(); // save model
        },

        /*
         * Select node and delegate work to tooltip
         */
        select: function (cellView, edit) {
            this.selected = this.find(cellView);
            this.selected.tooltip(edit);
        },

        /*
         * Remove node
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

        /*
         * Extract resulable variable from node (block)
         */
        extract: function (node) {
            var vars = [];
            if (node.attrs && node.attrs.elements) {
                for (var i = 0; i < node.attrs.elements.length; ++i) {
                    var element = node.attrs.elements[i];
                    var activity = node.attrs.label || '#' + node.cell.cid 
                    if (element.type == 'text') {
                        vars.push({
                            title: activity + '.' + element.name,
                            value: '#' + node.eid + '.r_' + element.eid
                        });
                    }
                }
            }
            return vars;
        },

        /*
         * Extract resusable variables from all nodes behind the current one
         * Does not work for multiple links, because of ambiguities
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

        /*
         * Open edit dialogue
         */
        edit: function (cellView) {
            this.find(cellView).edit(this.storage(cellView));
        },

        /*
         * Enter connection mode
         */
        connect: function (cellView) {
            this.mode = 'connect';
            this.selected = this.find(cellView);
        },

        /*
         * Check if link between two nodes is possible
         * Or if target is not specified, checks if links from this node are
         * possible at all
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

        /*
         * Auto align model
         */
        autoalign: function () {
            console.error("Not Implemented")
        },

        /*
         * Convert from xml
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
                if (name == 'Activity') name = 'Block'; // remap

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
                })
            });
        },

        /*
         * Convert model to XML
         */
        toXml: function () {
            var self = this;
            var G = this.graph;

            // root element
            var $xml = $('<processModel>', {
                name: this.name,
                'xmlns:pe': 'http://adaptivity.nl/ProcessEngine/'
            });

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
            ]).replace('servicens', 'serviceNS')
                .replace('servicename', 'serviceName');
        },

        /*
         * Save model to server
         */
        save: function () {
            if (this.nosave) return; // do not save unless needed
            return;

            var xml = this.toXml();
            return store.updateModel(this.handle, xml).then(function () {
                console.log('saved');
            }).fail(function () {
                alert('Connection Error');
            });
        }
    };

    return Model;
});
