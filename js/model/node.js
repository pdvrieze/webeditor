/*
 * Collection of Nodes to build the diagram with
 */
define(['jquery', 'joint', 'model/dialogue', 'lodash', 'util/util'],
       function ($, joint, Dialogue, _, util) {
    "use strict";

    // Node Size
    var SIZE = 30;

    /*
     * Base Node class to be used as a cell descroption
     */
    var NodeClass = joint.dia.Element.extend({
        // node marktip
        markup: '<g class="rotatable">' +
                    '<image width="' + SIZE + '" height="' + SIZE + '" ' +
                           'xlink:href="svg/end.svg"/>' +
                    '<text x="15" y="17" font-family="sans-serif" ' +
                          'font-size="7" text-anchor="middle"></text>' +
                '</g>',

        // node defaults
        defaults: joint.util.deepSupplement({
            type: 'Node'
        }, joint.dia.Element.prototype.defaults)
    });

    // a very random number to be used in node naming
    var random = 1;

    /*
     * Base Node class that contains base functions
     */
    var Base = Backbone.Model.extend({
        type: null, // to be specified
        eid: null, // to be specified
        linkLimit: { input: 1, output: 1 }, // 1 in 1 out default
        canEdit: false, // by default node is not to be edited

        /*
         * Creates node and assigns default values
         */
        create: function (offset, id) {
            this.eid = id || this.type + random++; // random ID if needed
            this.label = null; // label is missing by default
            this.cell = new NodeClass({
                position: offset, // node position
                attrs: { image: { 'xlink:href': 'svg/' + this.type + '.svg' } }
            });
        },

        /*
         * Sets text in the centre of a node
         */
        setText: function (text) {
            var id = this.cell.findView(this.paper).id;
            $('#' + id).find('text').get(0).textContent = text;
        },

        /*
         * Returns cell position for the tooltip hence the name
         */
        tooltip: function (callback) {
            callback(this.cell.position().x, this.cell.position().y);
        },

        // empty for start and end nodes
        fromXml: function () {}
    });

    /*
     * Start Node
     */
    var Start = Base.extend({
        type: 'start',
        linkLimit: { input: 0, output: 1 },
        toXml: function () {
            return $('<start>', {
                id: this.eid,
                x: this.cell.attributes.position.x,
                y: this.cell.attributes.position.y
            });
        }
    });

    /*
     * Added define and result items to the xml
     */
    function addItem($cell, $item, value, name, result) {
        if (result) {
            // add result
            var rname = 'r_' + value.eid;
            $('<result>', {
                xpath: '/values/' + value.eid + '/text()',
                name: rname
            }).appendTo($cell);
        }

        // add define
        var dname = 'd_' + value.eid;
        $('<define>', { name: dname }).append(value[name]).appendTo($cell);

        // append everything else as attributes
        $('<attribute>', {
            'xmlns:jbi': 'http://adaptivity.nl/ProcessEngine/activity',
            value: dname,
            name: name
        }).appendTo($item);
    }

    /*
     * Block
     */
    var Block = Base.extend({
        type: 'block',
        canEdit: true, // block is editable

        // create block dialogue on edit
        edit: function (storage) {
            new Dialogue.Block(this, storage);
        },

        // when initialised, set title
        init: function () {
            if (this.attrs && this.attrs.label) this.setText(this.attrs.label);
        },

        // load from xml
        fromXml: function ($xml, $model) {
            var attrs = { elements: [] }; // default attributes

            // set label if exists
            if ($xml.attr('label')) attrs.label = $xml.attr('label');

            // defines will be stored here
            var defines = {};
            $model.find(util.xmlSel('pe:define')).each(function () {
                defines[$(this).attr('name')] = this.textContent;
            });

            // elements will be stored here
            var elements = [];
            $xml.find(util.xmlSel('umh:item')).each(function () {
                var element = {};

                element.name = $(this).attr('name');
                element.type = $(this).attr('type');

                $(this).find(util.xmlSel('jbi:attribute')).each(function () {
                    var attribute = $(this).attr('name');
                    var value = $(this).attr('value');

                    // substitute with defines
                    if (defines[value]) {
                        element.eid = value.replace(/^d_/, '');
                        value = defines[value];
                    }

                    element[attribute] = value;
                });

                // add to the list
                elements.push(element);
            });

            // apply
            attrs.elements = elements;
            this.attrs = attrs;
        },

        // convert to xml
        toXml: function (predecessors) {
            var params = {
                id: this.eid,
                x: this.cell.attributes.position.x,
                y: this.cell.attributes.position.y,
            };

            // set default attributes
            if (!this.attrs) this.attrs = {};
            if (!this.attrs.elements) this.attrs.elements = [];

            // set label and predecessor
            if (this.attrs.label) params['label'] = this.attrs.label;
            if (predecessors.length == 1) {
                params['predecessor'] = predecessors[0].eid;
            }

            // big nothing start --------------------
            var $cell = $('<activity>', params);

            if (!this.attrs.elements.length) return $cell;

            var $message = $('<message>', {
                type: 'application/soap+xml',
                serviceNS: 'http://adaptivity.nl/userMessageHandler',
                serviceName: 'userMessageHandler',
                endpoint: 'internal',
                operation: 'postTask'
            }).appendTo($cell);
            
            var $envelope = $('<Envelope>', {
                'xmlns:env': 'http://www.w3.org/2003/05/soap-envelope',
                encodingStyle: 'http://www.w3.org/2003/05/soap-encoding'
            }).appendTo($message);

            var $body = $('<Body>').appendTo($envelope);
            var $postTask = $('<postTask>', {
                'xmlns:umh': 'http://adaptivity.nl/userMessageHandler'
            }).appendTo($body);

            var $repliesParam = $('<repliesParam>').appendTo($postTask);
            $('<element>', {
                'xmlns:jbi': 'http://adaptivity.nl/ProcessEngine/activity',
                value: 'endpoint'
            }).appendTo($repliesParam);

            var $taskParam = $('<taskParam>').appendTo($postTask);
            var $task = $('<task>').appendTo($taskParam);
            $('<attribute>', {
                'xmlns:jbi': 'http://adaptivity.nl/ProcessEngine/activity',
                value: 'instancehandle',
                name: 'instancehandle'
            }).appendTo($task);
            $('<attribute>', {
                'xmlns:jbi': 'http://adaptivity.nl/ProcessEngine/activity',
                value: 'handle',
                name: 'remotehandle'
            }).appendTo($task);
            $('<attribute>', {
                'xmlns:jbi': 'http://adaptivity.nl/ProcessEngine/activity',
                value: 'owner',
                name: 'owner'
            }).appendTo($task);
            // big nothing end --------------------------

            // add elements
            $.each(this.attrs.elements, function (i, val) {
                var $item = $('<item>', {
                    name: val.name,
                    type: val.type
                }).appendTo($task)

                // add defines and results
                if (val.label) addItem($cell, $item, val, 'label');   
                if (val.value) addItem($cell, $item, val, 'value', true);   
            }); 

            return $cell;
        }
    });

    /*
     * Gate Node
     */
    var Gate = Base.extend({
        canEdit: true, // editable

        // open gate dialogue
        edit: function () {
            new Dialogue.Gate(this);
        },

        // when initialised set the label
        init: function () {
            if (!this.attrs) this.attrs = { min: 0, max: -1 };

            // and ?
            if (this.attrs.min == 0 && this.attrs.max == -1) {
                this.setText('and');
            }

            // or ?
            else if (this.attrs.min == 1 && this.attrs.max == -1) {
                this.setText('or');
            }

            // xor ?
            else if (this.attrs.min == 1 && this.attrs.max == 1) {
                this.setText('xor');
            }
        },

        // init from xml
        fromXml: function ($xml) {
            this.attrs = { min: -1, max: -1 };
            if ($xml.attr('label')) this.attrs.label = $xml.attr('label');
            if ($xml.attr('min')) this.attrs.min = $xml.attr('min') - 0;
            if ($xml.attr('max')) this.attrs.max = $xml.attr('max') - 0;
        },

        // convert to xml
        toXml: function (predecessors) {
            var params = {
                id: this.eid,
                x: this.cell.attributes.position.x,
                y: this.cell.attributes.position.y
            };

            // default attributes
            if (!this.attrs) this.attrs = {};
            if (this.attrs.min >= 0) params.min = this.attrs.min;
            if (this.attrs.max >= 0) params.max = this.attrs.max;

            // predecessors
            if (this.type == 'split' && predecessors.length) {
                params['predecessor'] = predecessors[0].eid;
            }

            // create cell
            var $cell = $('<' + this.type + '>', params);

            // join special predecessors
            if (this.type == 'join' && predecessors.length) {
                $.each(predecessors, function (i, val) {
                    $('<predecessor>' + val.eid + '</predecessor>')
                        .appendTo($cell);
                });
            }

            return $cell;
        },
    });

    // split gate specialisation
    var Split = Gate.extend({
        type: 'split',
        linkLimit: { input: 1, output: -1 },
    });

    // join gate specialisation
    var Join = Gate.extend({
        type: 'join',
        linkLimit: { input: -1, output: 1 },
    });

    /*
     * End Node
     */
    var End = Base.extend({
        type: 'end',
        linkLimit: { input: 1, output: 0 },

        // convert to xml
        toXml: function (predecessors) {
            var params = {
                id: this.eid,
                x: this.cell.attributes.position.x,
                y: this.cell.attributes.position.y
            };

            // predecessors
            if (predecessors.length) {
                params['predecessor'] = predecessors[0].eid;
            }

            return $('<end>', params);
        }
    });

    // export
    return {
        Start: Start,
        Block: Block,
        Join: Join,
        Split: Split,
        End: End,
        SIZE: SIZE
    };
});
