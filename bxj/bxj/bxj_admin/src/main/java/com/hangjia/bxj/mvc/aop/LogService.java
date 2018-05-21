package com.hangjia.bxj.mvc.aop;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.hangjia.bxj.dao.log.SysLogMapper;
import com.hangjia.bxj.model.LoginUser;
import com.hangjia.bxj.model.log.SysLogDO;
import com.hangjia.bxj.mvc.aop.annotation.MethodLog;
import com.hangjia.bxj.mvc.util.AdminWebUtil;

/**
 * 
 * @Aspect 实现spring aop 切面（Aspect）：
 * @author yaoy
 * 
 */
@Component
@Aspect
public class LogService {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private SysLogMapper sysLogDao;

	public LogService() {
		logger.debug("aop");
	}

	/**
	 * 
	 * @param point
	 * @throws Throwable
	 */

	@Pointcut("@annotation(com.hangjia.bxj.mvc.aop.annotation.MethodLog)")
	public void methodCachePointcut() {

	}

	@Around("methodCachePointcut()")
	public Object around(ProceedingJoinPoint point) throws Throwable {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss E");
		Calendar ca = Calendar.getInstance();
		String operDate = df.format(ca.getTime());
		String ip = AdminWebUtil.getIpAddress(request);
		LoginUser loginUser = AdminWebUtil.getLoginUser(request);
		String loginName;
		String name;
		if (loginUser != null) {
			loginName = loginUser.getUserName();
			// name = user.name;
		} else {
			loginName = "匿名用户";
			// name = "匿名用户";
		}

		String monthRemark = getMthodRemark(point);
		String monthName = point.getSignature().getName();
		String packages = point.getThis().getClass().getName();
		if (packages.indexOf("$$EnhancerByCGLIB$$") > -1) { // 如果是CGLIB动态生成的类
			try {
				packages = packages.substring(0, packages.indexOf("$$"));
			} catch (Exception ex) {
				logger.error(ex.getMessage());
			}
		}

		String operatingcontent = "";
		Object[] method_param = null;

		Object object = null;
		try {
			method_param = point.getArgs();
			object = point.proceed();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		SysLogDO sysLogDO = new SysLogDO();
		sysLogDO.setOperationIp(ip);
		sysLogDO.setOperationName(loginName);
		sysLogDO.setOperationMethod(packages + "." + monthName);
		sysLogDO.setOperationRemark(monthRemark);
		
		sysLogDao.addLog(sysLogDO);
		return object;
	}

	/**
	 * 方法运行出现异常时调用	
	 * @param ex
	 * */
	@AfterThrowing(value="methodCachePointcut()",throwing="e") 
	public void afterThrowing(Exception e) {
		logger.error(e.getMessage());
	}

	/**
	 * 获取方法的中文备注____用于记录用户的操作日志描述
	 * @param joinPoint
	 * */ 
	private String getMthodRemark(ProceedingJoinPoint joinPoint)
			throws Exception {
		String targetName = joinPoint.getTarget().getClass().getName();
		String methodName = joinPoint.getSignature().getName();
		Object[] arguments = joinPoint.getArgs();

		Class targetClass = Class.forName(targetName);
		Method[] method = targetClass.getMethods();
		String methode = "";
		for (Method m : method) {
			if (m.getName().equals(methodName)) {
				Class[] tmpCs = m.getParameterTypes();
				if (tmpCs.length == arguments.length) {
					MethodLog methodCache = m.getAnnotation(MethodLog.class);
					if (methodCache != null) {
						methode = methodCache.remark();
					}
					break;
				}
			}
		}
		return methode;
	}


}

