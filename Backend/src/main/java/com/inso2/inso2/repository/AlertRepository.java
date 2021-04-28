package com.inso2.inso2.repository;

import com.inso2.inso2.model.Alert;
import com.inso2.inso2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlertRepository extends JpaRepository<Alert, Long> {
    List<Alert> findByUserOrderByIdAlert(User user);
}
