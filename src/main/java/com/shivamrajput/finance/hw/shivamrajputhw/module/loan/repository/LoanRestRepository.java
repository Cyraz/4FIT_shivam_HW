package com.shivamrajput.finance.hw.shivamrajputhw.module.loan.repository;

import com.shivamrajput.finance.hw.shivamrajputhw.module.loan.domain.Loan;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "loans", collectionResourceRel = "loans")
public interface LoanRestRepository  extends PagingAndSortingRepository<Loan, Long> {
}
