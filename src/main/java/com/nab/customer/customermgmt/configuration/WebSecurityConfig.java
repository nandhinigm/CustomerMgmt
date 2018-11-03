package com.nab.customer.customermgmt.configuration;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.nab.customer.customermgmt.security.CustomBasicAuthenticationEntryPoint;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	private static final Logger logger = Logger.getLogger(WebSecurityConfig.class);
 
    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
    	logger.debug("Inside global security");
    	auth.inMemoryAuthentication().withUser("admin").password("password").roles("ADMIN");
    	auth.inMemoryAuthentication().withUser("user").password("password").roles("USER");
    }
    
    @Bean
    public CustomBasicAuthenticationEntryPoint getBasicAuthEntryPoint() {
        return new CustomBasicAuthenticationEntryPoint();
    }
 
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	logger.debug("Inside security");
    	http.csrf().disable();
    	http.authorizeRequests().antMatchers("/").permitAll()
    	                        .anyRequest().authenticated();
    	http.httpBasic().authenticationEntryPoint(getBasicAuthEntryPoint());        
    }
}
