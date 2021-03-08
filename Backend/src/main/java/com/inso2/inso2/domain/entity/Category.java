package com.inso2.inso2.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "IdCategory")
    private long idCategory;

    @Size(min = 1, max = 15)
    @Column(name = "Type", length = 15, nullable = false)
    private String type;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="category")
    @JsonIgnore
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<Product> products;

    public Category() {
    }

    public Category(@Size(min = 1, max = 15) String type) {
        this.type = type;
    }

    public long getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(long idCategory) {
        this.idCategory = idCategory;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Category{" +
                "idCategory=" + idCategory +
                ", type='" + type + '\'' +
                ", products=" + products +
                '}';
    }
}
