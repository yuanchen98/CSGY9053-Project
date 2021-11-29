package com.example.demo.service;

import com.example.demo.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    User login(Integer number, String password);

}
