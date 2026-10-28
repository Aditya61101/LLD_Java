package com.lld.Practice.VendingMachine.Services;

import com.lld.Practice.VendingMachine.VendingMachine;

import java.util.Map;

public class InventoryService {
    VendingMachine vendingMachine;

    InventoryService(VendingMachine vm) {
        this.vendingMachine = vm;
    }
    Map<String, Integer> check(Map<String,Integer> userProducts) {
        return userProducts;
    }
}
