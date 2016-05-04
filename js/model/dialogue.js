/*
 * Concatenates diaogues into one object for simplicity
 */
define(['./dialogue/gate', './dialogue/block'], function (Gate, Block) {
    "use strict";

    return { Gate: Gate, Block: Block };
});
