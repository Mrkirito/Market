package com.hangjia.invite;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.hangjia.bxj.model.Invitation;
import com.hangjia.bxj.model.InvitationCopy;
import com.hangjia.bxj.vo.Pagination;
import com.hangjia.champion.base.TestBaseService;
import com.hangjia.invite.service.InviteService;

public class InviteServiceTest extends TestBaseService {
	@Autowired
	private InviteService inviteService;
	DateFormat date_format = new SimpleDateFormat("yyyy-MM-dd hh:mm");
	DateFormat DATE_FORMAT_HM = new SimpleDateFormat("yyyy-MM-dd hh:mm");
	@Test
	public void test(){
		 
	}

	@Test
	public void testSave() {
		Invitation invitation = new Invitation();
		invitation.setUserId(4);
		invitation.setName("邀请名称");
		try {
			Date d = date_format.parse("2016-04-23 14:10");
			invitation.setDateAt(d);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		invitation.setAddress("地址邀请");
		invitation.setContactName("联系人:33");
		invitation.setContactMobile("13612458897");
		invitation.setText("参加活动");
		// createAt
		invitation.setLng(121.3d);
		invitation.setLat(30.17d);
		// invitation.setModelType(1); //模板 类型
		invitation.setGuestName("宾客名称yi");
		int num=inviteService.isExist(invitation);
		if(num==0){
			Invitation invite = inviteService.save(invitation);
			System.out.println(JSON.toJSONString(invite));
			System.out.println("添加成功" + num);
		}else if(num == 1) {
			System.out.println("添加失败 已经存在" + num);
		} 
		
	}

	@Test
	public void testUpdate() {
		Invitation invitation = new Invitation();
		invitation.setId(168L);
		invitation.setUserId(4);
		invitation.setName("邀请名称改1");
		try {
			Date d = date_format.parse("2016-04-23 10:10");
			invitation.setDateAt(d);// "2016-04-23 10:10:58"
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		invitation.setAddress("广东省广州市番禺区番禺大道1号长隆酒店2楼宴会厅");
		invitation.setContactName("联系人-历史");
		invitation.setContactMobile("13612455678");
		invitation.setText("参加活动");
		// createAt
		invitation.setLng(1313d);
		invitation.setLat(1717d);
		/*
		 * invitation.setLng(121.3d); invitation.setLat(30.17d);
		 */
		invitation.setGuestName("宾客名称yi");

		int num = inviteService.update(invitation);
		if (num == 1) {
			System.out.println("修改成功" + num);
		} else {
			System.out.println("修改失败" + num);
		}

	}

	@Test
	public void testSelect() {

		Pagination<Invitation> pageInfo = inviteService.listpage(179433, 1, 1, 10,"id"); // 页码
																					// 条数
		List<Invitation> list = (List<Invitation>) pageInfo.getRows();
		System.out.println("查询返回个数：" + list.size());
		int i = 1;
		for (Invitation invitation : list) {
			System.out.println("返回对象：" + (i++) + "个。" + JSON.toJSONString(invitation));
		}

	}

	@Test
	public void getOneInvite() {

		// 根据 copyid 从表的id， 获取 分享
		InvitationCopy invicopy = inviteService.selInviteShareByID(1);
		System.out.println(invicopy.getDateAtCN());

		System.out.println(invicopy.getDateAt());

		System.out.println(JSON.toJSONString(invicopy));
	}

	@Test
	public void getInvicopyFid() {
		// 根据 相关内容 查询从表,得到id
		Invitation invitation = new Invitation();
		invitation.setId(169L);
		invitation.setUserId(4);
		invitation.setName("邀请名称");
		try {
			Date d = date_format.parse("2016-04-23 14:10");
			invitation.setDateAt(d);// "2016-04-23 10:10:58"
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		invitation.setAddress("地址邀请");
		invitation.setContactName("联系人:33");
		invitation.setContactMobile("13612458897");
		invitation.setText("参加活动");
		// createAt
		invitation.setGuestName("宾客名称yi");
		InvitationCopy invicopy = inviteService.getInvitation(invitation);

		System.out.println(JSON.toJSONString(invicopy));
	}
}
