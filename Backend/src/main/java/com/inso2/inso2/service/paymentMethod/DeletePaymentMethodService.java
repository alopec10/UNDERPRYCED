package com.inso2.inso2.service.paymentMethod;

import com.inso2.inso2.dto.user.payment.PaymentMethodDeleteRequest;
import com.inso2.inso2.model.PaymentMethod;
import com.inso2.inso2.model.User;
import com.inso2.inso2.repository.PaymentMethodRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DeletePaymentMethodService {
    private final PaymentMethodRepository paymentMethodRepository;

    public DeletePaymentMethodService(PaymentMethodRepository paymentMethodRepository) {
        this.paymentMethodRepository = paymentMethodRepository;
    }

    public void delete(PaymentMethodDeleteRequest req, User user) throws Exception {
        PaymentMethod p = paymentMethodRepository.findByUserAndIdPayMethod(user, req.getIdPaymentMethod());
        if (p == null) {
            throw new Exception("Payment method does not exist");
        }
        p.setActive(false);
        paymentMethodRepository.saveAndFlush(p);
    }
}
