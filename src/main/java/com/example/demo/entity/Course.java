package com.example.demo.entity;

import com.example.demo.entity.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.sql.Timestamp;


@Entity
@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Course")
public class Course extends BaseEntity {

    @Column(name = "name", columnDefinition = "varchar(255) not null")
    private String name;

    /**
     * a teacher can teach several courses
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "teacher_id")
    private User teacher;

    @Builder.Default
    @Column(name = "credit", columnDefinition = "int(11) not null default 0")
    private Integer credit = 0;

    @JsonFormat(locale = "US",shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @Column(name = "begin_time")
    private Timestamp startTime;

    @JsonFormat(locale = "US",shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @Column(name = "end_time")
    private Timestamp endTime;

    @Builder.Default
    @Column(name = "stu_limit", columnDefinition = "int(11) not null default 0")
    private Integer stuLimit=0;

    @Builder.Default
    @Column(name = "stu_num", columnDefinition = "int(11) not null default 0")
    private Integer stuNum=0;



}
