package com.example.demo.entity.dto;

import com.example.demo.entity.Course;
import com.example.demo.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.function.Function;

@Component
public class UserDTOFactory {

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UserDTO {
        private Integer id;

        private String name;

        private Integer number;

        @Builder.Default
        private Integer role = 0; // default 0 student

        @Builder.Default
        private Integer credit = 0;
    }

//    public UserDTO convertToDTO(User user){
//        UserDTO userDTO = new UserDTO();
//        userDTO.setId(user.getId());
//        userDTO.setName(user.getName());
//        userDTO.setNumber(user.getNumber());
//        userDTO.setRole(user.getRole());
//        userDTO.setCredit(user.getCredit());
//        return userDTO;
//    }

    public Function<User, UserDTOFactory.UserDTO> convertToDTO = user -> {
        return UserDTOFactory.UserDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .number(user.getNumber())
                .credit(user.getNumber())
                .role(user.getRole())
                .build();

    };
}
