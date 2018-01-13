package com.shivamrajput.finance.hw.shivamrajputhw.module.loan.service;


import com.shivamrajput.finance.hw.shivamrajputhw.module.Config.LoanSystemConfiguration;
import com.shivamrajput.finance.hw.shivamrajputhw.module.common.GenericResponse;
import com.shivamrajput.finance.hw.shivamrajputhw.module.loan.controller.dto.LoanReportDTO;
import com.shivamrajput.finance.hw.shivamrajputhw.module.loan.controller.dto.PayDTO;
import com.shivamrajput.finance.hw.shivamrajputhw.module.loan.domain.Loan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * Helper service to Loan Services
 *
 * Aim is to setup boundary between business operations/logic and controller side
 *
 */
@Service
public class LoanHelperService {
    private static final Logger log = LoggerFactory.getLogger(LoanHelperService.class);


    @Autowired
    LoanSystemConfiguration loanSystemConfiguration;


    public Long getDurationsInWeeks(LocalDateTime from, LocalDateTime to) {
        return ChronoUnit.WEEKS.between(from, to);
    }

    public BigDecimal calToatalamount(BigDecimal amount, BigDecimal totalNoOfWeeks) {

        BigDecimal si = (amount.multiply(totalNoOfWeeks.multiply(loanSystemConfiguration.getRate())))
                .divide(BigDecimal.valueOf(100));
        return si.add(amount);
    }

    public BigDecimal getAmountPaybleAtTermCompletion(Loan loan) {
        int noOfWeeks = loan.getTerm();
        final int[] extensionTime = new int[1];
        loan.getTimeExtensions().forEach(t -> {
            extensionTime[0] = extensionTime[0] + t.getExtension();
        });
        noOfWeeks += extensionTime[0];
        log.info("total no of weeks ->" + noOfWeeks);
        return calToatalamount(loan.getAmount(), BigDecimal.valueOf(noOfWeeks));
    }

    public BigDecimal getAmountPaybleNow(Loan loan) {
        LocalDateTime now = LocalDateTime.now();//.plusDays(86);//TODO remove extra days
        Long noOfWeeks = ChronoUnit.WEEKS.between(loan.getCreateDateTime(), now);
        log.info("total no of weeks ->" + noOfWeeks);
        return calToatalamount(loan.getAmount(), BigDecimal.valueOf(noOfWeeks));
    }

    public BigDecimal calTotalAmountForAllUnpaidLoansNow(List<Loan> loans) {
        final BigDecimal[] amt = {BigDecimal.ZERO};
        if (loans != null && loans.size() > 0) {
            loans.forEach(l -> {
                if (!l.isPaid()) {
                    amt[0] = amt[0].add(getAmountPaybleNow(l));
                } else {
                    log.info("loan is paid loan-> " + l.toString());
                }
            });
        }
        return amt[0];
    }

    public BigDecimal calTotalAmountForAllUnpaidLoansAtTerm(List<Loan> loans) {
        final BigDecimal[] amt = {BigDecimal.ZERO};
        if (loans != null && loans.size() > 0) {
            loans.forEach(l -> {
                if (!l.isPaid()) {
                    amt[0] = amt[0].add(getAmountPaybleAtTermCompletion(l));
                } else {
                    log.info("loan is paid loan-> " + l.toString());
                }
            });
        }
        return amt[0];
    }


    public LoanReportDTO generateUserLoanReport(List<Loan> loans) {
        int[] data = new int[2];
        data[0] = 0;
        data[1] = 0;
        LoanReportDTO r = new LoanReportDTO();
        r.setLIST_OF_LOANS_WITH_EXTENSION(loans);
        r.setTOTAL_LOANS(loans.size());
        r.setTOTAL_AMOUNT_PAYABLE(calTotalAmountForAllUnpaidLoansNow(loans));
        loans.forEach(l -> {
            if (!l.isPaid()) {
                data[0]++;
                l.getTimeExtensions().forEach(t -> data[1]++);
            }
        });
        r.setUNPAID_LOANS(data[0]);
        r.setTOTAL_EXTENSIONS_ASKED(data[1]);
        return r;
    }

    public int isAmountEqualorGreater(List<Loan> loans, PayDTO payDTO) {
        BigDecimal amountDue = calTotalAmountForAllUnpaidLoansNow(loans);
        return payDTO.getAmount().compareTo(amountDue);
    }


}

