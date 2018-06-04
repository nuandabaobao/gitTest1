package com.tbjj.portal.core.bo;

import lombok.Data;

import java.util.List;

/**
 * Created by Administrator on 2018/1/9/009.
 */
@Data
public class EChartData {
    private String yAxis;
    private List<Double> panelDatas;
}
