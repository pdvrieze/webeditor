/**
 * Shows the xml dialogue and allows the interactions with it
 *
 * @module Dialogue
 */
define(['jquery', 'webeditor'],
       function ($, webeditor) {
    "use strict";

    var template = webeditor.simpleTemplate;
    var util = webeditor.util.util;
    var store = webeditor.store.model;

    /** 
     * Xml Dialogue class
     *
     * @class Xml
     * @constructor
     * 
     * @param model {Object} calling model
     */
    function Xml(model) {
        this.model = model; // save model

        // find dialogue output
        this.$target = $('#dialogue');

        // set title
        this.$target.find('.modal-title').html('Edit XML');

        this.$body = this.$target.find('.modal-body');
        template.renderTo(this.$body, 'dialogue-xml');

        var self = this;

        // save xml
        this.$target.find('#save').off().click(function () {
            store.updateModel(
                self.model.handle,
                self.$body.find('textarea').val()
            ).then(function () {
                self.$target.modal('hide');
                self.model.reload();
            }).fail(function (e) {
                alert('Could not save XML');
                console.error('XML Saving Failed', e);
            });
        });

        // populate xml
        this.$body.find('textarea').val(util.formatXml(this.model.toXml()));
        
        // show dialogue
        this.$target.modal('show');
    }

    // export
    return Xml;
});
