package com.inso2.inso2.dto.bid;

import java.io.Serializable;

public class GetPriceBidRequest implements Serializable {
    private String ref, size;

    public GetPriceBidRequest() {
    }

    public GetPriceBidRequest(String ref, String size) {
        this.ref = ref;
        this.size = size;
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
}
