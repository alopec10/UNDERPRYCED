package com.inso2.inso2.repository;

import com.inso2.inso2.model.Ask;
import com.inso2.inso2.model.ProductDetails;
import com.inso2.inso2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AskRepository extends JpaRepository<Ask, Long> {
    Ask findByUserAndProductDetails(User user, ProductDetails productDetails);
    List<Ask> findByUser(User user);
    Ask findFirstByProductDetailsAndPriceOrderByIdAskAsc(ProductDetails productDetails, int price);
    @Query("SELECT a.price FROM Ask a WHERE a.productDetails = ?1 ORDER BY a.price ASC")
    List<Integer> findPriceByProductDetails(ProductDetails productDetails);
}
