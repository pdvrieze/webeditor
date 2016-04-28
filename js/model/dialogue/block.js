define(['jquery', 'util/simple-template', 'Sortable'],
       function ($, template, Sortable) {
    function Block(self) {
        this.self = self;
        var attrs = self.attrs || {};
        var $ptr = $('#dialogue');

        $ptr.find('.modal-title').html('Activity Settings');

        var $body = $ptr.find('.modal-body');
        template.render($body, 'dialogue-activity').done(function () {
            var sortable = Sortable.create($body.find('.list-group').get(0));

            $ptr.find('#save').off().on('click', function () {
            });

            $ptr.modal('show');
        });
    }

    return Block;
});
