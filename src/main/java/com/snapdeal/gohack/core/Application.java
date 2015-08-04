package com.snapdeal.gohack.core;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;


@EnableAutoConfiguration
@ComponentScan("com.snapdeal")
public class Application {


	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);	
	}

}
