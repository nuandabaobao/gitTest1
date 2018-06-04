package com.tbjj.portal.core.charts;

import lombok.Data;

import java.util.List;

/**
 * Created by Administrator on 2018/1/9/009.
 */
@Data
public class XAxis {
    private String type;
    private String name;
    private String splitLine;
    private List<String> data;
    private Boolean boundaryGap;
    private AxisTick axisTick;
}
