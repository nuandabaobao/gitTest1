package com.tbjj.portal.admin.dto;

import lombok.Data;

/**
 * 后台员工管理列表请求条件
 * Created by Administrator on 2017/12/12/012.
 */

@Data
public class EmpReqDTO {
    private Integer id;
    private String  userName;
    private String name;
    private String mobile;
    private Integer departmentId;
}
