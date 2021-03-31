package com.inso2.inso2.dto.productDetails;

import com.inso2.inso2.model.ProductDetails;

import java.io.Serializable;

public class ProductDetailsResponse implements Serializable {
    private String size;
    private Integer highestBid;
    private Integer lowestAsk;
    private Integer lastSale;

    public ProductDetailsResponse build(ProductDetails p){
        this.size = p.getSize();
        this.highestBid = p.getHighestBid();
        this.lowestAsk = p.getLowestAsk();
        this.lastSale = p.getLastSale();
        return this;
    }

    public ProductDetailsResponse() {
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Integer getHighestBid() {
        return highestBid;
    }

    public void setHighestBid(Integer highestBid) {
        this.highestBid = highestBid;
    }

    public Integer getLowestAsk() {
        return lowestAsk;
    }

    public void setLowestAsk(Integer lowestAsk) {
        this.lowestAsk = lowestAsk;
    }

    public Integer getLastSale() {
        return lastSale;
    }

    public void setLastSale(Integer lastSale) {
        this.lastSale = lastSale;
    }



    @Override
    public String toString() {
        return "ProductDetailsResponse{" +
                "size='" + size + '\'' +
                ", highestBid=" + highestBid +
                ", lowestAsk=" + lowestAsk +
                ", lastSale=" + lastSale +
                '}';
    }
}
