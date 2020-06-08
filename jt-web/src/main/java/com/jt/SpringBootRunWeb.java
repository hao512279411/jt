package com.jt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude=DataSourceAutoConfiguration.class)
public class SpringBootRunWeb {
	
	public static void main(String[] args) {
		
		SpringApplication.run(SpringBootRunWeb.class,args);
	}


}
