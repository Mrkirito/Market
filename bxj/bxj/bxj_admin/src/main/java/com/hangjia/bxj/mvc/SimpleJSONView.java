package com.hangjia.bxj.mvc;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.Map;

public class SimpleJSONView extends AbstractView {
    public static final String DEFAULT_CONTENT_TYPE = "application/json";
    public final static Charset UTF8    = Charset.forName("UTF-8");
    private String renderedAttribute;
    public void setRenderedAttribute(String renderedAttribute) {
        this.renderedAttribute = renderedAttribute;
    }


    public SimpleJSONView() {
        setContentType(DEFAULT_CONTENT_TYPE);
    }

    @Override
    protected void renderMergedOutputModel(Map<String, Object> model,
                                           HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        if (StringUtils.isBlank(renderedAttribute)){
            throw new Exception("renderedAttribute is null");
        }

        Object value = model.get(renderedAttribute);
        if (value == null){
            value = StringUtils.EMPTY;
        }

        byte[] bytes = JSON.toJSONBytes(value);;

        OutputStream stream = response.getOutputStream();
        stream.write(bytes);
    }

    @Override
    protected void prepareResponse(HttpServletRequest request,
                                   HttpServletResponse response) {
        response.setContentType(request.getContentType());
        response.setCharacterEncoding(UTF8.name());
        response.addHeader("Pragma", "no-cache");
        response.addHeader("Cache-Control", "no-cache, no-store, max-age=0");
        response.addDateHeader("Expires", 1L);
    }

}
