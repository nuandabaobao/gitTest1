package com.tbjj.portal.common.utils;

import lombok.Data;

/**
 * Created by Administrator on 2018/1/9/009.
 */
@Data
public class HttpResult {
    private Integer code;
    private String body;

    public HttpResult() {
    }

    public HttpResult(Integer code, String body) {
        this.code = code;
        this.body = body;
    }
}
