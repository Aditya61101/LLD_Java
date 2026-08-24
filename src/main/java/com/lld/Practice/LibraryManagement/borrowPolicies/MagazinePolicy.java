package com.lld.Practice.LibraryManagement.borrowPolicies;

import com.lld.Practice.LibraryManagement.BorrowPolicy;

public class MagazinePolicy extends BorrowPolicy {
    public MagazinePolicy(int borrowDuration, int delayFinePerDay, int borrowLimit) {
        super(borrowDuration, delayFinePerDay, borrowLimit);
    }
}
