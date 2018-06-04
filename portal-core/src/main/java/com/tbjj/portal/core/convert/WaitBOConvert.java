package com.tbjj.portal.core.convert;

import com.tbjj.portal.common.utils.DateUtil;
import com.tbjj.portal.core.bo.WaitBO;
import com.tbjj.portal.repository.api.entity.EventStream;

/**
 * Created by czhenzhen on 2017/12/28.
 */
public class WaitBOConvert {

    public static EventStream boToEntity(WaitBO source,EventStream target){
        if (source != null){
            target.setOperationTime(DateUtil.formatDate(source.getOperationTime()));
            target.setUserName(source.getRevceier());
            target.setApproveUrl(source.getApproveUrl());
            target.setBacklogId(source.getBacklogId());
        }
        return target;
    }
}
