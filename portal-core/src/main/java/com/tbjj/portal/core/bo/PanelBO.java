package com.tbjj.portal.core.bo;

import lombok.Data;

import java.util.List;

/**
 * Created by Administrator on 2017/12/16/016.
 */
@Data
public class PanelBO {
    private Integer id;
    private String name;
    private Integer departmentId;
    private List<Integer> yaxisList;
    private Integer employeeId;
    private Integer panelType;
    private String incoUrl;
}
