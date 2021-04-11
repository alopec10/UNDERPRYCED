package com.inso2.inso2.dto.ask;

import java.io.Serializable;

public class AskRequest implements Serializable {
    private long idProductDetails;
    private int price;

    public AskRequest() {
    }

    public AskRequest(long idProductDetails, int price) {
        this.idProductDetails = idProductDetails;
        this.price = price;
    }

    public long getIdProductDetails() {
        return idProductDetails;
    }

    public void setIdProductDetails(long idProductDetails) {
        this.idProductDetails = idProductDetails;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
