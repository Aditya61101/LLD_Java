package com.lld.Practice.Splitwise.services;

import com.lld.Practice.Splitwise.Group;
import com.lld.Practice.Splitwise.Settlement;
import com.lld.Practice.Splitwise.repositories.GroupRepository;
import com.lld.Practice.Splitwise.repositories.UserRepository;

import java.math.BigDecimal;
import java.util.Currency;

public class SettlementService {
    private final GroupRepository groupRepository;
    private final UserRepository userRepository;

    public SettlementService(GroupRepository groupRepository, UserRepository userRepository) {
        this.groupRepository = groupRepository;
        this.userRepository = userRepository;
    }
    public void createSettlement(String payerId, String payeeId, BigDecimal amount, String groupId, String currency) {
            // get group or throws exception
            Group group = groupRepository.getGroup(groupId);
            // checks payer and payee existence
            if(!userRepository.userExistsById(payerId)) throw new IllegalArgumentException("Payer doesn't exists");
            if(!userRepository.userExistsById(payeeId)) throw new IllegalArgumentException("Payee doesn't exists");

            // 5. create settlement instance
            Currency CURRENCY = Currency.getInstance(currency);
            Settlement settlement = new Settlement(payerId, payeeId, amount, CURRENCY);
            // 7. record settlement
            group.recordSettlement(settlement);
    }
}
