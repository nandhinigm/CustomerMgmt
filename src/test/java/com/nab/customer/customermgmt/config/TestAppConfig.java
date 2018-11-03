package com.nab.customer.customermgmt.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.nab.customer.customermgmt.configuration.AppConfig;

@Configuration
@EnableTransactionManagement
@Import({AppConfig.class})
public class TestAppConfig {
	
	@Bean
	public DataSource getDataSource() {	
		
	    BasicDataSource dataSource = new BasicDataSource();
	    dataSource.setDriverClassName("org.hsqldb.jdbcDriver");
	    dataSource.setUrl("jdbc:hsqldb:mem:customermgmt");
	    dataSource.setUsername("admin");
	    dataSource.setPassword("password");
	    return dataSource;
	}
	
	@Bean
	public LocalSessionFactoryBean getSessionFactory() {

		LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
		
		// Setting Hibernate properties
		Properties props = new Properties();		
		props.put("hibernate.show_sql", true);
		props.put("hibernate.hbm2ddl.auto", "create");
		props.put("hibernate.dialect", "org.hibernate.dialect.HSQLDialect");
		props.put("hibernate.archive.autodetection", "class,hbm");
		
		factoryBean.setDataSource(getDataSource());
		factoryBean.setHibernateProperties(props);
		factoryBean.setPackagesToScan("com.nab.customer.customermgmt.model");

		return factoryBean;
	}
}
