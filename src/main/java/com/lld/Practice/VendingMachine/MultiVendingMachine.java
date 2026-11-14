package com.lld.Practice.VendingMachine;

import com.lld.Practice.VendingMachine.States.Dispense;
import com.lld.Practice.VendingMachine.States.Idle;

import java.util.Map;

public class MultiVendingMachine implements VendingMachine {
    private VendingState state;
    private Session session;
    public Inventory inventory;

    public MultiVendingMachine() {
        state = new Idle(this);
        inventory = new Inventory();
    }
    // interface method
    public void setState(VendingState newState) {
        this.state = newState;
    }
    // interface method
    public void processMoney(PaymentMethod pm) {
        state.processMoney(pm);
    }

    public void setMoney(int money) {
        session = new Session();
        session.insertMoney(money);
    }
    // interface method
    public Session getSession() {
        return this.session;
    }

    // interface method
    public SelectionStatus placeOrder(Map<String, Integer> products) {
        for (Map.Entry<String, Integer> e : products.entrySet()) {
            if (!this.getInventory().productExists(e.getKey())) {
                return SelectionStatus.INVALID_PRODUCT;
            }
        }
        Map<String,Integer> checkedProducts = state.checkInventory(products);
        if(checkedProducts.isEmpty()) return SelectionStatus.OUT_OF_STOCK;

        session.createOrder(checkedProducts);
        Map<String, Integer> result = this.dispense();
        System.out.println("Dispensed products:");
        result.forEach((product, qty) ->
                System.out.println("  " + product + " -> " + qty)
        );
        this.reset();
        return SelectionStatus.SUCCESS;
    }
    // interface method
    public Inventory getInventory() { return this.inventory; }

    public int returnMoney() {
        if(!session.getSessionStatus()) throw new RuntimeException("Money not provided");
        setState(new Dispense(this));
        session.closeSession();
        return this.session.getMoney();
    }
    private Map<String, Integer> dispense() {
        setState(new Dispense(this));
        return state.dispense();
    }

    public void reset() {
        setState(new Idle(this));
    }
    public void showAvailableProducts() {
        this.getInventory().showAvailableProducts();
    }
    public boolean isSessionActive() {return this.session.getSessionStatus(); }
}
