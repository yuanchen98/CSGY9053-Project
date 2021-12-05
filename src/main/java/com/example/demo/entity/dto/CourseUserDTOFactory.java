package com.example.demo.entity.dto;

import com.example.demo.entity.CourseUser;
import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.function.Function;


@Component
public class CourseUserDTOFactory {

    @Resource
    private UserDTOFactory userDTOFactory;
    @Resource
    private CourseDTOFactory courseDTOFactory;

    @Data
    @Builder
    public static class CourseUserDTO {

        private Integer id;
        private Integer studentId;
        private Integer courseId;

        private CourseDTOFactory.CourseDTO course;
        private UserDTOFactory.UserDTO student;

        @Builder.Default
        private Double grade = 0d;

        @Builder.Default
        private Integer status = 0;
    }

    public Function<CourseUser, CourseUserDTO> convertToDTO = userCourse -> {
        return CourseUserDTO.builder()
                .courseId(userCourse.getCourse().getId())
                .studentId(userCourse.getStudent().getId())
                .course(courseDTOFactory.convertToDTO.apply(userCourse.getCourse()))
                .student(userDTOFactory.convertToDTO.apply(userCourse.getStudent()))
                .grade((userCourse.getGrade()))
                .status(userCourse.getStatus())
                .id(userCourse.getId())
                .build();
    };
}
