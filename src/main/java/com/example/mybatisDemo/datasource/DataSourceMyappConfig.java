
/*
 * Copyright (C) 2017-2018 huangjiawei 
 * 
 * All right reserved.
 * 
 * This software is the confidential and proprietary information of huangjiawei of China.
 * ("Confidential Information"). You shall not disclose such Confidential 
 * Information and shall use it only in accordance with the argeements 
 * reached into with huangjiawei himself.
 *
 */

package com.example.mybatisDemo.datasource;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * 数据源配置
 */
@Configuration
@MapperScan(basePackages = "com.example.mybatisDemo.dao", sqlSessionTemplateRef = "myappSqlSessionTemplate")
public class DataSourceMyappConfig {

	@Value("${spring.datasource.myapp.url}")
	private String dbUrl;

	@Value("${spring.datasource.myapp.username}")
	private String username;

	@Value("${spring.datasource.myapp.password}")
	private String password;

	@Value("${spring.datasource.myapp.driverClassName}")
	private String driverClassName;

	@Value("${spring.datasource.myapp.initialSize}")
	private int initialSize;

	@Value("${spring.datasource.myapp.minIdle}")
	private int minIdle;

	@Value("${spring.datasource.myapp.maxActive}")
	private int maxActive;

	@Value("${spring.datasource.myapp.maxWait}")
	private int maxWait;

	@Value("${spring.datasource.myapp.timeBetweenEvictionRunsMillis}")
	private int timeBetweenEvictionRunsMillis;

	@Value("${spring.datasource.myapp.minEvictableIdleTimeMillis}")
	private int minEvictableIdleTimeMillis;

	@Value("${spring.datasource.myapp.validationQuery}")
	private String validationQuery;

	@Value("${spring.datasource.myapp.testWhileIdle}")
	private boolean testWhileIdle;

	@Value("${spring.datasource.myapp.testOnBorrow}")
	private boolean testOnBorrow;

	@Value("${spring.datasource.myapp.testOnReturn}")
	private boolean testOnReturn;

	@Value("${spring.datasource.myapp.filters}")
	private String filters;

	@Bean(name = "myappDataSource")
	@ConfigurationProperties(prefix = "spring.datasource.myapp")
	@Primary
	public DataSource testDataSource() {
		//		return DataSourceBuilder.create().build();
		DruidDataSource datasource = new DruidDataSource();
		datasource.setUrl(this.dbUrl);
		datasource.setUsername(username);
		datasource.setPassword(password);
		datasource.setDriverClassName(driverClassName);
		// configuration
		datasource.setInitialSize(initialSize);
		datasource.setMinIdle(minIdle);
		datasource.setMaxActive(maxActive);
		datasource.setMaxWait(maxWait);
		datasource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
		datasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
		datasource.setValidationQuery(validationQuery);
		datasource.setTestWhileIdle(testWhileIdle);
		datasource.setTestOnBorrow(testOnBorrow);
		datasource.setTestOnReturn(testOnReturn);
		try {
			datasource.setFilters(filters);
		} catch (SQLException e) {
			System.err.println("druid configuration initialization filter:" + e);
			e.printStackTrace();
		}
		return datasource;
	}

	@Bean(name = "myappSqlSessionFactory")
	@Primary
	public SqlSessionFactory testSqlSessionFactory(
			@Qualifier("myappDataSource") DataSource dataSource) throws Exception {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(dataSource);
		return bean.getObject();
	}

	@Bean(name = "myappTransactionManager")
	@Primary
	public DataSourceTransactionManager testTransactionManager(
			@Qualifier("myappDataSource") DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

	@Bean(name = "myappSqlSessionTemplate")
	@Primary
	public SqlSessionTemplate testSqlSessionTemplate(
			@Qualifier("myappSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory);
	}
}
