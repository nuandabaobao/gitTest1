package com.tbjj.portal.core.bo;

import lombok.Data;

import java.util.List;

/**
 * Created by Administrator on 2017/12/15/015.
 */
@Data
public class UpdateStatusBO {
    //总数
    private Integer totalNum;
    //更新数
    private Integer updateNum;
    //添加数
    private Integer insertNum;
    //失败数
    private Integer failNum;
    //失败列
    private List<String> failLst;
}
