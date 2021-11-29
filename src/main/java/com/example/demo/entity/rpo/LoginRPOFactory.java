package com.example.demo.entity.rpo;

import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Component
public class LoginRPOFactory {

    @Data
    @Builder
    public static class LoginRPO implements Serializable {
        @NotNull(message = "account number can't be empty")
        private Integer number;

        @NotBlank(message = "password can't be empty")
        private String password;
    }
}
