package com.tikie.file;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jms.annotation.EnableJms;
import org.mybatis.spring.annotation.MapperScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 项目启动类
 * @author zhaocs
 * 
 */
@SpringBootApplication
@EnableSwagger2
@EnableJms
@ComponentScan("com.tikie")
@MapperScan("com.tikie.*.dao")
public class TikieBootstrapFileuploadApplication {
	public static void main(String[] args) {
		SpringApplication.run(TikieBootstrapFileuploadApplication.class, args);
	}
}
