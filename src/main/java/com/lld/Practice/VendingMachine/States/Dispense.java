package com.lld.Practice.VendingMachine.States;

import com.lld.Practice.VendingMachine.PaymentMethod;
import com.lld.Practice.VendingMachine.VendingMachine;
import com.lld.Practice.VendingMachine.VendingState;

import java.util.Map;

public class Dispense extends VendingState {
    public Dispense(VendingMachine vm) {
        super(vm);
    }

    @Override
    public void checkInventory(Map<String, Integer> userProducts) {

    }

    @Override
    public void dispense() {

    }

    @Override
    public void processMoney(PaymentMethod pm) {

    }
}