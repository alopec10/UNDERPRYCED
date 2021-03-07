package com.inso2.inso2;

import com.inso2.inso2.domain.entity.PaymentMethod;
import com.inso2.inso2.domain.entity.User;
import com.inso2.inso2.domain.persistence.PayMethodRepository;
import com.inso2.inso2.domain.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Inso2Application {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PayMethodRepository payMethodRepository;

	public static void main(String[] args) {
		SpringApplication.run(Inso2Application.class, args);
	}

	@Bean
	CommandLineRunner runner() {
		return args -> {
			User user = new User("Joaquin", "Dominguez", "a@gmail.com", "1234", "C/ KEKW", "24198", "España", "+34655123123", 0, 0);
			userRepository.save(user);
			PaymentMethod paym = new PaymentMethod("1234567891011121", "123", "12", "21", true, user);
			payMethodRepository.save(paym);
		};
	}



}
