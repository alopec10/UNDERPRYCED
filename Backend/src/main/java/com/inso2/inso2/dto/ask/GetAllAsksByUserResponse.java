package com.inso2.inso2.dto.ask;

import com.inso2.inso2.model.Ask;

import java.io.Serializable;

public class GetAllAsksByUserResponse implements Serializable {
    private String date, size, ref, title, url;
    private int price;
    private long idProductDetails;

    public GetAllAsksByUserResponse() {
    }

    public GetAllAsksByUserResponse(String date, String size, String ref, String title, String url, int price, long idProductDetails) {
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

    public GetAllAsksByUserResponse build (Ask a) {
        this.price = a.getPrice();
        this.date = a.getDate().toString().substring(0,10);
        this.idProductDetails = a.getProductDetails().getIdProductDetails();
        this.size = a.getProductDetails().getSize();
        this.ref = a.getProductDetails().getProduct().getRef();
        this.title = a.getProductDetails().getProduct().getTitle();
        this.url = a.getProductDetails().getProduct().getUrl();
        return this;
    }
}
