package com.lld.SOLID.LSP.Good;

public class WritableFile extends ReadableFile implements Writable {
    @Override
    public void write() {
        System.out.println("Writing data to writable file");
    }
}
