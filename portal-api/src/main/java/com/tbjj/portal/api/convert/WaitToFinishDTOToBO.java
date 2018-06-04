package com.tbjj.portal.api.convert;

import com.tbjj.portal.api.dto.WaitToFinishDTO;
import com.tbjj.portal.common.utils.DateUtil;
import com.tbjj.portal.core.bo.WaitToFinishBO;

/**
 * Created by czhenzhen on 2017/12/29.
 */
public class WaitToFinishDTOToBO {

    public static WaitToFinishBO dtoToBO(WaitToFinishDTO source,WaitToFinishBO target){
        if (source != null){
            target.setBacklogId(source.getBacklogId());
            target.setEventId(source.getEventId());
            target.setOperationTime(DateUtil.formatDate(source.getOperationTime()));
        }
        return target;
    }

}
