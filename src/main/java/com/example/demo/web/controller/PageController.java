package com.example.demo.web.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import static com.example.demo.entity.constant.SystemConstant.USER_ID;

@Controller
public class PageController {

    @Resource
    private HttpSession httpSession;

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
    public String dashboard() {

        return "dashboard";
    }
}
