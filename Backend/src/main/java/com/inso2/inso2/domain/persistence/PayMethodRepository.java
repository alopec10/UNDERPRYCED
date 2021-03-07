package com.inso2.inso2.domain.persistence;

import com.inso2.inso2.domain.entity.PaymentMethod;
import com.inso2.inso2.domain.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface PayMethodRepository extends CrudRepository<PaymentMethod, Long> {
}
