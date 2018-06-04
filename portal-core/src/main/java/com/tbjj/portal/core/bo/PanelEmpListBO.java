package com.tbjj.portal.core.bo;

import lombok.Data;

import java.util.List;

/**
 * Created by Administrator on 2018/1/2/002.
 */
@Data
public class PanelEmpListBO {
    private Integer panelEmpId;
    private Integer panelId;
    private List<Integer> panelYaxisList;
    private String panelName;
    private String panelIncoUrl;
    private Integer location;
    private Integer panelType;
}
