package com.tbjj.portal.core.convert;

import com.tbjj.portal.core.bo.PanelEmpListBO;
import com.tbjj.portal.repository.api.entity.EmployeeOnPanel;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/1/2/002.
 */
public class PanelEmpListConvert {

    public static PanelEmpListBO panelEmpToBO(PanelEmpListBO panelEmpListBO, EmployeeOnPanel employeeOnPanel){
        if(employeeOnPanel.getPanelEmpId()!=null){
            panelEmpListBO.setPanelEmpId(employeeOnPanel.getPanelEmpId());
        }
        if(StringUtils.isNotBlank(employeeOnPanel.getPanelYaxis())){
            String[] split = employeeOnPanel.getPanelYaxis().split(",");
            List<Integer> list=new ArrayList<>();
            if(split!=null){
                for (String yaxisStr:split) {
                    int yaxis = Integer.parseInt(yaxisStr);
                    list.add(yaxis);
                }
            }
            panelEmpListBO.setPanelYaxisList(list);
        }
        if(StringUtils.isNotBlank(employeeOnPanel.getPanelName())){
            panelEmpListBO.setPanelName(employeeOnPanel.getPanelName());
        }
        if(StringUtils.isNotBlank(employeeOnPanel.getPanelIncoUrl())){
            panelEmpListBO.setPanelIncoUrl(employeeOnPanel.getPanelIncoUrl());
        }
        if(employeeOnPanel.getPanelId()!=null){
            panelEmpListBO.setPanelId(employeeOnPanel.getPanelId());
        }
        if(employeeOnPanel.getLocation()!=null){
            panelEmpListBO.setLocation(employeeOnPanel.getLocation());
        }
        if(employeeOnPanel.getPanelType()!=null){
            panelEmpListBO.setPanelType(employeeOnPanel.getPanelType());
        }
        return panelEmpListBO;
    }
}
