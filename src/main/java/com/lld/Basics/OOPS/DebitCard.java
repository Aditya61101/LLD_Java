package com.lld.Basics.OOPS;

public class DebitCard extends Card {
    DebitCard(String cardNo, String userName) {
        super(cardNo, userName);
    }

    public void pay() {
        System.out.println("Paying using Debit Card");
    }
}
