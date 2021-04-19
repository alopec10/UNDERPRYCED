package com.inso2.inso2.repository;

import com.inso2.inso2.dto.user.payment.PaymentMethodResponse;
import com.inso2.inso2.model.PaymentMethod;
import com.inso2.inso2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Long> {
    List<PaymentMethod> findByUser(User user);
    PaymentMethod findByUserAndIdPayMethod(User user, long idPayMethod);
    PaymentMethod findFirstByUserOrderByIdPayMethodAsc(User user);
}
