package com.inso2.inso2.repository;

import com.inso2.inso2.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByType(String type);
    @Query("SELECT DISTINCT c.type FROM Category c")
    List<String> getTypes();
}
