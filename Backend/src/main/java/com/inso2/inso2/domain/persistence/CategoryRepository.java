package com.inso2.inso2.domain.persistence;

import com.inso2.inso2.domain.entity.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
