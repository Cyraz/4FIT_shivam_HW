package com.shivamrajput.finance.hw.shivamrajputhw.module.user.repository;

import com.shivamrajput.finance.hw.shivamrajputhw.module.user.domain.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "users", collectionResourceRel = "users")
public interface UserRestRepository extends PagingAndSortingRepository<User, Long> {

}
