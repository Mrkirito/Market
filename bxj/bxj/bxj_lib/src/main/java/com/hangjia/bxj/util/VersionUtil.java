package com.hangjia.bxj.util;

/**
 * 版本工具类
 * Created by bei.zhang on 2016/11/14.
 */
public class VersionUtil {

    public static Double getVersion(String os, int vCode) {
        Double version = null;
        if ("android".equals(os) && vCode == 11 || "iOS".equals(os) && vCode <= 104)
            version = 3.0;
        else if (("android".equals(os) && vCode >= 12 && vCode < 18) || ("iOS".equals(os) && vCode >= 105) && vCode < 120)
            version = 3.1;
        else if ("android".equals(os) && vCode == 18 || "iOS".equals(os) && vCode == 120)
            version = 3.2;
        else if ("android".equals(os) && vCode == 19 ||  ("iOS".equals(os) && vCode >= 121) && vCode < 123)
            version = 3.3;
        else if ("android".equals(os) && vCode >= 20 || "iOS".equals(os) && vCode >= 123)
            version = 3.4;
        return version;
    }
}
