package com.shivamrajput.finance.hw.shivamrajputhw.module.loan.controller.dto;

import java.math.BigDecimal;

public class LoanDTO {

    public  Long userId;

    public String firstname;

    public String lastname;

    public Long personalNumber;

    private Integer term;

    private BigDecimal amount;

    private String IP;



    public LoanDTO() {
    }

    public LoanDTO(Long userId, String firstname, String lastname, Long personalNumber, int term, BigDecimal amount) {
        this.userId = userId;
        this.firstname = firstname;
        this.lastname = lastname;
        this.personalNumber = personalNumber;
        this.term = term;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "LoanDTO{" +
                "userId=" + userId +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", personalNumber=" + personalNumber +
                ", term=" + term +
                ", amount=" + amount +
                ", IP='" + IP + '\'' +
                '}';
    }

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Long getPersonalNumber() {
        return personalNumber;
    }

    public void setPersonalNumber(Long personalNumber) {
        this.personalNumber = personalNumber;
    }

    public Integer getTerm() {
        return term;
    }

    public void setTerm(Integer term) {
        this.term = term;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
