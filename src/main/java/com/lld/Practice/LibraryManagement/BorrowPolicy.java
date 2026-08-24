package com.lld.Practice.LibraryManagement;

public class BorrowPolicy {
    private final int borrowDuration;
    private final int delayFinePerDay;
    private final int borrowLimit;

    public BorrowPolicy(int borrowDuration, int delayFinePerDay, int borrowLimit) {
        this.borrowDuration = borrowDuration;
        this.delayFinePerDay = delayFinePerDay;
        this.borrowLimit = borrowLimit;
    }

    public int getBorrowDuration() {
        return borrowDuration;
    }

    public int getDelayFinePerDay() {
        return delayFinePerDay;
    }

    public int getBorrowLimit() {
        return borrowLimit;
    }
}
