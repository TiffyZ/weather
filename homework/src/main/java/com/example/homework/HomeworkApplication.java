package com.example.homework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EntityScan("com.example.homework.domain")
@EnableEurekaClient
public class HomeworkApplication {
	public static void main(String[] args) {
		SpringApplication.run(HomeworkApplication.class, args);
	}

}
