package com.inso2.inso2.service.paymentMethod;

import com.inso2.inso2.dto.user.payment.PaymentMethodDeleteRequest;
import com.inso2.inso2.model.PaymentMethod;
import com.inso2.inso2.model.User;
import com.inso2.inso2.repository.PaymentMethodRepository;
import org.springframework.stereotype.Service;

@Service
public class CompletelyDeletePaymentMethodService {
    private final PaymentMethodRepository paymentMethodRepository;

    public CompletelyDeletePaymentMethodService(PaymentMethodRepository paymentMethodRepository) {
        this.paymentMethodRepository = paymentMethodRepository;
    }

    public void delete(PaymentMethodDeleteRequest req, User user) throws Exception {
        PaymentMethod p = paymentMethodRepository.findByUserAndIdPayMethod(user, req.getIdPaymentMethod());
        if (p == null) {
            throw new Exception("Payment method does not exist");
        }
        paymentMethodRepository.deleteById(p.getIdPayMethod());
    }
}
