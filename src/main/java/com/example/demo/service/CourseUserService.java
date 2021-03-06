package com.example.demo.service;

import com.example.demo.entity.Course;
import com.example.demo.entity.CourseUser;
import com.example.demo.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CourseUserService {
    CourseUser findByStudentAndCourse(User user, Course course);

    CourseUser update(CourseUser courseUser);

    List<CourseUser> findByStudent(User user);

    List<CourseUser> findByCourse(Course course);

    CourseUser findById(Integer id);

    Long deleteCourseUserByCourseAndStudent(Course course, User student);

    void archive(Course course);

    Long count();
}
