package com.tbjj.portal.api.security;

import org.springframework.context.ApplicationContext;

/**
 * Created by ChengZhenxing on 2017/2/13.
 */

public class Appctx {
    private static ApplicationContext ctx = null;

    public static ApplicationContext getCtx() {
        return ctx;
    }

    public static void setCtx(ApplicationContext ctx) {
        Appctx.ctx = ctx;
    }

    public static Object getObject(String string) {
        return ctx.getBean(string);
    }
}
