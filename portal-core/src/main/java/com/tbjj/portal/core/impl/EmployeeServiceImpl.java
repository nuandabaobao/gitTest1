package com.tbjj.portal.core.impl;

import com.github.pagehelper.ISelect;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.tbjj.portal.common.exception.ServiceException;
import com.tbjj.portal.common.utils.UpdateExcelUtil;
import com.tbjj.portal.core.EmployeeService;
import com.tbjj.portal.core.bo.*;
import com.tbjj.portal.core.convert.EmpBOConvert;
import com.tbjj.portal.core.convert.ExcelBoToEmp;
import com.tbjj.portal.core.convert.ExcelToEmpBo;
import com.tbjj.portal.repository.api.DepartmentRepository;
import com.tbjj.portal.repository.api.EmployeeRepository;
import com.tbjj.portal.repository.api.NaviEmpRepository;
import com.tbjj.portal.repository.api.PanelEmpRepository;
import com.tbjj.portal.repository.api.entity.*;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

/**
 * Created by Administrator on 2017/12/13/013.
 */
@Service
class EmployeeServiceImpl extends BaseServiceImpl implements  EmployeeService{

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private NaviEmpRepository naviEmpRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private PanelEmpRepository panelEmpRepository;

    public Page<EmployeeSearch> getEEList(EmpBO empBO, Integer currentPage, Integer pageSize){
        final Map<String,Object> employeeMap=new HashMap<>();
        //查询条件
        if(StringUtils.isNotBlank(empBO.getUserName())){
            employeeMap.put("userName",empBO.getUserName());
        }
        if(StringUtils.isNotBlank(empBO.getName())){
            employeeMap.put("name",empBO.getName());
        }
        if(StringUtils.isNotBlank(empBO.getMobile())){
            employeeMap.put("mobile",empBO.getMobile());
        }
        if(empBO.getDepartmentId()!=null){
            employeeMap.put("departmentId",empBO.getDepartmentId());
        }
        Page<EmployeeSearch> result= PageHelper.startPage(currentPage,pageSize).doSelectPage(new ISelect() {
            @Override
            public void doSelect() {
                employeeRepository.getEEList(employeeMap);
            }
        });

        return result;
    }

    @Override
    public void addEE(UpdateEmpBO updateEmpBO) {
        if(StringUtils.isBlank(updateEmpBO.getUserName())){
            throw new  ServiceException(2,null,"没有输入用户名");
        }
        if(StringUtils.isBlank(updateEmpBO.getName())){
            throw new ServiceException(2,null,"没有输入员工姓名");
        }
        if(StringUtils.isBlank(updateEmpBO.getMobile())){
            throw new ServiceException(2,null,"没有输入手机号");
        }
        if(updateEmpBO.getDepartmentId()==null){
            throw new ServiceException(2,null,"没有为员工选择部门");
        }

        //判断用户名是否存在
        EmployeeExample exampleUserName=new EmployeeExample();
        EmployeeExample.Criteria criteriaUserName = exampleUserName.createCriteria();
        criteriaUserName.andUserNameEqualTo(updateEmpBO.getUserName());
        criteriaUserName.andIsDeleteEqualTo((byte)0);
        //根据用户名查询数据库用户信息
        List<Employee> employeeUserNames = employeeRepository.selectByExample(exampleUserName);
        if(employeeUserNames!=null && employeeUserNames.size()>0){
            throw new ServiceException(3,null,"用户名已经存在");
        }

        //判断手机号是否存在
        EmployeeExample exampleMobile=new EmployeeExample();
        EmployeeExample.Criteria criteriaMobile = exampleMobile.createCriteria();
        criteriaMobile.andMobileEqualTo(updateEmpBO.getMobile());
        criteriaMobile.andIsDeleteEqualTo((byte)0);
        List<Employee> employeeMobiles = employeeRepository.selectByExample(exampleMobile);
        if(employeeMobiles!=null && employeeMobiles.size()>0){
            throw new ServiceException(3,null,"手机号已经存在");
        }

        //设置初始密码
        updateEmpBO.setPassword(DigestUtils.md5Hex("123456"));
        Employee employee=new Employee();
        EmpBOConvert.BOToEmployee(employee, updateEmpBO);
        employeeRepository.insertSelective(employee);
    }

    @Override
    public void updateService(UpdateEmpBO updateEmpBO) {
        if(updateEmpBO.getId()==null){
            throw new ServiceException(1,null,"修改失败,尽快联系管理员");
        }

        //判断用户名是否存在
        if(StringUtils.isNotBlank(updateEmpBO.getUserName())){
            EmployeeExample exampleUserName=new EmployeeExample();
            EmployeeExample.Criteria criteriaUserName = exampleUserName.createCriteria();
            criteriaUserName.andUserNameEqualTo(updateEmpBO.getUserName());
            criteriaUserName.andIdNotEqualTo(updateEmpBO.getId());
            criteriaUserName.andIsDeleteEqualTo((byte)0);
            List<Employee> employeeUserNames = employeeRepository.selectByExample(exampleUserName);
            if(employeeUserNames!=null && employeeUserNames.size()>0){
                throw new ServiceException(2,null,"用户名已经存在");
            }
        }

        //判断手机号是否存在
        if(StringUtils.isNotBlank(updateEmpBO.getMobile())){
            EmployeeExample exampleMobile=new EmployeeExample();
            EmployeeExample.Criteria criteriaMobile = exampleMobile.createCriteria();
            criteriaMobile.andMobileEqualTo(updateEmpBO.getMobile());
            criteriaMobile.andIdNotEqualTo(updateEmpBO.getId());
            criteriaMobile.andIsDeleteEqualTo((byte)0);
            List<Employee> employeeMobiles = employeeRepository.selectByExample(exampleMobile);
            if(employeeMobiles!=null && employeeMobiles.size()>0){
                throw new ServiceException(2,null,"手机号已经存在");
            }
        }

        Employee employee=new Employee();
        EmpBOConvert.BOToEmployee(employee, updateEmpBO);
        employeeRepository.updateByPrimaryKeySelective(employee);
    }

    @Override
    public void updateEEStatus(Integer id) {
        if(id==null){
            throw new ServiceException(1,null,"删除失败，请联系管理员");
        }
        Employee employee=new Employee();
        employee.setId(id);
        employee.setIsDelete((byte)1);
        employeeRepository.updateByPrimaryKeySelective(employee);

        //在删除员工的时候根据员工id将员工导航中间表关联的设置删除
        NaviEmpExample naviEmpExample=new NaviEmpExample();
        NaviEmpExample.Criteria naviEmpCriteria = naviEmpExample.createCriteria();
        naviEmpCriteria.andEmployeeIdEqualTo(id);

        NaviEmp naviEmp=new NaviEmp();
        naviEmp.setIsDelete((byte)1);
        naviEmpRepository.updateByExampleSelective(naviEmp,naviEmpExample);

        //在删除员工的时候根据员工id将员工面板中间表关联的设置删除
        PanelEmpExample panelEmpExample=new PanelEmpExample();
        PanelEmpExample.Criteria panelEmpCriteria = panelEmpExample.createCriteria();
        panelEmpCriteria.andEmployeeIdEqualTo(id);

        PanelEmp panelEmp=new PanelEmp();
        panelEmp.setIsDelete((byte)1);
        panelEmpRepository.updateByExampleSelective(panelEmp,panelEmpExample);
    }

    @Override
    public ImportEmpFileBO impoetEmpList(MultipartFile excelFile) {
        String file = excelFile.getOriginalFilename();
        if(!file.endsWith("xls") && !file.endsWith("xlsx") && !file.endsWith("")){
            throw new ServiceException(2,null,"您导入的不是excel文件");
        }

        ImportEmpFileBO importEmpFileBO=new ImportEmpFileBO();
        //更新状态
        UpdateStatusBO updateStatusBO=new UpdateStatusBO();
        //获取导入员工集合
        Integer[] notNUllArr={0,4};
        List<String[]> empListExcel = UpdateExcelUtil.readExcel(excelFile, notNUllArr);
        List<ExcelEmpBO> employeeListFromExcel=new ArrayList<>();
        for(String[] strArr:empListExcel){
            ExcelEmpBO excelEmpBO = ExcelToEmpBo.ArrTOBO(strArr);
            employeeListFromExcel.add(excelEmpBO);
        }

        //将Excel的id提取
        logger.info("实际导入员工数目："+empListExcel.size());
        //数据总数
        updateStatusBO.setTotalNum(empListExcel.size());
        //更新数
        updateStatusBO.setUpdateNum(0);
        //添加数
        updateStatusBO.setInsertNum(0);
        //失败数
        updateStatusBO.setFailNum(0);
        //失败列
        List<String> failLst=new ArrayList<>();

        //去重校验
//        List<String> userNameCheckList=new ArrayList<>();
        Integer updateNum=0;//已更新的行数
//        for(ExcelEmpBO excelEmpBO:employeeListFromExcel){
//            if(userNameCheckList.contains(excelEmpBO.getUserName())){
//                logger.info("userName："+excelEmpBO.getUserName());
//                failLst.add(excelEmpBO.getUserName());
//            }
//            userNameCheckList.add(excelEmpBO.getUserName());
//        }
//        if(!failLst.isEmpty()){
//            throw new ServiceException(3,null,"导入的excel中有重复电话号码："+ Arrays.toString(failLst.toArray()).substring(1,Arrays.toString(failLst.toArray()).length()-1));
//        }

        for (ExcelEmpBO excelEmpBO:employeeListFromExcel) {
            insertEmpMeth(excelEmpBO,updateStatusBO,failLst);
        }
        updateStatusBO.setFailLst(failLst);
        updateStatusBO.setFailNum(failLst.size());
        importEmpFileBO.setUpdateStatusBO(updateStatusBO);
        return importEmpFileBO;
    }

    @Override
    public void updatePassword(String newPassword,Integer id) {
        Employee employee=new Employee();
        employee.setId(id);
        employee.setPassword(newPassword);
        employee.setExpireTime(System.currentTimeMillis()-1L);
        employeeRepository.updateByPrimaryKeySelective(employee);
    }

    @Override
    public Employee getEmpByToken(String token) {
        EmployeeExample example=new EmployeeExample();
        EmployeeExample.Criteria criteria = example.createCriteria();
        criteria.andIsDeleteEqualTo((byte)0);
        criteria.andTokenEqualTo(token);
        List<Employee> employees = employeeRepository.selectByExample(example);
        if(employees!=null && employees.size()>0){
            return  employees.get(0);
        }
        return null;
    }

    @Override
    public void exit(Integer id) {
        Employee emp=new Employee();
        emp.setToken(UUID.randomUUID().toString().replaceAll("-",""));
        emp.setId(id);
        employeeRepository.updateByPrimaryKeySelective(emp);
    }

    @Override
    public Integer resetPassword(Integer empId) {
        Employee employee = new Employee();
        employee.setPassword(DigestUtils.md5Hex("123456"));
        employee.setId(empId);
        int result = employeeRepository.updateByPrimaryKeySelective(employee);
        return result;
    }

    private void insertEmpMeth(ExcelEmpBO excelEmpBO, UpdateStatusBO updateStatusBO, List<String> failList) {
        //判断密码是否为空
        if(StringUtils.isBlank(excelEmpBO.getPassword())){
            excelEmpBO.setPassword(DigestUtils.md5Hex("123456"));
        }

        //首先根据部门名称查询部门id
        DepartmentExample departmentExample=new DepartmentExample();
        DepartmentExample.Criteria criteria = departmentExample.createCriteria();
        criteria.andNameEqualTo(excelEmpBO.getDepartmentName());
        criteria.andIsDeleteEqualTo((byte)0);
        List<Department> departments = departmentRepository.selectByExample(departmentExample);
        if(departments.size()!=1){
            failList.add(excelEmpBO.getUserName()+" "+excelEmpBO.getMobile());
            return;
        }

        //首先根据用户名查询是否存在该用户名
        EmployeeExample exampleUserName=new EmployeeExample();
        EmployeeExample.Criteria criteriaUserName = exampleUserName.createCriteria();
        criteriaUserName.andIsDeleteEqualTo((byte)0);
        criteriaUserName.andUserNameEqualTo(excelEmpBO.getUserName());
        List<Employee> employeeUserNames = employeeRepository.selectByExample(exampleUserName);
        if(employeeUserNames!=null && employeeUserNames.size()>0){
            failList.add(excelEmpBO.getUserName()+" "+excelEmpBO.getMobile());
            return;
        }

        //查询手机号码是否唯一
        EmployeeExample exampleMobile=new EmployeeExample();
        EmployeeExample.Criteria criteriaMobile = exampleMobile.createCriteria();
        criteriaMobile.andIsDeleteEqualTo((byte)0);
        criteriaMobile.andMobileEqualTo(excelEmpBO.getMobile());
        List<Employee> employeeMobiles = employeeRepository.selectByExample(exampleMobile);
        if(employeeMobiles!=null && employeeMobiles.size()>0){
            failList.add(excelEmpBO.getUserName()+" "+excelEmpBO.getMobile());
            return;
        }

        //将excelEmpBO转为emp对象
        Employee emp=new Employee();
        Employee employee = ExcelBoToEmp.excelBoToEmp(emp, excelEmpBO);
        employee.setDepartmentId(departments.get(0).getId());

        //表明用户名和手机号都是唯一
        employee.setIsDelete((byte)0);
        int affectedRow = employeeRepository.insertSelective(employee);
        if(affectedRow!=1){
            failList.add(emp.getUserName()+" "+emp.getMobile());
        }
        updateStatusBO.setInsertNum(updateStatusBO.getInsertNum()+affectedRow);
    }
}
