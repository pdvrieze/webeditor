/**
 * Task Store
 *
 * Handles the interaction between server and client, and stores the
 * intermediate results of the execution flow
 *
 * Uses mixed API for managin both instances and tasks
 *
 * @module Store
 */
define(['jquery', 'lodash', 'util/util'], function ($, _, util) {
    "use strict";

    var URL_MODELS = '/ProcessEngine/processModels/';
    var URL_INSTANCES = '/ProcessEngine/processInstances/';
    var URL_TASKS = '/ProcessEngine/tasks/';
    var URL_PENDING = '/PEUserMessageHandler/UserMessageService/pendingTasks/';

    var INTERVAL = 1000; // 1s

    var Base = {
        destroy: function () {
            this.callback = null;
            clearInterval(this.interval);
        }
    };

    function PendingTaskManager(callback) {
        var self = this;
        this.tasks = [];
        this.callback = callback;

        var func = function () { self.update(); };
        this.interval = setInterval(func, INTERVAL);
        func();
    }

    function ModelTaskManager(model, callback) {
        var self = this;
        this.model = model;
        this.handle = null;
        this.callback = callback;

        var func = function () { self.update(); };
        this.interval = setInterval(func, INTERVAL);
        func();
    }

    PendingTaskManager.prototype = $.extend({
        update: function () {
            var self = this;
            $.get(URL_PENDING).then(function (xml) {
                var tasks = [];
                $(xml).find(util.xmlSel('umh:task')).each(function () {
                    var task = {
                        state: $(this).attr('state'),
                        handle: $(this).attr('handle'),
                        remotehandle: $(this).attr('remotehandle'),
                        instancehandle: $(this).attr('instancehandle'),
                        owner: $(this).attr('owner'),
                        items: []
                    };

                    $(this).find(util.xmlSel('umh:item')).each(function () {
                        var item = {};
                        $.each(this.attributes, function (i, attr) {
                            item[attr.name] = attr.value;
                        });
                        task.items.push(item);
                    });

                    tasks.push(task);
                });

                if (!_.isEqual(tasks, self.tasks)) {
                    self.tasks = tasks;
                    self.callback(tasks);
                }
            }).fail(function (e) { console.log('Cannot load tasks', e); });
        },

        accept: function (task) {
            $.post(URL_PENDING + task, {
                state: 'Taken'
            }).fail(function (e) { console.log('Cannot accept task', e); }); 
        },

        cancel: function (task) {
            $.post(URL_PENDING + task, {
                state: 'Finished'
            }).fail(function (e) { console.log('Cannot cancel task', e); }); 
        },

        submit: function (task, xml) {
            console.log(xml);
            util.upload(URL_PENDING + task, 'partialNewTask', xml, true);
        }
    }, Base);

    ModelTaskManager.prototype = $.extend({
        update: function () {
            var self = this;
            $.get(URL_INSTANCES).then(function (xml) {
                var $inst = $(xml).find('[processModel="' + self.model + '"]');
                if (!$inst.size() || $inst.attr('handle') != self.handle) {
                    self.handle = $inst.attr('handle');
                    self.callback(self.handle);
                }
            });
        },

        execute: function () {
            $.post(URL_MODELS + this.model, {
                op: 'newInstance'
            }).fail(function (e) { console.error('Cannot execute instance'); });
        },

        stop: function () {
            $.ajax({
                method: 'DELETE',
                url: URL_INSTANCES + this.handle
            }).fail(function (e) { console.error('Cannot stop instance'); });
        }
    }, Base);

    // export
    return {
        PendingTaskManager: PendingTaskManager,
        ModelTaskManager: ModelTaskManager
    };
});
