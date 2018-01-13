package com.shivamrajput.finance.hw.shivamrajputhw.module.user.repository;


import com.shivamrajput.finance.hw.shivamrajputhw.module.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Spring Data JPA repository for the User entity.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

   User findOneByPersonalNumber(Long personalNumber);
   User findOneByFirstNameAndLastName(String firstname,String lastname);
   User findOneById(Long id);

   @Modifying
   @Transactional
   @Query(value = "update User u  set u.firstName=?1 where  u.id=?2")
   void updateUserFisrtname(String firstName, Long userId);

   @Modifying
   @Transactional
   @Query(value = "update User u  set u.lastName=?1 where  u.id=?2")
   void updateUserLastname(String lastname, Long userId);


}
