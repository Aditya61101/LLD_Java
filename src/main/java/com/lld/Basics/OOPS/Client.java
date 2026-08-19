package com.lld.Basics.OOPS;

public class Client {
    public static void main(String[] args) {
        PaymentService ps = new PaymentService();
        ps.addPaymentMethod("aditya_debit_card", new DebitCard("1234-5678-9012-3456", "ADITYA KUMAR"));
        ps.addPaymentMethod("aditya_upi", new UPI("aditya@upi"));

        ps.makePayment("aditya_debit_card");
    }
}
