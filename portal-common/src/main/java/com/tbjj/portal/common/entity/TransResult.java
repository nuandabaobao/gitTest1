package com.tbjj.portal.common.entity;

import lombok.Data;

/**
 * Created by ebiz on 2017/7/28.
 */
@Data
public class TransResult {

    //0-成功;1-失败
    private Integer code;

    //错误信息
    private String message;

    private Object content;



    /**
     * 执行交易成功
     */
    public void success(){
        this.setCode(0);
        this.setMessage("成功");
    }

    public void success(String message){
        this.setCode(0);
        this.setMessage(message);
    }

    public void success(Object content){
        this.setCode(0);
        this.setMessage("成功");
        this.setContent(content);
    }

    public void success(String message,Object content){
        this.setCode(0);
        this.setMessage(message);
        this.setContent(content);
    }

    /**
     * 执行失败
     */
    public void failure(){
        this.setCode(1);
        this.setMessage("失败");
    }

    public void failure(String message){
        this.setCode(1);
        this.setMessage(message);
    }

}
