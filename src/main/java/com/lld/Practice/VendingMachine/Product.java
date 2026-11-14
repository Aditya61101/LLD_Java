package com.lld.Practice.VendingMachine;

public class Product {
    String code;
    String name;
    String productType;
    int price;
    int count;

    public Product(String code, String name, int price, int quantity) {
        this.code = code;
        this.name = name;
        this.price = price;
        this.count = quantity;
    }

    public String getCode() { return code; }
    public String getName() { return name; }

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
