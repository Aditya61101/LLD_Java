package com.lld.Practice.VendingMachine.States;

import com.lld.Practice.VendingMachine.PaymentMethod;
import com.lld.Practice.VendingMachine.VendingMachine;
import com.lld.Practice.VendingMachine.VendingState;

import java.util.Map;

public class HasMoney extends VendingState {
    public HasMoney(VendingMachine vm) {
        super(vm);
    }

    @Override
    public Map<String, Integer> checkInventory(Map<String, Integer> userProducts) {
        return vendingMachine.getInventory().check(userProducts);
//        if(checkedProducts.isEmpty()) {
//            if(userProducts.size() == 1) System.out.println("Item not available");
//            else System.out.println("Items not available");
//            vendingMachine.setState(new Dispense(vendingMachine));
//        }
    }

    @Override
    public Map<String, Integer> dispense() {
        throw new IllegalStateException("Feature not available in HasMoney state.");
    }

    @Override
    public void processMoney(PaymentMethod pm) {
        throw new IllegalStateException("Feature not available in HasMoney state.");
    }
}
