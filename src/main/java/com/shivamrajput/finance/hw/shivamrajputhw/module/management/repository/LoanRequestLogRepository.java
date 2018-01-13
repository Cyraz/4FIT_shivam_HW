package com.shivamrajput.finance.hw.shivamrajputhw.module.management.repository;

import com.shivamrajput.finance.hw.shivamrajputhw.module.management.domain.LoanRequestLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
@Repository
public interface LoanRequestLogRepository extends JpaRepository<LoanRequestLog, Long> {

    List<LoanRequestLog > findAllByCreateDateTimeAfter(LocalDateTime dateTimeAfter);

}
