package com.inso2.inso2;

import com.inso2.inso2.repository.*;
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
	private PaymentMethodRepository paymentMethodRepository;

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

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private ShipmentRepository shipmentRepository;

	public static void main(String[] args) {
		SpringApplication.run(Inso2Application.class, args);
	}

	@Bean
	CommandLineRunner runner() {
		return args -> {

		};
	}



}
