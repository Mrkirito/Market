define(['jquery',
    'plugins/summernote/summernote',
    'css!global/js/lib/plugins/summernote/summernote.css'/*,
    'css!global/scripts/lib/plugins/summernote/summernote-bs3.css'*/
], function ($, _) {

    /**
     *   类型	 按钮id	    方法
         Insert	 picture	Insert a picture
                 link	    Insert a hyperlink
                 video	    Insert a video
                 table	    Insert a table
                 hr	        Insert a horizontal rule
         Style	style	    Format selected block
                 fontname	Set font family
                 fontsize	Set font size
                 color	    Set foreground and background color
                 bold	    Toggle weight
                 italic	    Toggle italic
                 underline	Toggle underline
                 strikethrough	Toggle strikethrough
                 clear	    Clearing all styles
         Layout	ul	        Make an un-ordered list
                 ol	        Make an ordered list
                 paragraph	Set text alignment
                 height	Set height of text
         Misc	fullscreen	Toggle fullscreen editing mode
                 codeview	Toggle wysiwyg and html editing mode
                 undo	    Undo
                 redo	    Redo
                 help	    Show help dialog
     *
     */
    var defaults = {
        selector: null,
        lang: null, // default en-US
        width: null,
        height: null,
        minHeight: null,
        maxHeight: null,
        focus: true,
        toolbar: [
            ['style', ['fontsize']],
            ['font', ['bold', 'italic', 'underline', 'clear']],
            ['fontname', ['fontname']],
            ['color', ['color']],
            ['para', ['ul', 'ol', 'paragraph']],
            ['height', ['height']],
            ['table', ['table']],
            ['insert', ['link', 'picture', 'hr']],
            ['view', ['fullscreen', 'codeview']],
            ['help', ['help']],
            ['undo', ['undo']],
            ['redo', ['redo']]
        ],

        oninit: function () {},
        onenter: function (event) {},
        onfocus: function (event) {},
        onblur: function (event) {},
        onkeyup: function (event) {},
        onkeydown: function (event) {},
        onpaste: function (event) {},
        /**
         * 图片上传事件
         * @param files
         * @param editor
         * @param $editable
         */
        onImageUpload: function(files, editor, $editable) {
        	data = new FormData();
        	data.append("attach", 1);
        	data.append("imageFile",files[0]);
        	$.ajax({
        		data: data,
        		type: "POST",
        		url: serverHost + "/common/uploadImage.json",
        		cache: false,
        		contentType: false,
        		processData: false,
        		success: function(data) {
	        		editor.insertImage($editable, data.model.fileUrl, data.model.fileName);
        		},
        		error:function(){
        			
        		}
    		});
        },
        onChange: function(contents, $editable) {},

    };

    return {
        init: function (config) {
            var editor = {
                init: function (config) {
                    this.opts = $.extend({}, defaults, config);
                    this.editor = $(this.opts.selector).summernote(this.opts);
                    return this;
                },

                setValue: function (val) {
                    $(this.opts.selector).code(val);
                },

                getValue: function () {
                    return $(this.opts.selector).code();
                },

                destroy: function () {
                    $(this.opts.selector).destroy();
                }
            };

            return editor.init(config);
        }
    };
});