package com.pnu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan(basePackages = {"edu.com"})
public class Mission11Application {

	public static void main(String[] args) {
		SpringApplication.run(Mission11Application.class, args);
	}

}
