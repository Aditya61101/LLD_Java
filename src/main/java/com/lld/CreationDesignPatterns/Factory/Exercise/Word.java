package com.lld.CreationDesignPatterns.Factory.Exercise;

public class Word implements Document {
    @Override
    public void displayType() {
        System.out.println("Creating a Word Document");
    }
}
