package com.lld.CreationDesignPatterns.Factory.Problem;

public class Car implements Transport {
    @Override
    public void deliver() {
        System.out.println("Deliver by Car");
    }
}
