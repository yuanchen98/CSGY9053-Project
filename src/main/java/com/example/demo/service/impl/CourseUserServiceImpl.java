package com.example.demo.service.impl;

import com.example.demo.entity.Course;
import com.example.demo.entity.CourseUser;
import com.example.demo.entity.User;
import com.example.demo.repository.CourseUserRepository;
import com.example.demo.service.CourseUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CourseUserServiceImpl implements CourseUserService {

    @Resource
    private CourseUserRepository courseUserRepository;

    @Override
    public CourseUser findByStudentAndCourse(User user, Course course) {
        return courseUserRepository.findByStudentAndCourse(user, course);
    }

    @Override
    public CourseUser update(CourseUser courseUser) {
        return courseUserRepository.saveAndFlush(courseUser);
    }

    @Override
    public List<CourseUser> findByStudent(User user) {
        return courseUserRepository.findByStudent(user);
    }

    @Override
    public List<CourseUser> findByCourse(Course course) {
        return courseUserRepository.findByCourse(course);
    }

    @Override
    public Long deleteCourseUserByCourseAndStudent(Course course, User student) {
        return courseUserRepository.deleteCourseUserByCourseAndStudent(course, student);
    }

}
