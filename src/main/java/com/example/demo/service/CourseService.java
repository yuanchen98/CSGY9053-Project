package com.example.demo.service;

import com.example.demo.entity.Course;
import com.example.demo.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CourseService {
    Course update(Course course);

    Course getById(Integer id);

    List<Course> getByTeacher(User teacher);

    List<Course> getAll();

    Long count();



}
