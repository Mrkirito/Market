var debug = true,
timestamp = new Date().getTime(),
//    timestamp = '2016042221',
baseURL = serverHost + "/res/media/js/lib";
//baseURL ="./js";

//window.CKEDITOR_BASEPATH =  serverHost + 'assets/common/ckeditor/';
requirejs.config({
    baseUrl: baseURL,
//    urlArgs: "v=" + (!!debug?timestamp:"2016042221"),
//    urlArgs: "v=2016042221",
    paths: {
    	app: '../app',
        global: '../..'
    },
    map: {
        '*': {
            'css': 'css'
        }
    },
    enforceDefine: true
});

