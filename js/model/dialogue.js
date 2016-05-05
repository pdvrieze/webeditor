/**
 * Concatenates diaogues into one object for simplicity
 *
 * @module Model
 */
define(['./dialogue/gate', './dialogue/block', './dialogue/xml'],
       function (Gate, Block, Xml) {
    "use strict";

    // export
    return { Gate: Gate, Block: Block, Xml: Xml };
});
