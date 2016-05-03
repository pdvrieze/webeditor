define(['jquery', 'joint', 'model/dialogue', 'lodash'],
       function ($, joint, Dialogue, _) {
    "use strict";

    var SIZE = 30;

    var NodeClass = joint.dia.Element.extend({
        markup: '<g class="rotatable">' +
                    '<image width="' + SIZE + '" height="' + SIZE + '" ' +
                           'xlink:href="svg/end.svg"/>' +
                    '<text x="15" y="17" font-family="sans-serif" ' +
                          'font-size="7" text-anchor="middle"></text>' +
                '</g>',

        defaults: joint.util.deepSupplement({
            type: 'Node'
        }, joint.dia.Element.prototype.defaults)
    });

    var Base = Backbone.Model.extend({
        type: null,
        linkLimit: { input: 1, output: 1 },
        canEdit: false,

        create: function (offset) {
            this.label = null;
            this.cell = new NodeClass({
                position: offset,
                attrs: { image: { 'xlink:href': 'svg/' + this.type + '.svg' } }
            });
        },

        tooltip: function (callback) {
            callback(this.cell.position().x, this.cell.position().y);
        },

        fromXml: function ($xml) {}
    });

    /*
     * Start Node
     */

    var Start = Base.extend({
        type: 'start',
        linkLimit: { input: 0, output: 1 },
        toXml: function () {
            return $('<start>', {
                id: this.cid,
                x: this.cell.attributes.position.x,
                y: this.cell.attributes.position.y
            })
        }
    });

    /*
     * Block
     */

    var Block = Base.extend({
        type: 'block',
        canEdit: true,

        edit: function (storage) {
            var self = this;
            new Dialogue.Block(this, storage, function (label) {
                self.setText(label);
            });
        },

        setText: function (text) {
            var id = this.cell.findView(this.paper).id;
            $('#' + id).find('text').get(0).textContent = text;
        },

        fromXml: function ($xml) {
            var attrs = { elements: [] };
            if ($xml.attr('label')) attrs.label = $xml.attr('label');

            var defines = {};

            $xml.find('pe\\:define,define').each(function () {
                var name = $(this).attr('name');
                defines[name] = this.textContent;
            });

            var elements = [];
            $xml.find('umh\\:item,item').each(function () {
                var element = {};

                var label = $(this).attr('name');
                var type = $(this).attr('type');

                element.label = label;
                element.type = type;
                $(this).find('jbi\\:attribute,attribute').each(function () {
                    var attribute = $(this).attr('name');
                    var value = $(this).attr('value');

                    if (defines[value]) value = defines[value];

                    element[attribute] = value;
                });

                elements.push(element);
            });
            attrs.elements = elements;

            this.attrs = attrs;
        }
    });

    /*
     * Gate
     */

    var Gate = Base.extend({
        canEdit: true,

        edit: function () {
            new Dialogue.Gate(this);
        },

        fromXml: function ($xml) {
            this.attrs = { min: -1, max: -1 };
            if ($xml.attr('label')) this.attrs.label = $xml.attr('label');
            if ($xml.attr('min')) this.attrs.min = $xml.attr('min') - 0;
            if ($xml.attr('max')) this.attrs.max = $xml.attr('max') - 0;
        }
    });

    var Split = Gate.extend({
        type: 'split',
        linkLimit: { input: 1, output: -1 },
    });

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
        toXml: function () {
            return $('<end>', {
                id: this.cid,
                x: this.cell.attributes.position.x,
                y: this.cell.attributes.position.y
            })
        }
    });

    /*
     * Export
     */

    return {
        Start: Start,
        Block: Block,
        Join: Join,
        Split: Split,
        End: End,
        SIZE: SIZE
    };
});
