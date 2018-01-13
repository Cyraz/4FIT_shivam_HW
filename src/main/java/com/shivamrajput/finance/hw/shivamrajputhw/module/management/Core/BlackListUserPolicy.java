package com.shivamrajput.finance.hw.shivamrajputhw.module.management.Core;

/**
 * This policy can be used to block black listed users to take loan
 */
public class BlackListUserPolicy implements Policy {

    @Override
    public void execute(PolicyDTO policyDTO) {
        if(blackListStatus()){
            policyDTO.setAllowed(false);
            policyDTO.addMessage("BLACKLISTED");
        }else{
            policyDTO.setAllowed(true);
            policyDTO.addMessage("NOT_BLACKLISTED");
        }

    }
    private boolean blackListStatus(){
        //TODO apply blacklist operations
        return false;
    }
}
