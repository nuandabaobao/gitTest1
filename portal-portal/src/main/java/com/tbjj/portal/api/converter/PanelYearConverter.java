package com.tbjj.portal.api.converter;

import com.tbjj.portal.api.dto.IndexChangesDTO;
import com.tbjj.portal.common.exception.ServiceException;
import com.tbjj.portal.common.utils.DateUtil;
import com.tbjj.portal.core.bo.PanelYearChangeBO;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

/**
 * Created by Administrator on 2018/1/9/009.
 */
public class PanelYearConverter {

    public static PanelYearChangeBO DTOToBO(PanelYearChangeBO panelYearChangeBO,IndexChangesDTO indexChangesDTO){
        if(indexChangesDTO.getPanelId()!=null){
            panelYearChangeBO.setPanelId(indexChangesDTO.getPanelId());
        }
        if(StringUtils.isNotBlank(indexChangesDTO.getTimestampStr())){
            try{
                Date date = DateUtil.timestampToDate(indexChangesDTO.getTimestampStr());
                panelYearChangeBO.setDate(date);
            }catch (Exception e){
                throw new ServiceException(1,null,"时间转换错误");
            }
        }
        return panelYearChangeBO;
    }
}
