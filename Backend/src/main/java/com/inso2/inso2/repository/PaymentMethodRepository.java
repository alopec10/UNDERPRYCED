package com.inso2.inso2.repository;

import com.inso2.inso2.model.PaymentMethod;
import com.inso2.inso2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Long> {
    List<PaymentMethod> findByUser(User user);
    List<PaymentMethod> findByUserAndIsActive(User user, boolean isActive);
    PaymentMethod findByUserAndIdPayMethod(User user, long idPayMethod);
    PaymentMethod findFirstByUserAndIsActiveOrderByIdPayMethodAsc(User user, boolean isActive);
    PaymentMethod findByUserAndNumber(User user, String number);
}
