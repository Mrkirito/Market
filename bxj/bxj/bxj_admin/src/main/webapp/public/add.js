$(function(){
	   var mc = UE.getEditor('mc');
	   var bbrqy = UE.getEditor('bbrqy');
	   var cpts = UE.getEditor('cpts');
		$('#qy_table').on('click','button.del',function(){
			$(this).parent().parent().remove();
		});
		$("#add_qy").on('click',function(){
			$("#qy_table").append('<tr class="qys"><td><input type="text" name="head1"/></td><td><input type="text" name="head2"/></td><td><input type="text" name="head3"/></td><td><button type="button" class="del">删除</button></td></tr>');
		});
		var pid=$('input[name=fid]').val();
		if(pid){
			var _mc=$('input[name=mc]').val();
			var _bbrqy=$('input[name=bbrqy]').val()
			var _cpts=$('input[name=cpts]').val();
			mc.ready(function(){
			   mc.setContent(_mc);    
			});
			bbrqy.ready(function(){
				bbrqy.setContent(_bbrqy);    
			});
			cpts.ready(function(){
				cpts.setContent(_cpts);    
			});
		}
});
function trySubmit(e) {
	$('input[name=mc]').val(UE.getEditor('mc').getContent());
	$('input[name=bbrqy]').val(UE.getEditor('bbrqy').getContent());
	$('input[name=cpts]').val(UE.getEditor('cpts').getContent());
	var qymap = new HashMap();
	$('#qy_table tr.qys').each(function(i, o) {
		o = $(o);
		var obj = new Object();
		o.find('input').each(function(m, k) {
			k = $(k);
			obj[k.attr('name')] = k.val();
		});
		qymap.put(i, obj);
	});
	var para = $("#insure_info").serialize();
	if(qymap.size()>0){
		para+="&adminQys="+ JSON.stringify(qymap.values());		
	}
	$.ajax({
		type : "POST",
		url : bxj_path + "/refreshcache/product.json",
		data :para,
		dataType : "json",
		success : function(data) {
			if(data.success){
				alert('成功........');
			}
		},
		error : function(data) {
			
		}
	});	
}
function HashMap(){var length=0;var obj=new Object();this.isEmpty=function(){return length==0};this.containsKey=function(key){return(key in obj)};this.containsValue=function(value){for(var key in obj){if(obj[key]==value){return true}}return false};this.put=function(key,value){if(!this.containsKey(key)){length++}obj[key]=value};this.get=function(key){return this.containsKey(key)?obj[key]:null};this.remove=function(key){if(this.containsKey(key)&&(delete obj[key])){length--}};this.values=function(){var _values=new Array();for(var key in obj){_values.push(obj[key])}return _values};this.keySet=function(){var _keys=new Array();for(var key in obj){_keys.push(key)}return _keys};this.size=function(){return length};this.clear=function(){length=0;obj=new Object()}}