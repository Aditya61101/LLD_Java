package com.lld.Practice.VendingMachine;

import java.util.Map;

public abstract class VendingState {
    protected VendingMachine vendingMachine;

    public VendingState(VendingMachine vm) {
        vendingMachine = vm;
    }
    public abstract void processMoney(PaymentMethod pm);
    public abstract void checkInventory(Map<String, Integer> userProducts);
    public abstract void dispense();
}
