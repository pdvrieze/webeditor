/*
 * Concatenates diaogues into one object for simplicity
 */
define(['./dialogue/gate', './dialogue/block', './dialogue/xml'],
       function (Gate, Block, Xml) {
    "use strict";

    return { Gate: Gate, Block: Block, Xml: Xml };
});
