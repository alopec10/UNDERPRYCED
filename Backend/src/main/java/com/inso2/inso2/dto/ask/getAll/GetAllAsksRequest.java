package com.inso2.inso2.dto.ask.getAll;

import java.io.Serializable;

public class GetAllAsksRequest implements Serializable {
    private String ref, size;

    public GetAllAsksRequest() {
    }

    public GetAllAsksRequest(String ref, String size) {
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
