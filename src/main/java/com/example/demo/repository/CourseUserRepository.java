package com.example.demo.repository;

import com.example.demo.entity.Course;
import com.example.demo.entity.CourseUser;
import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface CourseUserRepository extends JpaRepository<CourseUser, Integer> {

    @Query("from CourseUser where student = ?1 and course = ?2 and deleted = false")
    CourseUser findByStudentAndCourse(User student, Course course);


    @Query("from CourseUser where student = ?1 and deleted = false")
    List<CourseUser> findByStudent(User student);

    @Transactional
    Long deleteCourseUserByCourseAndStudent(Course course, User student);
}
