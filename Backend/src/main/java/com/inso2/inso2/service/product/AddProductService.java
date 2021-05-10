package com.inso2.inso2.service.product;

import com.inso2.inso2.dto.product.AddProductRequest;
import com.inso2.inso2.model.Category;
import com.inso2.inso2.model.Product;
import com.inso2.inso2.model.ProductDetails;
import com.inso2.inso2.repository.CategoryRepository;
import com.inso2.inso2.repository.ProductDetailsRepository;
import com.inso2.inso2.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AddProductService {
    private final ProductRepository productRepository;
    private final ProductDetailsRepository productDetailsRepository;
    private final CategoryRepository categoryRepository;

    public AddProductService(ProductRepository productRepository, ProductDetailsRepository productDetailsRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.productDetailsRepository = productDetailsRepository;
        this.categoryRepository = categoryRepository;
    }

    public void add(AddProductRequest req) throws Exception {
        Category c = categoryRepository.findByType(req.getCategoryType());
        Product p = new Product();
        p.setRef(req.getRef());
        p.setBrand(req.getBrand());
        p.setColorway(req.getColorway());
        p.setName(req.getName());
        p.setModel(req.getModel());
        p.setTitle(req.getTitle());
        p.setYear(req.getYear());
        p.setUrl(req.getUrl());
        p.setGender(req.getGender());
        p.setRetailPrice(req.getRetailPrice());
        SimpleDateFormat formatter1=new SimpleDateFormat("dd-MM-yyyy");
        p.setReleaseDate(formatter1.parse(req.getReleaseDate()));
        p.setCategory(c);
        List<ProductDetails> productDetails = new ArrayList<ProductDetails>();
        for (String size: req.getSizes()){
            productDetails.add(new ProductDetails(size, p));
        }
        p.setProductDetails(productDetails);
        productRepository.saveAndFlush(p);
    }
}
