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

    @Query("from CourseUser where student = ?1 and course = ?2")
    CourseUser findByStudentAndCourse(User student, Course course);

    @Query("from CourseUser where student = ?1")
    List<CourseUser> findByStudent(User student);

    @Query("from CourseUser where course = ?1")
    List<CourseUser> findByCourse(Course course);

    @Transactional
    Long deleteCourseUserByCourseAndStudent(Course course, User student);
}
