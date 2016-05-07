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

    var URL_PE = '/PEUserMessageHandler/UserMessageService/';

    var URL_MODELS = '/ProcessEngine/processModels/';
    var URL_INSTANCES = '/ProcessEngine/processInstances/';
    var URL_TASKS = '/ProcessEngine/tasks/';

    var URL_PENDING = URL_PE + 'pendingTasks/';
    var URL_ALLPENDING = URL_PE + 'allPendingTasks/';

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
        this.task = null;
        this.tasks = {};
        this.state = null;
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
                if ($inst.attr('handle') !== self.handle) {
                    self.handle = $inst.attr('handle');
                    self.callback();
                }

                if (self.handle) {
                    $.get(URL_ALLPENDING).then(function (xml) {
                        var selector = '[instancehandle="' + self.handle + '"]';
                        var $task = $(xml).find(selector);
                        if ($task.attr('remotehandle') !== self.task) {
                            self.task = $task.attr('remotehandle');
                            self.tasks = {};
                            if (self.task) self.updateTask(self.task, true);
                        }
                        else if ($task.attr('state') !== self.state) {
                            self.state = $task.attr('state');
                            self.tasks[self.eid] = self.state;
                            self.callback();
                        }
                    }).fail(function (e) {
                        console.error('Cannot load tasks');
                    });
                }
            }).fail(function (e) { console.error('Cannot load instances'); });
        },

        updateTask: function (handle, main) {
            var self = this;
            $.get(URL_TASKS + handle).then(function (xml) {
                var $xml = $(xml);

                if (main) {
                    self.state = $xml.find('[nodeid]').attr('state');
                    self.eid = $xml.find('[nodeid]').attr('nodeid');
                }

                var node = {};
                node.id = $xml.find('[nodeid]').attr('nodeid');
                node.state = $xml.find('[nodeid]').attr('state');
                self.tasks[node.id] = node.state;

                var $preds = $xml.find(util.xmlSel('pe:predecessor'));
                $preds.each(function () {
                    self.updateTask(this.textContent);
                });
                if (!$preds.size()) self.callback();

            }).fail(function (e) {
                console.error('Cannot load task');
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
