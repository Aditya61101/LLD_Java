package com.lld.SOLID.ISP.Good;

public class SimplePrinter implements Printer {

    @Override
    public void print(Document doc) {
        System.out.println("Printing document");
    }
}
