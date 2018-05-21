window.CKEDITOR_BASEPATH =  serverHost + 'assets/common/ckeditor/';

requirejs.config({
    baseUrl: serverHost + "assets/dist/scripts/lib",
    urlArgs: "v=2016042221",
    paths: {
        app: '../app',
        global: '../..'
    },
    map: {
        '*': {
            'css': 'css'
        }
    },/*
    shim: {
        'ckeditor/ckeditor': {
            exports: 'CKEDITOR'
        }
    },*/
    enforceDefine: true
});