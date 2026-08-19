package com.lld.CreationDesignPatterns.Factory.Exercise;

public class PDF implements Document {
    @Override
    public void displayType() {
        System.out.println("Creating a PDF Document");
    }
}
