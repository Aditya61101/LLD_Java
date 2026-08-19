package com.lld.BehaviouralDesignPatterns.StrategyPattern.With;

interface PaymentStrategy {
    void processPayment();
}

class DebitCard implements PaymentStrategy {
    @Override
    public void processPayment() {
        System.out.println("Making payment via debit card");
    }
}

class UPI implements PaymentStrategy {
    @Override
    public void processPayment() {
        System.out.println("Making payment via UPI");
    }
}

class PaymentService {
    private final PaymentStrategy ps;

    public PaymentService(PaymentStrategy ps) {
        this.ps = ps;
    }

    void pay() {
        ps.processPayment();
    }
};

public class Main {
    public static void main(String[] args) {
        PaymentService paymentService = new PaymentService(new UPI());
        paymentService.pay();
    }
}
