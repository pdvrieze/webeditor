define(['jquery', 'util/simple-template', 'Sortable'],
       function ($, template, Sortable) {
    /*
     * Generates li for the resuable variable list
     */
    function makeLi(value, name) {
        var $li = $('<li>');
        var $a = $('<a>', {
            'class': 'variable-reuse',
            'value': value,
            'href': '#'
        }).append(name).appendTo($li);
        return $li;
    }

    function getStorage(storage, elements, self) {
        var $ul = $('<ul>');

        var added = false;
        $.each(elements, function (i, val) {
            if (val.type == 'text') {
                added = true;
                $ul.append(
                    makeLi('#' + self.eid + '.r_' + val.eid, 'this.' + val.name)
                );
            }
        });

        if (added && storage.length) {
            $ul.append('<li role="separator" class="divider">');
        }

        $.each(storage, function (i, val) {
            $ul.append(makeLi(val.value, val.title));
        });

        console.log($ul.html());
        return $ul.html();
    }

    function Block(self, storage) {
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
                views.push(template.render('activity-row', toview(val, i)));
            });
            $content.empty().append(views);
        }

        var $body = $ptr.find('.modal-body');
        template.renderTo($body, 'dialogue-activity');
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
            var random = Math.floor(Math.random() * 64 * 1024);
            attrs.elements.push({ type: 'empty', eid: random });
            render($content)
            $content.find('.collapse').last().collapse('show');
        });

        $body.on('show.bs.collapse', '.collapse', function () {
            var $this = $(this);

            $body.find('.collapse.in').collapse('hide');

            var href = $this.attr('id');
            $body.find('a[href="#' + href + '"]').addClass('active');

            var index = href.replace(/^activity_item_/, '');
            var view = attrs.elements[index];
            var name = 'activity-selector';
            template.renderTo($this, name, view);
            var $type = $this.find('.element-type');
            $type.change(function () {
                var name = 'activity-element-' + $(this).val();
                if (name != 'empty') {
                    $this.find('option[value=empty]')
                        .prop('disabled', true);
                }
                var $content = $this.find('.element-content');
                var newview = $.extend(true, {}, view);
                newview.stor = getStorage(storage, attrs.elements, self);
                template.renderTo($content, name, newview);
                $content.find('.dropdown-button').each(function (i) {
                    $(this).attr('id', 'menu_' + i);
                    $(this).next().attr('aria-labelledby', 'menu_' + i);
                    console.log(this)
                });
            });
            if (view && view.type != 'empty') {
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
                var element = {
                    type: $type.val(),
                    eid: attrs.elements[index].eid
                };
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
            self.setText(attrs.label);
            self.model.save();

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
    }

    return Block;
});
