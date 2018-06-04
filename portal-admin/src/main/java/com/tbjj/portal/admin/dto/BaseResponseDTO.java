package com.tbjj.portal.admin.dto;

import lombok.Data;

/**
 * Created by ebiz on 2017/7/30.
 */
@Data
public class BaseResponseDTO {
    private String result;
    private String resultMessage;

    public void success(){
        this.result = "0";
        this.resultMessage = "成功";
    }

    public void success(String resultMessage){
        this.result = "0";
        this.resultMessage = resultMessage;
    }

    public void fail(String resultMessage){
        this.result = "1";
        this.resultMessage = resultMessage;
    }
}