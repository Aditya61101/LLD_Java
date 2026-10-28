package com.lld.Practice.VendingMachine.PaymenyStrategies;

import com.lld.Practice.VendingMachine.PaymentMethod;

public class Cash implements PaymentMethod {
    @Override
    public int pay() {
        return 0;
    }
}
