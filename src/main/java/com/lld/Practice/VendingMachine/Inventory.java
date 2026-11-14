package com.lld.Practice.VendingMachine;

import java.util.HashMap;
import java.util.Map;

public class Inventory {
    private final Map<String,Product> availableProducts = new HashMap<>();

    public void addProduct(Product product) {
        availableProducts.put(product.getCode(), product);
    }
    public Map<String,Integer> check(Map<String,Integer> userProducts) {
        Map<String, Integer> finalUserProducts = new HashMap<>();
        userProducts.forEach((k,v) -> {
            Product product = availableProducts.get(k);
            int availableCount = product.count;
            if(v > 0 && availableCount >= v) {
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
        for (Map.Entry<String, Integer> entry : userProducts.entrySet()) {
            String productName = entry.getKey();
            int qty = entry.getValue();

            Product product = availableProducts.get(productName);
            if (product == null) {
                throw new IllegalArgumentException("Unknown product: " + productName);
            }
            if (product.getQuantity() < qty) {
                throw new IllegalStateException("Insufficient stock for " + productName
                        + ". Available: " + product.getQuantity());
            }
            product.setQuantity(product.getQuantity() - qty);
        }
    }

    public void showAvailableProducts() {
        // show product list with quantities and cost
        System.out.println("=== Available Products ===");
        System.out.println("Product\t\tPrice\t\tStock");
        System.out.println("------------------------------------------");
        for (Map.Entry<String, Product> entry : availableProducts.entrySet()) {
            Product p = entry.getValue();
            System.out.printf("%-14s₹%-8d%d%n", p.getName(), p.getPrice(), p.getQuantity());
        }
        System.out.println("==========================================");
    }
}
