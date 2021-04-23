package com.inso2.inso2.dto.product.specification;

import com.inso2.inso2.dto.productDetails.ProductDetailsResponse;
import com.inso2.inso2.model.Gender;
import com.inso2.inso2.model.Product;
import com.inso2.inso2.model.ProductDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ProductSpecificationResponse implements Serializable {
    private String ref;
    private String brand;
    private String colorway;
    private String name;
    private String model;
    private String title;
    private String year;
    private String url;
    private Gender gender;
    private int retailPrice;
    private String date;
    private String categoryType;
    private List<ProductDetailsResponse> productDetails;

    public ProductSpecificationResponse build(Product p){
        this.ref = p.getRef();
        this.brand = p.getBrand();
        this.colorway = p.getColorway();
        this.name = p.getColorway();
        this.model = p.getModel();
        this.title = p.getTitle();
        this.year = p.getYear();
        this.url = p.getUrl();
        this.gender = p.getGender();
        this.retailPrice = p.getRetailPrice();
        this.date = p.getReleaseDate().toString().substring(0,10);
        this.categoryType = p.getCategory().getType();
        this.productDetails = new ArrayList<>();
        for(ProductDetails pd: p.getProductDetails()){
            this.productDetails.add(new ProductDetailsResponse().build(pd));
        }
        return this;
    }

    public ProductSpecificationResponse() {
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getColorway() {
        return colorway;
    }

    public void setColorway(String colorway) {
        this.colorway = colorway;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public int getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(int retailPrice) {
        this.retailPrice = retailPrice;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(String categoryType) {
        this.categoryType = categoryType;
    }

    public List<ProductDetailsResponse> getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(List<ProductDetailsResponse> productDetails) {
        this.productDetails = productDetails;
    }
}
