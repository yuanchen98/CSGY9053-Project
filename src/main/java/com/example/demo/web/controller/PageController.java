package com.example.demo.web.controller;


import com.example.demo.entity.CourseUser;
import com.example.demo.service.CourseService;
import com.example.demo.service.CourseUserService;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import static com.example.demo.entity.constant.SystemConstant.USER_ID;
//import static com.example.demo.entity.constant.SystemConstant.ACTIVE_URL;

@Controller
public class PageController {

    @Resource
    private HttpSession httpSession;

    @Resource
    private UserService userService;
    @Resource
    private CourseService courseService;
    @Resource
    private CourseUserService userCourseService;

    @GetMapping(value = "/")
    public String index(){
        return "index";
    }

    @GetMapping(value = "/index")
    public String login() {
        if (httpSession.getAttribute(USER_ID) != null) {
            return "redirect:/dashboard";
        }
        return "index";
    }

    @GetMapping(value = "/dashboard")
    public String dashboard(Model model) {
//        httpSession.setAttribute(ACTIVE_URL, "dashboard");
//        httpSession.setAttribute("totalcourses", courseService.count());
//        httpSession.setAttribute("students", userService.count());
        httpSession.setAttribute("courses", userCourseService.count());
//        model.addAttribute("courses", userCourseService.count());
        return "dashboard";
    }

    @GetMapping(value = "/user")
    public String user() {
//        httpSession.setAttribute(ACTIVE_URL, "user");
        return "user";
    }

    @GetMapping(value = "/table")
    public String table() {
//        httpSession.setAttribute(ACTIVE_URL, "table");
        return "table";
    }

    @GetMapping(value = "/course")
    public String course() {
//        httpSession.setAttribute(ACTIVE_URL, "course");
        return "course";
    }

    @GetMapping(value = "/teacherTable")
    public String teacherTable() {
//        httpSession.setAttribute(ACTIVE_URL, "course");
        return "teacher_table";
    }

    @GetMapping(value = "/studentTable")
    public String studentTable() {
//        httpSession.setAttribute(ACTIVE_URL, "course");
        return "student_table";
    }

    @GetMapping(value = "/studentDrop")
    public String studentDrop() {
//        httpSession.setAttribute(ACTIVE_URL, "course");
        return "student_table_drop";
    }

    @GetMapping("/logOut")
    public String logOut() {
        httpSession.removeAttribute(USER_ID);
        return "redirect:/index";
    }
}
