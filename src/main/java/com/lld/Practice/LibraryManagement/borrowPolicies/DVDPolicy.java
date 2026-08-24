package com.lld.Practice.LibraryManagement.borrowPolicies;

import com.lld.Practice.LibraryManagement.BorrowPolicy;

public class DVDPolicy extends BorrowPolicy {
    public DVDPolicy(int borrowDuration, int delayFinePerDay, int borrowLimit) {
        super(borrowDuration, delayFinePerDay, borrowLimit);
    }
}
