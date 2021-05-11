package com.springboot2.htservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class HtserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(HtserviceApplication.class, args);
	}

}
