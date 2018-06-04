package com.tbjj.portal.core.bo;

import lombok.Data;

import java.util.Date;

/**
 * Created by czhenzhen on 2017/12/28.
 */
@Data
public class EventMessageBO {

    private Integer messId;

    private String messTitle;

    private String messUrl;

    private Date messDate;

    private String userName;

    private Integer messStatus;

    private Byte isDelete;

    private Integer eventStreamId;
}
