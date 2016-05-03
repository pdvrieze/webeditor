define(['jquery', 'joint', 'model/dialogue'], function ($, joint, Dialogue) {
    "use strict";

    var SIZE = 30;

    var NodeClass = joint.dia.Element.extend({
        markup: '<g class="rotatable"><g class="scalable">' +
                    '<image width="' + SIZE + '" height="' + SIZE + '" ' +
                           'xlink:href="svg/end.svg"/>' +
                    '<text x="30" y="34" font-family="sans-serif" ' +
                          'font-size="14" text-anchor="middle"></text>' +
                '</g></g>',

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
                //size: { width: SIZE, height: SIZE },
                attrs: { image: { 'xlink:href': 'svg/' + this.type + '.svg' } }
            });
        },

        tooltip: function (callback) {
            callback(this.cell.position().x, this.cell.position().y);
        }
    });

    /*
     * Start Node
     */

    var Start = Base.extend({
        type: 'start',
        linkLimit: { input: 0, output: 1 },
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
        }
    });

    /*
     * Gate
     */

    var Gate = Base.extend({
        canEdit: true,

        edit: function () {
            new Dialogue.Gate(this);
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
        linkLimit: { input: 1, output: 0 }
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
