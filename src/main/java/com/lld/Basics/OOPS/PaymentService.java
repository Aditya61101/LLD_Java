package com.lld.Basics.OOPS;

import java.util.HashMap;

public class PaymentService {
    HashMap<String, PaymentMethod> paymentMethods = new HashMap<>();
    // store payment methods
    public void addPaymentMethod(String key, PaymentMethod pm) {
        paymentMethods.put(key, pm);
    }
    // make payment
    public void makePayment(String key) {
        PaymentMethod pm = paymentMethods.get(key);
        // runtime polymorphism,
        // since which pay method to call is decided when we get the actual payment method
        pm.pay();
    }
}
