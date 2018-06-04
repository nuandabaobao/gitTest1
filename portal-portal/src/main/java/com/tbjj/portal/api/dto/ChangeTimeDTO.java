package com.tbjj.portal.api.dto;

import lombok.Data;

/**
 * Created by Administrator on 2018/1/10/010.
 */
@Data
public class ChangeTimeDTO {
    private Integer unit;//1年-->展示月 2月展示天 3周-->展示年的周 4天-->展示小时
    private Integer panelId;
    private String startTimeStr;
    private String endTimeStr;
}
