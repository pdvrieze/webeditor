define(['jquery', 'joint', 'lodash', './node'], function ($, joint, _, Nodes) {
    "use strict";

    function Model(graph, paper, name) {
        this.graph = graph;
        this.nodes = [];
        this.selected = null;
        this.mode = null;
        this.paper = paper;
        this.name = name || '';

        this.count = {
            start: 0,
            block: 0,
            join: 0,
            split: 0,
            end: 0
        };
    }

    function addNamespaces(str, items) {
        $.each(items, function (i, val) {
            var regex = new RegExp('(<\/?)' + val.split(':')[1], 'gi');
            str = str.replace(regex, '$1' + val);
        });
        return str;
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

        add: function (node, offset, id) {
            if (!node.cell) node.create(offset, id);
            node.paper = this.paper;
            this.graph.addCell(node.cell);
            this.nodes.push(node);
            this.count[node.type]++;
            if (node.init) node.init();
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
                router: { name: 'manhattan', args: {
                    startDirections: [ 'right' ],
                    endDirections: [ 'left' ],
                } },
                connector: { name: 'rounded', args: { radius: 4 } }
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

        autoalign: function () {
            console.log('sure');
        },

        fromXml: function ($xml) {
            var self = this;
            var nodes = {};
            var $model = $xml.find('pe\\:processModel,processModel');
            this.name = $model.attr('name');
            $model.children()
                .each(function () {
                    var name = _.capitalize(this.tagName.replace(/^pe:/, ''));
                    if (name == 'Activity') name = 'Block';

                    var offset = {
                        x: $(this).attr('x') - 0,
                        y: $(this).attr('y') - 0,
                    };

                    var id = $(this).attr('id');
                    var node = new Nodes[name]();
                    node.fromXml($(this), $model);
                    nodes[id] = node;
                    self.add(node, offset, $(this).attr('id'));
                    if (node.init) node.init();
                })
                .each(function () {
                    var id = $(this).attr('id');
                    var predecessor = $(this).attr('predecessor');
                    if (predecessor) self.link(nodes[predecessor], nodes[id]);
                    $(this).find('pe\\:predecessor,predecessor')
                        .each(function () {
                            self.link(nodes[this.textContent], nodes[id]);
                        })
                });
        },

        toXml: function () {
            var self = this;
            var G = this.graph;
            var $xml = $('<processModel>', {
                name: this.name,
                'xmlns:pe': 'http://adaptivity.nl/ProcessEngine/'
            });
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
            return addNamespaces($('<div>').append($xml).html(), [
                'pe:processModel', 'pe:start', 'pe:end', 'pe:join', 'pe:split',
                'pe:activity', 'pe:predecessor', 'pe:define', 'pe:result',
                'pe:message', 'jbi:attribute', 'umh:task', 'umh:taskParam',
                'umh:repliesParam', 'env:Body', 'env:Envelope', 'umh:postTask',
                'jbi:element', 'umh:item'
            ]).replace('servicens', 'serviceNS')
                .replace('servicename', 'serviceName');
        }
    };

    return Model;
});
