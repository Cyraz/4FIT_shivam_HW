package com.shivamrajput.finance.hw.shivamrajputhw.module.management.Core;


import com.shivamrajput.finance.hw.shivamrajputhw.module.loan.controller.dto.LoanDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Policy Builder, It bundles all policies and excites them against loan request
 */
public class PolicyBuilder {
    private List<Policy> policyList=new ArrayList<>();
    private PolicyDTO policyDTO=new PolicyDTO();

    public PolicyBuilder(LoanDTO loanDTO) {
        this.policyDTO.setOldAmount(loanDTO.getAmount());
        this.policyDTO.setNewAmount(loanDTO.getAmount());
        this.policyDTO.setPersonalNumber(loanDTO.getPersonalNumber());
    }

    public PolicyBuilder add(Policy policy) {
        this.policyList.add(policy);
        return this;
    }

    public void exicute(){
        policyList.forEach(p->{
            p.execute(this.policyDTO);
        });
    }

    public PolicyDTO getPolicyDTO() {
        return policyDTO;
    }
}