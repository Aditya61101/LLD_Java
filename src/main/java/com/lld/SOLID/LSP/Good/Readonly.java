package com.lld.SOLID.LSP.Good;

public class Readonly implements Readable {
    @Override
    public void read() {
        System.out.println("Reading data");
    }
}
