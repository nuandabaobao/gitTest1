package com.tbjj.portal.core;

import com.github.pagehelper.Page;
import com.tbjj.portal.core.bo.EmpBO;
import com.tbjj.portal.core.bo.ImportEmpFileBO;
import com.tbjj.portal.core.bo.UpdateEmpBO;
import com.tbjj.portal.repository.api.entity.Employee;
import com.tbjj.portal.repository.api.entity.EmployeeSearch;
import org.springframework.web.multipart.MultipartFile;

/**
 * 员工业务层接口
 * Created by Administrator on 2017/12/12/012.
 */
public interface EmployeeService {
    /**
     * 分页条件查询员工列表信息
     */
    Page<EmployeeSearch> getEEList(EmpBO empBO, Integer currentPage, Integer pageSize);

    /**
     * 添加员工信息
     */
    void addEE(UpdateEmpBO updateEmpBO);

    /**
     * 根据员工id修改员工信息
     */
    void updateService(UpdateEmpBO updateEmpBO);

    /**
     * 根据员工id修改员工状态
     */
    void updateEEStatus(Integer id);

    /**
     * 导入员工信息
     */
    ImportEmpFileBO impoetEmpList(MultipartFile excelFile);

    /**
     * 修改密码
     */
    void updatePassword(String newPassword,Integer id);

    /**
     * 根据token查询员工信息
     */
    Employee getEmpByToken(String token);

    /**
     * 退出登录
     */
    void exit(Integer id);

    /**
     * 重置密码
     * @param empId
     */
    Integer resetPassword(Integer empId);
}
