package com.lld.Practice.VendingMachine.States;

import com.lld.Practice.VendingMachine.*;

import java.util.Map;

public class Dispense extends VendingState {
    public Dispense(MultiVendingMachine vm) {
        super(vm);
    }

    @Override
    public Map<String, Integer> checkInventory(Map<String, Integer> userProducts) {

        throw new IllegalStateException("Feature not available in Dispense state.");
    }

    @Override
    public Map<String, Integer> dispense() {
        Order latestOrder = vendingMachine.getSession().getLatestOrder();
        Map<String,Integer> selectedProducts = latestOrder.getSelectedProducts();
        // deduct money
        this.deductMoney(selectedProducts);
        // deduct inventory
        vendingMachine.getInventory().deductProductsCount(selectedProducts);
        return selectedProducts;
    }

    @Override
    public void processMoney(PaymentMethod pm) {
        throw new IllegalStateException("Feature not available in Dispense state.");
    }

    // need denomination logic
    public void deductMoney(Map<String,Integer> selectedProducts) {
        int money = 0;
        for (Map.Entry<String, Integer> e : selectedProducts.entrySet()) {
            Product p = vendingMachine.getInventory().getProductByCode(e.getKey());
            if (p != null) {
                money += e.getValue() * p.getPrice();
            }
        }
        int deductedMoney = vendingMachine.getSession().getMoney() - money;
        vendingMachine.getSession().setMoney(deductedMoney);
    }
}