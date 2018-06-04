package com.tbjj.portal.core.bo;

import lombok.Data;

/**
 * Created by Administrator on 2017/12/14/014.
 */
@Data
public class NaviBO {
    private Integer id;
    private String name;
    private String url;
    private Integer departmentId;
    private Integer employeeId;
}
