package com.shivamrajput.finance.hw.shivamrajputhw.module.loan.repository;


import com.shivamrajput.finance.hw.shivamrajputhw.module.loan.domain.Loan;
import com.shivamrajput.finance.hw.shivamrajputhw.module.loan.domain.TimeExtension;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {

    List<Loan> findByPersonalNumber(Long personalNumber);
    Loan findOneById(Long id);

    @Modifying
    @Transactional
    @Query(value = "update Loan l  set l.timeExtensions=?1 where  l.id=?2")
    void updateTimeExtensionList(List<TimeExtension> timeExtensionList, Long loanId);

    @Modifying
    @Transactional
    @Query(value = "update Loan l  set l.timeExtensions=?1 where  l.id=?2")
    void updateLoan(List<TimeExtension> timeExtensionList, Long loanId);
}
