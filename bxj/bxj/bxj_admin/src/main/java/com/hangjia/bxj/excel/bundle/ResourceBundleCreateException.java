package com.hangjia.bxj.excel.bundle;

import java.text.MessageFormat;

public class ResourceBundleCreateException extends Exception {
    private static final long serialVersionUID = 3258132457613177654L;

    public ResourceBundleCreateException(String messageId, Object[] params, Throwable cause) {
        super(MessageFormat.format(messageId, params == null?new Object[0]:params), cause);
    }
}
