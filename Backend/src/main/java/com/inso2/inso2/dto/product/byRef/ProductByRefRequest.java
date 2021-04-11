package com.inso2.inso2.dto.product.byRef;

import java.io.Serializable;
import java.util.List;

public class ProductByRefRequest implements Serializable {

    private List<String> refs;

    public ProductByRefRequest() {
    }

    public ProductByRefRequest(List<String> refs) {
        this.refs = refs;
    }

    public List<String> getRefs() {
        return refs;
    }

    public void setRefs(List<String> refs) {
        this.refs = refs;
    }
}
