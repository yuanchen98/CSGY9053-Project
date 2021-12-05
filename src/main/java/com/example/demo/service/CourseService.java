package com.example.demo.service;

import com.example.demo.entity.Course;
import org.springframework.stereotype.Service;

@Service
public interface CourseService {
    Course update(Course course);

    Course getById(Integer id);
}
