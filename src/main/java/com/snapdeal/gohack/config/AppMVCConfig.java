package com.snapdeal.gohack.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class AppMVCConfig extends WebMvcConfigurerAdapter {
	
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/home").setViewName("index");
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/ideaPage").setViewName("ideaPage");
        registry.addViewController("/hacktube").setViewName("hacktube");
        registry.addViewController("/ideaDetail").setViewName("ideaDetail");
        registry.addViewController("/2048").setViewName("2048");
        registry.addViewController("/error").setViewName("error");
        registry.addViewController("/hackDetails").setViewName("hackDetails");
        registry.addViewController("/viewIdeas").setViewName("ideas");
        registry.addViewController("/hackFeature").setViewName("hackFeature");
        registry.addViewController("/count").setViewName("count");
        
       }
  
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    	registry.addResourceHandler("/resources/**")
        .addResourceLocations("/resources/**")
        .setCachePeriod(31556926);
     }
 }