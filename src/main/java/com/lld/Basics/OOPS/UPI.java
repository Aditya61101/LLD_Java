package com.lld.Basics.OOPS;

public class UPI implements PaymentMethod {
    private String upiId;

    UPI(String upiId) {
        this.upiId = upiId;
    }

    public void pay() {
        System.out.println("Paying using UPI "+upiId);
    }
}
