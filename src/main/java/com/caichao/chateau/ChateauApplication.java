package com.caichao.chateau;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.caichao.chateau.*.mapper")
@EnableScheduling
public class ChateauApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChateauApplication.class, args);
	}

}
