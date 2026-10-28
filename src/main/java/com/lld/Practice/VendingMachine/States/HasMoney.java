package com.lld.Practice.VendingMachine.States;

import com.lld.Practice.VendingMachine.PaymentMethod;
import com.lld.Practice.VendingMachine.Services.InventoryService;
import com.lld.Practice.VendingMachine.VendingMachine;
import com.lld.Practice.VendingMachine.VendingState;

import java.util.Map;

public class HasMoney extends VendingState {
    InventoryService inventoryService;
    public HasMoney(VendingMachine vm) {
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
