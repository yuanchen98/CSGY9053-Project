package com.example.demo.web.api;

import com.example.demo.entity.Course;
import com.example.demo.entity.CourseUser;
import com.example.demo.entity.User;
import com.example.demo.entity.other.ResponseEntity;
import com.example.demo.entity.other.SystemGlobalException;
import com.example.demo.service.CourseService;
import com.example.demo.service.CourseUserService;
import com.example.demo.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;


@RestController
@RequestMapping(value = "/api/courseUser")
public class CourseUserController {

    @Resource
    private UserService userService;

    @Resource
    private CourseService courseService;

    @Resource
    private CourseUserService courseUserService;

    @Resource
    private HttpSession httpSession;



    @GetMapping("/{courseId}")
    public ResponseEntity<Void> electiveByCourseId(@PathVariable Integer courseId) {
        User user = userService.getCurrentUser(httpSession);
        Course course = courseService.getById(courseId);
        if (course == null) {
            throw new SystemGlobalException("No such class");
        }
        CourseUser courseUser = courseUserService.findByStudentAndCourse(user, course);
        if(courseUser!=null){
            throw new SystemGlobalException("You've already enrolled in this class");
        }else{
            if(course.isDeleted()){
                throw new SystemGlobalException("This course has been closed");
            }else if(course.getEndTime().getTime()<new Date().getTime()){
                throw new SystemGlobalException("The course has already ended");
            }else if(course.getStuNum()>=course.getStuLimit()){
                throw new SystemGlobalException("The course is full");
            }
        }
        courseUser = new CourseUser();
        courseUser.setCourse(course);
        courseUser.setStudent(user);
        courseUser.setDeleted(false);
        course.setStuNum(course.getStuNum() + 1);
        courseService.update(course);
        courseUserService.update(courseUser);
        return new ResponseEntity<>(HttpStatus.OK.value(), "Enroll success");

    }
}
