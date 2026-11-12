package com.lld.Practice.VendingMachine;

import java.util.HashMap;
import java.util.Map;

public class Inventory {
    Map<String,Product> availableProducts;

    public Map<String,Integer> check(Map<String,Integer> userProducts) {
        Map<String, Integer> finalUserProducts = new HashMap<>();
        userProducts.forEach((k,v) -> {
            Product product = availableProducts.get(k);
            int availableCount = product.count;
            if(availableCount >= v) {
                finalUserProducts.put(k,v);
            } else if (availableCount == 0){
                System.out.println("⚠️ " + "Sorry, '" + product.name + "' is completely sold out.");
            } else {
                System.out.println("Limited stock! Only " + availableCount + " left of '" + product.name + "' (You requested " + v + ").");
                finalUserProducts.put(k,availableCount);
            }
        });
        return finalUserProducts;
    }

    public Product getProductByCode(String code) {
        return availableProducts.get(code);
    }
    public boolean productExists(String code) {
        return availableProducts.containsKey(code);
    }
    public void deductProductsCount(Map<String,Integer> userProducts) {

    }

    public void showAvailableProducts() {
        // show product list with quantities and cost
    }
}
