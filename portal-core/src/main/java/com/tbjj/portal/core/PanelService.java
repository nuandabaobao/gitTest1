package com.tbjj.portal.core;

import com.tbjj.portal.core.bo.PanelBO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by Administrator on 2017/12/16/016.
 */
public interface PanelService {

    /**
     * 条件查询所有面板信息
     */
    List<PanelBO> getPanelList(Integer departmentId);

    /**
     * 添加面板信息
     */
    void addPanel(PanelBO panelBO);

    /**
     * 根据面板id修改面板信息
     */
    void updatePanel(PanelBO panelBO);

    /**
     * 根据id修改面板状态信息
     */
    void updateStatus(Integer id,Integer employeeId);

    /**
     * 上传图片
     */
    String addIconPic(MultipartFile iconPic);
}
