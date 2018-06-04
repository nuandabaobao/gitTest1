package com.tbjj.portal.core.impl;

import com.tbjj.portal.common.exception.ServiceException;
import com.tbjj.portal.core.NaviEmpService;
import com.tbjj.portal.core.bo.NaviEmpListBO;
import com.tbjj.portal.core.bo.NaviNoEmpListBO;
import com.tbjj.portal.core.convert.NaviEmpListConvert;
import com.tbjj.portal.core.convert.NaviNoEmpListConvert;
import com.tbjj.portal.repository.api.NaviEmpRepository;
import com.tbjj.portal.repository.api.NaviRepository;
import com.tbjj.portal.repository.api.entity.EmployeeOnNavi;
import com.tbjj.portal.repository.api.entity.NaviEmp;
import com.tbjj.portal.repository.api.entity.NaviEmpExample;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/12/20/020.
 */
@Service
public class NaviEmpServiceImpl implements NaviEmpService{

    @Autowired
    private NaviEmpRepository naviEmpRepository;

    @Autowired
    private NaviRepository naviRepository;

    @Override
    public List<NaviEmpListBO> getIndexNaviList(Integer employeeId) {
        //调用自定义查询语句查询登录用户关联的导航信息
        List<EmployeeOnNavi> indexNaviList = naviEmpRepository.getIndexNaviList(employeeId);
        //创建一个前台导航需要的业务对象集合
        List<NaviEmpListBO> naviEmpListBOs=new ArrayList<>();
        if(indexNaviList!=null && indexNaviList.size()>0){
            for (EmployeeOnNavi indexNavi:indexNaviList) {
                NaviEmpListBO naviEmpListBO=new NaviEmpListBO();
                NaviEmpListConvert.NaviEmpToBO(naviEmpListBO,indexNavi);
                naviEmpListBOs.add(naviEmpListBO);
            }
        }
        return naviEmpListBOs;
    }

    @Override
    public List<NaviNoEmpListBO> getNaviNoEmpList(Integer employeeId) {
        //自定义查询登录用户未绑定的导航信息
        List<EmployeeOnNavi> indexNaviList = naviRepository.getNaviNoEmpList(employeeId);
        List<NaviNoEmpListBO> naviNoEmpListBOs=new ArrayList<>();
        if(indexNaviList!=null &indexNaviList.size()>0){
            for (EmployeeOnNavi indexNavi:indexNaviList) {
                NaviNoEmpListBO naviNoEmpListBO=new NaviNoEmpListBO();
                NaviNoEmpListConvert.NaviEmpToBO(naviNoEmpListBO,indexNavi);
                naviNoEmpListBOs.add(naviNoEmpListBO);
            }
        }
        return naviNoEmpListBOs;
    }

    @Override
    public void empAddNavi(Integer empId, Integer naviId) {
        if(naviId==null){
            throw new ServiceException(1,null,"绑定导航错误,请尽快联系管理员");
        }
        //首先根据用户id以及导航id查询导航用户中间表查看是原来用户是否关联过，这样可以减轻数据库数据量
        NaviEmpExample example=new NaviEmpExample();
        NaviEmpExample.Criteria criteria = example.createCriteria();
        criteria.andEmployeeIdEqualTo(empId);
        criteria.andNaviIdEqualTo(naviId);
        List<NaviEmp> naviEmps = naviEmpRepository.selectByExample(example);
        NaviEmp naviEmp=new NaviEmp();
        if(naviEmps!=null && naviEmps.size()>0){
            naviEmp.setIsDelete((byte)0);
            naviEmpRepository.updateByExampleSelective(naviEmp,example);
        }else{

            naviEmp.setEmployeeId(empId);
            naviEmp.setNaviId(naviId);
            naviEmp.setIsDelete((byte)0);
            naviEmpRepository.insertSelective(naviEmp);
        }
    }

    @Override
    public void empDeleteNavi(Integer naviEmpId) {
        if(naviEmpId==null){
            throw new ServiceException(1,null,"解绑错误,请尽快联系管理员");
        }
        NaviEmp naviEmp=new NaviEmp();
        naviEmp.setId(naviEmpId);
        naviEmp.setIsDelete((byte)1);
        naviEmpRepository.updateByPrimaryKeySelective(naviEmp);
    }

    @Override
    public void empAndNavi(Integer empId,String naviIdStrArr) {
        //首选判断传递的数据是否正确
        List<Integer> list=new ArrayList<>();
        try{
            String naviIdStr=naviIdStrArr.replace("[", "").replace("]", "").trim();
            if(StringUtils.isNotBlank(naviIdStr)) {
                String[] splits = naviIdStr.split(",");
                    for (String str : splits) {
                        int idInt = Integer.parseInt(str);
                        list.add(idInt);
                    }
            }
        }catch (Exception e){
            throw new ServiceException(1,null,"绑定解绑错误,请联系管理员");
        }

        //然后清除登录用户绑定的导航
        NaviEmpExample example=new NaviEmpExample();
        NaviEmpExample.Criteria criteria = example.createCriteria();
        criteria.andEmployeeIdEqualTo(empId);
        criteria.andIsDeleteEqualTo((byte)0);
        NaviEmp naviEmp=new NaviEmp();
        naviEmp.setIsDelete((byte)1);
        naviEmpRepository.updateByExampleSelective(naviEmp,example);

        if(list!=null && list.size()>0){
            for (Integer naviId:list) {
                //首先根据用户id以及导航id查询导航用户中间表查看是原来用户是否关联过，这样可以减轻数据库数据量
                NaviEmpExample naviEmpExample=new NaviEmpExample();
                NaviEmpExample.Criteria naviEmpCriteria = naviEmpExample.createCriteria();
                naviEmpCriteria.andEmployeeIdEqualTo(empId);
                naviEmpCriteria.andNaviIdEqualTo(naviId);
                List<NaviEmp> naviEmps = naviEmpRepository.selectByExample(naviEmpExample);
                NaviEmp naviEmpResult=new NaviEmp();
                if(naviEmps!=null && naviEmps.size()>0){
                    naviEmpResult.setIsDelete((byte)0);
                    naviEmpRepository.updateByExampleSelective(naviEmpResult,naviEmpExample);
                }else{

                    naviEmpResult.setEmployeeId(empId);
                    naviEmpResult.setNaviId(naviId);
                    naviEmpResult.setIsDelete((byte)0);
                    naviEmpRepository.insertSelective(naviEmpResult);
                }
            }
        }
    }
}
