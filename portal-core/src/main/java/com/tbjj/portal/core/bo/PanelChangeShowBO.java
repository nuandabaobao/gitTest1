package com.tbjj.portal.core.bo;

import com.tbjj.portal.core.charts.Option;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/1/10/010.
 */
@Data
public class PanelChangeShowBO {
    private Integer panelId;
    private String panelName;
    private List<Integer> panelYaxisList;
    private Integer location;
    private Integer panelType;
    private Date startTime;
    private Date endTime;
    private Integer unit;
    private Option option;
}
