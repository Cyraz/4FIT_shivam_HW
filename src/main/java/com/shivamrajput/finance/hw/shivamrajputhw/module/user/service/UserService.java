package com.shivamrajput.finance.hw.shivamrajputhw.module.user.service;


import com.shivamrajput.finance.hw.shivamrajputhw.module.common.ErrorResponse;
import com.shivamrajput.finance.hw.shivamrajputhw.module.user.controller.dto.UserDTO;
import com.shivamrajput.finance.hw.shivamrajputhw.module.user.domain.User;
import com.shivamrajput.finance.hw.shivamrajputhw.module.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserService {
    public interface userServiceCallback {
        void userAdded(User user);

        void userAlredyExits(String message);

        void badRequestParms(String message);
    }

    @Autowired
    UserRepository userRepository;

    public boolean isUserExistWithPersonalNumber(Long personalNumaber) {
        User u = userRepository.findOneByPersonalNumber(personalNumaber);
        if (u != null && u.getId() >= 0) {
            return true;
        } else return false;
    }

    public User fetchUserByPersonalNumber(Long personalNumber) {
        return userRepository.findOneByPersonalNumber(personalNumber);
    }

    public boolean isPersonamNumberValid(Long personalNumber) {
        if (personalNumber != null && personalNumber > 10000l) {
            return true;
        } else return false;
    }

    @Transactional
    public ResponseEntity createUser(UserDTO userDTO) {

        if (!isPersonamNumberValid(userDTO.getPersonalNumber())) {
            return new ResponseEntity<>(new ErrorResponse("ERROR", "PERSONAL NUMBER is not valid"), HttpStatus.BAD_REQUEST);
        }
        if (isUserExistWithPersonalNumber(userDTO.personalNumber)) {
            return new ResponseEntity<>(new ErrorResponse("ERROR", "User already exists with PERSONAL NUMBER: " + userDTO.personalNumber.toString()), HttpStatus.BAD_REQUEST);
        }
        if (userDTO.firstname == null || userDTO.firstname.length() == 0) {
            userDTO.firstname = "NOT_AVAILABLE";
        }
        if (userDTO.lastname == null || userDTO.lastname.length() == 0) {
            userDTO.lastname = "NOT_AVAILABLE";
        }
        User u;
        u = new User();
        u.setPersonalNumber(userDTO.getPersonalNumber());
        u.setFirstName(userDTO.getFirstname());
        u.setLastName(userDTO.getLastname());
        u = userRepository.save(u);
        return new ResponseEntity<>(u, HttpStatus.CREATED);
    }

    @Transactional
    public void createUser(UserDTO userDTO, userServiceCallback serviceCallback) {

        if (!isPersonamNumberValid(userDTO.getPersonalNumber())) {
            serviceCallback.badRequestParms("PERSONAL NUMBER is not valid");
        }
        if (isUserExistWithPersonalNumber(userDTO.personalNumber)) {
            serviceCallback.userAlredyExits("User already exists with PERSONAL NUMBER");
        }
        if (userDTO.firstname == null || userDTO.firstname.length() == 0) {
            userDTO.firstname = "NOT_AVAILABLE";
        }
        if (userDTO.lastname == null || userDTO.lastname.length() == 0) {
            userDTO.lastname = "NOT_AVAILABLE";
        }
        User u;
        u = new User();
        u.setPersonalNumber(userDTO.getPersonalNumber());
        u.setFirstName(userDTO.getFirstname());
        u.setLastName(userDTO.getLastname());
        u = userRepository.save(u);
        serviceCallback.userAdded(u);
    }

    @Transactional
    @Modifying
    public int updateUserFistnameAndLastname(Long id, String firstname, String lastname) {
        User u=userRepository.findOneById(id);
        if(u!=null){
            userRepository.updateUserFisrtname(firstname,id);
            userRepository.updateUserLastname(lastname,id);
            return 0;
        }else {
            return 1;
        }
    }


}
