package com.tbjj.portal.core.convert;

import com.tbjj.portal.core.bo.PanelBO;
import com.tbjj.portal.repository.api.entity.Panel;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/12/16/016.
 */
public class PanelBOConvert {

    public static PanelBO PanelToBO(PanelBO panelBO, Panel panel){
        if(panel.getId()!=null){
            panelBO.setId(panel.getId());
        }
        if(StringUtils.isNotBlank(panel.getName())){
            panelBO.setName(panel.getName());
        }
        if(panel.getDepartmentId()!=null){
            panelBO.setDepartmentId(panel.getDepartmentId());
        }
        if(StringUtils.isNotBlank(panel.getYaxis())){
            String[] split = panel.getYaxis().split(",");
            List<Integer> list=new ArrayList<>();
            if(split!=null){
                for (String yaxisStr:split) {
                    int yaxis = Integer.parseInt(yaxisStr);
                    list.add(yaxis);
                }
            }
            panelBO.setYaxisList(list);
        }
        if(panel.getEmployeeId()!=null){
            panelBO.setEmployeeId(panel.getId());
        }
        if(StringUtils.isNotBlank(panel.getIncoUrl())){
            panelBO.setIncoUrl(panel.getIncoUrl());
        }
        if(panel.getPanelType()!=null){
            panelBO.setPanelType(panel.getPanelType());
        }
        return panelBO;
    }

    /**
     *

     }
     * @param panel
     * @param panelBO
     * @return
     */
    public static Panel BoToPanel(Panel panel,PanelBO panelBO){
        if(panelBO.getId()!=null){
            panel.setId(panelBO.getId());
        }
        if(StringUtils.isNotBlank(panelBO.getName())){
            panel.setName(panelBO.getName());
        }
        if(panelBO.getDepartmentId()!=null){
            panel.setDepartmentId(panelBO.getDepartmentId());
        }
        if(panelBO.getYaxisList()!=null && panelBO.getYaxisList().size()>0){
            StringBuilder sb=new StringBuilder();
            for(int i=0;i<panelBO.getYaxisList().size();i++) {
                if (i == panelBO.getYaxisList().size() - 1) {
                    sb.append(panelBO.getYaxisList().get(i));
                } else {
                    sb.append(panelBO.getYaxisList().get(i));
                    sb.append(",");
                }
            panel.setYaxis(sb.toString());
        }
        }
        if(panelBO.getEmployeeId()!=null){
            panel.setEmployeeId(panelBO.getEmployeeId());
        }
        if(StringUtils.isNotBlank(panelBO.getIncoUrl())){
            panel.setIncoUrl(panelBO.getIncoUrl());
        }
        if(panelBO.getPanelType()!=null){
            panel.setPanelType(panelBO.getPanelType());
        }
        return panel;
    }
}
