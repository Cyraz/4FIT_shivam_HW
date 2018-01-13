package com.shivamrajput.finance.hw.shivamrajputhw.module.management.service;


import com.shivamrajput.finance.hw.shivamrajputhw.module.loan.controller.dto.LoanDTO;
import com.shivamrajput.finance.hw.shivamrajputhw.module.management.Core.*;
import com.shivamrajput.finance.hw.shivamrajputhw.module.management.repository.LoanRequestLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * this service uses different policies and  calculates the risk
 */

@Service
public class RiskAnalysisService {

    @Autowired
    LoanRequestLogRepository loanRequestLogRepository;

    public interface riskAnalysisCallback {
        void passed(PolicyDTO policyDTO);

        void failed(PolicyDTO policyDTO);
    }

    private void setUp() {

    }

    public void performAnalysis(LoanDTO loanDTO, riskAnalysisCallback callback) {
        PolicyBuilder policyBuilder = new PolicyBuilder(loanDTO);
        policyBuilder.add(new BlackListUserPolicy())
                .add(new MaxLoanAmountPolicy())
                .add(new MaxLoanApllicationPolicy(loanRequestLogRepository.findAll(), loanDTO.getIP()))
        .add(new NextDayMaxTrialPolicy());
        policyBuilder.exicute();
        PolicyDTO policyDTO = policyBuilder.getPolicyDTO();
        if (policyDTO.getAllowed()) {
            callback.passed(policyDTO);
        } else {
            callback.failed(policyDTO);
        }
    }

}
