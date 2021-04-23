package com.inso2.inso2.controller;

import com.inso2.inso2.dto.user.payment.PaymentMethodDeleteRequest;
import com.inso2.inso2.dto.user.payment.PaymentMethodRequest;
import com.inso2.inso2.dto.user.payment.PaymentMethodResponse;
import com.inso2.inso2.model.*;
import com.inso2.inso2.repository.PaymentMethodRepository;
import com.inso2.inso2.security.Encrypter;
import com.inso2.inso2.service.paymentMethod.CreatePaymentMethodService;
import com.inso2.inso2.service.paymentMethod.DeletePaymentMethodService;
import com.inso2.inso2.service.paymentMethod.GetAllPaymentMethodsService;
import com.inso2.inso2.service.user.LoadUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    private final LoadUserService loadUserService;

    private final PaymentMethodRepository paymentMethodRepository;

    private final CreatePaymentMethodService createPaymentMethodService;

    private final GetAllPaymentMethodsService getAllPaymentMethodsService;

    private final DeletePaymentMethodService deletePaymentMethodService;

    public PaymentMethodController(Encrypter encrypter, LoadUserService loadUserService, PaymentMethodRepository paymentMethodRepository, CreatePaymentMethodService createPaymentMethodService, GetAllPaymentMethodsService getAllPaymentMethodsService, DeletePaymentMethodService deletePaymentMethodService) {
        this.encrypter = encrypter;
        this.loadUserService = loadUserService;
        this.paymentMethodRepository = paymentMethodRepository;
        this.createPaymentMethodService = createPaymentMethodService;
        this.getAllPaymentMethodsService = getAllPaymentMethodsService;
        this.deletePaymentMethodService = deletePaymentMethodService;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<?> addPaymentMethod(@RequestBody PaymentMethodRequest req) {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            User user = loadUserService.load(auth);
            createPaymentMethodService.create(req, user);
            return ResponseEntity.ok("Payment Method added");
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
            return ResponseEntity.ok(getAllPaymentMethodsService.get(user));
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
            deletePaymentMethodService.delete(req, user);
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
