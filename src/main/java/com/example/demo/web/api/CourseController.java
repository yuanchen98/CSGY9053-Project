package com.example.demo.web.api;


import com.example.demo.entity.Course;
import com.example.demo.entity.User;
import com.example.demo.entity.dto.CourseDTOFactory;
import com.example.demo.entity.other.ResponseEntity;
import com.example.demo.entity.rpo.CourseRPOFactory;
import com.example.demo.service.CourseService;
import com.example.demo.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * xx
 * <p>
 * Description:
 * </p>
 *
 * @author: https://github.com/IvenCc
 * @date: 2021/3/1
 * @see: com.example.elective.web.api
 * @version: v1.0.0
 */
@RestController
@RequestMapping(value = "/api/course")
public class CourseController {

    @Resource
    private UserService userService;

    @Resource
    private CourseService courseService;

    @Resource
    private CourseDTOFactory courseDTOFactory;

    @Resource
    private CourseRPOFactory courseRPOFactory;

    @Resource
    private HttpSession httpSession;

    @PostMapping("")
    public ResponseEntity<Void> save(@RequestBody @Valid CourseRPOFactory.CourseRPO courseRPO, BindingResult bindingResult) {
        User user = userService.getCurrentUser(httpSession);
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), bindingResult.getFieldError().getDefaultMessage());
        }
        Course course = courseRPOFactory.rpoToPojo.apply(courseRPO);
        course.setTeacher(user);
        courseService.update(course);
        return new ResponseEntity<>(HttpStatus.OK.value(), "Add course success");
    }

    @PostMapping("list")
    public ResponseEntity<List<CourseDTOFactory.CourseDTO>> list() {
//        @RequestBody CourseRPOFactory.CourseRPO courseRPO

        User teacher = userService.getCurrentUser(httpSession);
        List<CourseDTOFactory.CourseDTO> courseList=
                courseService.getByTeacher(teacher).stream().map(courseDTOFactory.convertToDTO).collect(Collectors.toList());;
        return new ResponseEntity<>(HttpStatus.OK.value(), "Find your course list success", courseList);

    }

}

