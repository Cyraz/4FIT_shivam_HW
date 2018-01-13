package com.shivamrajput.finance.hw.shivamrajputhw.module.loan.domain;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Loan {

    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id = 0L;

    @NotNull
    private int term;
    @NotNull
    private BigDecimal amount;
    @NotNull
    private Long personalNumber;

    private boolean isPaid;
    private BigDecimal finalPaidAmount;


    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<TimeExtension> timeExtensions;

    @CreationTimestamp
    private LocalDateTime createDateTime;


    @UpdateTimestamp
    private LocalDateTime updateDateTime;

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    public Loan() {
    }

    public List<TimeExtension> getTimeExtensions() {
        return timeExtensions;
    }

    public TimeExtension addTimeExtension(TimeExtension timeExtension) {
        this.timeExtensions.add(timeExtension);
        return timeExtension;
    }

    public void setTimeExtensions(List<TimeExtension> timeExtensions) {
        this.timeExtensions = timeExtensions;
    }

    public BigDecimal getFinalPaidAmount() {
        return finalPaidAmount;
    }

    public void setFinalPaidAmount(BigDecimal finalPaidAmount) {
        this.finalPaidAmount = finalPaidAmount;
    }

    public LocalDateTime getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(LocalDateTime createDateTime) {
        this.createDateTime = createDateTime;
    }

    public LocalDateTime getUpdateDateTime() {
        return updateDateTime;
    }

    public void setUpdateDateTime(LocalDateTime updateDateTime) {
        this.updateDateTime = updateDateTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }

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
        return "Loan{" +
                "id=" + id +
                ", term=" + term + " weeks" +
                ", amount=" + amount +
                ", personalNumber=" + personalNumber +
                ", isPaid=" + isPaid +
                ", finalPaidAmount=" + finalPaidAmount +
                ", timeExtensions=" + timeExtensions +
                ", createDateTime=" + createDateTime +
                ", updateDateTime=" + updateDateTime +
                '}';
    }
}

