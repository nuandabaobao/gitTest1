package com.tbjj.portal.api.convert;

import com.tbjj.portal.api.dto.ApproveMsgReqDTO;
import com.tbjj.portal.common.utils.DateUtil;
import com.tbjj.portal.core.bo.ApproveMsgBO;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

/**
 * Created by Administrator on 2018/1/9/009.
 */
public class ApproveMsgReqDTOToBO {

    public static ApproveMsgBO DTOToBO(ApproveMsgBO approveMsgBO, ApproveMsgReqDTO approveMsgReqDTO){
        if(StringUtils.isNotBlank(approveMsgReqDTO.getEventId())){
            approveMsgBO.setEventCode(approveMsgReqDTO.getEventId());
        }
        if(StringUtils.isNotBlank(approveMsgReqDTO.getSystemCode())){
            approveMsgBO.setSystemCode(approveMsgReqDTO.getSystemCode());
        }
        if(StringUtils.isNotBlank(approveMsgReqDTO.getIsContract())){
            approveMsgBO.setIsContract(approveMsgReqDTO.getIsContract());
        }
        if(StringUtils.isNotBlank(approveMsgReqDTO.getEventTitle())){
            approveMsgBO.setEventTitle(approveMsgReqDTO.getEventTitle());
        }
        if(StringUtils.isNotBlank(approveMsgReqDTO.getOperationTime())){
            Date date = DateUtil.formatDate(approveMsgReqDTO.getOperationTime());
            approveMsgBO.setOperationTime(date);
        }
        if(StringUtils.isNotBlank(approveMsgReqDTO.getOperator())){
            approveMsgBO.setOperator(approveMsgReqDTO.getOperator());
        }
        if(StringUtils.isNotBlank(approveMsgReqDTO.getReceiver())){
            approveMsgBO.setReceiver(approveMsgReqDTO.getReceiver());
        }
        if(approveMsgReqDTO.getApproveResult()!=null){
            approveMsgBO.setApproveResult(approveMsgReqDTO.getApproveResult());
        }
        if(approveMsgReqDTO.getWorkflowStatus()!=null){
            approveMsgBO.setWorkflowStatus(approveMsgReqDTO.getWorkflowStatus());
        }
        if(StringUtils.isNotBlank(approveMsgReqDTO.getResponseDesc())){
            approveMsgBO.setResponseDesc(approveMsgReqDTO.getResponseDesc());
        }
        if(StringUtils.isNotBlank(approveMsgReqDTO.getApproveUrl())){
            approveMsgBO.setApproveUrl(approveMsgReqDTO.getApproveUrl());
        }
        if(StringUtils.isNotBlank(approveMsgReqDTO.getApplyUrl())){
            approveMsgBO.setApplyUrl(approveMsgReqDTO.getApplyUrl());
        }
        return approveMsgBO;
    }
}
