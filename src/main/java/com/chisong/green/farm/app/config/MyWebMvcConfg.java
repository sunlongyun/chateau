package com.chisong.green.farm.app.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.chisong.green.farm.app.interceptor.TableShardInterceptor;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.sql.DataSource;

import net.sf.jsqlparser.schema.Table;
import org.apache.ibatis.mapping.ResultMapping;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.ReflectorFactory;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
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
