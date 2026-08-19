package com.lld.SOLID.LSP.Bad;

public class Client {
    public static void main(String[] args) {
        File file = new File();
        file.read();
        file.write();

        File readOnlyFile = new ReadOnly();
        readOnlyFile.read();
        readOnlyFile.write(); // This will throw an exception
    }
}
