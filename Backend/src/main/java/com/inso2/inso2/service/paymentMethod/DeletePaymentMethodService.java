package com.inso2.inso2.service.paymentMethod;

import com.inso2.inso2.dto.user.payment.PaymentMethodDeleteRequest;
import com.inso2.inso2.model.PaymentMethod;
import com.inso2.inso2.model.User;
import com.inso2.inso2.repository.PaymentMethodRepository;
import com.inso2.inso2.service.ask.DeleteAllAsksOfUserService;
import com.inso2.inso2.service.bid.DeleteAllBidsOfUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DeletePaymentMethodService {
    private final PaymentMethodRepository paymentMethodRepository;
    private final DeleteAllAsksOfUserService deleteAllAsksOfUserService;
    private final DeleteAllBidsOfUserService deleteAllBidsOfUserService;

    public DeletePaymentMethodService(PaymentMethodRepository paymentMethodRepository, DeleteAllAsksOfUserService deleteAllAsksOfUserService, DeleteAllBidsOfUserService deleteAllBidsOfUserService) {
        this.paymentMethodRepository = paymentMethodRepository;
        this.deleteAllAsksOfUserService = deleteAllAsksOfUserService;
        this.deleteAllBidsOfUserService = deleteAllBidsOfUserService;
    }

    public void delete(PaymentMethodDeleteRequest req, User user) throws Exception {
        PaymentMethod p = paymentMethodRepository.findByUserAndIdPayMethod(user, req.getIdPaymentMethod());
        if (p == null) {
            throw new Exception("Payment method does not exist");
        }
        p.setActive(false);
        paymentMethodRepository.saveAndFlush(p);
        // Check if there is another payment method added or not
        PaymentMethod paymentMethod = paymentMethodRepository.findFirstByUserAndIsActiveOrderByIdPayMethodAsc(user, true);
        if(paymentMethod == null){
            this.deleteAsksAndBids(user);
        }
    }

    private void deleteAsksAndBids(User user){
        deleteAllAsksOfUserService.delete(user);
        deleteAllBidsOfUserService.delete(user);
    }
}
