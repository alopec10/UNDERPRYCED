package com.inso2.inso2.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "IdProduct")
    private long idProduct;

    private String ref, brand, colorway, name, model, title, year, url;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "RetailPrice")
    private int retailPrice;

    @Column(name = "ReleaseDate", columnDefinition="DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date releaseDate;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "IdCategory")
    private Category category;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="product")
    @JsonIgnore
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<ProductDetails> productDetails;

    public Product() {
    }

    public Product(String ref, String brand, String colorway, String name, String model, String title, String year, String url, Gender gender, int retailPrice, Date releaseDate, Category category) {
        this.ref = ref;
        this.brand = brand;
        this.colorway = colorway;
        this.name = name;
        this.model = model;
        this.title = title;
        this.year = year;
        this.url = url;
        this.gender = gender;
        this.retailPrice = retailPrice;
        this.releaseDate = releaseDate;
        this.category = category;
    }

    public long getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(long idProduct) {
        this.idProduct = idProduct;
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

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<ProductDetails> getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(List<ProductDetails> productDetails) {
        this.productDetails = productDetails;
    }
}
