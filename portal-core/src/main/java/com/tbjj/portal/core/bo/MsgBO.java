package com.tbjj.portal.core.bo;

import lombok.Data;

import java.util.Date;

/**
 * Created by Administrator on 2018/1/15/015.
 */
@Data
public class MsgBO {
     private Integer messId;
     private String  messTitle;
     private String messUrl;
     private Date messDate;
     private String userName;
     private Integer messStatus;
     private Integer eventStreamId;
}
