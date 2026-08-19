package com.lld.SOLID.ISP.Bad;

// violation of ISP since we are forced to implement methods which are not relevant to the class
public class SimplePrinter implements Machine {

    @Override
    public void print(Document doc) {
        System.out.println("Printing document");
    }

    @Override
    public void scan(Document doc) {
        throw new UnsupportedOperationException("Scan operation is not supported");
    }

    @Override
    public void copy(Document doc) {
        throw new UnsupportedOperationException("Copy operation is not supported");
    }
}
