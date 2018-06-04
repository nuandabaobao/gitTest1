package com.tbjj.portal.api.dto;

import lombok.Data;

/**
 * Created by Administrator on 2017/12/20/020.
 */
@Data
public class PortalLoginRespDTO extends BaseResponseDTO{
    private String token;
    private String userName;
}
