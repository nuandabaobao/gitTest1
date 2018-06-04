package com.tbjj.portal.common.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ebiz on 2017/7/20.
 */
public class TransResultCodeConst {
    private static Map<Integer, String> resultMap = new HashMap<Integer, String>() {{
        put(0, "成功");
        //1W数字段为token校验错误码
        put(-10001, "token校验错误");
        put(-10002, "无效的token");
        put(-10003, "token已过期");
        //2W字段customer相关错误
        put(-20001, "手机号格式不正确");
        put(-20002, "密码格式不正确");
        put(-20003, "手机号已被注册");
        put(-20004, "注册短信验证码错误或已经失效");
        put(-20005, "注册失败请重试");
        put(-20006, "短信验证码不能为空");
        //3W字段
        put(-30001, "账号或密码错误");
    }};

    public static String getMessage(int code) {
        return resultMap.get(code);
    }
}
