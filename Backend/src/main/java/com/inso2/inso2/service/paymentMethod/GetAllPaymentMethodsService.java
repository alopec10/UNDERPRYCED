package com.inso2.inso2.service.paymentMethod;

import com.inso2.inso2.dto.user.payment.PaymentMethodResponse;
import com.inso2.inso2.model.PaymentMethod;
import com.inso2.inso2.model.User;
import com.inso2.inso2.repository.PaymentMethodRepository;
import com.inso2.inso2.security.Encrypter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GetAllPaymentMethodsService {

    private final PaymentMethodRepository paymentMethodRepository;

    public GetAllPaymentMethodsService(PaymentMethodRepository paymentMethodRepository) {
        this.paymentMethodRepository = paymentMethodRepository;
    }

    public ArrayList<PaymentMethodResponse> get(User user){
        List<PaymentMethod> paymentMethods = paymentMethodRepository.findByUserAndIsActive(user, true);
        ArrayList<PaymentMethodResponse> paymentMethodsResponse = new ArrayList<>();
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
        return paymentMethodsResponse;
    }
}
