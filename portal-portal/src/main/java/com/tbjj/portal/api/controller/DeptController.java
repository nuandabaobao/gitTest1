package com.tbjj.portal.api.controller;

import com.alibaba.fastjson.JSON;
import com.tbjj.portal.api.dto.DeptRespDTO;
import com.tbjj.portal.common.entity.TransResult;
import com.tbjj.portal.core.DeptService;
import com.tbjj.portal.core.bo.DeptBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/1/7/007.
 */
@RestController
@RequestMapping("/portal/dept")
public class DeptController extends BaseController{

    @Autowired
    private DeptService deptService;

    /**
     * 获取部门列表
     * @return
     */
    @RequestMapping(value = "getDeptList",method = RequestMethod.GET)
    public TransResult getdeptList(){
        logger.info("获取部门信息列表【开始】进入com.tbjj.portal.admin.controller.DeptController.getdeptList()");

        TransResult result=new TransResult();

        List<DeptBO> deptBOs = deptService.getDeptList();
        /**
         * 因为前台需要,需要设置请选择在第一个
         */
        List<DeptBO> list=new ArrayList<>();
        DeptBO deptBO=new DeptBO();
        deptBO.setId(0);
        deptBO.setName(" 请选择 ");
        list.add(deptBO);
        /**
         * 因为前台需要设置一个请选择
         */
        if(deptBOs!=null){
            for (DeptBO db:deptBOs) {
                list.add(db);
            }
        }
        DeptRespDTO deptRespDTO =new DeptRespDTO();
        deptRespDTO.setData(list);
        deptRespDTO.success();

        result.setContent(deptRespDTO);
        result.success();

        logger.info("获取部门列表【结束】,返回值：\n"+ JSON.toJSONString(result));

        return result;
    }
}
