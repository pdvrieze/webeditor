"use strict";

var should = require('should');
var sinon = require('sinon');

require('mocha-jsdom')({skipWindowCheck: true});

var requirejs = require('requirejs');

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

describe('Utilities Testing', function () {
    var util;

    before(function (done) {
        requirejs(['util/util'], function (mod) { util = mod; done(); });
    });

    describe('xmlSel', function () {
        it('Should escape selector', function () {
            util.xmlSel('pe:xxx').should.startWith('pe\\:xxx');
        });

        it('Should additionally search by tag name', function () {
            util.xmlSel('ns:tag').should.endWith(',tag');
        });
    });

    describe('replaceAttr', function () {
        var string = '<tag attr="value">...';
        it('Should be able to remove attribute', function () {
            util.replaceAttr(string, 'attr').should.equal('<tag >...');
        });
        it('Should be able to replace attribute', function () {
            util.replaceAttr(string, 'attr', 'value', 'new')
                .should.equal('<tag attr="new">...');
        });
    });

    describe('formatXml', function () {
        var xml = '<xml><tag><tag/></tag></xml>';
        var formatted = '<xml>\n\t<tag>\n\t\t<tag/>\n\t</tag>\n</xml>\n'
            .replace(/\n/g, '\r\n').replace(/\t/g, '  ');
        it('Should format simple xml', function () {
            util.formatXml(xml).should.equal(formatted);
        });
    });

    describe('upload', function () {
        beforeEach(function () {
            this.xhr = sinon.useFakeXMLHttpRequest();

            // fix jsdom override
            var req = sinon.FakeXMLHttpRequest;
            global.XMLHttpRequest = global.window.XMLHttpRequest = req;

            this.request = null;
            this.xhr.onCreate = function (xhr) {
                this.request = xhr;
            }.bind(this);
        });

        it('Has valid format', function () {
            util.upload('url', 'attribute', 'data');

            var line = 'Content-Disposition: form-data; name="attribute"; ' +
                       'filename="temp.xml"';

            var req = this.request.requestBody.split(/\r\n|\r|\n/);
            req[0].should.startWith('--');

            req[1].should.equal(line);
            req[2].should.equal('Content-type: plain/text');

            req[4].should.equal('data');

            req[5].should.startWith(req[0]);
            req[5].should.endWith('--');
        });

        it('Returns promise', function(done) {
            var $def = util.upload('url', 'attribute', 'data');
            $def.always(function () { done(); });
            this.request.respond(200, {}, '');
        });

        afterEach(function () {
            this.xhr.restore();
        });
    });
});
