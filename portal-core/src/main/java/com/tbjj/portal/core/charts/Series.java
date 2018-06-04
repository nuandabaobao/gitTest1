package com.tbjj.portal.core.charts;

import lombok.Data;

import java.util.List;

/**
 * Created by Administrator on 2018/1/9/009.
 */
@Data
public class Series {
    private String name;
    private String type;
    private List<Double> data;
}
