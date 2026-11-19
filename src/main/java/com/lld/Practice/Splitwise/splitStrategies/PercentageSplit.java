package com.lld.Practice.Splitwise.splitStrategies;

import com.lld.Practice.Splitwise.SplitStrategy;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class PercentageSplit implements SplitStrategy {
    private static final Currency CURRENCY = Currency.getInstance("INR");

    @Override
    public Map<String, BigDecimal> split(BigDecimal amount, List<String> participants, Map<String, BigDecimal> allocations) {
        int scale = CURRENCY.getDefaultFractionDigits();

        if (!this.validate(amount, allocations, scale)) throw new IllegalArgumentException("Invalid percentage split");

        Map<String, BigDecimal> shares = new LinkedHashMap<>();
        Map<String, BigDecimal> remainders = new HashMap<>();

        BigDecimal totalAllocated = BigDecimal.ZERO;

        // Step 1: Calculate each participant's share, rounding DOWN to whole paise.
        for (String userId : participants) {
            // exactShare = 125.456, roundedShare = 125.45
            BigDecimal exactShare = amount.multiply(allocations.get(userId)).divide(BigDecimal.valueOf(100));
            BigDecimal roundedShare = exactShare.setScale(scale, RoundingMode.DOWN);
            // shares stores roundedShare
            shares.put(userId, roundedShare);
            remainders.put(userId, exactShare.subtract(roundedShare));

            totalAllocated = totalAllocated.add(roundedShare);
        }

        // Step 2: Calculate leftover paise.
        int extraUnits = amount.subtract(totalAllocated)
                .movePointRight(scale)
                .intValueExact();

        /*
        Step 3: Sort participants by fractional remainder,
        largest first. Stable sort preserves original order
        when fractional remainders are equal.
        */
        List<String> sortedParticipants = new ArrayList<>(participants);
        sortedParticipants.sort((a,b) -> remainders.get(b).compareTo(remainders.get(a)));

        // Step 4: Give one extra paisa to each of the
        // participants with the largest remainders.
        BigDecimal unit = BigDecimal.ONE.movePointLeft(scale);
        for(int i=0; i<extraUnits; i++) {
            String userId = sortedParticipants.get(i);
            shares.put(userId, shares.get(userId).add(unit));
        }

        return shares;
    }

    private boolean validate(BigDecimal amount, Map<String, BigDecimal> allocations, int scale) {
        try {
            // setScale(2) means represent the number with two digits after the decimal point.
            // example 100->100.00, 100.2 -> 100.20, 100.055 -> raises exception
            amount.setScale(scale, RoundingMode.UNNECESSARY);
        } catch (ArithmeticException e) {
            return false;
        }

        BigDecimal totalPercentage = BigDecimal.ZERO;
        for (BigDecimal allocation : allocations.values()) {
            if (allocation == null || allocation.signum() <= 0) return false;
            totalPercentage = totalPercentage.add(allocation);
        }

        return totalPercentage.compareTo(BigDecimal.valueOf(100)) == 0;
    }
}
