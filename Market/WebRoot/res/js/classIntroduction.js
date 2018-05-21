$(function(){
	var ulNode = $("ul:first");
	var aNode = ulNode.children("li").children("a").eq(0);
	aNode.click(function(){
		sendRequest();
	});
});

function sendRequest(){
	$.ajax({
		type:'post',
		url:'classIntroduction.do'
	});
}