/*
 * Concatenates diaogues into one object for simplicity
 */
define(['./dialogue/gate', './dialogue/block'], function (Gate, Block) {
    return { Gate: Gate, Block: Block };
});
