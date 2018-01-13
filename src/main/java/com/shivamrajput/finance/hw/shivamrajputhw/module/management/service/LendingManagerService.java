package com.shivamrajput.finance.hw.shivamrajputhw.module.management.service;

import com.shivamrajput.finance.hw.shivamrajputhw.module.loan.controller.dto.LoanDTO;
import com.shivamrajput.finance.hw.shivamrajputhw.module.loan.repository.LoanRepository;
import com.shivamrajput.finance.hw.shivamrajputhw.module.loan.service.LoanService;
import com.shivamrajput.finance.hw.shivamrajputhw.module.management.Core.PolicyDTO;
import com.shivamrajput.finance.hw.shivamrajputhw.module.user.repository.UserRepository;
import com.shivamrajput.finance.hw.shivamrajputhw.module.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Use this class to encapsulate multiple versions of risk  analysis configurations
 */
@Service
public class LendingManagerService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;
    @Autowired
    LoanRepository loanRepository;
    @Autowired
    LoanService loanService;
    @Autowired
    RiskAnalysisService riskAnalysisService;


    public void riskAnalysisType1() {
        //Placeholders
    }

    public void riskAnalysisType2() {
        //Placeholders

    }

    public void riskAnalysisType3() {
        //Placeholders

    }

    public void riskAnalysisType4() {
        //Placeholders
    }

    public void whiteListUser() {
        //TODO white list users
    }

    public void blackListUsers() {
        //TODO Black list users
    }

}
