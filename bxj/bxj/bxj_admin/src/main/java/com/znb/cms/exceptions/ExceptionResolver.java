package com.znb.cms.exceptions;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.znb.cms.common.AjaxResult;

/**
 * 异常处理器 SpringMVC 调度。
 *
 * @author 
 */
public class ExceptionResolver implements HandlerExceptionResolver {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionResolver.class);

    private String unknowExceptionTipMessage = "系统繁忙，请稍后重试";

    private HttpMessageConverter<Object> httpMessageConverter;

    /**
     * 设置【未知异常】的提示信息，非 {@link BaseException}
     *
     * @param unknowExceptionTipMessage
     */
    public void setUnknowExceptionTipMessage(String unknowExceptionTipMessage) {
        unknowExceptionTipMessage = this.unknowExceptionTipMessage;
    }

    public void setHttpMessageConverter(HttpMessageConverter<Object> httpMessageConverter) {
        this.httpMessageConverter = httpMessageConverter;
    }

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        // 获取方法上的 responsebody 注解
        ResponseBody responseBody = method.getAnnotation(ResponseBody.class);
        // BusinessException 为业务异常，程序主动抛出。
        // 比如用户漏填某些必须字段，上传图片不是图片类型等。
        // 这类异常，将异常里的提示直接显示给用户
        if (ex instanceof BusinessException) {
            logger.error("业务异常--------异常编码：{},异常原因：{}", ((BusinessException) ex).getCode(), ex.getMessage());
            if (responseBody == null) {
                return view(request, ((BusinessException) ex).getCode(), ex.getMessage());
            } else {
                return json(response, ((BusinessException) ex).getCode(), ex.getMessage());
            }
        } else if (ex instanceof SystemException) {
            logger.error("系统异常--------异常编码：{},异常原因：{}", ((BusinessException) ex).getCode(), ex.getMessage());
            if (responseBody == null) {
                return view(request, ((BusinessException) ex).getCode(), ex.getMessage());
            } else {
                return json(response, ((BusinessException) ex).getCode(), ex.getMessage());
            }
        } else {
            // 其他异常非程序主动抛出，如 SQL 异常，网络异常。
            // 这类异常统一使用一种提示信息，默认：系统异常，请稍后重试。
            logger.error("程序未捕获异常：", ex);
            if (responseBody == null) {
                return view(request, "SA1009000001", unknowExceptionTipMessage);
            } else {
                return json(response, "SA1009000001", unknowExceptionTipMessage);
            }
        }
    }

    private ModelAndView view(HttpServletRequest request, String code, Object message) {
        request.setAttribute("code", code);
        request.setAttribute("error", message);
        return new ModelAndView("error");
    }

    private ModelAndView json(HttpServletResponse response, String code, String message) {
        AjaxResult result = new AjaxResult.error(code, message);
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
