package com.shivamrajput.finance.hw.shivamrajputhw.module.management.Core;

import com.shivamrajput.finance.hw.shivamrajputhw.module.loan.service.LoanHelperService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

/**
 * This policy restrict users to to take loan under the allowed amount
 */
public class MaxLoanAmountPolicy implements Policy {
    private static final Logger log = LoggerFactory.getLogger(LoanHelperService.class);

    public final static  BigDecimal maxAllowed = BigDecimal.valueOf(2000);


    @Override
    public void execute(PolicyDTO policyDTO) {
      //  log.info(policyDTO.toString());
        if (policyDTO.getOldAmount().compareTo(maxAllowed) == 1) {
            policyDTO.newAmount = maxAllowed;
            policyDTO.setAmountModified(true);
            policyDTO.addMessage("Amount reduced to max limit");
        }
    }
}
