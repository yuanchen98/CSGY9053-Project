package com.example.demo.entity.rpo;

import com.example.demo.entity.Course;
import com.example.demo.entity.User;
import com.example.demo.entity.other.SystemGlobalException;
import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.function.Function;

@Component
public class UserRPOFactory {

    @Data
    @Builder
    public static class UserRPO implements Serializable{

        @NotBlank(message = "User name can not be null")
        private String name;

        @NotNull(message = "User number can not be null")
        private Integer number;

        @NotBlank(message = "Password can not be null")
        private String password;

//        /**
//         default 0: student
//         */
//        @Builder.Default
//        private Integer role=0;
//
//        @Builder.Default
//        private Integer credit=0;

    }
    public Function<UserRPOFactory.UserRPO, User> rpoToPojo = userSaveRPO -> {
//            Course course = null;
//            if (userSaveRPO.getId() != null) {
//                course = courseService.getById(userSaveRPO.getId());
//            }
//            if (course == null) {
//                course = new Course();
//            }
//            if(courseSaveRPO.getBeginTime().getTime()>courseSaveRPO.getEndTime().getTime()){
//                throw new SystemGlobalException("Start time must be earlier than end time!");
//            }
//            course.setName(courseSaveRPO.getName());
//            course.setCredit(courseSaveRPO.getCredit());
//            course.setStartTime(courseSaveRPO.getBeginTime());
//            course.setEndTime(courseSaveRPO.getEndTime());
//            course.setStuLimit(courseSaveRPO.getLimitNum());
//            return course;

        User user = new User();
        user.setName(userSaveRPO.getName());
        user.setNumber(userSaveRPO.getNumber());
        user.setPassword(DigestUtils.md5DigestAsHex(userSaveRPO.getPassword().getBytes()));
        return user;
    };

}
