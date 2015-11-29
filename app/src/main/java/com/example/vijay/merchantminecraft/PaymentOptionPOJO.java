package com.example.vijay.merchantminecraft;

import android.widget.Checkable;

/**
 * Created by Vijay on 28/11/2015.
 */
public class PaymentOptionPOJO{
    private String paymentOptionName;
    private boolean isOptionSelected;
    private int paymentOptionResource;
    private int order;

    public PaymentOptionPOJO(String paymentOptionName, boolean isOptionSelected, int paymentOptionResource, int order) {
        this.paymentOptionName = paymentOptionName;
        this.isOptionSelected = isOptionSelected;
        this.paymentOptionResource = paymentOptionResource;
        this.order = order;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public boolean isOptionSelected() {
        return isOptionSelected;
    }

    public void setOptionSelected(boolean isOptionSelected) {
        this.isOptionSelected = isOptionSelected;
    }

    public int getPaymentOptionResource() {
        return paymentOptionResource;
    }

    public void setPaymentOptionResource(int paymentOptionResource) {
        this.paymentOptionResource = paymentOptionResource;
    }

    public String getPaymentOptionName() {

        return paymentOptionName;
    }

    public void setPaymentOptionName(String paymentOptionName) {
        this.paymentOptionName = paymentOptionName;
    }

    public PaymentOptionPOJO(String paymentOptionName, boolean isOptionSelected, int paymentOptionResource) {

        this.paymentOptionName = paymentOptionName;
        this.isOptionSelected = isOptionSelected;
        this.paymentOptionResource = paymentOptionResource;
    }
}
