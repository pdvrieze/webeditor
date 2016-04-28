define(['jquery', 'util/simple-template'], function ($, template) {
    function Gate(self) {
        this.self = self;
        var attrs = self.attrs || {};
        var $ptr = $('#dialogue');

        $ptr.find('.modal-title').html('Gate Settings');

        var mapping = {
            and: { in: 0, out: 20 },
            or: { in: 1, out: 20 },
            xor: { in: 1, out: 1 }
        };

        var $body = $ptr.find('.modal-body');
        template.render($body, 'dialogue-gate').done(function () {
            var $in = $body.find('#gate_in');
            var $out = $body.find('#gate_out');
            var $controls = $body.find('#gate_controls');

            if (attrs.label) $body.find('#label').val(attrs.label);

            if (attrs.in && attrs.out) {
                $in.val(attrs.in);
                $out.val(attrs.out);
            }

            $body.find('button').on('click', function () {
                var $this = $(this);
                attrs.gate = $this.val();

                $body.find('button').removeClass('active');
                $this.addClass('active');

                var values = mapping[$this.val()];
                if (values) {
                    $in.val(values.in);
                    $out.val(values.out);
                }

                if ($this.val() != 'other') $controls.hide();
                else $controls.show();
            }).filter('[value=' + (attrs.gate || 'and') + ']').click();

            $ptr.find('#save').off().on('click', function () {
                if ($body.find('#label').val()) {
                    attrs.label = $body.find('#label').val();
                }
                if ($body.find('[value=other].active').size()) {
                    attrs.in = $in.val(); 
                    attrs.out = $out.val(); 
                }
                self.attrs = attrs;
                $ptr.modal('hide');
            });

            $ptr.modal('show');
        });
    }

    return {
        Gate: Gate
    };
});
