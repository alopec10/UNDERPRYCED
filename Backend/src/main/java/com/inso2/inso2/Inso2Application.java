package com.inso2.inso2;

import com.inso2.inso2.domain.entity.*;
import com.inso2.inso2.domain.persistence.CategoryRepository;
import com.inso2.inso2.domain.persistence.PayMethodRepository;
import com.inso2.inso2.domain.persistence.ProductRepository;
import com.inso2.inso2.domain.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class Inso2Application {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PayMethodRepository payMethodRepository;

	@Autowired
    private CategoryRepository catRepository;

	@Autowired
    private ProductRepository prodRepository;

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
			Category category = new Category("Sneaker");
			catRepository.save(category);
			Product product = new Product("1231321-321", "Nike", "Blue", "Janoski", "Janoski", "Nike Janoski Blue", "2016", "https://nike.com", Gender.M, 124, new Date(),category);
			prodRepository.save(product);
		};
	}



}
