package com.shivamrajput.finance.hw.shivamrajputhw.module.management.Core;


import com.shivamrajput.finance.hw.shivamrajputhw.module.loan.service.LoanHelperService;
import com.shivamrajput.finance.hw.shivamrajputhw.module.management.domain.LoanRequestLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * this policy prevents users to get loan from an ip which already exhausted its quota
 */
public class MaxLoanApllicationPolicy implements Policy {
    private static final Logger log = LoggerFactory.getLogger(LoanHelperService.class);

    public final static int MAX_TRY = 4;
    public final static String POLICY_NAME = "max_allowed_loan_application_per_day";
    List<LoanRequestLog> logs;
    int usersLogs;
    String IP;

    public MaxLoanApllicationPolicy(List<LoanRequestLog> logs, String IP) {
      //  log.info("MaxLoanApllicationPolicy-> ip focus"+IP);
        this.logs = logs;
        this.IP = IP;
    }

    @Override
    public void execute(PolicyDTO policyDTO) {
        if (process()) {
            policyDTO.setAllowed(false);
            policyDTO.addMessage("Flaged IP (TO many requests)");
        } else {
            policyDTO.setAllowed(true);
            policyDTO.addMessage("Allowed IP");
        }
    }

    boolean process() {
        logs.forEach(l -> {
           // log.info(l.toString());
            if (l.getIP().toUpperCase().matches(this.IP.toUpperCase()) && duration(l)<=24) {
                usersLogs++;
            }
        });
        if (usersLogs > 4) {
            return true;
        } else {
            return false;
        }
    }

    long duration(LoanRequestLog l){
        LocalDateTime logTime=l.getCreateDateTime();
        LocalDateTime now=LocalDateTime.now();
        return ChronoUnit.HOURS.between(logTime,now);
    }

}
