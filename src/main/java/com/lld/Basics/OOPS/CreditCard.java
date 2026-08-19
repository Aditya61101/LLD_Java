package com.lld.Basics.OOPS;

public class CreditCard extends Card {

    public CreditCard(String cardNo, String userName) {
        super(cardNo, userName);
    }

    public void pay() {
        System.out.println("Paying using Credit Card");
    }
}
