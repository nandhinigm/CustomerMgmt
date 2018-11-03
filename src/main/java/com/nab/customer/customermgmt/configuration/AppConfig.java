package com.nab.customer.customermgmt.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.nab.customer.customermgmt.dao.CustomerDao;
import com.nab.customer.customermgmt.dao.CustomerDaoImpl;
import com.nab.customer.customermgmt.service.CustomerService;
import com.nab.customer.customermgmt.service.CustomerServiceImpl;

@Configuration
@PropertySource("classpath:database.properties")
@EnableTransactionManagement
@ComponentScans(value = { @ComponentScan("com.nab.customer.customermgmt.dao"),
		@ComponentScan("com.nab.customer.customermgmt.service") })
public class AppConfig {
	
	@Autowired
	private Environment env;

	@Bean
	public LocalSessionFactoryBean getSessionFactory() {

		LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
		
		// Setting Hibernate properties
		Properties props = new Properties();		
		props.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
		props.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
		
		factoryBean.setDataSource(getDataSource());
		factoryBean.setHibernateProperties(props);
		factoryBean.setPackagesToScan("com.nab.customer.customermgmt.model");

		return factoryBean;
	}
	
	@Bean
	public DataSource getDataSource() {
		
	    BasicDataSource dataSource = new BasicDataSource();
	    dataSource.setDriverClassName(env.getProperty("mysql.driver"));
	    dataSource.setUrl(env.getProperty("mysql.url"));
	    dataSource.setUsername(env.getProperty("mysql.user"));
	    dataSource.setPassword(env.getProperty("mysql.password"));
	    return dataSource;
	}

	@Bean
	public HibernateTransactionManager getTransactionManager() {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(getSessionFactory().getObject());
		return transactionManager;
	}	
}
