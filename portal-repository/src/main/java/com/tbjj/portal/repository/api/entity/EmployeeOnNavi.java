package com.tbjj.portal.repository.api.entity;

import lombok.Data;

/**
 * Created by Administrator on 2017/12/20/020.
 */
@Data
public class EmployeeOnNavi {
    //导航员工中间表id
    private Integer naviEmpId;
    //员工id
    private Integer empId;
    //导航id
    private Integer naviId;
    //导航名称
    private String naviName;
    //导航url
    private String naviUrl;
}
