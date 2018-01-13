package com.shivamrajput.finance.hw.shivamrajputhw.module.loan.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.shivamrajput.finance.hw.shivamrajputhw.module.loan.domain.Loan;
import com.shivamrajput.finance.hw.shivamrajputhw.module.user.domain.User;

import java.math.BigDecimal;
import java.util.List;

public class LoanReportDTO {

    private User USER_DETAILS;
    private BigDecimal TOTAL_AMOUNT_PAYABLE;
    private int TOTAL_LOANS;
    private int UNPAID_LOANS;
    private int TOTAL_EXTENSIONS_ASKED;
    private List<Loan> LIST_OF_LOANS_WITH_EXTENSION;

    @JsonProperty("TOTAL_AMOUNT_PAYABLE")
    public BigDecimal getTOTAL_AMOUNT_PAYABLE() {
        return TOTAL_AMOUNT_PAYABLE;
    }

    public void setTOTAL_AMOUNT_PAYABLE(BigDecimal TOTAL_AMOUNT_PAYABLE) {
        this.TOTAL_AMOUNT_PAYABLE = TOTAL_AMOUNT_PAYABLE;
    }

    @JsonProperty("TOTAL_LOANS")
    public int getTOTAL_LOANS() {
        return TOTAL_LOANS;
    }

    public void setTOTAL_LOANS(int TOTAL_LOANS) {
        this.TOTAL_LOANS = TOTAL_LOANS;
    }

    @JsonProperty("UNPAID_LOANS")
    public int getUNPAID_LOANS() {
        return UNPAID_LOANS;
    }

    public void setUNPAID_LOANS(int UNPAID_LOANS) {
        this.UNPAID_LOANS = UNPAID_LOANS;
    }

    @JsonProperty("TOTAL_EXTENSIONS_ASKED")
    public int getTOTAL_EXTENSIONS_ASKED() {
        return TOTAL_EXTENSIONS_ASKED;
    }

    public void setTOTAL_EXTENSIONS_ASKED(int TOTAL_EXTENSIONS_ASKED) {
        this.TOTAL_EXTENSIONS_ASKED = TOTAL_EXTENSIONS_ASKED;
    }

    @JsonProperty("LIST_OF_LOANS_WITH_EXTENSION")
    public List<Loan> getLIST_OF_LOANS_WITH_EXTENSION() {
        return LIST_OF_LOANS_WITH_EXTENSION;
    }

    public void setLIST_OF_LOANS_WITH_EXTENSION(List<Loan> LIST_OF_LOANS_WITH_EXTENSION) {
        this.LIST_OF_LOANS_WITH_EXTENSION = LIST_OF_LOANS_WITH_EXTENSION;
    }
    @JsonProperty("USER_DETAILS")
    public User getUSER_DETAILS() {
        return USER_DETAILS;
    }

    public void setUSER_DETAILS(User USER_DETAILS) {
        this.USER_DETAILS = USER_DETAILS;
    }

    @Override
    public String toString() {
        return "LoanReportDTO{" +
                "TOTAL_AMOUNT_PAYABLE=" + TOTAL_AMOUNT_PAYABLE +
                ", TOTAL_LOANS=" + TOTAL_LOANS +
                ", UNPAID_LOANS=" + UNPAID_LOANS +
                ", TOTAL_EXTENSIONS_ASKED=" + TOTAL_EXTENSIONS_ASKED +
                ", LIST_OF_LOANS_WITH_EXTENSION=" + LIST_OF_LOANS_WITH_EXTENSION +
                '}';
    }
}
