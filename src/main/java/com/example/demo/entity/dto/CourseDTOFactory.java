package com.example.demo.entity.dto;

import com.example.demo.entity.Course;
import com.example.demo.entity.other.SystemGlobalException;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.function.Function;

@Component
public class CourseDTOFactory {

    @Resource
    private UserDTOFactory userDTOFactory;

    @Data
    @Builder
    public static class CourseDTO implements Serializable {

        private Integer id;

        private String name;

        private UserDTOFactory.UserDTO teacher;

        @Builder.Default
        private Integer credit = 0;

        @JsonFormat(locale = "US",shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private Timestamp startTime;

        @JsonFormat(locale = "US",shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private Timestamp endTime;

        @Builder.Default
        private Integer limitNum = 0;

        @Builder.Default
        private Integer stuNum = 0; // 已选的人数
    }

    public Function<Course, CourseDTO> pojoToDTO = course -> {
        return CourseDTO.builder()
                .id(course.getId())
                .name(course.getName())
                .credit(course.getCredit())
                .teacher(userDTOFactory.convertToDTO(course.getTeacher()))
                .startTime(course.getStartTime())
                .endTime(course.getEndTime())
                .limitNum(course.getStuLimit())
                .stuNum(course.getStuNum())
                .build();
    };
}
