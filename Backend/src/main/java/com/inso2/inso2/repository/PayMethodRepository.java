package com.inso2.inso2.repository;

import com.inso2.inso2.model.PaymentMethod;
import org.springframework.data.repository.CrudRepository;

public interface PayMethodRepository extends CrudRepository<PaymentMethod, Long> {
}
