package com.tbjj.portal;


import com.tbjj.portal.admin.security.Appctx;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

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
