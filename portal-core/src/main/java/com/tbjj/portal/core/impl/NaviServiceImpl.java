package com.tbjj.portal.core.impl;

import com.tbjj.portal.common.exception.ServiceException;
import com.tbjj.portal.core.DeptService;
import com.tbjj.portal.core.NaviService;
import com.tbjj.portal.core.bo.DeptBO;
import com.tbjj.portal.core.bo.NaviBO;
import com.tbjj.portal.core.convert.NaviBOConvert;
import com.tbjj.portal.repository.api.NaviEmpRepository;
import com.tbjj.portal.repository.api.NaviRepository;
import com.tbjj.portal.repository.api.entity.Navi;
import com.tbjj.portal.repository.api.entity.NaviEmp;
import com.tbjj.portal.repository.api.entity.NaviEmpExample;
import com.tbjj.portal.repository.api.entity.NaviExample;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/12/14/014.
 */
@Service
public class NaviServiceImpl implements NaviService{

    @Autowired
    private NaviRepository naviRepository;

    @Autowired
    private DeptService deptService;

    @Autowired
    private NaviEmpRepository naviEmpRepository;

    @Override
    public List<NaviBO> getNaviList(Integer departmentId) {
        if(departmentId==null){
            List<DeptBO> deptList = deptService.getDeptList();
            if(deptList!=null && deptList.size()>0){
                departmentId=deptList.get(0).getId();
            }
        }

        NaviExample naviExample=new NaviExample();
        NaviExample.Criteria criteria = naviExample.createCriteria();
        criteria.andDepartmentIdEqualTo(departmentId);
        criteria.andIsDeleteEqualTo((byte)0);
        naviExample.setOrderByClause("update_time desc");

        List<Navi> navis = naviRepository.selectByExample(naviExample);

        List<NaviBO> naviBos=new ArrayList<>();
        if(navis!=null&& navis.size()>0){
            for (Navi navi: navis) {
                NaviBO naviBO=new NaviBO();
                NaviBOConvert.NaviToBO(naviBO,navi);
                naviBos.add(naviBO);
            }
        }
        return naviBos;
    }

    @Override
    public void addNavi(NaviBO naviBO) {
        if(StringUtils.isBlank(naviBO.getName())){
            throw new ServiceException(1,null,"未输入用户名");
        }
        if(StringUtils.isBlank(naviBO.getUrl())){
            throw new ServiceException(1,null,"未输入链接地址");
        }
        if(naviBO.getDepartmentId()==null){
            throw new ServiceException(1,null,"部门信息错误，请联系管理员");
        }

        //判断新增导航信息是否存在
        NaviExample naviExampleName=new NaviExample();
        NaviExample.Criteria criteriaName = naviExampleName.createCriteria();
        criteriaName.andNameEqualTo(naviBO.getName());
        criteriaName.andIsDeleteEqualTo((byte)0);
        List<Navi> naviNames = naviRepository.selectByExample(naviExampleName);
        if(naviNames!=null && naviNames.size()>0){
            throw new ServiceException(2,null,"导航名称已存在");
        }

        //判断新增链接地址是否存在
        NaviExample naviExampleUrl=new NaviExample();
        NaviExample.Criteria criteriaUrl = naviExampleUrl.createCriteria();
        criteriaUrl.andUrlEqualTo(naviBO.getUrl());
        criteriaUrl.andIsDeleteEqualTo((byte)0);
        List<Navi> naviUrls = naviRepository.selectByExample(naviExampleUrl);
        if(naviUrls!=null && naviUrls.size()>0){
            throw new ServiceException(2,null,"导航链接地址已存在");
        }

        Navi navi=new Navi();
        NaviBOConvert.BOToNavi(navi,naviBO);
        navi.setCreateTime(new Date());
        navi.setUpdateTime(new Date());
        navi.setIsDelete((byte)0);
        naviRepository.insertSelective(navi);

    }

    @Override
    public void updateNavi(NaviBO naviBO) {
        if(naviBO.getId()==null){
            throw new ServiceException(1,null,"修改失败，尽快联系管理员");
        }
        if(naviBO.getDepartmentId()==null){
            throw new ServiceException(1,null,"修改失败，尽快联系管理员");
        }

        //判断用户修改的导航名称是否已经存在
        if(StringUtils.isNotBlank(naviBO.getName())){
            NaviExample exampleName=new NaviExample();
            NaviExample.Criteria criteriaName = exampleName.createCriteria();
            criteriaName.andIdNotEqualTo(naviBO.getId());
            criteriaName.andDepartmentIdEqualTo(naviBO.getDepartmentId());
            criteriaName.andNameEqualTo(naviBO.getName());
            criteriaName.andIsDeleteEqualTo((byte)0);
            List<Navi> naviNames = naviRepository.selectByExample(exampleName);
            if(naviNames!=null && naviNames.size()>0){
                throw new ServiceException(2,null,"导航名称已经存在");
            }
        }

        //判断用户修改的导航地址是否已经存在
        if(StringUtils.isNotBlank(naviBO.getUrl())){
            NaviExample exmapleUrl=new NaviExample();
            NaviExample.Criteria criteriaUrl = exmapleUrl.createCriteria();
            criteriaUrl.andIdNotEqualTo(naviBO.getId());
            criteriaUrl.andDepartmentIdEqualTo(naviBO.getDepartmentId());
            criteriaUrl.andUrlEqualTo(naviBO.getUrl());
            criteriaUrl.andIsDeleteEqualTo((byte)0);
            List<Navi> naviUrls = naviRepository.selectByExample(exmapleUrl);
            if(naviUrls!=null && naviUrls.size()>0){
                throw new ServiceException(2,null,"导航地址已经存在");
            }
        }

        Navi navi=new Navi();
        NaviBOConvert.BOToNavi(navi,naviBO);
        navi.setUpdateTime(new Date());
        naviRepository.updateByPrimaryKeySelective(navi);
    }

    @Override
    public void updateNaviStatus(Integer id,Integer employeeId) {
        if(id==null){
            throw new ServiceException(1,null,"删除失败，请尽快联系管理员");
        }
        Navi navi=new Navi();
        navi.setId(id);
        navi.setUpdateTime(new Date());
        navi.setIsDelete((byte)1);
        navi.setEmployeeId(employeeId);
        naviRepository.updateByPrimaryKeySelective(navi);

        //在删除导航的时候根据导航id删除导航员工中间表中的关联
        NaviEmpExample naviEmpExample=new NaviEmpExample();
        NaviEmpExample.Criteria criteria = naviEmpExample.createCriteria();
        criteria.andNaviIdEqualTo(id);

        NaviEmp naviEmp=new NaviEmp();
        naviEmp.setIsDelete((byte)1);
        naviEmpRepository.updateByExampleSelective(naviEmp,naviEmpExample);
    }
}
