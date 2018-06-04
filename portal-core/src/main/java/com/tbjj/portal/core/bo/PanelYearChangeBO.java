package com.tbjj.portal.core.bo;

import lombok.Data;

import java.util.Date;

/**
 * Created by Administrator on 2018/1/9/009.
 */
@Data
public class PanelYearChangeBO {
    private Integer panelId;
    private Date date;
    private Integer empId;
    private String userName;
}
