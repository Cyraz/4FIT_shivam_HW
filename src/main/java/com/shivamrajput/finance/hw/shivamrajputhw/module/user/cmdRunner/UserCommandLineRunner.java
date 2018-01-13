package com.shivamrajput.finance.hw.shivamrajputhw.module.user.cmdRunner;

import com.shivamrajput.finance.hw.shivamrajputhw.module.user.domain.Role;
import com.shivamrajput.finance.hw.shivamrajputhw.module.user.domain.User;
import com.shivamrajput.finance.hw.shivamrajputhw.module.user.repository.RoleRepository;
import com.shivamrajput.finance.hw.shivamrajputhw.module.user.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Created by codebeast
 */
@Component
public class UserCommandLineRunner implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(UserCommandLineRunner.class);

    @Autowired
    RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;


    @Override
    public void run(String... strings) throws Exception {
        User u = new User();
        u.setFirstName("Shivam");
        u.setLastName("Rajput");
        u.setPersonalNumber(223312231235l);
        userRepository.save(u);

        for (User user : userRepository.findAll()) {
            log.info(user.toString());
        }

    }
}
