package com.inso2.inso2.dto.order.get;

import com.inso2.inso2.model.Order;
import com.inso2.inso2.model.Product;

import java.io.Serializable;

public class GetOrderInformationResponse implements Serializable {
    private String orderRef, date, productRef, size, brand, colorway, title, url;
    private int price;

    public GetOrderInformationResponse() {
    }

    public GetOrderInformationResponse(String orderRef, String date, String productRef, String size, String brand, String colorway, String title, String url, int price) {
        this.orderRef = orderRef;
        this.date = date;
        this.productRef = productRef;
        this.size = size;
        this.brand = brand;
        this.colorway = colorway;
        this.title = title;
        this.url = url;
        this.price = price;
    }

    public String getOrderRef() {
        return orderRef;
    }

    public void setOrderRef(String orderRef) {
        this.orderRef = orderRef;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getProductRef() {
        return productRef;
    }

    public void setProductRef(String productRef) {
        this.productRef = productRef;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public GetOrderInformationResponse build(Order o){
        this.orderRef = o.getRef();
        this.date = o.getDate().toString().substring(0,10);
        this.price = o.getPrice();
        this.size = o.getProductDetails().getSize();
        Product p = o.getProductDetails().getProduct();
        this.productRef = p.getRef();
        this.brand = p.getBrand();
        this.colorway = p.getColorway();
        this.title = p.getTitle();
        this.url = p.getUrl();
        return this;
    }
}
