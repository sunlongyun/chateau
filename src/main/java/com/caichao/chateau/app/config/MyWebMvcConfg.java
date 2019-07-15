package com.caichao.chateau.app.config;

import com.alibaba.druid.pool.DruidDataSource;
import javax.sql.DataSource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 描述:
 *
 * @AUTHOR 孙龙云
 * @date 2019-06-07 14:31
 */
@Configuration
public class MyWebMvcConfg implements WebMvcConfigurer{
	@Value("${spring.datasource.url}")
	private String url;

	@Value("${spring.datasource.password}")
	private String password;

	@Value("${spring.datasource.driverClassName}")
	private String driverClassName;
	@Value("${spring.datasource.username}")
	private String userName;

	/**
	 * 获取RestTemplate
	 * @return
	 */
	@Bean
	@ConditionalOnMissingBean
	public RestTemplate  getRestTemplate(){
		return new RestTemplate();
	}
	/**
	 * datasource
	 * @return
	 */
	@Bean("myDataSource")
	@ConditionalOnMissingBean
	public DataSource getDatasource(){
		DruidDataSource druidDataSource = new DruidDataSource();
		druidDataSource.setUrl(url);
		druidDataSource.setUsername(userName);
		druidDataSource.setPassword(password);
		druidDataSource.setDriverClassName(driverClassName);

		return druidDataSource;
	}
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/*").allowedOrigins("*").allowedHeaders("*").allowedMethods("*");
	}
}
