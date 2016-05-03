define(['jquery', 'joint'], function ($, joint) {
    "use strict";

    function Model(graph, paper) {
        this.graph = graph;
        this.nodes = [];
        this.selected = null;
        this.mode = null;
        this.paper = paper;

        this.count = {
            start: 0,
            block: 0,
            join: 0,
            split: 0,
            end: 0
        };
    }

    Model.prototype = {
        click: function (cellView, edit) {
            var node = this.find(cellView)
            if (!node) return;
            if (this.mode == 'connect' && this.selected != node) {
                this.link(this.selected, node);
                this.mode = null;
            }
            else this.select(cellView, edit);
        },

        add: function (node, offset) {
            if (!node.cell) node.create(offset);
            node.paper = this.paper;
            this.graph.addCell(node.cell);
            this.nodes.push(node);
            this.count[node.type]++;
        },

        find: function (cellView) {
            for (var i = 0; i < this.nodes.length; ++i) {
                if (this.nodes[i].cell.id == cellView.model.id) {
                    return this.nodes[i];
                }
            }
        },

        link: function (source, target) {
            if (!this.linkPossible(source, target)) {
                console.log('Impossible to connect');
                return;
            }

            var cell = new joint.shapes.fsa.Arrow({
                source: { id: source.cell.id },
                target: { id: target.cell.id },
                router: { name: 'metro' },
                connector: { name: 'rounded' }
            });

            this.graph.addCell(cell);
        },

        select: function (cellView, edit) {
            this.selected = this.find(cellView);
            this.selected.tooltip(edit);
        },

        remove: function (cellView) {
            var node = this.find(cellView);
            var index = this.nodes.indexOf(node);
            node.cell.remove();
            this.count[node.type]--;
            this.nodes.splice(index, 1);
        },

        extract: function (node) {
            var vars = [];
            if (node.attrs && node.attrs.elements) {
                for (var i = 0; i < node.attrs.elements.length; ++i) {
                    var element = node.attrs.elements[i];
                    var activity = node.attrs.label || '#' + node.cell.cid 
                    if (element.type == 'input') {
                        vars.push(activity + '.' + element.label);
                    }
                }
            }
            return vars;
        },

        storage: function (cellView) {
            var cell = this.find(cellView).cell;
            var storage = [];

            var G = this.graph;
            var links = G.getConnectedLinks(cell, {inbound: true});
            while (links.length == 1) {
                cell = this.find({model: {id: links[0].attributes.source.id}});
                storage = storage.concat(this.extract(cell));
                links = G.getConnectedLinks(cell, {inbound: true});
            }

            return storage;
        },

        edit: function (cellView) {
            this.find(cellView).edit(this.storage(cellView));
        },

        connect: function (cellView) {
            this.mode = 'connect';
            this.selected = this.find(cellView);
        },

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

        fromXml: function ($xml) {
            console.log($xml);
        }
    };

    return Model;
});
