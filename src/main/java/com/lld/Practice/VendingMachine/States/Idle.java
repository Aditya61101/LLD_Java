package com.lld.Practice.VendingMachine.States;

import com.lld.Practice.VendingMachine.PaymentMethod;
import com.lld.Practice.VendingMachine.MultiVendingMachine;
import com.lld.Practice.VendingMachine.VendingState;

import java.util.Map;

public class Idle extends VendingState {
    public Idle(MultiVendingMachine vm) {
        super(vm);
    }

    @Override
    public void processMoney(PaymentMethod pm) {
        try {
            int money = pm.pay();
            vendingMachine.setMoney(money);
            vendingMachine.setState(new HasMoney(vendingMachine));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Map<String, Integer> checkInventory(Map<String, Integer> userProducts) {
        throw new IllegalStateException("Feature not available in Idle state.");
    }

    @Override
    public Map<String, Integer> dispense() {
        throw new IllegalStateException("Feature not available in Idle state.");
    }
}
