package com.lld.SOLID.LSP.Bad;

public class ReadOnly extends File {
    @Override
    public void write() {
        throw new UnsupportedOperationException("Cannot write to a read-only file");
    }
}
