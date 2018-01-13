package com.shivamrajput.finance.hw.shivamrajputhw.module.management.Core;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * This policy prevents users to take full amount of loan at 00:00
 * this is extra step to block users who are blocked by MaxLoanApllicationPolicy
 */
public class NextDayMaxTrialPolicy implements Policy {

    @Override
    public void execute(PolicyDTO policyDTO) {
        if (process(policyDTO)) {
            policyDTO.setAllowed(false);
            policyDTO.addMessage("Max amount requested at 00:**");
        }else {
            policyDTO.setAllowed(true);
            policyDTO.addMessage("NextDayMaxTrialPolicy:Passed");
        }
    }

    boolean process(PolicyDTO policyDTO) {
        LocalDateTime time = LocalDateTime.now();
        int h = time.getHour();
        if (h == 0 && policyDTO.getOldAmount().compareTo(MaxLoanAmountPolicy.maxAllowed) >= 0) {
            return true;
        } else {
            return false;
        }
    }
}
