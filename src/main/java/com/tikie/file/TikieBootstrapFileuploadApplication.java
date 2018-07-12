package com.tikie.file;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@MapperScan("com.tikie")
public class TikieBootstrapFileuploadApplication {

	public static void main(String[] args) {
		SpringApplication.run(TikieBootstrapFileuploadApplication.class, args);
	}
}
