define(['jquery', 'util/simple-template', 'Sortable'],
       function ($, template, Sortable) {
    var dropdown_index = 1;

    function makeLi(value, name, prefix) {
        if (!prefix) prefix = '';

        var $li = $('<li>');
        var $a = $('<a>', {
            'href': '#',
            'class': 'variable-reuse',
            'value': value
        }).append(prefix + name).appendTo($li);

        return $li;
    }

    // http://stackoverflow.com/a/15710692
    function hashCode(s) {
        return s.split("")
            .reduce(function(a,b){a=((a<<5)-a)+b.charCodeAt(0);return a&a},0);              
    }

    function addStorage(view, storage, elements, name, self) {
        var $div = $('<div class="dropdown pull-right">');

        var $button = $('<button>', {
            'class': 'btn btn-default dropdown-toggle',
            'type': 'button',
            'id': 'vars_dropdown_' + dropdown_index,
            'data-toggle': 'dropdown',
            'aria-haspopup': 'true',
            'aria-expanded': 'true'
        }).append('Variables ').append('<span class="caret">').appendTo($div);

        var $ul = $('<ul>', {
            'class': 'dropdown-menu',
            'aria-labelledby': 'var_dropdown_' + dropdown_index
        }).appendTo($div);

        var added = false;
        $.each(elements, function (i, val) {
            if (val.type == 'text') {
                added = true;
                $ul.append(
                    makeLi('#' + self.cell.cid + '.r_' + hashCode(val.value),
                           val.name, 'this.'));
            }
        });

        if (added) {
            $ul.append('<li role="separator" class="divider">');
        }

        $.each(storage, function (i, val) {
            $ul.append(makeLi(val.value, val.title));
        });

        view[name] = $div.get(0).outerHTML;
        dropdown_index++;

        return view;
    }

    function Block(self, storage, callback) {
        this.self = self;
        var attrs = $.extend(true, {}, self.attrs);
        var $ptr = $('#dialogue');

        $ptr.find('.modal-title').html('Activity Settings');

        attrs.elements = attrs.elements || [];
        
        function toview(element, index) {
            var title = element.type;

            var description = {};
            if (element.text) description['Name'] = element.name;
            if (element.hasOwnProperty('text')) {
                description['Text'] = element.text;
            }
            if (element.hasOwnProperty('value')) {
                description['Default'] = element.value;
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

        function render($content) {
            var views = [];
            $.each(attrs.elements, function (i, val) {
                views.push(toview(val, i));
            });
            return template.render($content, 'activity-row', views);
        }

        var $body = $ptr.find('.modal-body');
        template.render($body, 'dialogue-activity').done(function () {
            var $content = $body.find('.list-group');
            var sortable = Sortable.create($content.get(0), {
                onSort: function () {
                    var list = [];
                    $content.find('.collapse').each(function () {
                        var id = $(this).attr('id')
                            .replace(/^activity_item_/, '');
                        list.push(attrs.elements[id]);
                    });
                    attrs.elements = list;
                    render($content);
                }
            });

            $body.find('#label').val(attrs.label || '');

            $body.find('#activity_type').on('change', function () {
                var type = $(this).val();
                if (type == 'other') $content.hide();
                else $content.show();
                attrs.type = type;
            }).val(attrs.type || 'human').change();

            render($content);
            $body.find('#add').on('click', function () {
                attrs.elements.push({ type: 'empty' });
                render($content).done(function () {
                    $content.find('.collapse').last().collapse('show');
                });
            });

            $body.on('show.bs.collapse', '.collapse', function () {
                var $this = $(this);

                $body.find('.collapse.in').collapse('hide');

                var href = $this.attr('id');
                $body.find('a[href="#' + href + '"]').addClass('active');

                var index = href.replace(/^activity_item_/, '');
                var view = attrs.elements[index];
                var name = 'activity-selector';
                template.render($this, name, [view]).done(function () {
                    var $type = $this.find('.element-type');
                    $type.change(function () {
                        var name = 'activity-element-' + $(this).val();
                        if (name != 'empty') {
                            $this.find('option[value=empty]')
                                .prop('disabled', true);
                        }
                        var $content = $this.find('.element-content');
                        var newview = $.extend(true, {}, view);
                        addStorage(newview, storage, attrs.elements, 'stor1',
                                   self);
                        addStorage(newview, storage, attrs.elements, 'stor2',
                                   self);
                        template.render($content, name, [newview]);
                    });
                    if (view.type != 'empty') {
                        $this.find('.element-type').val(view.type).change();
                    }
                    $this.find('#element_discard').click(function () {
                        $this.collapse('hide');
                    });
                    $this.find('#element_remove').click(function () {
                        attrs.elements.splice(index, 1);
                        render($content);
                    });
                    $this.find('#element_save').click(function () {
                        var element = { type: $type.val() };
                        if (element.type == 'empty') return;
                        $this.find('.element-content').find('input[name]')
                            .each(function () {
                                var $this = $(this);
                                element[$this.attr('name')] = $this.val();
                            });
                        attrs.elements[index] = element;
                        render($content);
                    });
                });
            });
            $body.on('hide.bs.collapse', '.collapse', function () {
                var $this = $(this);
                var href = $this.attr('id');
                $body.find('a[href="#' + href + '"]').removeClass('active');
                $this.empty();
            });

            $body.on('click', 'a.variable-reuse', function () {
                var $this = $(this);
                var $text = $this.parents('.form-group').find('input');
                var text = $text.val().trim() + ' ' + $this.attr('value');
                $text.val(text.trim());
            });

            $ptr.find('#save').off().on('click', function () {
                attrs.label = $body.find('#label').val();
                callback(attrs.label);

                self.attrs = attrs;
                if (self.attrs.type != 'human' && self.attrs.elements) {
                    delete self.attrs.elements;
                }
                $('#content').trigger('save-model');
                $ptr.modal('hide');
            });

            $ptr.on('hide.bs.modal', function () {
                $body.off(); 
            });

            $ptr.modal('show');
        });
    }

    return Block;
});
