package com.tbjj.portal.core;

import com.tbjj.portal.core.bo.PortalLoginBO;
import com.tbjj.portal.repository.api.entity.Employee;

/**
 * Created by Administrator on 2017/12/20/020.
 */
public interface PortalLoginService {
    /**
     * 前台用户登录
     */
    Employee login(PortalLoginBO portalLoginBO);
}
