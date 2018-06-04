package com.tbjj.portal.repository.api.entity;

import lombok.Data;

/**
 * Created by Administrator on 2017/12/13/013.
 */
@Data
public class EmployeeSearch {
    private Integer id;
    private Integer departmentId;
    private String departmentName;
    private String userName;
    private String password;
    private String name;
    private String mobile;
}
