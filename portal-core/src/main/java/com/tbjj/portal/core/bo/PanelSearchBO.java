package com.tbjj.portal.core.bo;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/1/8/008.
 */
@Data
public class PanelSearchBO {
    private Integer unit;//1年-->展示月 2月展示天 3周-->展示年的周 4天-->展示小时
    private Date startTime;
    private Date endTime;
    private List<Integer> yaxisList;
    private String userName;
    private Integer panelType;
}
