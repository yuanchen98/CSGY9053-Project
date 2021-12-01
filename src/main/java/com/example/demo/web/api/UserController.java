package com.example.demo.web.api;

import com.example.demo.entity.User;
import com.example.demo.entity.dto.UserDTOFactory;
import com.example.demo.entity.other.ResponseEntity;
import com.example.demo.entity.rpo.LoginRPOFactory;
import com.example.demo.service.impl.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import static com.example.demo.entity.constant.SystemConstant.USER_ID;

@RestController
@RequestMapping(value = "/api/user")
public class UserController {

    @Resource
    private UserServiceImpl userService;


    @Resource
    private HttpSession httpSession;

    @Resource
    private LoginRPOFactory loginRPOFactory;

    @Resource
    private UserDTOFactory userDTOFactory;

    @PostMapping(value = "/login")
    @ResponseBody
    public ResponseEntity<UserDTOFactory.UserDTO> login(@RequestBody @Valid LoginRPOFactory.LoginRPO loginRPO
            , BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    bindingResult.getFieldError().getDefaultMessage());
        } else {
            User user = userService.login(loginRPO.getNumber(), loginRPO.getPassword());
            if (user != null) {
                httpSession.setAttribute(USER_ID, user.getId());
                return new ResponseEntity<>(HttpStatus.OK.value(), "login success", userDTOFactory.convertToDTO(user));
            }
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR.value(),"No such user or the account sign-in was incorrect");
        }

    }
}
