package com.example.demo.entity.rpo;

import com.example.demo.entity.Course;
import com.example.demo.entity.other.SystemGlobalException;
import com.example.demo.service.CourseService;
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
public class CourseRPOFactory {

    @Resource
    private CourseService courseService;



    @Data
    @Builder
    public static class CourseRPO implements Serializable {

        private Integer id;

        @NotBlank(message = "Course name can not be null")
        private String name;

        @Builder.Default
        private Integer credit = 0;

        @NotNull(message = "Begin time can not be null")
        @JsonFormat(locale = "US",shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private Timestamp beginTime;

        @NotNull(message = "End time can not be null")
        @JsonFormat(locale = "US",shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private Timestamp endTime;

        @Builder.Default
        private Integer limitNum = 0;


    }

    public Function<CourseRPO, Course> rpoToPojo = courseSaveRPO -> {
        Course course = null;
        if (courseSaveRPO.getId() != null) {
            course = courseService.getById(courseSaveRPO.getId());
        }
        if (course == null) {
            course = new Course();
        }
        if(courseSaveRPO.getBeginTime().getTime()>courseSaveRPO.getEndTime().getTime()){
            throw new SystemGlobalException("Start time must be earlier than end time!");
        }
        course.setName(courseSaveRPO.getName());
        course.setCredit(courseSaveRPO.getCredit());
        course.setStartTime(courseSaveRPO.getBeginTime());
        course.setEndTime(courseSaveRPO.getEndTime());
        course.setStuLimit(courseSaveRPO.getLimitNum());
        return course;
    };
}
