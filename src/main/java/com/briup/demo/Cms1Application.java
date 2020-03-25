package com.briup.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan({"com.briup.demo.mapper"})
@SpringBootApplication
public class Cms1Application {

	public static void main(String[] args) {
		SpringApplication.run(Cms1Application.class, args);
	}

}
