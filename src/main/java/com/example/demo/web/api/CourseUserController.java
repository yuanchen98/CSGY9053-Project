package com.example.demo.web.api;

import com.example.demo.entity.Course;
import com.example.demo.entity.CourseUser;
import com.example.demo.entity.User;
import com.example.demo.entity.dto.CourseDTOFactory;
import com.example.demo.entity.dto.CourseUserDTOFactory;
import com.example.demo.entity.other.ResponseEntity;
import com.example.demo.entity.other.SystemGlobalException;
import com.example.demo.entity.rpo.CourseRPOFactory;
import com.example.demo.service.CourseService;
import com.example.demo.service.CourseUserService;
import com.example.demo.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


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

    @Resource
    private CourseDTOFactory courseDTOFactory;

    @Resource
    private CourseUserDTOFactory courseUserDTOFactory;


    @GetMapping("/{courseId}")
    public ResponseEntity<Void> electiveByCourseId(@PathVariable Integer courseId) {
        User user = userService.getCurrentUser(httpSession);
        Course course = courseService.getById(courseId);
        if (course == null) {
            throw new SystemGlobalException("No such class");
        }
        CourseUser courseUser = courseUserService.findByStudentAndCourse(user, course);
        if (courseUser != null) {
            throw new SystemGlobalException("You've already enrolled in this class");
        } else {
            if (course.isDeleted()) {
                throw new SystemGlobalException("This course has been closed");
            } else if (course.getEndTime().getTime() < new Date().getTime()) {
                throw new SystemGlobalException("The course has already ended");
            } else if (course.getStuNum() >= course.getStuLimit()) {
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

    @PostMapping("/listEnrolled")
    public ResponseEntity<List<CourseUserDTOFactory.CourseUserDTO>> list() {

        User stu = userService.getCurrentUser(httpSession);
        List<CourseUserDTOFactory.CourseUserDTO> courseUserDTOList =
                courseUserService.findByStudent(stu).stream().map(courseUserDTOFactory.convertToDTO).collect(Collectors.toList());
        ;
        return new ResponseEntity<>(HttpStatus.OK.value(), "Find your course list success", courseUserDTOList);
    }

    @GetMapping("/{courseId}/drop")
    public ResponseEntity<Void> dropByCourseId(@PathVariable Integer courseId) {
        User user = userService.getCurrentUser(httpSession);
        Course course = courseService.getById(courseId);
        if (course == null) {
            throw new SystemGlobalException("No such class");
        }
        CourseUser courseUser = courseUserService.findByStudentAndCourse(user, course);
        if (courseUser == null) {
            throw new SystemGlobalException("You didn't enroll this class");
        }
        if(courseUser.isDeleted()){
            throw new SystemGlobalException("The course has been already archived, you can't drop it");
        }
        course.setStuNum(course.getStuNum() - 1);
        courseUserService.deleteCourseUserByCourseAndStudent(course, user);
        courseService.update(course);
        return new ResponseEntity<>(HttpStatus.OK.value(), "Drop success");
    }

    @GetMapping("/{courseId}/detail")
    public ResponseEntity<List<CourseUserDTOFactory.CourseUserDTO>> courseuserDetail(@PathVariable Integer courseId) {
        User user = userService.getCurrentUser(httpSession);
        if (user.getRole() != 1) {
            throw new SystemGlobalException("You don't have permission, please contact the administrator");
        }
        Course course = courseService.getById(courseId);
        List<CourseUserDTOFactory.CourseUserDTO> courseUserDTOList = courseUserService.findByCourse(course)
                .stream().map(courseUserDTOFactory.convertToDTO).collect(Collectors.toList());
        return new ResponseEntity<>(HttpStatus.OK.value(), "Find detail success", courseUserDTOList);
    }

    @PostMapping("/{id}/grade/{grade}")
    public ResponseEntity<Void> saveGrade(@PathVariable Integer id, @PathVariable Double grade) {
        User user = userService.getCurrentUser(httpSession);
        if (user.getRole() != 1) {
            throw new SystemGlobalException("You don't have permission, please contact the administrator");
        }
        CourseUser courseUser = courseUserService.findById(id);
        if (courseUser == null) {
            throw new SystemGlobalException("No such data");
        }
        if (courseUser.isDeleted()){
            throw new SystemGlobalException("The course has been already archived");

        }
        courseUser.setGrade(grade);
        courseUserService.update(courseUser);
        return new ResponseEntity<>(HttpStatus.OK.value(), "Grade save success");
    }

    @PostMapping("/{courseId}/archive")
    public ResponseEntity<Void> archive(@PathVariable Integer courseId) {
        User user = userService.getCurrentUser(httpSession);
        Course course = courseService.getById(courseId);
        if (user.getRole() != 1) {
            throw new SystemGlobalException("You don't have permission, please contact the administrator");
        }
        if (course == null) {
            throw new SystemGlobalException("No such course");
        }
        if (course.isDeleted()){
            throw new SystemGlobalException("The course has been already archived");
        }
        courseUserService.archive(course);
        return new ResponseEntity<>(HttpStatus.OK.value(), "Course archive success");
    }

    @GetMapping("/avgGrade")
    public ResponseEntity<Double> avgGrade() {
        User user = userService.getCurrentUser(httpSession);
        List<CourseUser> courseUserList = courseUserService.findByStudent(user);
        if(courseUserList==null){
            return new ResponseEntity<>(HttpStatus.OK.value(), "Course archive success", 0d);
        }
        double totalGrade = 0;
        int totalCourses = 0;
        for(CourseUser courseUser:courseUserList){
            if(courseUser.isDeleted()){
                totalGrade+=courseUser.getGrade()*courseUser.getCourse().getCredit();
                totalCourses+=courseUser.getCourse().getCredit();
            }
        }
        if(totalCourses==0){
            return new ResponseEntity<>(HttpStatus.OK.value(), "Course archive success", 0d);
        }
        double avgGrade = totalGrade/totalCourses;
        return new ResponseEntity<>(HttpStatus.OK.value(), "Course archive success", avgGrade);
    }
}
