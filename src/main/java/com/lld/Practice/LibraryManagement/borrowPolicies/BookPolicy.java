package com.lld.Practice.LibraryManagement.borrowPolicies;

import com.lld.Practice.LibraryManagement.BorrowPolicy;

public class BookPolicy extends BorrowPolicy {
    public BookPolicy(int borrowDuration, int delayFinePerDay, int borrowLimit) {
        super(borrowDuration, delayFinePerDay, borrowLimit);
    }
}
