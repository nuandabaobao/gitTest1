package com.tbjj.portal.core.bo;

import lombok.Data;

import java.util.List;

/**
 * Created by Administrator on 2018/1/2/002.
 */
@Data
public class PanelNoEmpListBO {
    private Integer panelId;
    private String panelName;
    private List<Integer> panelYaxisList;
    private String panelIncoUrl;
    private Integer panelType;
}
