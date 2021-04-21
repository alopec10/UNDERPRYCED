package com.inso2.inso2;

import com.inso2.inso2.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Inso2Application {

	public static void main(String[] args) {
		SpringApplication.run(Inso2Application.class, args);
	}

	@Bean
	CommandLineRunner runner() {
		return args -> {

		};
	}



}
