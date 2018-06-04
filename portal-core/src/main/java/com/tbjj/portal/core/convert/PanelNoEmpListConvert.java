package com.tbjj.portal.core.convert;

import com.tbjj.portal.core.bo.PanelNoEmpListBO;
import com.tbjj.portal.repository.api.entity.EmployeeOnPanel;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/1/2/002.
 */
public class PanelNoEmpListConvert {

    public static PanelNoEmpListBO panelNoEmpToBO(PanelNoEmpListBO panelNoEmpListBO, EmployeeOnPanel employeeOnPanel){
        if(employeeOnPanel.getPanelId()!=null){
            panelNoEmpListBO.setPanelId(employeeOnPanel.getPanelId());
        }
        if(StringUtils.isNotBlank(employeeOnPanel.getPanelName())){
            panelNoEmpListBO.setPanelName(employeeOnPanel.getPanelName());
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
            panelNoEmpListBO.setPanelYaxisList(list);
        }
        if(StringUtils.isNotBlank(employeeOnPanel.getPanelIncoUrl())){
            panelNoEmpListBO.setPanelIncoUrl(employeeOnPanel.getPanelIncoUrl());
        }
        if(employeeOnPanel.getPanelType()!=null){
            panelNoEmpListBO.setPanelType(employeeOnPanel.getPanelType());
        }
        return panelNoEmpListBO;
    }
}
