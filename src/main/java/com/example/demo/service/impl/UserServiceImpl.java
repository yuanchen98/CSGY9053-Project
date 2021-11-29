package com.example.demo.service.impl;

import com.example.demo.entity.User;
import com.example.demo.entity.other.SystemGlobalException;
import com.example.demo.repository.CourseUserRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserRepository userRepository;
    @Resource
    private CourseUserRepository userCourseRepository;

    public User login(Integer number, String password){
        User user = userRepository.findByNumber(number);
        if (user == null) {
            throw new SystemGlobalException("No such user");
        }
        if (!DigestUtils.md5DigestAsHex(password.getBytes()).equals(user.getPassword())) {
            throw new SystemGlobalException("The account sign-in was incorrect");
        }
        if (user.isDeleted() == true) {
            throw new SystemGlobalException("Your account is disabled temporarily");
        }
        return user;
    }

}
