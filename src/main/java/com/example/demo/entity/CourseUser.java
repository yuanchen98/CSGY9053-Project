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
@Table(name = "CourseUser")
public class CourseUser extends BaseEntity {

    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "stu_id",referencedColumnName = "id")
    @JoinColumn(name = "stu_id")
    private User student;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "course_id")
    private Course course;

    @Builder.Default
    @Column(name = "grade", columnDefinition = "double not null default 0")
    private Double grade =0d;

    /**
     * status 0 means default
     * 1 means pass
     * 2 means fail
     */
    @Builder.Default
    @Column(name = "status", columnDefinition = "int(11) not null default 0")
    private int status = 0;
}
