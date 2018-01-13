package com.shivamrajput.finance.hw.shivamrajputhw.module.loan.repository;


import com.shivamrajput.finance.hw.shivamrajputhw.module.loan.domain.TimeExtension;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeExtensionRepository  extends JpaRepository<TimeExtension, Long> {

}
