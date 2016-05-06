"use strict";

var fs = require('fs');

var should = require('should');
var sinon = require('sinon');

require('mocha-jsdom')();

var requirejs = require('requirejs');
var xmldom = require('xmldom');

requirejs.config({
    baseUrl: 'js/',
    paths: {
        jquery: '../bower_components/jquery/jquery.min',
        bootstrap: '../bower_components/bootstrap/dist/js/bootstrap.min',
        joint: '../bower_components/jointjs/dist/joint.min',
        lodash: '../bower_components/lodash/lodash.min',
        underscore: '../bower_components/underscore/underscore-min',
        backbone: '../bower_components/backbone/backbone-min',
        Sortable: '../bower_components/Sortable/Sortable.min',
        dagre: '../bower_components/dagre/dist/dagre.core.min',
        graphlib: '../bower_components/graphlib/dist/graphlib.core.min'
    },
    shim: {
        dagre: {
            deps: [ 'graphlib' ]
        }
    },
    nodeRequire: require
});

function serve(req) {
    var filename = 'test/url/' + req.url.replace(/\//g, '_').substr(1) + '.xml';
    if (!fs.existsSync(filename)) should.fail('Incorrect URL, ' + req.url);

    var xml = fs.readFileSync(filename, 'utf-8');
    req.respond(200, {}, xml);
    //req.respond(200, { 'Content-type': 'text/xml' }, xml);
}

function load(store, reqs) {
    var $def = store.getList();
    serve(reqs.pop());
    reqs.forEach(function (req) { serve(req); });
    return $def;
}

describe('Store Testing', function () {
    var store, $;

    before(function (done) {
        requirejs(['store/model', 'jquery'], function (mod, jquery) {
            store = mod;
            $ = jquery;

            $.parseXML = function (text) {
                var parser = new xmldom.DOMParser();
                var xml = parser.parseFromString(text, 'text/xml');
                return xml;
            };

            global.XMLSerializer = xmldom.XMLSerializer;
            global.window.XMLSerializer = xmldom.XMLSerializer;

            done();
        });
    });

    beforeEach(function () {
        this.xhr = sinon.useFakeXMLHttpRequest();

        // fix jsdom override
        var req = sinon.FakeXMLHttpRequest;
        global.XMLHttpRequest = global.window.XMLHttpRequest = req;

        this.requests = [];
        this.xhr.onCreate = function (xhr) {
            this.requests.push(xhr);
        }.bind(this);

        store.reset();
    });

    it('Succesfully downloads models', function (done) {
        store.getList().then(function () {
            store.getModel(2).name.should.equal('wtf is that');

            var d = store.getModel(52).xml.documentElement.getAttribute('uuid');
            d.should.equal('db21a994-61cd-4f2d-abbe-a2429bdddd5f');

            done();
        });

        serve(this.requests.pop());
        this.requests.forEach(function (req) { serve(req); });
    });

    it('Can rename model', function (done) {
        load(store, this.requests).then(function() {
            store.renameModel(2, 'new name').then(function () {
                var model = store.getModel(2);
                model.name.should.equal('new name');

                var xname = model.xml.documentElement.getAttribute('name');
                xname.should.equal('new name');

                done();
            });

            serve(this.requests.pop());
        }.bind(this));
    });

    it('Can update model', function (done) {
        load(store, this.requests).then(function() {
            store.updateModel(2, '<xml/>').then(function () {
                var model = store.getModel(2);
                model.xml.documentElement.tagName.should.equal('xml');

                done();
            });

            serve(this.requests.pop());
        }.bind(this));
    });

    it('Can delete model', function (done) {
        load(store, this.requests).then(function() {
            store.deleteModel(2).then(function () {
                var model = store.getModel(2);
                if (model) should.fail('Model is not removed');

                done();
            });

            serve(this.requests.pop());
        }.bind(this));
    });

    it('Can create model', function (done) {
        load(store, this.requests).then(function() {
            store.createModel('new name').then(function () {
                var model = store.getModel('undefined');

                var xname = model.xml.documentElement.getAttribute('uuid');
                xname.should.equal('uuid');

                done();
            });

            serve(this.requests.pop());
            serve(this.requests.pop());
        }.bind(this));
    });

    it('Can clone model', function (done) {
        load(store, this.requests).then(function() {
            store.createModel(2, 'new name').then(function () {
                var model = store.getModel('undefined');

                var xname = model.xml.documentElement.getAttribute('uuid');
                xname.should.equal('uuid');

                done();
            });

            serve(this.requests.pop());
            serve(this.requests.pop());
        }.bind(this));
    });

    afterEach(function () {
        this.xhr.restore();
    });
});
