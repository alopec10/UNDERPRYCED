package com.inso2.inso2.controller;

import com.inso2.inso2.dto.ask.delete.DeleteAskRequest;
import com.inso2.inso2.dto.user.payment.PaymentMethodDeleteRequest;
import com.inso2.inso2.dto.user.payment.PaymentMethodRequest;
import com.inso2.inso2.dto.user.payment.PaymentMethodResponse;
import com.inso2.inso2.model.*;
import com.inso2.inso2.repository.PaymentMethodRepository;
import com.inso2.inso2.repository.UserRepository;
import com.inso2.inso2.security.Encrypter;
import com.inso2.inso2.service.user.LoadUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/payMethod")
public class PaymentMethodController {
    private Encrypter encrypter;

    private LoadUserService loadUserService;

    private UserRepository userRepository;

    private PaymentMethodRepository paymentMethodRepository;

    public PaymentMethodController(Encrypter encrypter, LoadUserService loadUserService, UserRepository userRepository, PaymentMethodRepository paymentMethodRepository) {
        this.encrypter = encrypter;
        this.loadUserService = loadUserService;
        this.userRepository = userRepository;
        this.paymentMethodRepository = paymentMethodRepository;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<?> addPaymentMethod(@RequestBody PaymentMethodRequest req) {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            User user = loadUserService.load(auth);

            if (req.getNumber().length() != 16) {
                return new ResponseEntity<>(
                        "16 digits required",
                        HttpStatus.SERVICE_UNAVAILABLE);
            }
            PaymentMethod paymentMethod = paymentMethodRepository.findByUserAndNumber(user, Encrypter.encrypt(req.getNumber()));
            if (paymentMethod == null) {
                paymentMethod = new PaymentMethod();
                paymentMethod.setName(req.getName());
                paymentMethod.setNumber(Encrypter.encrypt(req.getNumber()));
                paymentMethod.setCvv(Encrypter.encrypt(req.getCvv()));
                paymentMethod.setExpMonth(Encrypter.encrypt(req.getExpMonth()));
                paymentMethod.setExpYear(Encrypter.encrypt(req.getExpYear()));
                paymentMethod.setUser(user);
                paymentMethod.setDefaultMethod(req.isDefaultMethod());
                paymentMethod.setActive(true);
                paymentMethodRepository.save(paymentMethod);
                return ResponseEntity.ok("Payment Method added");
            }
            else {
                paymentMethod.setCvv(Encrypter.encrypt(req.getCvv()));
                paymentMethod.setExpMonth(Encrypter.encrypt(req.getExpMonth()));
                paymentMethod.setExpYear(Encrypter.encrypt(req.getExpYear()));
                paymentMethod.setActive(true);
                paymentMethodRepository.save(paymentMethod);
                return ResponseEntity.ok("Payment Method added");
            }

        } catch (Exception e) {
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public ResponseEntity<?> getAllPaymentMethods() {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            User user = loadUserService.load(auth);
            List<PaymentMethod> paymentMethods = paymentMethodRepository.findByUserAndIsActive(user, true);
            ArrayList<PaymentMethodResponse> paymentMethodsResponse = new ArrayList<PaymentMethodResponse>();
            for (PaymentMethod p : paymentMethods) {
                PaymentMethodResponse pr = new PaymentMethodResponse();
                pr.setIdPayMethod(p.getIdPayMethod());
                pr.setName(p.getName());
                pr.setDefaultMethod(p.isDefaultMethod());
                pr.setExpMonth(Encrypter.decrypt(p.getExpMonth()));
                pr.setExpYear(Encrypter.decrypt(p.getExpYear()));
                String num = Encrypter.decrypt(p.getNumber());
                num = num.substring(12);
                pr.setNumber(num);
                paymentMethodsResponse.add(pr);
            }
            return ResponseEntity.ok(paymentMethodsResponse);
        } catch (Exception e) {
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ResponseEntity<?> delete(@RequestBody PaymentMethodDeleteRequest req) {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            User user = loadUserService.load(auth);
            PaymentMethod p = paymentMethodRepository.findByUserAndIdPayMethod(user, req.getIdPaymentMethod());
            if (p == null) {
                return new ResponseEntity<>(
                        "Payment method does not exist",
                        HttpStatus.SERVICE_UNAVAILABLE);
            }
            p.setActive(false);
            paymentMethodRepository.saveAndFlush(p);
            return ResponseEntity.ok("Payment method deleted");
        } catch (Exception e) {
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    @RequestMapping(value = "/deleteCompletely", method = RequestMethod.POST)
    public ResponseEntity<?> deleteCompletely(@RequestBody PaymentMethodDeleteRequest req) {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            User user = loadUserService.load(auth);
            PaymentMethod p = paymentMethodRepository.findByUserAndIdPayMethod(user, req.getIdPaymentMethod());
            if (p == null) {
                return new ResponseEntity<>(
                        "Payment method does not exist",
                        HttpStatus.SERVICE_UNAVAILABLE);
            }
            paymentMethodRepository.deleteById(p.getIdPayMethod());
            return ResponseEntity.ok("Payment method deleted");
        } catch (Exception e) {
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

}
