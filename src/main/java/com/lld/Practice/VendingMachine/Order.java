package com.lld.Practice.VendingMachine;

import java.util.Map;
import java.util.UUID;

public class Order {
    String orderId;
    Map<String, Integer> selectedProducts;
    public Order() {
        orderId = "OR-"+ UUID.randomUUID();
    }
    public void addOrders(Map<String, Integer> selectedProducts) {
        this.selectedProducts = selectedProducts;
    }
    public Map<String,Integer> getSelectedProducts() {
        return this.selectedProducts;
    }
}
