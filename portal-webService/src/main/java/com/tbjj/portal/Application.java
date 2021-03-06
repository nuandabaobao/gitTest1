package com.tbjj.portal;

import com.tbjj.portal.security.Appctx;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * Created by Administrator on 2017/12/28/028.
 */
@SpringBootApplication
@ServletComponentScan
@MapperScan("com.tbjj.portal.repository")
@EnableAsync
public class Application {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Application.class);
        Appctx.setCtx(app.run(args));
    }
}
