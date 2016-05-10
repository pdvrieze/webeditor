/** 
 * Activity set up, currently only small choice but nicely fits together
 *
 * @module Dialogue
 */
define(['jquery', 'util/simple-template', 'Sortable'],
       function ($, template, Sortable) {
    "use strict";

    /** 
     * Block Dialogue class
     *
     * @class Block
     * @constructor
     * 
     * @param node {Object} calling node
     * @param storage {Array} dynamic variable storage
     */
    function Block(node, storage) {
        this.node = node;
        this.storage = storage;

        // default parameters
        this.attrs = $.extend(true, {}, node.attrs);
        this.attrs.elements = this.attrs.elements || [];

        // find target dialogue
        this.$target = $('#dialogue');

        // set title
        this.$target.find('.modal-title').html('Activity Settings');

        // init
        this.init();
        this.initChange();
        this.initListeners();
        this.initSave();
        this.initCollapse();

        // show modal
        this.$target.modal('show');
    }

    Block.prototype = {
        /** 
         * Render element list
         * @method render
         * 
         * @param $content
         */
        render: function ($content) {
            var views = [];
            $.each(this.attrs.elements, function (i, val) {
                // generate view for each element
                views.push(template.render('activity-row', toView(val, i)));
            });
            $content.empty().append(views);
        },

        /** 
         * Initialise Dialogue
         * @method init
         */
        init: function () {
            var self = this;

            this.$body = this.$target.find('.modal-body');

            // render template
            template.renderTo(this.$body, 'dialogue-activity');
            this.$content = this.$body.find('.list-group');

            // make elements sortable
            Sortable.create(this.$content.get(0), {
                onSort: function () {
                    var list = [];
                    self.$content.find('.collapse').each(function () {
                        var id = $(this).attr('id')
                            .replace(/^activity_item_/, '');
                        list.push(self.attrs.elements[id]);
                    });
                    self.attrs.elements = list;
                    self.render(self.$content);
                }
            });

            // show label
            this.$body.find('#label').val(this.attrs.label || '');

            // render content
            this.render(this.$content);
        },

        /** 
         * Handle activity type change and add element
         * @method initChange
         */
        initChange: function () {
            var self = this;

            // type change
            this.$body.find('#activity_type').on('change', function () {
                var type = $(this).val();

                if (type == 'other') self.$content.hide();
                else self.$content.show();

                self.attrs.type = type;
            }).val(this.attrs.type || 'human').change(); // select curernt

            // add element
            this.$body.find('#add').on('click', function () {
                var random = Math.floor(Math.random() * 64 * 1024);
                self.attrs.elements.push({ type: 'empty', eid: random });
                self.render(self.$content);
                self.$content.find('.collapse').last().collapse('show');
            });
        },

        /** 
         * Init variaous listeners
         * @method initListeners
         */
        initListeners: function () {
            var self = this;

            // listener for collapsing elements
            this.$body.on('hide.bs.collapse', '.collapse', function () {
                var $this = $(this);
                var href = $this.attr('id');
                self.$body.find('a[href="#' + href + '"]')
                    .removeClass('active');
                $this.empty();
            });

            // listener for resuable variables
            this.$body.on('click', 'a.variable-reuse', function (e) {
                e.preventDefault();
                var $this = $(this);
                var $text = $this.parents('.form-group').find('input');
                var text = $text.val().trim() + ' ' + $this.attr('value');
                $text.val(text.trim());
            });

            // listener for collapsing modal
            this.$target.on('hide.bs.modal', function () { self.$body.off(); });
        },

        /** 
         * Init save button behaviour
         * @method initSave
         */
        initSave: function () {
            var self = this;

            this.$target.find('#save').off().on('click', function () {
                self.attrs.label = self.$body.find('#label').val();

                self.node.attrs = self.attrs;
                var ishuman = self.node.attrs.type == 'human';
                if (!ishuman && self.node.attrs.elements) {
                    delete self.node.attrs.elements;
                }

                self.node.setText(self.attrs.label);
                self.node.model.save();

                self.$target.modal('hide');
            });
        },

        /** 
         * Initialise collapse element interaction
         * @method initCollapse
         */
        initCollapse: function () {
            var self = this;

            this.$body.on('show.bs.collapse', '.collapse', function () {
                var $this = $(this);

                // close other elements
                self.$body.find('.collapse.in').collapse('hide');

                // make this element active
                var href = $this.attr('id');
                self.$body.find('a[href="#' + href + '"]').addClass('active');

                // get index name and view, and render
                var index = href.replace(/^activity_item_/, '');
                var view = self.attrs.elements[index];
                var name = 'activity-selector';
                template.renderTo($this, name, view);

                // setup lisetener for type change
                var $type = $this.find('.element-type');
                $type.change(function () {
                    var name = 'activity-element-' + $(this).val();
                    if (name != 'empty') {
                        // as soon as type selected, 'empty' is disabled
                        $this.find('option[value=empty]')
                            .prop('disabled', true);
                    }

                    // add storage
                    var $content = $this.find('.element-content');
                    var newview = $.extend(true, {}, view);
                    newview.stor = getStorage(
                        self.storage, self.attrs.elements
                    );

                    // render
                    template.renderTo($content, name, newview);

                    // initialise storage vars
                    $content.find('.dropdown-button').each(function (i) {
                        $(this).attr('id', 'menu_' + i);
                        $(this).next().attr('aria-labelledby', 'menu_' + i);
                    });
                });

                // auto change type if possible
                if (view && view.type != 'empty') {
                    $this.find('.element-type').val(view.type).change();
                }

                // listener for discard
                $this.find('#element_discard').click(function () {
                    $this.collapse('hide');
                });

                // listener for remove
                $this.find('#element_remove').click(function () {
                    self.attrs.elements.splice(index, 1);
                    self.render(self.$content);
                });

                // listener for save
                $this.find('#element_save').click(function () {
                    var element = {
                        type: $type.val(),
                        eid: self.attrs.elements[index].eid // save eid
                    };

                    if (element.type == 'empty') return; // can't save

                    // put attributes from html to attrs
                    $this.find('.element-content').find('input[name]')
                        .each(function () {
                            var $this = $(this);
                            element[$this.attr('name')] = $this.val();
                        });

                    // apply and rerender
                    self.attrs.elements[index] = element;
                    self.render(self.$content);
                });
            });

        }
    };

    /** 
     * Generates <li> element for the list of reusable variables
     * 
     * @param value {String} underlying value
     * @param name {String} value to print
     * 
     * @return {Object} jQuery <li> element for the list
     */
    function makeLi(value, name) {
        var $li = $('<li>');
        $('<a>', {
            'class': 'variable-reuse',
            'value': '%' + value,
            'href': '#'
        }).append(name).appendTo($li);
        return $li;
    }

    /** 
     * Generate storage into a list of <li> elements for the template
     *
     * @param storage {Array} array of previous dynamic values
     * @param elements {Array} arry of current labels in this
     * 
     * @return {String} HTML of set of <li> elements for the list
     */
    function getStorage(storage, elements) {
        // this is just a wrapper to get html
        var $ul = $('<ul>');

        // first lets add current block elements
        var added = false;
        $.each(elements, function (i, val) {
            if (val.value) {
                added = true;
                $ul.append(
                    makeLi('@' + val.eid, 'this.' + val.name)
                );
            }
        });

        // if both groups exist we need to separate
        if (added && storage.length) {
            $ul.append('<li role="separator" class="divider">');
        }

        // now we add elemetns from activities before
        $.each(storage, function (i, val) {
            $ul.append(makeLi(val.value, val.title));
        });

        // return as a string for the template
        return $ul.html();
    }

    /** 
     * Convert element to template view
     * 
     * @param element {Object} element to be converted
     * @param index {Integer} elements position
     * 
     * @return {Object} generated view
     */
    function toView(element, index) {
        var title = element.type;

        var description = {};
        if (element.text) description.Name = element.name;
        if (element.hasOwnProperty('text')) {
            description.Text = element.text;
        }
        if (element.hasOwnProperty('value')) {
            description.Default = element.value;
        }

        var descarray = [];
        for (var key in description) {
            if (!description.hasOwnProperty(key)) continue;
            descarray.push(key + ": '" + description[key] + "'");
        }

        return {
            index: index,
            title: title,
            description: descarray.join(', ') || ''
        };
    }

    // export
    return Block;
});
