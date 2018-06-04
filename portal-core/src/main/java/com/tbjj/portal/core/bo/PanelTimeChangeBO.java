package com.tbjj.portal.core.bo;

import lombok.Data;

import java.util.Date;

/**
 * Created by Administrator on 2018/1/10/010.
 */
@Data
public class PanelTimeChangeBO {
    private Integer unit;
    private Integer panelId;
    private Date startTime;
    private Date endTime;
    private Integer empId;
    private String userName;
}
