package com.chisong.green.farm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.chisong.green.farm.*.mapper")
@EnableScheduling
@PropertySource("file:${green_farm_path_file_test}")
public class ChateauApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChateauApplication.class, args);
	}

}
