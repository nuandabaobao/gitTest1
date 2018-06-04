package com.tbjj.portal.api.dto;

import lombok.Data;

/**
 * Created by Administrator on 2017/12/20/020.
 */
@Data
public class UpdatePwdReqDTO {
    private String oldPassword;
    private String newPassword;
}
