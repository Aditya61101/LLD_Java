package com.lld.Practice.Splitwise.splitStrategies;

import com.lld.Practice.Splitwise.SplitStrategy;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class EqualSplit implements SplitStrategy {
    @Override
    public Map<String, BigDecimal> split(BigDecimal amount, List<String> participants, Map<String, BigDecimal> allocations, int scale) {
        // LinkedHashMap preserves participant insertion order, making remainder distribution deterministic.
        Map<String, BigDecimal> shares = new LinkedHashMap<>();
        BigDecimal unit = BigDecimal.ONE.movePointLeft(scale);
        /*
            Argument	            Meaning
            BigDecimal.valueOf(3)	Divide by 3 participants
            scale	                Keep 2 digits after the decimal point
            RoundingMode.DOWN	    Discard extra decimal digits toward zero

            example: amount=100, baseShare=33.33
        * */
        BigDecimal baseShare = amount.divide(
                BigDecimal.valueOf(participants.size()),
                scale,
                RoundingMode.DOWN
        );
        //remainder = (100-33.33*3)=0.01
        BigDecimal remainder = amount.subtract(
                baseShare.multiply(BigDecimal.valueOf(participants.size()))
        );
        // movePointRight(scale) moves the decimal point scale places right
        // intValueExact() converts the BigDecimal to an int
        int extraUnits = remainder.movePointRight(scale).intValueExact();

        for (int i = 0; i < participants.size(); i++) {
            BigDecimal share = baseShare;
            // deterministic remainder distribution
            if (i < extraUnits) {
                share = share.add(unit);
            }

            shares.put(participants.get(i), share);
        }
        return shares;
    }

//    private boolean validate(BigDecimal amount, int scale) {
//        return true;
//    }
}
