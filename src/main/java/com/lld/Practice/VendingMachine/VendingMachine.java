package com.lld.Practice.VendingMachine;

import com.lld.Practice.VendingMachine.States.Idle;

public class VendingMachine {
    VendingState state;
    private int money;
    VendingMachine(VendingState vs) {
        state = vs;

    }
    public void setState(VendingState newState) {
        this.state = newState;
    }
    public void processMoney(PaymentMethod pm) {
        state.processMoney(pm);
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getMoney() {
        return this.money;
    }
}
