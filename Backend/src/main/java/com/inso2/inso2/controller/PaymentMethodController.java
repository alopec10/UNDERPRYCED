package com.inso2.inso2.controller;

import com.inso2.inso2.dto.product.byRef.ProductByRefRequest;
import com.inso2.inso2.dto.product.specification.ProductSpecificationResponse;
import com.inso2.inso2.dto.user.payment.PaymentMethodRequest;
import com.inso2.inso2.model.*;
import com.inso2.inso2.repository.PaymentMethodRepository;
import com.inso2.inso2.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/payMethod")
public class PaymentMethodController {
    private PasswordEncoder passwordEncoder;

    private UserRepository userRepository;

    private PaymentMethodRepository paymentMethodRepository;

    public PaymentMethodController(PasswordEncoder passwordEncoder, UserRepository userRepository, PaymentMethodRepository paymentMethodRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.paymentMethodRepository = paymentMethodRepository;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<?> addPaymentMethod(@RequestBody PaymentMethodRequest req) {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            UserDetails userDetails = (UserDetails) auth.getPrincipal();
            String email = userDetails.getUsername();
            User user = userRepository.findByEmail(email);
            PaymentMethod paymentMethod = new PaymentMethod();
            paymentMethod.setNumber(passwordEncoder.encode(req.getNumber()));
            paymentMethod.setCvv(passwordEncoder.encode(req.getCvv()));
            paymentMethod.setExpMonth(passwordEncoder.encode(req.getExpMonth()));
            paymentMethod.setExpYear(passwordEncoder.encode(req.getExpYear()));
            paymentMethod.setUser(user);
            paymentMethod.setDefaultMethod(req.isDefaultMethod());
            paymentMethodRepository.save(paymentMethod);
            return ResponseEntity.ok("Payment Method added");
        } catch (Exception e) {
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

}
