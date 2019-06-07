package com.caichao.chateau;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.caichao.chateau.*.mapper")
public class ChateauApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChateauApplication.class, args);
	}

}
