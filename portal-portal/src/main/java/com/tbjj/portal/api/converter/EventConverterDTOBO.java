package com.tbjj.portal.api.converter;

import com.tbjj.portal.api.dto.EventListReqDTO;
import com.tbjj.portal.common.exception.ServiceException;
import com.tbjj.portal.common.utils.DateUtil;
import com.tbjj.portal.core.bo.EventListCondBO;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

/**
 * Created by Administrator on 2018/1/6/006.
 */
public class EventConverterDTOBO {

    public static EventListCondBO DTOToBO(EventListCondBO eventListCondBO,EventListReqDTO eventListReqDTO){
        if(eventListReqDTO.getEventStatus()!=null){
            eventListCondBO.setEventStatus(eventListReqDTO.getEventStatus());
        }
        if(eventListReqDTO.getDeptId()!=null){
            eventListCondBO.setDeptId(eventListReqDTO.getDeptId());
        }
        if(eventListReqDTO.getReadStatus()!=null){
            eventListCondBO.setReadStatus(eventListReqDTO.getReadStatus());
        }
        if(StringUtils.isNotBlank(eventListReqDTO.getApplyTimeStartStr())){
            //将时间戳转为date
            try{
                Date date = DateUtil.timestampToDate(eventListReqDTO.getApplyTimeStartStr());
                eventListCondBO.setApplyTimeSatrt(date);
            }catch (Exception e){
                throw new ServiceException(1,null,"时间格式不正确");
            }
        }
        if(StringUtils.isNotBlank(eventListReqDTO.getApplyTimeEndStr())){
            //将时间戳转为date
            try{
                Date date = DateUtil.timestampToDate(eventListReqDTO.getApplyTimeEndStr());
                eventListCondBO.setApplyTimeEnd(date);
            }catch (Exception e){
                throw new ServiceException(1,null,"时间格式不正确");
            }
        }
        if(StringUtils.isNotBlank(eventListReqDTO.getOpeTimeStartStr())){
            //将时间戳转为date
            try{
                Date date = DateUtil.timestampToDate(eventListReqDTO.getOpeTimeStartStr());
                eventListCondBO.setOpeTimeStart(date);
            }catch (Exception e){
                throw new ServiceException(1,null,"时间格式不正确");
            }
        }
        if(StringUtils.isNotBlank(eventListReqDTO.getOpeTimeEndStr())){
            //将时间戳转为date
            try{
                Date date = DateUtil.timestampToDate(eventListReqDTO.getOpeTimeEndStr());
                eventListCondBO.setOpeTimeEnd(date);
            }catch (Exception e){
                throw new ServiceException(1,null,"时间格式不正确");
            }
        }
        if(StringUtils.isNotBlank(eventListReqDTO.getWord())){
            eventListCondBO.setWord(eventListReqDTO.getWord());
        }
        return eventListCondBO;
    }
}
