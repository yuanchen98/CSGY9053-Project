package com.example.demo.repository;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("select u from User u where u.number = ?1 and u.deleted = false")
    User findByNumber(Integer number);

    @Query("select u from User u where u.name = ?1 and u.deleted = false")
    User findByName(String name);

}
