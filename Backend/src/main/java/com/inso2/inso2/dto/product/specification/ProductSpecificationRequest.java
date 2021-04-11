package com.inso2.inso2.dto.product.specification;

import java.io.Serializable;
import java.util.List;

public class ProductSpecificationRequest implements Serializable {
    private List<SpecificationDTO> specifications;

    public ProductSpecificationRequest() {
    }

    public ProductSpecificationRequest(List<SpecificationDTO> specifications) {
        this.specifications = specifications;
    }

    public List<SpecificationDTO> getSpecifications() {
        return specifications;
    }

    public void setSpecifications(List<SpecificationDTO> specifications) {
        this.specifications = specifications;
    }

    @Override
    public String toString() {
        return "ProductSpecificationRequest{" +
                "specifications=" + specifications +
                '}';
    }
}
