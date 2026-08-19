package com.lld.SOLID.LSP.Good;

public class ReadableFile implements Readable {

    @Override
    public void read() {
        System.out.println("Reading data");
    }
}
