package com.shivamrajput.finance.hw.shivamrajputhw.module.management.Core;

import java.math.BigDecimal;

public class PolicyDTO {

    Boolean isAllowed=true;
    Boolean isAmountModified=false;
    BigDecimal newAmount;
    BigDecimal oldAmount;
    Long personalNumber;
    String message="Risk Analysis: ";

    public void addMessage(String  msg){
        this.message=message+msg+", ";
    }

    public String getMessage() {
        return message;
    }

    public Boolean getAllowed() {
        return isAllowed;
    }

    public void setAllowed(Boolean allowed) {
        isAllowed = allowed;
    }

    public Boolean getAmountModified() {
        return isAmountModified;
    }

    public void setAmountModified(Boolean amountModified) {
        isAmountModified = amountModified;
    }

    public BigDecimal getNewAmount() {
        return newAmount;
    }

    public void setNewAmount(BigDecimal newAmount) {
        this.newAmount = newAmount;
    }

    public BigDecimal getOldAmount() {
        return oldAmount;
    }

    public void setOldAmount(BigDecimal oldAmount) {
        this.oldAmount = oldAmount;
    }

    public Long getPersonalNumber() {
        return personalNumber;
    }

    public void setPersonalNumber(Long personalNumber) {
        this.personalNumber = personalNumber;
    }

    @Override
    public String toString() {
        return "PolicyDTO{" +
                "isAllowed=" + isAllowed +
                ", isAmountModified=" + isAmountModified +
                ", newAmount=" + newAmount +
                ", oldAmount=" + oldAmount +
                ", personalNumber=" + personalNumber +
                '}';
    }
}
