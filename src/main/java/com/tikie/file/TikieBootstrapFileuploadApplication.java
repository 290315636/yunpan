package com.tikie.file;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jms.annotation.EnableJms;

import com.tikie.file.service.impl.DemoWebServiceImpl;

import javax.xml.ws.Endpoint;

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
		// 发布WebService服务
//		Endpoint.publish("http://127.0.0.1:12345/weather", new DemoWebServiceImpl());
	}
}
