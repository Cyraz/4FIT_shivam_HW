package com.shivamrajput.finance.hw.shivamrajputhw.module.loan.controller.dto;

import java.math.BigDecimal;

public class PayDTO {

    BigDecimal amount;
    Long personalNumber;


    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Long getPersonalNumber() {
        return personalNumber;
    }

    public void setPersonalNumber(Long personalNumber) {
        this.personalNumber = personalNumber;
    }

    @Override
    public String toString() {
        return "PayDTO{" +
                "amount=" + amount +
                ", personalNumber=" + personalNumber +
                '}';
    }
}
