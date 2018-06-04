package com.tbjj.portal.repository.api;

import com.tbjj.portal.common.repository.MybatisBaseRepository;
import com.tbjj.portal.repository.api.entity.EmployeeOnNavi;
import com.tbjj.portal.repository.api.entity.Navi;
import com.tbjj.portal.repository.api.entity.NaviExample;

import java.util.List;

public interface NaviRepository extends MybatisBaseRepository<Navi,Integer,NaviExample>{
    /**
     * 查询登录用户未绑定的导航的信息
     */
    List<EmployeeOnNavi> getNaviNoEmpList(Integer employeeId);
}