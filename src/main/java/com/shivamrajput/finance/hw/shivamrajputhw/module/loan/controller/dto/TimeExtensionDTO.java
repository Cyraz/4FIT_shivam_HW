package com.shivamrajput.finance.hw.shivamrajputhw.module.loan.controller.dto;

public class TimeExtensionDTO {
    private Long personalNumber;
    private Long loanId;
    private int extraTerm;

    public Long getPersonalNumber() {
        return personalNumber;
    }

    public void setPersonalNumber(Long personalNumber) {
        this.personalNumber = personalNumber;
    }

    public Long getLoanId() {
        return loanId;
    }

    public void setLoanId(Long loanId) {
        this.loanId = loanId;
    }

    public int getExtraTerm() {
        return extraTerm;
    }

    public void setExtraTerm(int extraTerm) {
        this.extraTerm = extraTerm;
    }

    @Override
    public String toString() {
        return "TimeExtensionDTO{" +
                "personalNumber=" + personalNumber +
                ", loanId=" + loanId +
                ", extraTerm=" + extraTerm +
                '}';
    }
}
