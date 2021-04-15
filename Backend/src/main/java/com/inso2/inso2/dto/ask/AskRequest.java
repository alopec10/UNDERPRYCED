package com.inso2.inso2.dto.ask;

import java.io.Serializable;

public class AskRequest implements Serializable {
    private String ref, size;
    private int price;

    public AskRequest() {
    }

    public AskRequest(String ref, String size, int price) {
        this.ref = ref;
        this.size = size;
        this.price = price;
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
