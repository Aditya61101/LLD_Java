package com.lld.Practice.VendingMachine.States;

import com.lld.Practice.VendingMachine.PaymentMethod;
import com.lld.Practice.VendingMachine.MultiVendingMachine;
import com.lld.Practice.VendingMachine.VendingState;

import java.util.Map;

public class OutOfOrder extends VendingState {
    public OutOfOrder(MultiVendingMachine vm) {
        super(vm);
    }

    @Override
    public Map<String, Integer> checkInventory(Map<String, Integer> userProducts) {
        throw new RuntimeException("Machine is out of order, please try after sometime.");
    }

    @Override
    public Map<String, Integer> dispense() {
        throw new RuntimeException("Machine is out of order, please try after sometime.");
    }

    @Override
    public void processMoney(PaymentMethod pm) {
        throw new RuntimeException("Machine is out of order, please try after sometime.");
    }
}