package com.lld.SOLID.ISP.Bad;

public class MultiPurposeMachine implements Machine {
    @Override
    public void print(Document doc) {

    }

    @Override
    public void scan(Document doc) {

    }

    @Override
    public void copy(Document doc) {

    }
}
