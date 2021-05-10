package com.inso2.inso2.dto.product;

import com.inso2.inso2.model.Category;
import com.inso2.inso2.model.Gender;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class AddProductRequest implements Serializable {

    private String ref, brand, colorway, name, model, title, year, url, categoryType;
    private Gender gender;
    private int retailPrice;
    private String releaseDate;
    private List<String> sizes;

    public AddProductRequest() {
    }

    public AddProductRequest(String ref, String brand, String colorway, String name, String model, String title, String year, String url, String categoryType, Gender gender, int retailPrice, String releaseDate, List<String> sizes) {
        this.ref = ref;
        this.brand = brand;
        this.colorway = colorway;
        this.name = name;
        this.model = model;
        this.title = title;
        this.year = year;
        this.url = url;
        this.categoryType = categoryType;
        this.gender = gender;
        this.retailPrice = retailPrice;
        this.releaseDate = releaseDate;
        this.sizes = sizes;
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

    public String getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(String categoryType) {
        this.categoryType = categoryType;
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

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public List<String> getSizes() {
        return sizes;
    }

    public void setSizes(List<String> sizes) {
        this.sizes = sizes;
    }
}
