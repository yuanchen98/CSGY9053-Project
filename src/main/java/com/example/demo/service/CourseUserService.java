package com.example.demo.service;

import com.example.demo.entity.Course;
import com.example.demo.entity.CourseUser;
import com.example.demo.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface CourseUserService {
    CourseUser findByStudentAndCourse(User user, Course course);

    CourseUser update(CourseUser courseUser);
}
