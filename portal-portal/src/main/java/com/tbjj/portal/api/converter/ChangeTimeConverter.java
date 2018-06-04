package com.tbjj.portal.api.converter;

import com.tbjj.portal.api.dto.ChangeTimeDTO;
import com.tbjj.portal.common.utils.DateUtil;
import com.tbjj.portal.core.bo.PanelTimeChangeBO;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

/**
 * Created by Administrator on 2018/1/10/010.
 */
public class ChangeTimeConverter {

    public static PanelTimeChangeBO DTOToBO(PanelTimeChangeBO panelTimeChangeBO,ChangeTimeDTO changeTimeDTO){
        if(changeTimeDTO.getPanelId()!=null){
            panelTimeChangeBO.setPanelId(changeTimeDTO.getPanelId());
        }
        if(changeTimeDTO.getUnit()!=null){
            panelTimeChangeBO.setUnit(changeTimeDTO.getUnit());
        }
        if(StringUtils.isNotBlank(changeTimeDTO.getStartTimeStr())){
            Date date = DateUtil.timestampToDate(changeTimeDTO.getStartTimeStr());
            panelTimeChangeBO.setStartTime(date);
        }
        if(StringUtils.isNotBlank(changeTimeDTO.getEndTimeStr())){
            Date date = DateUtil.timestampToDate(changeTimeDTO.getEndTimeStr());
            panelTimeChangeBO.setEndTime(date);
        }
        return panelTimeChangeBO;
    }
}
