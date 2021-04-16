package com.inso2.inso2.dto.bid.getAll;

import java.io.Serializable;

public class GetAllBidsResponse implements Serializable {
    private long count;
    private int price;

    public GetAllBidsResponse() {
    }

    public GetAllBidsResponse(int price, long count) {
        this.count = count;
        this.price = price;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
