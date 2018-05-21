/*
 * 					<li>
						<div class="link">
							A.Market简介<i class="fa fa-caret-right"></i>
						</div>
						<ul class="submenu">
							<li><a href="jsp/teacherHelpDocument/AMarketIntroduction.jsp"
								target="main">&nbsp;1.竞赛背景</a></li>
							<li><a href="jsp/teacherHelpDocument/AMarketIntroduction.jsp"
								target="main">&nbsp;2.如何获胜</a></li>
							<li><a href="jsp/teacherHelpDocument/AMarketIntroduction.jsp"
								target="main">&nbsp;3.每季度决策</a></li>
						</ul>
					</li>
 * 
 */

$(function(){
	$.ajax({
		type:'post',
		url:'selectAllHelpDocument.do',
		success:function(data){//返回json结果
			var count = 0;
			for(var i = 0; i < data[0].size; i ++){//$("#u1").append("<li>"+data[i].name+"</li>");
				var liNode = $("<li></li>");
				var divNode = $("<div></div>");
				divNode.addClass("link");
				divNode.html(data[count].titleLevelOne);
				divNode.append("<i class='fa fa-caret-right'></i>");
				liNode.append(divNode);
				var ulNode = $("<ul class='submenu'></ul>");
				for(var j = count; j < data.length; j ++ ){
					if(data[j].titleLevelOne == data[count].titleLevelOne){
						var liNode1 = $("<li></li>");
						var aNode = $("<a></a>");
						aNode.attr("href","javascript:void(0)");
						aNode.attr("titleOne",data[j].titleLevelOne);
						aNode.attr("titleTwo",data[j].titleLevelTwo);
						aNode.click(function(){
							var titleOne = $(this).attr("titleone");
							var titleTwo = $(this).attr("titletwo");
							select(titleOne,titleTwo);
						});
						aNode.html(data[j].titleLevelTwo);
						liNode1.append(aNode);
						ulNode.append(liNode1);
					}
					else{
						count = j;
						break;
					}
				}
				liNode.append(ulNode);
				$("#accordion").append(liNode);
				
			}
			openCloseMenu();
			addClick();
		}
		
	});
});

function select(titleOne, titleTwo){
	$.ajax({
		type:'post',
		url:'selectSingleItem.do',
		data:'titleOne=' + titleOne + '&titleTwo=' + titleTwo,
		success:function(data){//返回json结果
			$("#title").html(data.titleLevelOne + "----" + data.titleLevelTwo);
			$("#documentContent").html(data.content);
		}
		
	});
}

function addClick(){   		
	$(".list-group").children("a").click(function(){
		$(".xianshi,.yincang").hide();       		
		$(this).next("div").show();
	});
	
}


$("#myiframe").load(function () {
    var mainheight = $(this).contents().find("body").height() + 20;
    $(this).height(mainheight);
    //注意：这里设置了最小长度
});
function openCloseMenu() {
	var Accordion = function(el, multiple) {
		this.el = el || {};
		this.multiple = multiple || false;

		// Variables privadas
		var links = this.el.find('.link');
		// Evento
		links.on('click', {el: this.el, multiple: this.multiple}, this.dropdown)
	}

	Accordion.prototype.dropdown = function(e) {
		var $el = e.data.el;
			$this = $(this),
			$next = $this.next();

		$next.slideToggle();
		$this.parent().toggleClass('open');

		if (!e.data.multiple) {
			$el.find('.submenu').not($next).slideUp().parent().removeClass('open');
		};
	}	

	var accordion = new Accordion($('#accordion'), false);
}


