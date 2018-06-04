package com.tbjj.portal.core;

import com.tbjj.portal.core.bo.AdminLoginBO;
import com.tbjj.portal.repository.api.entity.Employee;

/**
 * Created by Administrator on 2017/12/18/018.
 */
public interface AdminLoginService {
    /**
     * 后台管理员登录
     */

    Employee login(AdminLoginBO adminLoginBO);
}
