package com.tbjj.portal.common.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ebiz on 2017/7/20.
 */
public class ResultConst {
    private static Map<Integer, String> resultMap = new HashMap<Integer, String>() {{
        put(0, "成功");
        put(-1, "请求失败");
        put(-2, "未登录");
        put(-2, "手机号码不正确");
        put(-3, "密码格式不正确");
        put(-4, "账号或密码错误");
    }};

    public static String getMessage(int code) {
        return resultMap.get(code);
    }
}
