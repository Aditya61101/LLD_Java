package com.lld.Practice.VendingMachine.PaymentStrategies;

import com.lld.Practice.VendingMachine.PaymentMethod;

public class Cash implements PaymentMethod {
    @Override
    public int pay() {
        return 0;
    }
}
