package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.entity.other.SystemGlobalException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

import static com.example.demo.entity.constant.SystemConstant.USER_ID;

@Service
public interface UserService {

    User login(Integer number, String password);

    Boolean modifyPassword(User user, String password);

    User getCurrentUser(HttpSession httpSession);

    Long count();

    User regist(User user);




}
