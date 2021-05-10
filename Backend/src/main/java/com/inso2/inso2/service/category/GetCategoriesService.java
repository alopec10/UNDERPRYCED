package com.inso2.inso2.service.category;

import com.inso2.inso2.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetCategoriesService {

    private final CategoryRepository categoryRepository;

    public GetCategoriesService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<String> get(){
        return categoryRepository.getTypes();
    }
}
