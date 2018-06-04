package com.tbjj.portal.test.controller;

import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Liu Dong
 */
@SpringBootApplication(exclude= {MybatisAutoConfiguration.class})
public class TestConfiguration {

}
