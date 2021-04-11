package com.inso2.inso2.repository;

import com.inso2.inso2.model.Ask;
import com.inso2.inso2.model.ProductDetails;
import com.inso2.inso2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface AskRepository extends JpaRepository<Ask, Long> {
    Ask findByUserAndProductDetails(User user, ProductDetails productDetails);
}
