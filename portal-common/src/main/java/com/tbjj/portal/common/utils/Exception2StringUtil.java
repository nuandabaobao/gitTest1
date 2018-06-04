package com.tbjj.portal.common.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by ChengZhenxing on 2017/5/23.
 */
public class Exception2StringUtil {

    public static String getErrorInfoFromException(Exception e) {
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            return sw.toString();
        } catch (Exception e2) {
            return "bad getErrorInfoFromException";
        }
    }
}
