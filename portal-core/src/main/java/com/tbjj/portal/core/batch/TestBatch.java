package com.tbjj.portal.core.batch;

import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by ebiz on 2017/7/17.
 */
@Service
public class TestBatch {
    private void hello(){
        System.out.println(new Date());
    }
}
