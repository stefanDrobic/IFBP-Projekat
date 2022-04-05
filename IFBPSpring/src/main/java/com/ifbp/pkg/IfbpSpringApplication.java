package com.ifbp.pkg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("model")
public class IfbpSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(IfbpSpringApplication.class, args);
	}

}
