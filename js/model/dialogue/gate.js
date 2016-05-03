define(['jquery', 'util/simple-template'], function ($, template) {
    function Gate(self) {
        this.self = self;
        var attrs = self.attrs || $.extend({}, { min: 0, max: -1 });
        var $ptr = $('#dialogue');

        $ptr.find('.modal-title').html('Gate Settings');

        var $body = $ptr.find('.modal-body');
        template.render($body, 'dialogue-gate').done(function () {
            var $in = $body.find('#gate_in');
            var $out = $body.find('#gate_out');
            var $controls = $body.find('#gate_controls');

            if (attrs.label) $body.find('#label').val(attrs.label);

            $in.val(attrs.min);
            $out.val(attrs.max);

            $body.find('button').on('click', function () {
                var $this = $(this);

                $body.find('button').removeClass('active');
                $this.addClass('active');

                if ($this.attr('min')) {
                    attrs.min = $this.attr('min');
                    attrs.max = $this.attr('max');
                    $in.val(attrs.min);
                    $out.val(attrs.max);
                }

                if ($this.val() != 'other') $controls.hide();
                else $controls.show();
            })
                .filter('[min=' + attrs.min  + '][max=' + attrs.max + ']')
                .click();

            $ptr.find('#save').off().on('click', function () {
                if ($body.find('#label').val()) {
                    attrs.label = $body.find('#label').val();
                }
                var text = $body.find('.active').val();
                if ($body.find('[value=other].active').size()) {
                    attrs.min = $in.val(); 
                    attrs.max = $out.val(); 
                    text = '';
                }
                self.attrs = attrs;
                $('#content').trigger('save-model');
                $ptr.modal('hide');
                self.setText(text);
            });

            $ptr.modal('show');
        });
    }

    return Gate;
});
