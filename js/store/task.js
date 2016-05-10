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

    // user message service URL
    var URL_PE = '/PEUserMessageHandler/UserMessageService/';

    // process engine URLs
    var URL_MODELS = '/ProcessEngine/processModels/';
    var URL_INSTANCES = '/ProcessEngine/processInstances/';
    var URL_TASKS = '/ProcessEngine/tasks/';

    // pending taks URLs
    var URL_PENDING = URL_PE + 'pendingTasks/';
    var URL_ALLPENDING = URL_PE + 'allPendingTasks/';

    /**
     * Update interval
     *
     * @final
     * @type Integer
     * @default 1 second
     */
    var INTERVAL = 1000; // 1s

    /**
     * Base functions for manager classes
     * For now destroy() only
     *
     * @class Base
     */
    var Base = {
        /**
         * Removes interval updates
         *
         * @method destroy
         */
        destroy: function () {
            this.callback = null;
            clearInterval(this.interval);
        }
    };

    /**
     * Pending Task Manager manages pending tasks
     *
     * @class PendingTaskManager
     * @constructor
     *
     * @param callback {Function} callback
     */
    function PendingTaskManager(callback) {
        var self = this;
        this.tasks = [];
        this.callback = callback;

        var func = function () { self.update(); };
        this.interval = setInterval(func, INTERVAL);
        func();
    }

    PendingTaskManager.prototype = $.extend({
        /**
         * Sync with server
         *
         * @method update
         */
        update: function () {
            var self = this;

            // send request
            $.get(URL_PENDING).then(function (xml) {
                var tasks = [];

                // parse every task
                $(xml).find(util.xmlSel('umh:task')).each(function () {
                    // extract data from xml
                    var task = {
                        state: $(this).attr('state'),
                        handle: $(this).attr('handle'),
                        remotehandle: $(this).attr('remotehandle'),
                        instancehandle: $(this).attr('instancehandle'),
                        owner: $(this).attr('owner'),
                        items: []
                    };

                    // extract items
                    $(this).find(util.xmlSel('umh:item')).each(function () {
                        var item = {};
                        $.each(this.attributes, function (i, attr) {
                            item[attr.name] = attr.value;
                        });
                        task.items.push(item);
                    });

                    tasks.push(task);
                });

                // only update if something changed
                if (!_.isEqual(tasks, self.tasks)) {
                    self.tasks = tasks;
                    self.callback(tasks);
                }
            }).fail(function (e) { console.log('Cannot load tasks', e); });
        },

        /**
         * Accept task
         *
         * @method accept
         *
         * @param task {Integer} task handle
         */
        accept: function (task) {
            $.post(URL_PENDING + task, {
                state: 'Taken'
            }).fail(function (e) { console.log('Cannot accept task', e); }); 
        },

        /**
         * Cancel Task
         * Currently just finishes task
         *
         * @method cancel
         *
         * @param task {Integer} task handle
         */
        cancel: function (task) {
            console.log(task);
            $.post(URL_PENDING + task, {
                state: 'Finished'
            }).fail(function (e) { console.log('Cannot cancel task', e); }); 
        },

        /**
         * Submit task
         *
         * @method submit
         *
         * @param task {Integer} task handle
         * @param xml {String} xml to send
         */
        submit: function (task, xml) {
            console.log(xml);
            util.upload(URL_PENDING + task, 'partialNewTask', xml, true);
        }
    }, Base);

    /**
     * Model Task Manager for editor
     *
     * @class ModelTaskManager
     * @constructor
     *
     * @param model {Integer} model handle
     * @param callback {Function} update callback
     */
    function ModelTaskManager(model, callback) {
        var self = this;

        // default values
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

    ModelTaskManager.prototype = $.extend({
        /**
         * Sync with server
         *
         * @method update
         */
        update: function () {
            var self = this;

            // run
            $.get(URL_INSTANCES).then(function (xml) {
                var $inst = $(xml).find('[processModel="' + self.model + '"]');

                // make sure something is changed
                if ($inst.attr('handle') !== self.handle) {
                    self.handle = $inst.attr('handle');
                    self.callback();
                }

                if (self.handle) {
                    // search for tasks
                    $.get(URL_ALLPENDING).then(function (xml) {
                        var selector = '[instancehandle="' + self.handle + '"]';
                        var $task = $(xml).find(selector);

                        // make sure its new task
                        if ($task.attr('remotehandle') !== self.task) {
                            self.task = $task.attr('remotehandle');
                            self.tasks = {};
                            if (self.task) self.updateTask(self.task, true);
                        }
                        else if ($task.attr('state') !== self.state) {
                            // or state of the task changed
                            self.state = $task.attr('state');
                            self.tasks[self.eid] = self.state;
                            self.callback();
                        }
                    }).fail(function () {
                        console.error('Cannot load tasks');
                    });
                }
            }).fail(function () { console.error('Cannot load instances'); });
        },

        /**
         * Sync task with server
         *
         * @method updateTask
         *
         * @param handle {Integer} task handle
         * @param main {Boolean} is main task
         */
        updateTask: function (handle, main) {
            var self = this;
            $.get(URL_TASKS + handle).then(function (xml) {
                var $xml = $(xml);

                // save main attributes
                if (main) {
                    self.state = $xml.find('[nodeid]').attr('state');
                    self.eid = $xml.find('[nodeid]').attr('nodeid');
                }

                // extract node
                var node = {};
                node.id = $xml.find('[nodeid]').attr('nodeid');
                node.state = $xml.find('[nodeid]').attr('state');
                self.tasks[node.id] = node.state;

                // run same for predecessors
                var $preds = $xml.find(util.xmlSel('pe:predecessor'));
                $preds.each(function () {
                    self.updateTask(this.textContent);
                });

                // execture callback when done
                if (!$preds.size()) self.callback();

            }).fail(function () {
                console.error('Cannot load task');
            });
        },

        /**
         * Execute model
         *
         * @method execute
         */
        execute: function () {
            $.post(URL_MODELS + this.model, {
                op: 'newInstance'
            }).fail(function () { console.error('Cannot execute instance'); });
        },

        /**
         * Stop model
         *
         * @method execute
         */
        stop: function () {
            $.ajax({
                method: 'DELETE',
                url: URL_INSTANCES + this.handle
            }).fail(function () { console.error('Cannot stop instance'); });
        }
    }, Base);

    // export
    return {
        PendingTaskManager: PendingTaskManager,
        ModelTaskManager: ModelTaskManager
    };
});
