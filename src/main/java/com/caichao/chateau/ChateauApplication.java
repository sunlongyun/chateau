package com.caichao.chateau;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.caichao.chateau.*.mapper")
@EnableScheduling
@PropertySource("file:${chateau_path_file}")
public class ChateauApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChateauApplication.class, args);
	}

}
