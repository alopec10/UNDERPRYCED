package com.inso2.inso2.dto.user.payment;

import java.io.Serializable;

public class PaymentMethodDeleteRequest  implements Serializable {
    private long idPaymentMethod;

    public PaymentMethodDeleteRequest() {
    }

    public PaymentMethodDeleteRequest(long idPaymentMethod) {
        this.idPaymentMethod = idPaymentMethod;
    }

    public long getIdPaymentMethod() {
        return idPaymentMethod;
    }

    public void setIdPaymentMethod(long idPaymentMethod) {
        this.idPaymentMethod = idPaymentMethod;
    }
}
