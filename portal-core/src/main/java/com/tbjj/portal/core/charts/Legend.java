package com.tbjj.portal.core.charts;

import lombok.Data;

import java.util.List;

/**
 * Created by Administrator on 2018/1/9/009.
 */
@Data
public class Legend {
    private String left;
    private Boolean selectedMode;
    private List<String> data;
}
