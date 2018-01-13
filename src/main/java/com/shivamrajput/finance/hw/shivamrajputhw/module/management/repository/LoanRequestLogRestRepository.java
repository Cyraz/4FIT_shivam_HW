package com.shivamrajput.finance.hw.shivamrajputhw.module.management.repository;

import com.shivamrajput.finance.hw.shivamrajputhw.module.management.domain.LoanRequestLog;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "lrlogs", collectionResourceRel = "lrlogs")
public interface LoanRequestLogRestRepository extends PagingAndSortingRepository<LoanRequestLog, Long> {

}
