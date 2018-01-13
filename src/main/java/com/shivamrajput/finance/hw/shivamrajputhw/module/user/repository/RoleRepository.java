package com.shivamrajput.finance.hw.shivamrajputhw.module.user.repository;

import com.shivamrajput.finance.hw.shivamrajputhw.module.user.domain.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {

    List<Role> findAll();
}
