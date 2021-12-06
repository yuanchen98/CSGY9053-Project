package com.example.demo.service.impl;

import com.example.demo.entity.Course;
import com.example.demo.entity.CourseUser;
import com.example.demo.entity.User;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.CourseUserRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.CourseUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class CourseUserServiceImpl implements CourseUserService {

    @Resource
    private CourseUserRepository courseUserRepository;

    @Resource
    private UserRepository userRepository;

    @Resource
    private CourseRepository courseRepository;

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
    public CourseUser findById(Integer id) {
        return courseUserRepository.getById(id);
    }


    @Override
    public Long deleteCourseUserByCourseAndStudent(Course course, User student) {
        return courseUserRepository.deleteCourseUserByCourseAndStudent(course, student);
    }

    @Override
    public void archive(Course course) {
        List<User> stuList = new ArrayList<>();
        List<CourseUser> courseUserList = courseUserRepository.findByCourse(course);
        for(CourseUser courseuser: courseUserList){
            courseuser.setStatus(1);
            User stu = courseuser.getStudent();
            stu.setCredit(stu.getCredit()+courseuser.getCourse().getCredit());
            stuList.add(stu);
            courseuser.setDeleted(true);
        }
        course.setDeleted(true);
        courseUserRepository.saveAll(courseUserList);
        userRepository.saveAll(stuList);
        courseRepository.save(course);
    }

}
