package com.nab.customer.customermgmt.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "com.nab.customer.customermgmt.controller" })
@Import({WebSecurityConfig.class,})
public class WebConfig extends WebMvcConfigurerAdapter {

}


