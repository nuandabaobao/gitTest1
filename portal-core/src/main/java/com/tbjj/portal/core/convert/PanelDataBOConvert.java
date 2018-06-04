package com.tbjj.portal.core.convert;

import com.tbjj.portal.core.bo.PanelDataBO;
import com.tbjj.portal.repository.api.entity.PanelData;

/**
 * Created by Administrator on 2018/1/8/008.
 */
public class PanelDataBOConvert {

    public static PanelDataBO panelDataToBO(PanelDataBO panelDataBO, PanelData panelData){
        if(panelData.getXaxis()!=null){
            panelDataBO.setXaxis(panelData.getXaxis());
        }
        if(panelData.getCount()!=null){
            panelDataBO.setCount(panelData.getCount());
        }
        return panelDataBO;
    }
}
