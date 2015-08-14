package com.snapdeal.gohack.core;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {
	
	

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/home").setViewName("index");
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/hello").setViewName("hello");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/idea").setViewName("idea");
        registry.addViewController("/hacktube").setViewName("hacktube");
        registry.addViewController("/ideaDetail").setViewName("ideaDetail");
        registry.addViewController("/2048").setViewName("2048");
       }
    
    



}