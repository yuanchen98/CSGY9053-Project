package com.example.demo.entity.dto;

import com.example.demo.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

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

    public UserDTO convertToDTO(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setNumber(user.getNumber());
        userDTO.setRole(user.getRole());
        userDTO.setCredit(user.getCredit());
        return userDTO;
    }
}
