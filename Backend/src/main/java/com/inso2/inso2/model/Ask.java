package com.inso2.inso2.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "asks")
public class Ask {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "IdAsk")
    private long idAsk;

    @Column(name = "Price", nullable = false)
    private int price;

    @Column(name = "Date", columnDefinition="DATETIME",  nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @Column(name = "Completed", nullable = false)
    private boolean completed;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "IdUser", nullable = false)
    private User user;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "IdProductDetails", nullable = false)
    private ProductDetails productDetails;

    public Ask() {
    }

    public Ask(int price, Date date, boolean completed, User user, ProductDetails productDetails) {
        this.price = price;
        this.date = date;
        this.completed = completed;
        this.user = user;
        this.productDetails = productDetails;
    }

    public long getIdAsk() {
        return idAsk;
    }

    public void setIdAsk(long idAsk) {
        this.idAsk = idAsk;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ProductDetails getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(ProductDetails productDetails) {
        this.productDetails = productDetails;
    }

    @Override
    public String toString() {
        return "Ask{" +
                "idAsk=" + idAsk +
                ", price=" + price +
                ", date=" + date +
                ", completed=" + completed +
                ", user=" + user +
                ", productDetails=" + productDetails +
                '}';
    }
}
