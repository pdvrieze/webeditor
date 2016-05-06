/**
 * Collection of Nodes to build the diagram with
 *
 * @module Model
 */
define(['jquery', 'joint', 'model/dialogue', 'lodash', 'util/util'],
       function ($, joint, Dialogue, _, util) {
    "use strict";

    /**
     * Single element size on the graph
     *
     * @final
     * @type Integer
     */
    var SIZE = 30;

    // half the size
    var HSIZE = SIZE / 2;

    /**
     * Base Node class to be used as a cell descroption
     *
     * @class NodeClass
     * @extends joint.dia.Element
     */
    var NodeClass = joint.dia.Element.extend({
        // node marktip
        markup: '<g transform="translate(-' + HSIZE + ', -' + HSIZE + ')">' +
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

    /**
     * Base Node class that contains base functions
     *
     * @class Base
     * @extends Backbone.Model
     */
    var Base = Backbone.Model.extend({
        /**
         * Node Type name
         *
         * @property type
         * @type String
         * @default null
         */
        type: null,

        /**
         * Node unique ID
         *
         * @property eid
         * @type String
         * @default null
         */
        eid: null, // to be specified

        /**
         * Link limit setup
         *
         * @property linkLimit
         * @type Object { input: Integer, output: Integer }
         * @default { input: 1, output: 1 }
         */
        linkLimit: { input: 1, output: 1 }, // 1 in 1 out default

        /**
         * Set to true if node has edit dialogue
         *
         * @property canEdit
         * @type Boolean
         * @default false
         */
        canEdit: false, // by default node is not to be edited

        /**
         * Creates node and assigns default values
         *
         * @method create
         * @param offset {Object} offset coordinates
         */
        create: function (offset, id) {
            this.eid = id || this.type + random++; // random ID if needed
            this.label = null; // label is missing by default
            this.cell = new NodeClass({
                position: offset, // node position
                attrs: { image: { 'xlink:href': 'svg/' + this.type + '.svg' } }
            });
        },

        /**
         * Sets text in the centre of a node
         *
         * @method setText
         * @param text {String} text to set
         */
        setText: function (text) {
            var id = this.cell.findView(this.paper).id;
            var $node = $('#' + id).find('text');
            $node.get(0).textContent = text;
            this.styleText($node);
        },

        /**
         * Override to style text
         *
         * @method styleText
         */
        styleText: function () {},

        /**
         * Returns cell position for the tooltip hence the name
         *
         * @method tooltip
         * @param callback {Function} callback to pass arguments to
         */
        tooltip: function (callback) {
            callback(this.cell.position().x, this.cell.position().y);
        },

        /**
         * Override in to output advanced XML
         *
         * @method fromXml
         */
        fromXml: function () {}
    });

    /**
     * Start Node
     *
     * @class Start
     * @extends Base
     */
    var Start = Base.extend({
        /** @override */
        type: 'start',

        /** @override */
        linkLimit: { input: 0, output: 1 },

        /** @override */
        toXml: function () {
            return $('<start>', {
                id: this.eid,
                x: this.cell.attributes.position.x,
                y: this.cell.attributes.position.y
            });
        }
    });

    /**
     * Format define to reference other activity or labels in current one
     *
     * @param $define {Object} jQuery define element
     * @param text {String} string to include in define
     */
    function formatDefine($define, text) {
        // uglyass url
        var url = 'http://adaptivity.nl/ProcessEngine/activity';

        // replace local labels
        var replace = '<jbi:value xmlns:jbi="' + url + '" value="d_$1"/>';
        text = text.replace(/%@(\w+)/g, replace);

        // find global values
        var match = text.match(/%#(\w+)\.(\w+)/);
        if (match) {
            var activity = match[1];
            var ref = match[2];

            // make define reference another activity
            $define.attr('xpath', '.');
            $define.attr('refnode', activity);
            $define.attr('refname', 'r_' + ref);

            // replace all occurances
            var replace2 = '<jbi:value xmlns:jbi="' + url + '"/>';
            text = text.replace(new RegExp(match[0], 'g'), replace2);
        }

        // apply
        $define.append(text);
    }

    /**
     * Add result tag
     *
     * @param $cell {Object} jQuery cell to add tag to
     * @param value {Object} element that needs to be added
     */
    function addResult($cell, value) {
        // add result
        var rname = 'r_' + value.eid;
        $('<result>', {
            xpath: '/values/' + value.name + '/text()',
            name: rname
        }).appendTo($cell);
    }

    /**
     * Added define and result items to the xml
     *
     * @param $cell {Object} jQuery activity cell
     * @param $item {Object} jQuery attribute item
     * @param value {Object} element that needs to be added
     * @param name {String} attibute name
     */
    function addItem($cell, $item, value, name) {
        // add define
        var dname = 'd_' + value.eid;
        var $define = $('<define>', { name: dname }).appendTo($cell);
        formatDefine($define, value[name].trim());

        // append everything else as attributes
        $('<attribute>', {
            'xmlns:jbi': 'http://adaptivity.nl/ProcessEngine/activity',
            value: dname,
            name: name
        }).appendTo($item);
    }

    /**
     * Parse define from xml into text
     * 
     * @param define {String} text of define element to parse
     *
     * @return {String}
     */
    function parseDefine(define) {
        var $define = $(define);
        var text = $(define).html();
        text = text.replace(/<jbi:value.*?value="d_(.+?)".*?\/>/gi, '%@$1');

        var refnode = $define.attr('refnode');
        var refname = $define.attr('refname');
        if (refnode && refname) {
            refname = refname.substr(2);
            var replace = '%#' + refnode + '.' + refname;
            text = text.replace(/<jbi:value.*?>/g, replace);
        }

        return text;
    }

    /**
     * Activity Class
     *
     * @class Block
     * @extends Base
     */
    var Block = Base.extend({
        /** @override */
        type: 'block',

        /** @override */
        canEdit: true, // block is editable

        /**
         * Open edit dialogue
         *
         * @param storage {Array} previous dynamic elements
         */
        edit: function (storage) {
            new Dialogue.Block(this, storage);
        },

        /**
         * Initialisation in graph
         */
        init: function () {
            if (this.attrs && this.attrs.label) this.setText(this.attrs.label);
        },

        /** @override */
        fromXml: function ($xml, $model) {
            var attrs = { elements: [] }; // default attributes

            // set label if exists
            if ($xml.attr('label')) attrs.label = $xml.attr('label');

            // defines will be stored here
            var defines = {};
            $model.find(util.xmlSel('pe:define')).each(function () {
                defines[$(this).attr('name')] = parseDefine(this);
            });

            // elements will be stored here
            var elements = [];
            $xml.find(util.xmlSel('umh:item')).each(function () {
                var element = {};

                element.name = $(this).attr('name');
                element.type = $(this).attr('type');

                $(this).find(util.xmlSel('jbi:attribute')).each(function () {
                    var attribute = $(this).attr('name').trim();
                    var value = $(this).attr('value').trim();

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

        /** @override */
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
            if (this.attrs.label) params.label = this.attrs.label;
            if (predecessors.length == 1) {
                params.predecessor = predecessors[0].eid;
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
                }).appendTo($task);

                for (var key in val) {
                    if (!val.hasOwnProperty(key)) continue;
                    if (['name', 'type', 'eid'].indexOf(key) !== -1) continue;
                    addItem($cell, $item, val, key);   
                }

                // add defines and results
                if (val.type != 'label') addResult($cell, val);
            }); 

            return $cell;
        }
    });

    /**
     * Gate Node
     *
     * @class Gate
     * @extends Base
     */
    var Gate = Base.extend({
        /** @override */
        canEdit: true, // editable

        /**
         * Open edit dialogue
         */
        edit: function () {
            new Dialogue.Gate(this);
        },

        /** @override */
        styleText: function ($text) {
            $text.get(0).textContent = $text.get(0).textContent.toUpperCase();
            $text.attr('style', 'font-weight: bold;');
        },

        /**
         * Initialisation in graph
         */
        init: function () {
            if (!this.attrs) this.attrs = { min: 2, max: 2 };

            // and ?
            if (this.attrs.min == 2 && this.attrs.max == 2) {
                this.setText('and');
            }

            // or ?
            else if (this.attrs.min == 1 && this.attrs.max == 2) {
                this.setText('or');
            }

            // xor ?
            else if (this.attrs.min == 1 && this.attrs.max == 1) {
                this.setText('xor');
            }
        },

        /** @override */
        fromXml: function ($xml) {
            this.attrs = { min: -1, max: -1 };
            if ($xml.attr('label')) this.attrs.label = $xml.attr('label');
            if ($xml.attr('min')) this.attrs.min = $xml.attr('min') - 0;
            if ($xml.attr('max')) this.attrs.max = $xml.attr('max') - 0;
        },

        /** @override */
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
                params.predecessor = predecessors[0].eid;
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

    /**
     * Split class
     *
     * @class Split
     * @extends Gate
     */
    var Split = Gate.extend({
        /** @override */
        type: 'split',

        /** @override */
        linkLimit: { input: 1, output: -1 },
    });

    /**
     * Join class
     *
     * @class Join
     * @extends Gate
     */
    var Join = Gate.extend({
        /** @override */
        type: 'join',

        /** @override */
        linkLimit: { input: -1, output: 1 },
    });

    /**
     * End Node
     *
     * @class End
     * @extends Base
     */
    var End = Base.extend({
        /** @override */
        type: 'end',

        /** @override */
        linkLimit: { input: 1, output: 0 },

        /** @override */
        toXml: function (predecessors) {
            var params = {
                id: this.eid,
                x: this.cell.attributes.position.x,
                y: this.cell.attributes.position.y
            };

            // predecessors
            if (predecessors.length) {
                params.predecessor = predecessors[0].eid;
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
