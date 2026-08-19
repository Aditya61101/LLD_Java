package com.lld.SOLID.OCP.Good;

// OCP: A entity(class, function, modules) should be open for extension but closed for modification.
public interface PaymentMethod {
    void pay(double amount);
}
