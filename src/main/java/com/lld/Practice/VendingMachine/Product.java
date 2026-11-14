package com.lld.Practice.VendingMachine;

public class Product {
    String code;
    String name;
    String productType;
    int price;
    int count;

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return count;
    }
    public void setQuantity(int quantity) {
        this.count = quantity;
    }
}
