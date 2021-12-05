package com.example.demo.service.impl;

import com.example.demo.entity.Course;
import com.example.demo.entity.User;
import com.example.demo.repository.CourseRepository;
import com.example.demo.service.CourseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Resource
    private CourseRepository courseRepository;


    @Override
    public Course update(Course course) {
        return courseRepository.saveAndFlush(course);
    }

    @Override
    public Course getById(Integer id) {
        return courseRepository.getById(id);
    }

    @Override
    public List<Course> getByTeacher(User teacher) {
        return courseRepository.findByTeacher(teacher);
    }

    @Override
    public List<Course> getAll() {
        return courseRepository.findAll();
    }


}
