package com.example.demo.entity;

import com.example.demo.entity.base.BaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;


@Entity
@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "User")
public class User extends BaseEntity {

    /**
    student or teacher number
     */
    @Column(name = "number", columnDefinition = "int(11) not null")
    private Integer number;

    @Column(name = "password", columnDefinition = "varchar(255) not null")
    private String password;

    @Builder.Default
    @Column(name = "role", columnDefinition = "int(11) not null default 0")
    private Integer role=0;

    @Builder.Default
    @Column(name = "credit", columnDefinition = "int(11) not null default 0")
    private Integer credit=0;

}
