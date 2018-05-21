package cn.usst.market.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.usst.market.annotation.MethodLog;
import cn.usst.market.po.Company;
import cn.usst.market.po.CompanyDuty;
import cn.usst.market.po.CompanyQuarterTime;
import cn.usst.market.po.Competition;
import cn.usst.market.po.Member;
import cn.usst.market.po.Page;
import cn.usst.market.po.Teacher;
import cn.usst.market.po.TeacherQueryVo;
import cn.usst.market.service.CompanyService;
import cn.usst.market.service.CompetitionService;
import cn.usst.market.service.MemberService;
import cn.usst.market.service.TeacherService;


@Controller
public class UserController {

	@Autowired
	private TeacherService teacherService;
	@Autowired
	private MemberService memberService;
	@Autowired
	private CompanyService companyService;
	@Autowired
	private CompetitionService competitionService;
	
	
//	@RequestMapping("/queryItems.action")
//	public ModelAndView queryItems()throws Exception{
//		System.out.println("success");
//		List<ItemsCustom> itemsList = itemsService.findItemsList(null);
//		ModelAndView modelAndView =  new ModelAndView();
//		modelAndView.addObject("itemsList", itemsList);	
//		modelAndView.setViewName("items/itemsList");	
//		return modelAndView;
//	}
	
	/*@RequestMapping("/queryUser.do")
	public ModelAndView queryUser()throws Exception{
		ModelAndView modelAndView =  new ModelAndView();
		User user=userService.findUserById(7);
		System.out.println(user);
		modelAndView.addObject("user", user);	
		modelAndView.setViewName("user/showUser");
		return modelAndView;
	}
	*/
	@RequestMapping("/Login1.do")
	public ModelAndView doLogin1(HttpServletRequest request) throws Exception{
		String role=request.getParameter("role");
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		System.out.println(role);
		System.out.println(email);
		System.out.println(password);
		ModelAndView modelAndView = new ModelAndView();
		Teacher record =new Teacher();
		Teacher flag=null;
		record.setEmail(email);
		record.setPassword(password);
		flag=teacherService.doTeacherLogin(record);
		if(flag!=null){
			modelAndView.addObject("teacher", flag);
			modelAndView.setViewName("teacher_index");
		}else{
			modelAndView.setViewName("login");
		}
		return modelAndView;
	}
	@MethodLog(description="学生登录操作")
	@RequestMapping("/Login.do")
	public ModelAndView doLogin(HttpServletRequest request) throws Exception{
		String role=request.getParameter("role");
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		ModelAndView modelAndView = new ModelAndView();
		if(role.equals("member")){//角色是学员
			Member record =new Member();
			Member flag=null;
			record.setEmail(email);
			record.setPassword(password);
			flag=memberService.doMemberLogin(record);
			request.getSession().setAttribute("student", flag);
			if(flag!=null){
				int companyId=flag.getCompanyId();
				
				//根据公司id 查询竞赛当前季度
				int currentQuarter=competitionService.getCurrentQuarterByCompanyId(companyId);
	
				Competition competition=competitionService.findCompetitionByCompanyId(companyId);
				
				CompanyQuarterTime cqt=competitionService.findOneByCompetitionIdQuarter(companyId,currentQuarter);
				int isSubmit=cqt.getIsSubmit();
				System.out.println(isSubmit);
				request.getSession().setAttribute("isSubmit", isSubmit);
				System.out.println(request.getSession().getAttribute("isSubmit"));
				request.getSession().setAttribute("competitionId", competition.getId());
				
				request.getSession().setAttribute("companyId", companyId);
				request.getSession().setAttribute("currentQuarter", currentQuarter);
				
				modelAndView.addObject("competition",competition);
				modelAndView.addObject("member", flag);
				modelAndView.addObject("quarter",currentQuarter);
				modelAndView.setViewName("index1");
				return modelAndView;
			}else{
				modelAndView.addObject("errorInfo", "邮箱或密码错误！");
				modelAndView.setViewName("login");
				return modelAndView;
			}
		}else{//角色是教师
			Teacher record =new Teacher();
			Teacher flag=null;
			record.setEmail(email);
			record.setPassword(password);
			flag=teacherService.doTeacherLogin(record);
			request.getSession().setAttribute("teacher", flag);
			if(flag!=null){
				modelAndView.addObject("teacher", flag);
				modelAndView.setViewName("teacher_index");
				return modelAndView;
			}else{
				modelAndView.addObject("errorInfo", "邮箱或密码错误！");
				modelAndView.setViewName("login");
				return modelAndView;
			}
		}
	}

	@RequestMapping("/teacherRegister.do")
	public ModelAndView teacherRegister(HttpServletRequest request,Teacher record) throws Exception{
		record.setStatus("0");
		teacherService.insert(record);
		ModelAndView modelAndView = new ModelAndView();		
		modelAndView.setViewName("login");
		return modelAndView;
		
		
	}
	@MethodLog(description="学生注册操作")
	@RequestMapping("/memberRegister.do")
	public ModelAndView doRegister(HttpServletRequest request,Member record) throws Exception{
		int teamOrder=Integer.valueOf(request.getParameter("team_order"));
		String license1=request.getParameter("license1");
		String license2=request.getParameter("license2");
		System.out.println(teamOrder);
		System.out.println(license1);
		System.out.println(license2);
		System.out.println(record.getName());
		System.out.println(record.getEmail());
		System.out.println(record.getPassword());
		
		Competition competition =new Competition();
		competition.setLicense(license1);
		
		Company company=new Company();
		company.setSerialNumber(teamOrder);;
		company.setLicense(license2);
		
		//检测竞赛许可证号是否存在
		Competition compe=null;
		compe=competitionService.findCompetitionByLicense(competition);
		//检测团队许可证号是否存在
		Company compa=null;
		compa=companyService.findCompanyByCondition(company);
		
		ModelAndView modelAndView = new ModelAndView();
		
		String info="";
		
		if(compe!=null){
			System.out.println(compe);
			if(compa!=null){
				System.out.println(compa);
				int companyId=compa.getId();
				//限制学员数量
				int memberNum=compa.getPeopleNumber();
				
				//检测邮箱是否存在 findMeberByEmail
				
				record.setCompanyId(companyId);
				
				Member flag=null;
				flag=memberService.findMeberByEmail(record);
				if(flag==null){
					memberService.insert(record);
					CompanyDuty cd=new CompanyDuty();
					cd.setCompanyId(companyId);
					cd.setImg("img");
					cd.setMemberId(record.getId());
					cd.setMainId(1);
					cd.setMinorId(1);
					companyService.insertCompanyDuty(cd);
					info="注册成功！";
					modelAndView.addObject("errorInfo", info);
				}
				else{
					info="用户名已存在！";
					modelAndView.addObject("errorInfo", info);
				}	
			}else{
				//无效的团队许可证号
				info="无效的团队许可证号";
				modelAndView.addObject("errorInfo", info);
			}
		}else{
			//无效的竞赛许可证号
			info="无效的竞赛许可证号";
			modelAndView.addObject("errorInfo", info);
		}
		modelAndView.setViewName("login");
		return modelAndView;
	}

	@RequestMapping(value="/updateTeacherById.do" )
	public ModelAndView updateTeacherById(HttpServletRequest request,Teacher record) throws Exception{
		teacherService.updateByPrimaryKeySelective(record);
		ModelAndView modelAndView =  new ModelAndView();
		modelAndView.setViewName("teacher_index");	
		return modelAndView;
	}
	
	@RequestMapping("/queryMemberSubmit.do")
	public String queryMemberSubmit(HttpServletRequest request,Model model,TeacherQueryVo teacherQueryVo) throws Exception{
		
		Member member= teacherQueryVo.getMember();
		Company company= teacherQueryVo.getCompany();
		Competition competition= teacherQueryVo.getCompetition();
		
		Teacher record=(Teacher) request.getSession().getAttribute("teacher");
		teacherQueryVo.setTeacher(record);
		List<TeacherQueryVo> memberList = memberService.findMemberList(teacherQueryVo);
		//通过形参中的model将model数据传到页面,相当于上面addObjeect
		model.addAttribute("member", member);
		model.addAttribute("company", company);
		model.addAttribute("competition", competition);
		model.addAttribute("memberList", memberList);
		
		//返回jsp页面，相当于modelAndView.setViewName
		return "jsp/teacherQuery/queryMember";
		
	}
	
	@RequestMapping("/queryMemberByPage.do")
	public ModelAndView queryMemberByPage(HttpServletRequest request,TeacherQueryVo teacherQueryVo) throws Exception{
		String pageNow=request.getParameter("pageNow");
		Page page = null;
		List<TeacherQueryVo> memberList =  new ArrayList<TeacherQueryVo>();
		Teacher record=(Teacher) request.getSession().getAttribute("teacher");
		teacherQueryVo.setTeacher(record);
	    int totalCount = (int) memberService.getMemberCount(teacherQueryVo);
	    System.out.println(totalCount);    
	    
	    Member member= teacherQueryVo.getMember();
	    if (pageNow != null) {  
	        page = new Page(totalCount, Integer.parseInt(pageNow));
	        
	    } else {  
	        page = new Page(totalCount, 1); 
	        }  
	    teacherQueryVo.setStartPos(page.getStartPos());
	    teacherQueryVo.setPageSize(page.getPageSize());
	    memberList = memberService.selectMemberByPage(teacherQueryVo);
	    
	    ModelAndView modelAndView =  new ModelAndView();
	    modelAndView.addObject("page",page);
	    modelAndView.addObject("memberList", memberList);
	    modelAndView.addObject("member", member);
		modelAndView.setViewName("jsp/teacherQuery/queryMember");
		System.out.println("队员："+memberList);
		return modelAndView;
	}

}
