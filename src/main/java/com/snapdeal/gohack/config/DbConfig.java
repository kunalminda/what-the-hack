package com.snapdeal.gohack.config;
import javax.annotation.Resource;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;



@Configuration
@PropertySource("classpath:db.properties")
public class DbConfig {

	@Resource
	private Environment environment;


	@Bean
	public DataSource doDbInitialize(){
	     DataSource dataSource = new DataSource();
		dataSource.setDriverClassName(environment.getRequiredProperty("mysql.driver"));
		dataSource.setUrl(environment.getRequiredProperty("mysql.jdbcurl"));
		dataSource.setUsername(environment.getRequiredProperty("mysql.user"));
		dataSource.setPassword(environment.getRequiredProperty("mysql.password"));
		return dataSource;
	}
	
	@Bean
	public JdbcTemplate setJdbcTemplate(){
		JdbcTemplate jdbcTemplate= new JdbcTemplate();
		jdbcTemplate.setDataSource(doDbInitialize());
		return jdbcTemplate;
	}
	
	@Bean
	public SimpleJdbcCall setJdbcSimpleCall(){
		SimpleJdbcCall simpleJdbc= new SimpleJdbcCall(doDbInitialize()).withProcedureName("wth_insert");
		return simpleJdbc;
	}

}
