package com.tbjj.portal.core.bo;

import lombok.Data;

/**
 * Created by Administrator on 2017/12/13/013.
 */
@Data
public class UpdateEmpBO {
    private Integer id;
    private String userName;
    private String password;
    private String name;
    private String mobile;
    private Integer departmentId;
    private Boolean isDelete;
}
