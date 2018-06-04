package com.tbjj.portal.api.controller;

import com.tbjj.portal.api.dto.EventReqDTO;
import com.tbjj.portal.common.entity.TransResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by czhenzhen on 2017/12/22.
 */
@RestController
public class EventController extends BaseController{

    public TransResult getList(@RequestBody EventReqDTO dto,
                               @RequestParam(value = "currentPage", defaultValue = "1", required = false) Integer currentPage,
                               @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize){
        TransResult transResult = new TransResult();

        return transResult;

    }
}
