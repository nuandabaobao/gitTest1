package com.tbjj.portal.admin.dto;

import lombok.Data;

/**
 * Created by Administrator on 2017/12/18/018.
 */
@Data
public class UpdatePwdDTO {
    private String oldPassword;
    private String newPassword;
}
