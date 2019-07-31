package com.dragon.demo.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional(rollbackOn = Exception.class)
    public User save(User user){
        userRepository.save(user);
        int aa = 1 / 0;
        user.setPassword("123456");
        return userRepository.save(user);
    }
}
