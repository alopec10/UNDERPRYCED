package com.inso2.inso2.dto.bid.getAll;

import java.io.Serializable;

public class GetAllBidsRequest implements Serializable {
    private String ref, size;

    public GetAllBidsRequest() {
    }

    public GetAllBidsRequest(String ref, String size) {
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
