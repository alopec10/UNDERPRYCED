package com.inso2.inso2.dto.bid;

import com.inso2.inso2.model.Bid;

import java.io.Serializable;

public class GetAllBidsByUserResponse implements Serializable {
    private String date, size, ref, title, url;
    private int price;
    private long idProductDetails;

    public GetAllBidsByUserResponse() {
    }

    public GetAllBidsByUserResponse(String date, String size, String ref, String title, String url, int price, long idProductDetails) {
        this.date = date;
        this.size = size;
        this.ref = ref;
        this.title = title;
        this.url = url;
        this.price = price;
        this.idProductDetails = idProductDetails;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
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

    public long getIdProductDetails() {
        return idProductDetails;
    }

    public void setIdProductDetails(long idProductDetails) {
        this.idProductDetails = idProductDetails;
    }

    public GetAllBidsByUserResponse build (Bid b) {
        this.price = b.getPrice();
        this.date = b.getDate().toString().substring(0,10);
        this.idProductDetails = b.getProductDetails().getIdProductDetails();
        this.size = b.getProductDetails().getSize();
        this.ref = b.getProductDetails().getProduct().getRef();
        this.title = b.getProductDetails().getProduct().getTitle();
        this.url = b.getProductDetails().getProduct().getUrl();
        return this;
    }
}
