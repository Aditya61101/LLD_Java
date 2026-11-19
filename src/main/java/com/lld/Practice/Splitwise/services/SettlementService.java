package com.lld.Practice.Splitwise.services;

import com.lld.Practice.Splitwise.Group;
import com.lld.Practice.Splitwise.Settlement;
import com.lld.Practice.Splitwise.repositories.GroupRepository;
import com.lld.Practice.Splitwise.repositories.UserRepository;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class SettlementService {
    private final GroupRepository groupRepository;
    private final UserRepository userRepository;

    public SettlementService(GroupRepository groupRepository, UserRepository userRepository) {
        this.groupRepository = groupRepository;
        this.userRepository = userRepository;
    }
    public void createSettlement(String payerId, String payeeId, BigDecimal amount, String groupId) {
        try {
            // 1. amount should be positive
            if(amount.compareTo(BigDecimal.ZERO) <= 0) throw new RuntimeException("Expense amount should be positive");
            // 2. get group or throws exception
            Group group = groupRepository.getGroup(groupId);
            // 3. checks payer and payee existence
            this.checkParticipantsExistence(Arrays.asList(payerId, payeeId), group);
            // 4. check whether amount <= actual debt of payee on payer
            group.checkSettlementValidity(payerId, payeeId, amount);
            // 5. create settlement instance
            Settlement settlement = new Settlement(payerId, payeeId, amount);
            // 7. record settlement
            group.recordSettlement(settlement);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    private void checkParticipantsExistence(List<String> participants, Group group) {
        for(String userId:participants) {
            if(!userRepository.userExistsById(userId)) throw new IllegalArgumentException("Participant doesn't exists");
            if(!group.isGroupMember(userId)) throw new IllegalArgumentException("Participant not a member of the group");
        }
    }
}
