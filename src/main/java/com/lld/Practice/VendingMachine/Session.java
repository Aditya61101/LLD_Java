package com.lld.Practice.VendingMachine;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Session {
    private final String sessionId;
    private final List<Order>orders;
    private int money;
    private boolean isSessionActive;

    Session() {
        sessionId = "SE-"+ UUID.randomUUID();
        orders = new ArrayList<>();
        isSessionActive = true;
    }
    void createOrder(Map<String, Integer> up) {
        if(!getSessionStatus()) {
            throw new RuntimeException("Money not provided.");
        }
        Order newOrder = new Order();
        newOrder.addOrders(up);
        orders.add(newOrder);
    }
    void insertMoney(int money) { this.money = money; }

    public int getMoney() { return this.money; }
    public void setMoney(int money) {this.insertMoney(money);}

    public Order getLatestOrder() {
        if(orders.isEmpty()) throw new IllegalStateException("No order placed");
        return orders.getLast();
    }
    public boolean getSessionStatus() { return this.isSessionActive; }
    public void closeSession() { this.isSessionActive = false; }
}
