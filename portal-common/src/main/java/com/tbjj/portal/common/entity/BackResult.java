package com.tbjj.portal.common.entity;

import lombok.Data;

/**
 * Created by ebiz on 2017/7/28.
 */
@Data
public class BackResult {

    //0-成功;1-失败
    private Integer errorCode;

    //错误信息
    private String msg;

    private Object content;



    /**
     * 执行交易成功
     */
    public void success(){
        this.setErrorCode(0);
        this.setMsg("成功");
    }

    public void success(String message){
        this.setErrorCode(0);
        this.setMsg(message);
    }

    public void success(Object content){
        this.setErrorCode(0);
        this.setMsg("成功");
        this.setContent(content);
    }

    public void success(String message,Object content){
        this.setErrorCode(0);
        this.setMsg(message);
        this.setContent(content);
    }

    /**
     * 执行失败
     */
    public void failure(){
        this.setErrorCode(1);
        this.setMsg("失败");
    }

    public void failure(String message){
        this.setErrorCode(1);
        this.setMsg(message);
    }

}
