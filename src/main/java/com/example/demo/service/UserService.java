package com.example.demo.service;

import com.example.demo.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    public User login(Integer number, String password);

}
