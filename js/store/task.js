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
define(['jquery', 'util/util'], function ($, util) {
    "use strict";

    var instances = {}; // instance storage
    var tasks = {}; // tasks storage

    var URL_INSTANCES = '/ProcessEngine/processInstances/';
    var URL_TASKS = '/ProcessEngine/tasks/';

    var URL_PENDING = '/PEUserMessageHandler/UserMessageService/pendingTasks';

    // export
    return {
    };
});
