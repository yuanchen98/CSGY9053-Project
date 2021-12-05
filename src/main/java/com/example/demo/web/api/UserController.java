package com.example.demo.web.api;

import com.example.demo.entity.User;
import com.example.demo.entity.dto.UserDTOFactory;
import com.example.demo.entity.other.ResponseEntity;
import com.example.demo.entity.other.SystemGlobalException;
import com.example.demo.entity.rpo.LoginRPOFactory;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.impl.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import static com.example.demo.entity.constant.SystemConstant.USER_ID;
import static com.example.demo.entity.constant.SystemConstant.USER_ROLE;


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
                httpSession.setAttribute(USER_ROLE, user.getRole());
                return new ResponseEntity<>(HttpStatus.OK.value(), "login success", userDTOFactory.convertToDTO.apply(user));
            }
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR.value(),"No such user or the account sign-in was incorrect");
        }

    }

    @PostMapping("/modify/password")
    public ResponseEntity<Void> modifyPwd(@NotBlank(message = "Password can't be null") String password) {
        Boolean result = userService.modifyPassword(userService.getCurrentUser(httpSession), password);
        if (result) {
            httpSession.removeAttribute(USER_ID);
        }
        return new ResponseEntity<>(HttpStatus.OK.value(), "Password change success");
    }


}
