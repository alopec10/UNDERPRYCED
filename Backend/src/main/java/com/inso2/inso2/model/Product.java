package com.inso2.inso2.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "IdProduct")
    private long idProduct;

    @Size(min = 1, max = 20)
    @Column(name = "Ref", length = 20, nullable = false)
    private String ref;

    @Size(min = 1, max = 16)
    @Column(name = "Brand", length = 16, nullable = false)
    private String brand;

    @Size(min = 1, max = 16)
    @Column(name = "Colorway", length = 16, nullable = false)
    private String colorway;

    @Size(min = 1, max = 20)
    @Column(name = "Name", length = 20, nullable = false)
    private String name;

    @Size(min = 1, max = 20)
    @Column(name = "Model", length = 20, nullable = false)
    private String model;

    @Size(min = 1, max = 30)
    @Column(name = "Title", length = 30, nullable = false)
    private String title;

    @Size(min = 4, max = 4)
    @Column(name = "Year", length = 4, nullable = false)
    private String year;

    @Size(min = 6, max = 300)
    @Column(name = "URL", length = 300, nullable = false)
    private String url;

    @Enumerated(EnumType.STRING)
    @Column(name = "Gender", nullable = false)
    private Gender gender;

    @Column(name = "RetailPrice", nullable = false)
    private int retailPrice;

    @Column(name = "ReleaseDate", columnDefinition="DATETIME", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date releaseDate;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "IdCategory", nullable = false)
    private Category category;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="product")
    @JsonIgnore
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<ProductDetails> productDetails;

    public Product() {
    }

    public Product(@Size(min = 1, max = 20) String ref, @Size(min = 1, max = 16) String brand, @Size(min = 1, max = 16) String colorway, @Size(min = 1, max = 20) String name, @Size(min = 1, max = 20) String model, @Size(min = 1, max = 30) String title, @Size(min = 4, max = 4) String year, @Size(min = 6, max = 300) String url, Gender gender, int retailPrice, Date releaseDate, Category category) {
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

    @Override
    public String toString() {
        return "Product{" +
                "idProduct=" + idProduct +
                ", ref='" + ref + '\'' +
                ", brand='" + brand + '\'' +
                ", colorway='" + colorway + '\'' +
                ", name='" + name + '\'' +
                ", model='" + model + '\'' +
                ", title='" + title + '\'' +
                ", year='" + year + '\'' +
                ", url='" + url + '\'' +
                ", gender=" + gender +
                ", retailPrice=" + retailPrice +
                ", releaseDate=" + releaseDate +
                ", category=" + category +
                ", productDetails=" + productDetails +
                '}';
    }
}
