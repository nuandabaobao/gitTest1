package com.tbjj.portal.core.bo;

import lombok.Data;

/**
 * 員工信息对象
 * Created by Administrator on 2017/12/12/012.
 */
@Data
public class EmpBO {
    private Integer id;
    private Integer departmentId;
    private String departmentName;
    private String userName;
    private String password;
    private String name;
    private String mobile;
}
