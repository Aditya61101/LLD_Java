package com.lld.SOLID.LSP.Good;

public class Client {
    public static void readAnyFile(ReadableFile file) {
        file.read();
    }
    public static void main(String[] args) {
        WritableFile writableFile  = new WritableFile();
        writableFile.read();
        writableFile.write();

        ReadableFile readOnlyFile = new Readonly();
        readOnlyFile.read();

        readAnyFile(writableFile);
        readAnyFile(readOnlyFile);
    }
}
