package com.inso2.inso2.dto.order.create;

import java.io.Serializable;

public class CreateOrderSellRequest implements Serializable {
    private long idPayMethod;
    private String ref, size;
    private int price;

    public CreateOrderSellRequest() {
    }

    public CreateOrderSellRequest(long idPayMethod, String ref, String size, int price) {
        this.idPayMethod = idPayMethod;
        this.ref = ref;
        this.size = size;
        this.price = price;
    }

    public long getIdPayMethod() {
        return idPayMethod;
    }

    public void setIdPayMethod(long idPayMethod) {
        this.idPayMethod = idPayMethod;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
