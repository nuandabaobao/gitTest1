package com.tbjj.portal.core;

import com.tbjj.portal.core.bo.*;

/**
 * Created by Administrator on 2018/1/9/009.
 */
public interface FinanceService {

    /**
     * 通知接口
     * @param approveMsgBO
     */
    void approveMsg(ApproveMsgBO approveMsgBO);

    /**
     * 撤销接口
     * @param revocationBO
     */
    void revocationMsg(RevocationBO revocationBO);

    /**
     *财务系统实付款信息返回接口
     * @param realPayBO
     */
    void approvePayment(RealPayBO realPayBO);


    /**
     * 调用财务系统建立单据接口方法
     * @param createItemOrderBO
     * @return
     */
    public BillRespBO createBill(CreateItemOrderBO createItemOrderBO);
}
