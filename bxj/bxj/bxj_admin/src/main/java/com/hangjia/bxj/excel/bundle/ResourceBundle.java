package com.hangjia.bxj.excel.bundle;

import java.text.MessageFormat;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public abstract class ResourceBundle extends java.util.ResourceBundle {
    private String baseName;
    private Locale locale;

    public ResourceBundle() {
    }

    public String getBaseName() {
        return this.baseName;
    }

    public Locale getLocale() {
        return this.locale;
    }

    public final Map getMap(String key) {
        return (Map)this.getObject(key);
    }

    public final List getList(String key) {
        return (List)this.getObject(key);
    }

    protected final void setBaseName(String baseName) {
        this.baseName = baseName;
    }

    protected final void setLocale(Locale locale) {
        this.locale = locale;
    }

    protected final void setLocale(String baseName, String bundleName) {
        if(baseName.length() == bundleName.length()) {
            this.locale = new Locale("", "");
        } else {
            if(baseName.length() >= bundleName.length()) {
                throw new IllegalArgumentException(MessageFormat.format("The basename \"{0}\" is longer than the bundle name \"{1}\"", new Object[]{baseName, bundleName}));
            }

            int pos = baseName.length();
            String temp = bundleName.substring(pos + 1);
            pos = temp.indexOf(95);
            if(pos == -1) {
                this.locale = new Locale(temp, "", "");
                return;
            }

            String language = temp.substring(0, pos);
            temp = temp.substring(pos + 1);
            pos = temp.indexOf(95);
            if(pos == -1) {
                this.locale = new Locale(language, temp, "");
                return;
            }

            String country = temp.substring(0, pos);
            temp = temp.substring(pos + 1);
            this.locale = new Locale(language, country, temp);
        }

    }

    protected final void setParent(ResourceBundle parent) {
        this.parent = parent;
    }

    protected final java.util.ResourceBundle getParent() {
        return this.parent;
    }
}
