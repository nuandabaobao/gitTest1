package com.tbjj.portal.admin.dto;

import lombok.Data;

import java.util.List;

/**
 * Created by Administrator on 2017/12/16/016.
 */
@Data
public class PanelReqDTO {
    private Integer id;
    private String name;
    private Integer departmentId;
    private List<Integer> yaxisList;
    private Integer panelType;
    private String incoUrl;
}
