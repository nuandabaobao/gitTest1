package com.tbjj.portal.core.bo;

import lombok.Data;

import java.util.Date;

/**
 * Created by czhenzhen on 2017/12/22.
 */
@Data
public class EventBO {
    private String name;//员工姓名(模糊查询)
    private String deptCode;//部门编码
    private Date applyDateStart;//申请时间从
    private Date applyDateEnd;//申请时间到
    private Integer readStatus;//0-未读；1-已读
    private Integer handleStatus;//办理状态：0-经办；1-待办；2-已办；3-完成
}
