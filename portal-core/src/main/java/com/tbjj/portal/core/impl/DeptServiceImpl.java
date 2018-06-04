package com.tbjj.portal.core.impl;

import com.tbjj.portal.common.exception.ServiceException;
import com.tbjj.portal.core.DeptService;
import com.tbjj.portal.core.bo.DeptBO;
import com.tbjj.portal.core.convert.DeptBOConvert;
import com.tbjj.portal.repository.api.DepartmentRepository;
import com.tbjj.portal.repository.api.entity.Department;
import com.tbjj.portal.repository.api.entity.DepartmentExample;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/12/13/013.
 */
@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public List<DeptBO> getDeptList() {
        List<DeptBO> result=new ArrayList<>();

        DepartmentExample example=new DepartmentExample();
        DepartmentExample.Criteria criteria = example.createCriteria();
        criteria.andIsDeleteEqualTo((byte)0);
        example.setOrderByClause("update_time desc");

        List<Department> departments = departmentRepository.selectByExample(example);

        if(departments!=null && departments.size()>0){
            for (Department dept:departments){
                DeptBO deptBO=new DeptBO();
                result.add(DeptBOConvert.DeptToBO(deptBO,dept));
            }
        }

        return result;
    }

    @Override
    public void addDept(DeptBO deptBO) {
        if(StringUtils.isBlank(deptBO.getName())){
            throw new ServiceException(1,null,"未输入部门名称");
        }

        DepartmentExample example=new DepartmentExample();
        DepartmentExample.Criteria criteria = example.createCriteria();
        criteria.andNameEqualTo(deptBO.getName());
        criteria.andIsDeleteEqualTo((byte)0);
        List<Department> departments = departmentRepository.selectByExample(example);
        if(departments!=null && departments.size()>0){
            throw new ServiceException(2,null,"部门名称已存在");
        }

        Department dept=new Department();
        DeptBOConvert.BOToDept(dept,deptBO);
        dept.setCreateTime(new Date());
        dept.setUpdateTime(new Date());
        dept.setIsDelete((byte)0);

        departmentRepository.insertSelective(dept);
    }

    @Override
    public void updateDept(DeptBO deptBO) {
        if(deptBO.getId()==null){
            throw new ServiceException(1,null,"修改失败，请尽快联系管理员");
        }
        if(StringUtils.isBlank(deptBO.getName())){
            throw new ServiceException(1,null,"部门名称未填写");
        }

        DepartmentExample example=new DepartmentExample();
        DepartmentExample.Criteria criteria = example.createCriteria();
        criteria.andIdNotEqualTo(deptBO.getId());
        criteria.andNameEqualTo(deptBO.getName());
        criteria.andIsDeleteEqualTo((byte)0);
        List<Department> departments = departmentRepository.selectByExample(example);
        if(departments!=null && departments.size()>0){
            throw new ServiceException(2,null,"部门名称已存在");
        }

        Department dept=new Department();
        DeptBOConvert.BOToDept(dept,deptBO);
        dept.setUpdateTime(new Date());

        departmentRepository.updateByPrimaryKeySelective(dept);

    }

    @Override
    public void updateStatusDept(Integer id,Integer employeeId) {
        if(id==null){
            throw new ServiceException(1,null,"删除失败，请尽快联系管理员");
        }

        Department dept=new Department();
        dept.setId(id);
        dept.setUpdateTime(new Date());
        dept.setIsDelete((byte)1);
        dept.setEmployeeId(employeeId);

        departmentRepository.updateByPrimaryKeySelective(dept);
    }
}
