define(['jquery', 'util/simple-template'], function ($, template) {
    function Gate(self) {
        this.self = self;
        this.self.attrs = self.attrs || {};
        this.$ptr = $('#dialogue').modal('show');

        this.$ptr.find('.modal-title').html('Gate Settings');

        var mapping = {
            and: { in: 0, out: 20 },
            or: { in: 1, out: 20 },
            xor: { in: 1, out: 1 }
        };

        var $body = this.$ptr.find('.modal-body');
        template.render($body, 'dialogue-gate').done(function () {
            var $in = $body.find('#gate_in');
            var $out = $body.find('#gate_out');
            var $controls = $body.find('#gate_controls');

            if (self.attrs.label) $body.find('#label').val(self.attrs.label);

            $body.find('button').on('click', function () {
                var $this = $(this);
                self.attrs.gate = $this.val();

                $body.find('button').removeClass('active');
                $this.addClass('active');

                var values = mapping[$this.val()];
                if (values) {
                    $in.val(values.in);
                    $out.val(values.out);
                }

                if ($this.val() != 'other') $controls.hide();
                else $controls.show();
            }).filter('[value=' + (self.attrs.gate || 'and') + ']').click();
        })
    }

    return {
        Gate: Gate
    };
});
