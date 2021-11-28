package com.example.demo.repository;

import com.example.demo.entity.CourseUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseUserRepository extends JpaRepository<CourseUser, Integer> {
}
