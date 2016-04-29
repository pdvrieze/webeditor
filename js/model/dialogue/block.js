define(['jquery', 'util/simple-template', 'Sortable'],
       function ($, template, Sortable) {
    function Block(self) {
        this.self = self;
        var attrs = self.attrs || {};
        var $ptr = $('#dialogue');

        $ptr.find('.modal-title').html('Activity Settings');

        // TODO
        attrs.elements = [{
            type: 'label',
            text: 'Label'
        }, {
            type: 'input',
            text: 'Input',
            value: ''
        }];
        //
        
        function toview(element, index) {
            var title = element.type;

            var description = {};
            if (element.text) description['Label'] = element.text;
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
            var sortable = Sortable.create($content.get(0));

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
                render($content);
            });

            $body.on('show.bs.collapse', '.collapse', function () {
                var href = $(this).attr('id');
                $body.find('a[href="#' + href + '"]').addClass('active');
            });
            $body.on('hide.bs.collapse', '.collapse', function () {
                var href = $(this).attr('id');
                $body.find('a[href="#' + href + '"]').removeClass('active');
            });

            $ptr.find('#save').off().on('click', function () {
                attrs.label = $body.find('#label').val();
                self.attrs = attrs;
                $ptr.modal('hide');
            });

            $ptr.modal('show');
        });
    }

    return Block;
});
