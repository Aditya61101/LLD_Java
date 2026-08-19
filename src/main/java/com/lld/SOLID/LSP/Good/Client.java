package com.lld.SOLID.LSP.Good;

public class Client {
    public static void main(String[] args) {
        Writable file = new WritableFile();
        file.read();
        file.write();

        Readable readOnlyFile = new Readonly();
        readOnlyFile.read();
    }
}
