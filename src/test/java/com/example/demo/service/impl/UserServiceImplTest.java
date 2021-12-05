package com.example.demo.service.impl;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceImplTest {

    @Resource
    private UserRepository userRepository;

    @Test
    void testUser() {
        User user = new User();
        user.setName("test");
        user.setNumber(123456);
        user.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
        user.setRole(0);
        userRepository.save(user);
    }

    @Test
    void testUser1() {
        User user = new User();
        user.setName("testStu");
        user.setNumber(23456);
        user.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
        user.setRole(1);
        userRepository.save(user);
    }

}