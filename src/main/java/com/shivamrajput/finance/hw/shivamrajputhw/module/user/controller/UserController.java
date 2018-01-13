package com.shivamrajput.finance.hw.shivamrajputhw.module.user.controller;


import com.shivamrajput.finance.hw.shivamrajputhw.module.user.controller.dto.UserDTO;
import com.shivamrajput.finance.hw.shivamrajputhw.module.user.domain.User;
import com.shivamrajput.finance.hw.shivamrajputhw.module.user.repository.UserRepository;
import com.shivamrajput.finance.hw.shivamrajputhw.module.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
//@RequestMapping("/app")
public class UserController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;

    @RequestMapping(value = "/getAllUsers", method = RequestMethod.GET)
    public ResponseEntity test() {
        return new ResponseEntity<>(
                userRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/createUser", method = RequestMethod.POST)
    public ResponseEntity createUser(@RequestBody UserDTO userDTO) {
        return  userService.createUser(userDTO);
    }

    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    public ResponseEntity createUserFirstname(@RequestBody UserDTO userDTO) {
        int result=userService.updateUserFistnameAndLastname(userDTO.id,userDTO.firstname,userDTO.lastname);
        if(result==0){
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }
}