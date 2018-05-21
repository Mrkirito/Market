package com.hangjia.bxj.exception;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.hangjia.bxj.BXJException;
import com.hangjia.bxj.mvc.AjaxResult;

/**
 * 异常处理器，
 * 由 SpringMVC 调度。
 * @author K9999
 *
 */
public class ExceptionResolver implements HandlerExceptionResolver {
	
	private static final Log log = LogFactory.getLog(ExceptionResolver.class);
	
	private String unknowExceptionTipMessage = "系统繁忙，请稍后重试";
	
	private HttpMessageConverter<Object> httpMessageConverter;
	
	/**
	 * 设置【未知异常】的提示信息，非 {@link BXJException} 
	 * @param unknowExceptionTipMessage
	 */
	public void setUnknowExceptionTipMessage(String unknowExceptionTipMessage) {
		unknowExceptionTipMessage = this.unknowExceptionTipMessage;
	}
	
	public void setHttpMessageConverter(HttpMessageConverter<Object> httpMessageConverter) {
		this.httpMessageConverter = httpMessageConverter;
	}

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e) {
		
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		Method method = handlerMethod.getMethod();
		
		// 获取方法上的 responsebody 注解
		ResponseBody responseBody = method.getAnnotation(ResponseBody.class);
		
		// BXJException 是系统已知的异常，为程序主动抛出。
		// 比如用户漏填某些必须字段，上传图片不是图片类型等。
		// 这类异常，将异常里的提示直接显示给用户
		if (e instanceof BXJException) {
			
			if (log.isInfoEnabled()) {
				log.info("业务异常：" + e.getMessage());
			}
			
			if (responseBody == null) {
				return view(request, e.getMessage());
			} else {
				
				return json(response, e.getMessage());
			}
			
		}
		
		// 其他异常非程序主动抛出，如 SQL 异常，网络异常。
		// 这类异常统一使用一种提示信息，默认：系统异常，请稍后重试。
		else {
			
			log.error("系统异常：", e);
			
			if (responseBody == null) {
				return view(request, unknowExceptionTipMessage);
			} else {
				return json(response, unknowExceptionTipMessage);
			}
		}
		
	}
	
	private ModelAndView view(HttpServletRequest request, Object message) {
		request.setAttribute("msg", message);
		return new ModelAndView("error");
	}

	private ModelAndView json(HttpServletResponse response, String message) {
		AjaxResult result = new AjaxResult.error(message);
		writeJson(response, result);
		return new ModelAndView();
	}
	
	private void writeJson(HttpServletResponse response, Object message) {
		HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
		try {
			httpMessageConverter.write(message, null, outputMessage);
		} catch (IOException e) {
		}
	}
	
}
