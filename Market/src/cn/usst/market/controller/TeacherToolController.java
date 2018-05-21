package cn.usst.market.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.usst.market.po.Company;
import cn.usst.market.po.Competition;
import cn.usst.market.po.CompetitionQuarterTime;
import cn.usst.market.po.Member;
import cn.usst.market.po.StudentTextbook;
import cn.usst.market.po.Teacher;
import cn.usst.market.po.TeacherQueryVo;
import cn.usst.market.po.TeacherTextbook;
import cn.usst.market.service.CompanyService;
import cn.usst.market.service.CompetitionService;
import cn.usst.market.service.MemberService;
import cn.usst.market.service.TeacherService;

@Controller
public class TeacherToolController {
	@Autowired
	private TeacherService teacherService;
	
	@Autowired
	private CompetitionService competitionService;
	
	@Autowired
	private CompanyService companyService;
	
	@Autowired
	private MemberService memberService;
	//首先是教材的操作
	@RequestMapping("/jumpTeacherTextbook.do")
	public String jumpTeacherTextBook(Model model,Integer id) throws Exception{
		Competition competition= competitionService.findCompetitionById(id);
		model.addAttribute("competition", competition);
		return "jsp/teacherTextbook/teacherTextbook";	
	}
	
	//跳转教材列表页面
	@RequestMapping("/jumpTeacherTextbookList.do")
	public String jumpTeacherTextBookList(HttpSession session,Model model,Integer id) throws Exception{
		
		Competition competition= competitionService.findCompetitionById(id);
		/*Teacher teacher=(Teacher)session.getAttribute("teacher");*/
		int teacherId=competition.getTeacherId();
		
		//int teacherId=teacher.getId();
		List<TeacherTextbook> textbookList= teacherService.selectTextbookByCompetitionId(id);
		List<TeacherTextbook> textbookList2=teacherService.selectTextbookListByTeacherId(teacherId);
		model.addAttribute("competition", competition);
		model.addAttribute("teacherId", teacherId);
		model.addAttribute("textbookList", textbookList);
		model.addAttribute("textbookList2", textbookList2);
		
		return "jsp/teacherTextbook/teacherTextbookList";	
	}
	
	//删除教材操作
	@RequestMapping("/deleteTeacherTextbook.do")
	public String deleteTeacherTextbook(HttpServletRequest request,Model model,Integer id,Integer competitionId,Integer teacherId) throws Exception{
		TeacherTextbook teacherTextbook=teacherService.selectTeacherTextbookById(id);
		teacherService.deleteTextbookByPrimaryKey(id);
		String filePath=request.getServletContext().getRealPath("/")+"\\res\\teacherTextbook\\"+teacherId+"\\"+competitionId+"\\";
		//String filePath=teacherTextbook.getPath();
		String fileName= teacherTextbook.getName();
		File f = new File(filePath+fileName);
		f.delete();
		
		//下面这句话表示删除学生资料
		teacherService.deleteStudentTextbookByTeacherTextbookId(id);
		//return "redirect:jumpTeacherTextbookList.do";
		
		Competition competition= competitionService.findCompetitionById(competitionId);

		List<TeacherTextbook> textbookList= teacherService.selectTextbookByCompetitionId(competitionId);
		List<TeacherTextbook> textbookList2=teacherService.selectTextbookListByTeacherId(teacherId);
		model.addAttribute("competition", competition);
		model.addAttribute("teacherId", teacherId);
		model.addAttribute("textbookList", textbookList);
		model.addAttribute("textbookList2", textbookList2);
		
		return "jsp/teacherTextbook/teacherTextbookList";
		
	}
	
	//这个暂时没有用
	@RequestMapping("/teacherTextbook.do")
	public @ResponseBody List<TeacherTextbook> findTeacherTextbookList(Integer id) throws Exception{
		//int id=teacher.getId();
		List<TeacherTextbook> list=teacherService.selectTextbookByCompetitionId(id);
		
		return list;
	}
	
	//上传教材操作
	@RequestMapping("/teacherTool/uploadTeacherTextbook.do")
	public String uploadTeacherTextbook(HttpServletRequest request,Model model,Integer teacherId,Integer competitionId,MultipartFile textbook) throws Exception{
		//int tId=teacherId;
		if(textbook!=null){
			//存储文件的路径
			//String filePath="C:\\Users\\Pengju\\Desktop\\项目\\教材上传测试\\";
			String filePath=request.getServletContext().getRealPath("/")+"\\res\\teacherTextbook\\"+teacherId+"\\"+competitionId+"\\";
			//数据库路径
			System.out.println(filePath);
			String path="teacherTextbook/"+teacherId+"/"+competitionId+"/";
			//判断是否有文件夹，没有就创建
			File f = new File(filePath);
			if (!f.isDirectory()) {
				f.mkdirs();
			}
			
			//System.out.println(filePath);
			//原始文件名称
			String fileName=textbook.getOriginalFilename();
			//新文件
			File newFile= new File(filePath+fileName);
			//将内存中的数据写入磁盘
			textbook.transferTo(newFile);
			
			TeacherTextbook teacherTextbook= new TeacherTextbook();
			teacherTextbook.setName(fileName);
			teacherTextbook.setPath(path);
			
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			String uploadTime=formatter.format(new Date());
			teacherTextbook.setUploadTime(uploadTime);
			teacherTextbook.setCompetitionId(competitionId);
			
			teacherService.insertTeacherTextbook(teacherTextbook);
		}
		Competition competition = competitionService.findCompetitionById(competitionId);

		List<TeacherTextbook> textbookList=teacherService.selectTextbookByCompetitionId(competitionId);
		List<TeacherTextbook> textbookList2=teacherService.selectTextbookListByTeacherId(teacherId);
		
		model.addAttribute("teacherId", teacherId);
		model.addAttribute("competition",competition);
		model.addAttribute("textbookList", textbookList);
		model.addAttribute("textbookList2", textbookList2);
		return "jsp/teacherTextbook/teacherTextbookList";
		
	}
	
	//分享教材的操作
	@RequestMapping(value="/teacherTool/shareTextbookToCompetition.do",method=RequestMethod.POST)
	@ResponseBody
	public String shareTextbookToCompetition(Model model,Integer competitionId, Integer[] arr){
		//int id=Integer.valueOf(request.getParameter("params.CompetitionId"));
		List<Company> companyList=companyService.showCompanyByCompetitionId(competitionId);
		for(int i=0;i<companyList.size();i++){
			List<Member> memberList=memberService.showAllMemberByComapnyId(companyList.get(i).getId());
			for(int j=0;j<memberList.size();j++){
				StudentTextbook textbook= new StudentTextbook();
				//得到学生id
				textbook.setStudentId(memberList.get(j).getId());
				//得到资料id
				for(int k=0;k<arr.length;k++){
					textbook.setTeacherTextbookId(arr[k]);
					teacherService.insertStudentTextbook(textbook);
				}
			}
		
		}		
		return "SUCCESS";
	}
	
	//下面是教员工具箱中的操作
	
	@RequestMapping("/jumpTeacherTool.do")
	public String queryCompetitionSubmit(Model model,Integer id) throws Exception{
		
		Competition competition= competitionService.findCompetitionById(id);
		//List<Company> companyList= competitionService
		//通过形参中的model将model数据传到页面,相当于上面addObjeect
		model.addAttribute("competition", competition);
		
		//返回jsp页面，相当于modelAndView.setViewName
		return "jsp/teacherTool/teacherTool";	
	}
	
	//更新竞赛名称相关操作
	@RequestMapping("/teacherTool/competitionName.do")
	public String queryCompetitionName(Model model,Integer id) throws Exception{
		
		Competition competition= competitionService.findCompetitionById(id);
		//List<Company> companyList= competitionService
		//通过形参中的model将model数据传到页面,相当于上面addObjeect
		model.addAttribute("competition", competition);
		
		//返回jsp页面，相当于modelAndView.setViewName
		return "jsp/teacherTool/competitionName";	
	}
	@RequestMapping("/teacherTool/modifyCompetitionName.do")
	public @ResponseBody List<TeacherTextbook> modifyCompetitionName(Integer id,String name) throws Exception{
		//int id=teacher.getId();
		Competition competition= new Competition();
		competition.setId(id);
		competition.setName(name);
		teacherService.updateCompetitionNameByPrimaryKey(competition);
		
		return null;
	}
	//竞赛处理日程
	@RequestMapping("/teacherTool/competitionDealDate.do")
	public String competitionDealDate(Model model,Integer id) throws Exception{
		
		Competition competition= competitionService.findCompetitionById(id);
		List<CompetitionQuarterTime> competitionQuarterTimeList=teacherService.findQuarterTimeByCompetitionId(id); 
		//通过形参中的model将model数据传到页面,相当于上面addObjeect
		model.addAttribute("competition", competition);
		model.addAttribute("competitionQuarterTimeList",competitionQuarterTimeList);
		//返回jsp页面，相当于modelAndView.setViewName
		return "jsp/teacherTool/competitionDealDate";	
	}
	//锁定竞赛
	@RequestMapping(value="/teacherTool/CompetitionLock.do",method=RequestMethod.POST)
	@ResponseBody
	public String CompetitionLock(Model model,Integer competitionId){
		Competition competition= new Competition();
		int flag=1;
		competition.setId(competitionId);
		competition.setIsLock(flag);
		teacherService.updateCompetitionIsLockByPrimaryKey(competition);
		return "success";
	}
	
	//解锁竞赛
	@RequestMapping(value="/teacherTool/CompetitionUnLock.do",method=RequestMethod.POST)
	@ResponseBody
	public String CompetitionUnLock(Model model,Integer competitionId){
		Competition competition= new Competition();
		int flag=0;
		competition.setId(competitionId);
		competition.setIsLock(flag);
		teacherService.updateCompetitionIsLockByPrimaryKey(competition);
		return "success";
	}
	
	//设置可以过期提交
	@RequestMapping(value="/teacherTool/CompetitionAllowOverdueSubmit.do",method=RequestMethod.POST)
	@ResponseBody
	public String CompetitionAllowOverdueSubmit(Model model,Integer competitionId){
		CompetitionQuarterTime competitionQuarterTime= new CompetitionQuarterTime();
		String flag="true";
		competitionQuarterTime.setCompetitionId(competitionId);
		competitionQuarterTime.setIsAllowOverdueSubmit(flag);
		teacherService.updateAllowSubmitByCompetitionId(competitionQuarterTime);
		return "success";
	}
	//设置不可以过期提交
	@RequestMapping(value="/teacherTool/CompetitionNotAllowOverdueSubmit.do",method=RequestMethod.POST)
	@ResponseBody
	public String CompetitionNotAllowOverdueSubmit(Model model,Integer competitionId){
		CompetitionQuarterTime competitionQuarterTime= new CompetitionQuarterTime();
		String flag="false";
		competitionQuarterTime.setCompetitionId(competitionId);
		competitionQuarterTime.setIsAllowOverdueSubmit(flag);
		teacherService.updateAllowSubmitByCompetitionId(competitionQuarterTime);
		return "success";
	}
	
	//每支队伍参赛人数相关操作
	@RequestMapping("/teacherTool/competitionTeamMemberNumber.do")
	public String queryCompetitionTeamMemberNumber(Model model,Integer id) throws Exception{
		
		Competition competition= competitionService.findCompetitionById(id);
		List<TeacherQueryVo> companyList=teacherService.findCompanyListByCompetitionId(id);
		//List<Company> companyList= competitionService
		//通过形参中的model将model数据传到页面,相当于上面addObjeect
		model.addAttribute("competition", competition);
		model.addAttribute("companyList", companyList);
		
		//返回jsp页面，相当于modelAndView.setViewName
		return "jsp/teacherTool/competitionTeamMemberNumber";	
	}
	
	@RequestMapping("/teacherTool/modifyTeamNumber.do")
	public @ResponseBody List<TeacherQueryVo> modifyTeamNumber(Integer id,Integer teamNumber) throws Exception{
		//int id=teacher.getId();
		Competition competition= new Competition();
		competition.setId(id);
		competition.setMember(teamNumber);
		teacherService.updateCompetitionMemberByPrimaryKey(competition);
		
		Company company=new Company();
		company.setPeopleNumber(teamNumber);
		company.setCompetitionId(id);
		teacherService.updateCompanyPeopleNumberByCompetitionId(company);
		return null;
	}
	
	
	//队员注册说明
	@RequestMapping("/teacherTool/studentRegisterNotes.do")
	public String queryStudentRegisterNotes(Model model,Integer id) throws Exception{
		
		Competition competition= competitionService.findCompetitionById(id);
		List<Company> companyList=companyService.showCompanyByCompetitionId(id);
		//List<Company> companyList= competitionService
		//通过形参中的model将model数据传到页面,相当于上面addObjeect
		model.addAttribute("competition", competition);
		model.addAttribute("companyList", companyList);
		
		//返回jsp页面，相当于modelAndView.setViewName
		return "jsp/teacherTool/studentRegisterNotes";	
	}
	
	//批次注册学员
	
	//跳转移动参赛队员
	@RequestMapping("/teacherTool/studentMove.do")
	public String studentMove(Model model,Integer id) throws Exception{
		
		Competition competition= competitionService.findCompetitionById(id);
		List<TeacherQueryVo> companyAndMemberList=teacherService.findCompanyAndMemberListByCompetitionId(id);
		//List<Company> companyList= competitionService
		//通过形参中的model将model数据传到页面,相当于上面addObjeect
		model.addAttribute("competition", competition);
		model.addAttribute("companyAndMemberList", companyAndMemberList);
		
		//返回jsp页面，相当于modelAndView.setViewName
		return "jsp/teacherTool/studentMove";	
	}
	//跳转到编辑移动参赛队员界面
	@RequestMapping("/teacherTool/studentMoveEdit.do")
	public String studentMoveEdit(Model model,String memberEmail,Integer competitionId) throws Exception{
		Member mem=new Member();
		mem.setEmail(memberEmail);
		
		Member member=memberService.findMeberByEmail(mem);
		Competition competition= competitionService.findCompetitionById(competitionId);
		List<TeacherQueryVo> companyList=teacherService.findCompanyListByCompetitionId(competitionId);
		//List<TeacherQueryVo> companyAndMemberList=teacherService.findCompanyAndMemberListByCompetitionId(competitionId);
		
		model.addAttribute("member", member);
		model.addAttribute("competition", competition);
		model.addAttribute("companyList", companyList);
		
		//返回jsp页面，相当于modelAndView.setViewName
		return "jsp/teacherTool/studentMoveEdit";	
	}
	//移动队员提交
	@RequestMapping("/teacherTool/studentMoveSubmit.do")
	public String studentMoveSubmit(Model model,Integer memberId,Integer companyId,Integer competitionId) throws Exception{
		Member member= new Member();
		member.setId(memberId);
		member.setCompanyId(companyId);
		
		teacherService.moveMemberToCompanyById(member);
		Competition competition= competitionService.findCompetitionById(competitionId);

		List<TeacherQueryVo> companyAndMemberList=teacherService.findCompanyAndMemberListByCompetitionId(competitionId);
		model.addAttribute("competition", competition);
		model.addAttribute("companyAndMemberList", companyAndMemberList);
		
		return "jsp/teacherTool/studentMove";
		
	}
	
	
	//删除参赛队员
	@RequestMapping("/teacherTool/deleteMember.do")
	public String deleteMemberById(Model model,Integer memberId,Integer competitionId) throws Exception{
		teacherService.deleteMemberById(memberId);
		Competition competition= competitionService.findCompetitionById(competitionId);
		List<TeacherQueryVo> companyAndMemberList=teacherService.findCompanyAndMemberListByCompetitionId(competitionId);

		model.addAttribute("competition", competition);
		model.addAttribute("companyAndMemberList", companyAndMemberList);
		
		return "jsp/teacherTool/studentMove";
	}	
	
	//跳转学员注册权限
	@RequestMapping("/teacherTool/studentRegistryRight.do")
	public String studentRegistryRight(Model model,Integer id) throws Exception{
		
		Competition competition= competitionService.findCompetitionById(id);
		List<TeacherQueryVo> companyAndMemberList=teacherService.findCompanyAndMemberListByCompetitionId(id);
		//List<Company> companyList= competitionService
		//通过形参中的model将model数据传到页面,相当于上面addObjeect
		model.addAttribute("competition", competition);
		model.addAttribute("companyAndMemberList", companyAndMemberList);
		
		//返回jsp页面，相当于modelAndView.setViewName
		return "jsp/teacherTool/studentRegistryRight";	
	}
	
	
	//跳转教学质量评估界面
	@RequestMapping("/jumpTeachingEvaluation.do")
	public String jumpTeachingEvaluation(Model model,Integer id) throws Exception{
		
		Competition competition= competitionService.findCompetitionById(id);
		//List<Company> companyList= competitionService
		//通过形参中的model将model数据传到页面,相当于上面addObjeect
		model.addAttribute("competition", competition);
		
		//返回jsp页面，相当于modelAndView.setViewName
		return "jsp/teachingEvaluation/teachingEvaluation";	
	}
	
	//评估准则
	@RequestMapping("/teachingEvaluation/evaluationRule.do")
	public String evaluationRule(Model model,Integer id) throws Exception{
		
		Competition competition= competitionService.findCompetitionById(id);
		//List<Company> companyList= competitionService
		//通过形参中的model将model数据传到页面,相当于上面addObjeect
		model.addAttribute("competition", competition);
		
		//返回jsp页面，相当于modelAndView.setViewName
		return "jsp/teachingEvaluation/evaluationRule";	
	}
	
	//自定义教学质量评估
	@RequestMapping("/teachingEvaluation/evaluationRuleCustom.do")
	public String evaluationRuleCustom(Model model,Integer id) throws Exception{
		
		Competition competition= competitionService.findCompetitionById(id);
		//List<Company> companyList= competitionService
		//通过形参中的model将model数据传到页面,相当于上面addObjeect
		model.addAttribute("competition", competition);
		
		//返回jsp页面，相当于modelAndView.setViewName
		return "jsp/teachingEvaluation/evaluationRuleCustom";	
	}
	
	
	
}
