package com.example.demo.service.impl;

import com.example.demo.entity.User;
import com.example.demo.entity.other.SystemGlobalException;
import com.example.demo.repository.CourseUserRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import static com.example.demo.entity.constant.SystemConstant.USER_ID;

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

    public Boolean modifyPassword(User user, String password) {
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        if(user.getPassword().equals(password)){
            throw new SystemGlobalException("Password can't be same with the original");
        }
        user.setPassword(password);
        userRepository.save(user);
        return true;
    }

    public User getCurrentUser(HttpSession httpSession) {
        Integer userId = (Integer) httpSession.getAttribute(USER_ID);
        if (userId == null) {
            throw new SystemGlobalException("Please login");
        }
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            throw new SystemGlobalException("Please login");
        }
        return user;
    }

    @Override
    public Long count() {
        return userRepository.count();
    }

}
