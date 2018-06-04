package com.tbjj.portal.repository.api.entity;

import lombok.Data;

/**
 * Created by Administrator on 2018/1/2/002.
 */
@Data
public class EmployeeOnPanel {
    private Integer empId;
    private Integer panelId;
    private Integer panelEmpId;
    private Integer location;
    private String panelName;
    private String panelYaxis;
    private String panelIncoUrl;
    private Integer panelType;
}
