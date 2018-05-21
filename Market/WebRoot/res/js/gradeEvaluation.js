$(function(){
	var competitionId = $("#competitionId").val();
	$.ajax({
		type:'post',
		url:'loadGradeEvaluation.do',
		data:'competitionId=' + competitionId,
		success:function(data){
			if(data.length == 0){
				alert("目前没有平衡记分卡数据！！");
			}
			else{
				$("#quarter").html(data[0].balanceScore.quarter);
				var quarter =$("#quarter").html();
				$("#currentQuarter").html(parseInt(quarter)+1);
				var trNodes = $("tr");
				operateGradeEvaluation(trNodes, data);
			}
			
		}
	});
});

function toThousands(num) {
    return (num || 0).toString().replace(/(\d)(?=(?:\d{3})+$)/g, '$1,');
}

function operateGradeEvaluation(trNodes, data){
	/*var minTd = $("<td></td>");
	minTd.html(toThousands(data[0].balanceScoreMinData.grossRevenue);
	trNodes[0].append(minTd);
	var maxTd = $("<td></td>");
	maxTd.html(toThousands(data[0].balanceScoreMaxData.grossRevenue);
	trNodes[0].append(maxTd);
	var avgTd = $("<td></td>");
	avgTd.html(toThousands(data[0].balanceScoreAvgData.grossRevenue);
	trNodes[0].append(avgTd);*/
	for(var i = 0; i < data.length; i ++){
		var nameTd = $("<td></td>");
		nameTd.html(data[i].company.name);
		trNodes.eq(0).append(nameTd);
	}
	var minTd = $("<td></td>");
	minTd.html(toThousands(data[0].balanceScoreMinData.grossRevenue));
	trNodes.eq(1).append(minTd);
	var maxTd = $("<td></td>");
	maxTd.html(toThousands(data[0].balanceScoreMaxData.grossRevenue));
	trNodes.eq(1).append(maxTd);
	var avgTd = $("<td></td>");
	avgTd.html(toThousands(data[0].balanceScoreAvgData.grossRevenue));
	trNodes.eq(1).append(avgTd);
	for(var i = 0; i < data.length; i ++){
		var nameTd = $("<td></td>");
		nameTd.html(toThousands(data[0].balanceScoreMinData.grossRevenue));
		trNodes.eq(1).append(nameTd);
	}
	
	minTd = $("<td></td>");
	minTd.html(toThousands(data[0].balanceScoreMinData.grossCost));
	trNodes.eq(2).append(minTd);
	maxTd = $("<td></td>");
	maxTd.html(toThousands(data[0].balanceScoreMaxData.grossCost));
	trNodes.eq(2).append(maxTd);
	avgTd = $("<td></td>");
	avgTd.html(toThousands(data[0].balanceScoreAvgData.grossCost));
	trNodes.eq(2).append(avgTd);
	for(var i = 0; i < data.length; i ++){
		var nameTd = $("<td></td>");
		nameTd.html(toThousands(data[i].balanceScore.grossCost));
		trNodes.eq(2).append(nameTd);
	}
	
	minTd = $("<td></td>");
	minTd.html(toThousands(data[0].balanceScoreMinData.operatingProfit));
	trNodes.eq(3).append(minTd);
	maxTd = $("<td></td>");
	maxTd.html(toThousands(data[0].balanceScoreMaxData.operatingProfit));
	trNodes.eq(3).append(maxTd);
	avgTd = $("<td></td>");
	avgTd.html(toThousands(data[0].balanceScoreAvgData.operatingProfit));
	trNodes.eq(3).append(avgTd);
	for(var i = 0; i < data.length; i ++){
		var nameTd = $("<td></td>");
		nameTd.html(toThousands(data[i].balanceScore.operatingProfit));
		trNodes.eq(3).append(nameTd);
	}
	
	
	
	minTd = $("<td></td>");
	minTd.html(toThousands(data[0].balanceScoreMinData.cashEquivalent));
	trNodes.eq(4).append(minTd);
	maxTd = $("<td></td>");
	maxTd.html(toThousands(data[0].balanceScoreMaxData.cashEquivalent));
	trNodes.eq(4).append(maxTd);
	avgTd = $("<td></td>");
	avgTd.html(toThousands(data[0].balanceScoreAvgData.cashEquivalent));
	trNodes.eq(4).append(avgTd);
	for(var i = 0; i < data.length; i ++){
		var nameTd = $("<td></td>");
		nameTd.html(toThousands(data[i].balanceScore.cashEquivalent));
		trNodes.eq(4).append(nameTd);
	}
	
	minTd = $("<td></td>");
	minTd.html(toThousands(data[0].balanceScoreMinData.marketShare));
	trNodes.eq(5).append(minTd);
	maxTd = $("<td></td>");
	maxTd.html(toThousands(data[0].balanceScoreMaxData.marketShare));
	trNodes.eq(5).append(maxTd);
	avgTd = $("<td></td>");
	avgTd.html(toThousands(data[0].balanceScoreAvgData.marketShare));
	trNodes.eq(5).append(avgTd);
	for(var i = 0; i < data.length; i ++){
		var nameTd = $("<td></td>");
		nameTd.html(toThousands(data[i].balanceScore.marketShare));
		trNodes.eq(5).append(nameTd);
	}
	
	minTd = $("<td></td>");
	minTd.html(toThousands(data[0].balanceScoreMinData.unitMarketingRevenue));
	trNodes.eq(6).append(minTd);
	maxTd = $("<td></td>");
	maxTd.html(toThousands(data[0].balanceScoreMaxData.unitMarketingRevenue));
	trNodes.eq(6).append(maxTd);
	avgTd = $("<td></td>");
	avgTd.html(toThousands(data[0].balanceScoreAvgData.unitMarketingRevenue));
	trNodes.eq(6).append(avgTd);
	for(var i = 0; i < data.length; i ++){
		var nameTd = $("<td></td>");
		nameTd.html(toThousands(data[i].balanceScore.unitMarketingRevenue));
		trNodes.eq(6).append(nameTd);
	}
	
	minTd = $("<td></td>");
	minTd.html(toThousands(data[0].balanceScoreMinData.salesStaffRemuneration));
	trNodes.eq(7).append(minTd);
	maxTd = $("<td></td>");
	maxTd.html(toThousands(data[0].balanceScoreMaxData.salesStaffRemuneration));
	trNodes.eq(7).append(maxTd);
	avgTd = $("<td></td>");
	avgTd.html(toThousands(data[0].balanceScoreAvgData.salesStaffRemuneration));
	trNodes.eq(7).append(avgTd);
	for(var i = 0; i < data.length; i ++){
		var nameTd = $("<td></td>");
		nameTd.html(toThousands(data[i].balanceScore.salesStaffRemuneration));
		trNodes.eq(7).append(nameTd);
	}
	
	minTd = $("<td></td>");
	minTd.html(toThousands(data[0].balanceScoreMinData.trainingTime));
	trNodes.eq(8).append(minTd);
	maxTd = $("<td></td>");
	maxTd.html(toThousands(data[0].balanceScoreMaxData.trainingTime));
	trNodes.eq(8).append(maxTd);
	avgTd = $("<td></td>");
	avgTd.html(toThousands(data[0].balanceScoreAvgData.trainingTime));
	trNodes.eq(8).append(avgTd);
	for(var i = 0; i < data.length; i ++){
		var nameTd = $("<td></td>");
		nameTd.html(toThousands(data[i].balanceScore.trainingTime));
		trNodes.eq(8).append(nameTd);
	}
	
	minTd = $("<td></td>");
	minTd.html(toThousands(data[0].balanceScoreMinData.assetManagement));
	trNodes.eq(9).append(minTd);
	maxTd = $("<td></td>");
	maxTd.html(toThousands(data[0].balanceScoreMaxData.assetManagement));
	trNodes.eq(9).append(maxTd);
	avgTd = $("<td></td>");
	avgTd.html(toThousands(data[0].balanceScoreAvgData.assetManagement));
	trNodes.eq(9).append(avgTd);
	for(var i = 0; i < data.length; i ++){
		var nameTd = $("<td></td>");
		nameTd.html(toThousands(data[i].balanceScore.assetManagement));
		trNodes.eq(9).append(nameTd);
	}
	
	minTd = $("<td></td>");
	minTd.html(toThousands(data[0].balanceScoreMinData.productionEfficiency));
	trNodes.eq(10).append(minTd);
	maxTd = $("<td></td>");
	maxTd.html(toThousands(data[0].balanceScoreMaxData.productionEfficiency));
	trNodes.eq(10).append(maxTd);
	avgTd = $("<td></td>");
	avgTd.html(toThousands(data[0].balanceScoreAvgData.productionEfficiency));
	trNodes.eq(10).append(avgTd);
	for(var i = 0; i < data.length; i ++){
		var nameTd = $("<td></td>");
		nameTd.html(toThousands(data[i].balanceScore.productionEfficiency));
		trNodes.eq(10).append(nameTd);
	}
	
}
