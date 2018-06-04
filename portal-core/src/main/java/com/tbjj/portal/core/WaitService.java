package com.tbjj.portal.core;

import com.tbjj.portal.common.entity.BackResult;
import com.tbjj.portal.common.exception.ServiceException;
import com.tbjj.portal.core.bo.ApplyBO;
import com.tbjj.portal.core.bo.CreateItemOrderBO;
import com.tbjj.portal.core.bo.WaitBO;
import com.tbjj.portal.core.bo.WaitToFinishBO;

import java.io.IOException;

/**
 * Created by czhenzhen on 2017/12/28.
 */
public interface WaitService {
    //添加申请
    int addApply(ApplyBO bo);

    //添加待办事项
    int addWait(WaitBO bo) throws IOException;

    //待办转已办
    int waitToFinish(WaitToFinishBO bo);

    BackResult test() throws ServiceException;

    /**
     * 付款申请制单接口
     * @param createItemOrderBO
     */
    void createItemOrder(CreateItemOrderBO createItemOrderBO);
}
