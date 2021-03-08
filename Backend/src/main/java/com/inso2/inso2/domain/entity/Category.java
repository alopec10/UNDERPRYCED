package com.inso2.inso2.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "IdCategory")
    private long idCategory;

    private String type;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="category")
    @JsonIgnore
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<Product> products;

    public Category() {
    }

    public Category(String type) {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return getIdCategory() == category.getIdCategory() &&
                Objects.equals(getType(), category.getType()) &&
                Objects.equals(getProducts(), category.getProducts());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdCategory(), getType(), getProducts());
    }

    @Override
    public String toString() {
        return "Category{" +
                "idUser=" + idCategory +
                ", type='" + type + '\'' +
                ", products=" + products +
                '}';
    }
}
