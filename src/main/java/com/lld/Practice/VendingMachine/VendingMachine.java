package com.lld.Practice.VendingMachine;

import java.util.Map;

public interface VendingMachine {
    public void setState(VendingState newState);
    public void processMoney(PaymentMethod pm);
    public SelectionStatus placeOrder(Map<String, Integer> products);
    public Session getSession();
    public Inventory getInventory();
    public void setMoney(int money);
    public void showAvailableProducts();
    public boolean isSessionActive();
    public int returnMoney();
}
