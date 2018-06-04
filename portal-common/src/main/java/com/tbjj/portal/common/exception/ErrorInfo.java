package com.tbjj.portal.common.exception;

import lombok.Data;

/**
 * @author Fu JinHui
 */
@Data
public class ErrorInfo {

    private String result ;

    private String resultMessage ;

    private Object errData ;

    public ErrorInfo(){

    }

    public ErrorInfo(Integer errCode, String message, Object errData) {
        this.result = errCode.toString();
        this.resultMessage = message;
        this.errData = errData;
    }

}
