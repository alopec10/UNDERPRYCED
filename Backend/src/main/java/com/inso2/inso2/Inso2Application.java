package com.inso2.inso2;

import com.inso2.inso2.domain.entity.*;
import com.inso2.inso2.domain.persistence.*;
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

	@Autowired
	private ProductDetailsRepository prodDetailsRepository;

	@Autowired
	private AskRepository askRepository;

	@Autowired
	private BidRepository bidRepository;

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
			ProductDetails prodDetails = new ProductDetails("42", 135, 190, 175, product);
			prodDetailsRepository.save(prodDetails);
			Ask ask = new Ask(170, new Date(), false, user, prodDetails);
			askRepository.save(ask);
			Bid bid = new Bid(150, new Date(), false, user, prodDetails);
			bidRepository.save(bid);
		};
	}



}
