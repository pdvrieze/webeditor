/**
 * Concatenates diaogues into one object for simplicity
 *
 * @module Model
 */
define(['./dialogue/gate', './dialogue/activity', './dialogue/xml'],
       function (Gate, Activity, Xml) {
    "use strict";

    // export
    return { Gate: Gate, Activity: Activity, Xml: Xml };
});
