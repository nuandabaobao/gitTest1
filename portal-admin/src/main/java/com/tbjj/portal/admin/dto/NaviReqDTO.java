package com.tbjj.portal.admin.dto;

import lombok.Data;

/**
 * Created by Administrator on 2017/12/14/014.
 */
@Data
public class NaviReqDTO {
    private Integer id;
    private String name;
    private String url;
    private Integer departmentId;
}
