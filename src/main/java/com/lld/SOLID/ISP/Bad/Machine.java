package com.lld.SOLID.ISP.Bad;

public interface Machine {
    void print(Document doc);
    void scan(Document doc);
    void copy(Document doc);
}
