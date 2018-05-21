package cn.usst.market.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.usst.market.po.HelpDocument;
import cn.usst.market.service.TeacherService;

@Controller
public class HelpDocumentController {
	@Autowired
	private TeacherService teacherService;
	
	@RequestMapping("/selectAllHelpDocument.do")
	public @ResponseBody List<HelpDocument> selectAllHelpDocument(){
		List<HelpDocument> helpDocList = new ArrayList<HelpDocument>();
		int titleOneCount = teacherService.getTitleLevelOneCount();
		helpDocList = teacherService.selectAllHelpDocument();
		helpDocList.get(0).setSize(titleOneCount);
		return helpDocList;
	}
	
	@RequestMapping("/selectSingleItem.do")
	public @ResponseBody HelpDocument selectHelpDocument(String titleOne, String titleTwo){
		HelpDocument helpDocument = new HelpDocument();
		helpDocument.setTitleLevelOne(titleOne);
		helpDocument.setTitleLevelTwo(titleTwo);
		helpDocument = teacherService.selectHelpDocument(helpDocument);
		return helpDocument;
	}
}
