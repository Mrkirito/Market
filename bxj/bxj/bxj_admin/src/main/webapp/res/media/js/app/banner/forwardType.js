String.prototype.startWith=function(str){  
            if(str==null||str==""||this.length==0||str.length>this.length)  
              return false;  
            if(this.substr(0,str.length)==str)  
              return true;  
            else  
              return false;  
            return true;  
        };
        
	String.prototype.endWith=function(str){  
            if(str==null||str==""||this.length==0||str.length>this.length)  
              return false;  
            if(this.substring(this.length-str.length)==str)  
              return true;  
            else  
              return false;  
            return true;  
        };
	
	/*
	 * tips 为空时，提示 无参数，且不用获取参数值。
	 * getForwardUrl 方法必须返回一个值，否则认为是报错。可利用这点检查参数合理性，并在检查正确的时候返回正确的地址，否则进行提示，并返回空值。
	 * getDatas 方法不是必须的。可返回一个数组，其中包含一些参数可能的值，其中对象的格式 {value: '参数值', name '参数值描述'}
	 * group 不是必须的，指定对 option 进行分组显示。
	 */
	var forwardTypies = {
	
		links: {
			name: '外部链接',
			tips: '链接地址，必须由 http:// 或 https:// 起始',
			group: '展业宝',
			getDatas: function() {
				return [{
					name: '',
					value: 'http://'
				}, {
					name: '',
					value: 'https://'
				}];
			},
			getStart: function() {
				return 'http';
			},
			getForwardUrl: function(str) {
				return str;
			}
		},
		
		invite: {
			name: '邀请函',
			group: '展业宝',
			minVersion: 3.1,
			isH5: false,
			getForwardUrl: function() {
				return 'app://invite';
			},
			parse: parseEq
		},
		
		usercard: {
			name: '显示名片',
			group: '展业宝',
			minVersion: 3.1,
			isH5: false,
			getForwardUrl: function() {
				return 'app://usercard';
			},
			parse: parseEq
		},
		
		listPlanbook: {
			name: '计划书列表',
			tips: '公司ID',
			dataType: 'number',
			group: '计划书',
			isH5: false,
			minVersion: 3.1,
			required: false,
			getStart: function() {
				return 'app://planbook';
			},
			getForwardUrl: function(id) {
				var url = this.getStart();
				if (id) {
					return url += '?gs=' + id;
				}
				return url;
			},
			parse: function(url) {
				var f = url.startWith(this.getStart());
				if (f) {
					return {
						match: true,
						arg: url.substring(this.getStart().length + 4)
					};
				}
			}
		},
		
		makePlanbook: {
			name: '制作计划书',
			tips: '计划书ID',
			dataType: 'number',
			group: '计划书',
			isH5: true,
			getStart: function() {
				return bxjPath + '/bxj_web/plan/make';
			},
			getEnd: function() {
				return '.page?video=1&hd=1';
			},
			getForwardUrl: function(id) {
				return this.getStart() + id + this.getEnd();
			},
			parse: betweenArg
		},
		
		freeInsurances: {
			name: '赠险列表',
			group: '赠险',
			isH5: true,
			getForwardUrl: function() {
				return bxjPath + '/bxj_web/free/listFreeInsurances.page?hd=1';
			},
			parse: parseEq
		},
		
		freeInsuranceDetails: {
			name: '赠险详情',
			tips: '增险ID',
			dataType: 'number',
			group: '赠险',
			isH5: true,
			getDatas: function() {
				return [{
					name: '航空意外免费保障',
					value: '10'
				}, {
					name: '发烧补偿服务',
					value: '147'
				}, {
					name: '金动力意外住院',
					value: '148'
				}, {
					name: '燃气意外险',
					value: '149'
				}, {
					name: '乘驾意外保障',
					value: '151'
				}];
			},
			getStart: function() {
				return bxjPath + '/bxj_web/free/getFreeInsuranceDetails1.page?shareios=1&pid=';
			},
			getForwardUrl: function(id) {
				return this.getStart() + id;
			},
			parse: endWithArg
		},
		
		championBbs: {
			name: '冠军论坛场次列表',
			group: '冠军论坛',
			isH5: true,
			getForwardUrl: function() {
			   return bxjPath + '/bxj_web/summit/summitlist.page?head=0';
				//return 'http://cdn.hangjiabao.com/bxj_web/bxj_three/static/championforum/forum_index0624.html?head=2#&forum_index';
			},
			parse: parseEq
		},
		
		championBbsDetails: {
			name: '冠军论坛会议详情',
			tips: '会议ID',
			group: '冠军论坛',
			isH5: true,
			getDatas: function() {
				return [{
					name: '广州会议',
					value: 'GuangzhouMeeting'
				}, {
					name: '西安会议',
					value: 'XiAnMeeting'
				}, {
					name: '兰州会议',
					value: 'LanzhouMeeting'
				}, {
					name: '东莞会议',
					value: 'DongguanMeeting'
				}, {
					name: '合肥会议',
					value: 'HefeiMeeting'
				}, {
					name: '长沙会议',
					value: 'ChangshaMeeting'
				}];
			},
			getStart: function() {
				return 'http://cdn.hangjiabao.com/bxj_web/bxj_three/static/championforum/forum_index0624.html?head=2&hd=1#&';
			},
			getForwardUrl: function(id) {
				return this.getStart() + id;
			},
			parse: endWithArg
		},
		
		championsayIndex: {
			name: '冠军说首页',
			tips: '分类标识',
			group: '冠军说',
			minVersion: 3.1,
			isH5: false,
			getDatas: function() {
				return [{
					name: '精选',
					value: 'jx'
				}, {
					name: '分类',
					value: 'fl'
				}, {
					name: '讲师',
					value: 'js'
				}, {
					name: '榜单',
					value: 'bd'
				}];
			},
			getStart: function() {
				return 'app://championsay?type=';
			},
			getForwardUrl: function(id) {
				return this.getStart() + id;
			},
			parse: endWithArg
		},
		
		championsayVideoDetails: {
			name: '视频详情', // 3.1 之后原生化，之前的版本需使用h5
			tips: '视频ID',
			dataType: 'number',
			group: '冠军说',
			minVersion: 3.1,
			isH5: false,
			getStart: function() {
				return 'app://championsay?type=sp&id=';
			},
			getForwardUrl: function(id) {
				return this.getStart() + id;
			},
			parse: endWithArg
		},
		
		championsayVideoDetailsH5: {
			name: '视频详情',
			tips: '视频ID',
			dataType: 'number',
			group: '冠军说',
			maxVersion: 3.1,
			isH5: true,
			getStart: function() {
				return bxjPath + '/champion_web/champion/video/videoDetail.page?fid=';
			},
			getForwardUrl: function(id) {
				return this.getStart() + id;
			},
			parse: endWithArg
		},
		
		championsayTeacherDetails: {
			name: '讲师详情',
			tips: '讲师ID',
			dataType: 'number',
			group: '冠军说',
			minVersion: 3.1,
			isH5: false,
			getStart: function() {
				return 'app://championsay?type=js&id=';
			},
			getForwardUrl: function(id) {
				return this.getStart() + id;
			},
			parse: endWithArg
		},
		
		championsayJxFree: {
			name: '精选-限时免费看',
			group: '冠军说',
			minVersion: 3.1,
			isH5: false,
			getForwardUrl: function() {
				return 'app://championsay?type=jx&module=xm';
			},
			parse: parseEq
		},
		
		championsayJxType: {
			name: '精选-类别',
			tips: '类别ID',
			dataType: 'number',
			group: '冠军说',
			minVersion: 3.1,
			isH5: false,
			getStart: function() {
				return 'app://championsay?type=jx&id=';
			},
			getForwardUrl: function(id) {
				return this.getStart() + id;
			},
			parse: endWithArg
		},
		
		championsayFlTopice: {
			name: '分类-主题',
			tips: '主题ID',
			dataType: 'number',
			group: '冠军说',
			minVersion: 3.1,
			isH5: false,
			getStart: function() {
				return 'app://championsay?type=fl&id=';
			},
			getForwardUrl: function(id) {
				return this.getStart() + id;
			},
			parse: endWithArg
		},
		
		championsayFlBbs: {
			name: '分类-冠军论坛',
			group: '冠军说',
			minVersion: 3.1,
			isH5: false,
			getForwardUrl: function() {
				return 'app://championsay?type=fl&module=gjlt';
			},
			parse: parseEq
		},
		
		championsayJsAll: {
			name: '讲师-全部讲师',
			group: '冠军说',
			minVersion: 3.1,
			isH5: false,
			getForwardUrl: function() {
				return 'app://championsay?type=js&module=all';
			},
			parse: parseEq
		},
		
		championsayBdVideo: {
			name: '分榜单-视频',
			tips: '视频榜单ID',
			dataType: 'number',
			group: '冠军说',
			minVersion: 3.1,
			isH5: false,
			getStart: function() {
				return 'app://championsay?type=bd&module=sp&id=';
			},
			getForwardUrl: function(id) {
				return this.getStart() + id;
			},
			parse: endWithArg
		},
		
		championsayBdTeacher: {
			name: '分榜单-讲师',
			tips: '讲师榜单ID',
			dataType: 'number',
			group: '冠军说',
			minVersion: 3.1,
			isH5: false,
			getStart: function() {
				return 'app://championsay?type=bd&module=js&id=';
			},
			getForwardUrl: function(id) {
				return this.getStart() + id;
			},
			parse: endWithArg
		},
		
		studyNewMan: {
			name: '新人通',
			group: '学习',
			isH5: true,
			getForwardUrl: function() {
				return bxjPath + '/bxj_web/newPeoplePass/showNewPeople.page';
			},
			parse: parseEq
		},
		
		studyArticleList: {
			name: '文章分类列表',
			group: '学习',
			minVersion: 3.1,
			isH5: false,
			getForwardUrl: function() {
				return 'app://study?module=category';
			},
			parse: parseEq
		},
		
		studyArticleDetails: {
			name: '文章详情',
			tips: '文章ID',
			group: '学习',
			dataType: 'number',
			isH5: true,
			getStart: function() {
				return bxjPath + '/study_web/study/details.page?articleId=';
			},
			getForwardUrl: function(id) {
				return this.getStart() + id;
			},
			parse: endWithArg
		},
		
		myInfo: {
			name: '我的资料',
			group: '我的',
			minVersion: 3.1,
			isH5: false,
			getForwardUrl: function() {
				return 'app://myInfo';
			},
			parse: parseEq
		},
		
		myConcern: {
			name: '我的关注',
			group: '我的',
			minVersion: 3.1,
			isH5: false,
			getForwardUrl: function() {
				return 'app://myConcern';
			},
			parse: parseEq
		},
		
		myCollects: {
			name: '我的收藏',
			group: '我的',
			minVersion: 3.1,
			isH5: false,
			getForwardUrl: function() {
				return 'app://myCollects';
			},
			parse: parseEq
		},
		
		myPayed: {
			name: '我的购买',
			group: '我的',
			minVersion: 3.1,
			isH5: false,
			getForwardUrl: function() {
				return 'app://myPayed';
			},
			parse: parseEq
		},
		
		myItems: {
			name: '我的福袋',
			group: '我的',
			minVersion: 3.1,
			isH5: false,
			getForwardUrl: function() {
				return 'app://myItems';
			},
			parse: parseEq
		},
		
		myPlanBook: {
			name: '我的计划书',
			group: '我的',
			isH5: true,
			getForwardUrl: function() {
				return bxjPath + '/bxj_web/planUserCard/myPlanbook.page?hd=1';
			},
			parse: parseEq
		},
		
		myCustomer: {
			name: '我的客户',
			group: '我的',
			isH5: true,
			getForwardUrl: function() {
				return bxjPath + '/bxj_web/planUserCard/myPlanbook.page?hd=1';
			},
			parse: parseEq
		},
		
		myInvite: {
			name: '邀请好友',
			group: '我的',
			isH5: true,
			getForwardUrl: function() {
				return bxjPath + '/bxj_web/myInvite/toMyInviteVoucher.page?hd=1';
			},
			parse: parseEq
		},
		
		myMessage: {
			name: '消息',
			group: '我的',
			isH5: false,
			getForwardUrl: function() {
				return 'app://myMessage';
			},
			parse: parseEq
		},
		
		friendCircle: {
			name: '朋友圈神器',
			group: '我的',
			isH5: false,
			getForwardUrl: function() {
				return 'app://friendCircle';
			},
			parse: parseEq
		}
		
	};
	
	function parseEq(url) {
		var u = this.getForwardUrl();
		if (u.startWith(url)) {
			return {
				match: true
			};
		}
	}
	
	function endWithArg(url) {
		var u = this.getForwardUrl('');
		if (url.startWith(u)) {
			var arg = url.substring(u.length);
			return {
				match: true,
				arg: arg
			};
		}
	}
	
	function betweenArg(url) {
		var starts = this.getStart();
		var ends = this.getEnd();
		if (url.startWith(starts) && url.endWith(ends)) {
			var start = this.getStart().length;
			var end = url.indexOf(this.getEnd());
			
			var arg = url.substring(start, end);
			
			return {
				match: true,
				arg: arg
			};
		}
	}
	
	function initForwardOptions(selector) {
		var e = $(selector);
		
		for (var i in forwardTypies) {
			var obj = forwardTypies[i];
			
			var target = e;
			
			var group = obj.group;
			if (group) {
				var gele = e.find('optgroup[label=' + group + ']');
				
				if (gele[0]) {
					target = gele;
				} else {
					var optgroup = $('<optgroup>').attr('label', group).appendTo(e);
					target = optgroup;
				}
			}
			
			var optHtml = obj.name;
			if (obj.isH5 == true) {
				optHtml += '（h5）';
			} else if (obj.isH5 == false) {
				optHtml += '（原生）';
			}
			
			if (obj.minVersion && obj.maxVersion) {
				optHtml += obj.minVersion + '-' + obj.maxVersion;
			} else if (obj.minVersion) {
				optHtml += obj.minVersion + '起';
			} else if (obj.maxVersion) {
				optHtml += obj.maxVersion + '以下';
			}
			
			$('<option>').val(i).html(optHtml).appendTo(target);
			
		}
		
		return e;
	}
	
	var imageSize = {
			home: {
				width: 750,
				height: 130
			},
			
			nav: {
				width: 250,
				height: 265
			},
			
			champion: {
				width: 750,
				height: 130
			},
			
			study: {
				width: 750,
				height: 250
			},
			
			championVE: {
				width: 750,
				height: 400
			},
			
			championVC: {
				width: 54,
				height: 50
			},
			friend: {
				width: 250,
				height: 265
			}
		
		};