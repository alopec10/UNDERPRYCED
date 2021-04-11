package com.inso2.inso2.dto.ask.remove;

import java.io.Serializable;

public class RemoveAskRequest implements Serializable {
    private long idProductDetails;

    public RemoveAskRequest() {
    }

    public RemoveAskRequest(long idProductDetails) {
        this.idProductDetails = idProductDetails;
    }

    public long getIdProductDetails() {
        return idProductDetails;
    }

    public void setIdProductDetails(long idProductDetails) {
        this.idProductDetails = idProductDetails;
    }
}
