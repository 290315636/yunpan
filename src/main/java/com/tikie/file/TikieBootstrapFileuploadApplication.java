package com.tikie.file;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.mybatis.spring.annotation.MapperScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@MapperScan("com.tikie.file.mapper")
public class TikieBootstrapFileuploadApplication {

	public static void main(String[] args) {
		SpringApplication.run(TikieBootstrapFileuploadApplication.class, args);
	}
}
