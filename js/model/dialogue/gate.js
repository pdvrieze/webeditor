/*
 * Gate Dialogue
 *
 * Shows the gate dialogue and allows the interactions with it
 */
define(['jquery', 'util/simple-template'], function ($, template) {
    "use strict";

    function Gate(node) {
        this.node = node; // save the node

        // initialise the attributes with default values if needed
        this.attrs = node.attrs || { min: 0, max: -1 };
        
        // find dialogue output
        this.$target = $('#dialogue');

        // set title
        this.$target.find('.modal-title').html('Gate Settings');

        // render content
        this.$body = this.$target.find('.modal-body');
        template.renderTo(this.$body, 'dialogue-gate');

        // initialise values
        this.init();
        this.initChange();
        this.initSave();
        
        // show dialogue
        this.$target.modal('show');
    }

    Gate.prototype = {
        /*
         * Initialise elements
         */
        init: function () {
            // find all controls
            this.$in = this.$body.find('#gate_in');
            this.$out = this.$body.find('#gate_out');
            this.$controls = this.$body.find('#gate_controls');

            // initialise label
            if (this.attrs.label) {
                this.$body.find('#label').val(this.attrs.label);   
            }

            // initialise gates
            this.$in.val(this.attrs.min);
            this.$out.val(this.attrs.max);
        },

        /*
         * Initialise change listener for buttons
         */
        initChange: function () {
            var self = this;
            // selector for current choice
            var selector = '[min=' + this.attrs.min + ']' +
                           '[max=' + this.attrs.max + ']';
            this.$body.find('button').on('click', function () {
                var $this = $(this);

                // select this button, deselecting others
                self.$body.find('button').removeClass('active');
                $this.addClass('active');

                // if button has hardcoded values save them to attributes
                // and propogate to inputs
                if ($this.attr('min')) {
                    self.attrs.min = $this.attr('min');
                    self.attrs.max = $this.attr('max');
                    self.$in.val(self.attrs.min);
                    self.$out.val(self.attrs.max);
                }

                // collapse or unhide the controls if other button is selected
                if ($this.val() != 'other') self.$controls.hide();
                else self.$controls.show();
            }).filter(selector).click(); // click on current selection
        },

        /*
         * Setup listeners for saving this modal
         */
        initSave: function () {
            var self = this;

            self.$target.find('#save').off().on('click', function () {
                if (self.$body.find('#label').val()) {
                    // if label is set add it to attributes
                    self.attrs.label = self.$body.find('#label').val();
                }

                // find what button is selected
                var text = self.$body.find('.active').val();
                if (self.$body.find('[value=other].active').size()) {
                    // manually apply attributes when other is selected
                    self.attrs.min = self.$in.val(); 
                    self.attrs.max = self.$out.val(); 
                    text = '';
                }

                // apply attriutes on node
                self.node.attrs = self.attrs;
                self.node.setText(text);
                self.node.model.save();

                // hide modal
                self.$target.modal('hide');
            });
        }
    }

    return Gate;
});
