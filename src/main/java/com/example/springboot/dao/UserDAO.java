package com.example.springboot.dao;

import com.example.springboot.models.User;
import com.sun.security.auth.UserPrincipal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserDAO extends JpaRepository<User, Integer> {

    @Query("select u from User u where u.name=:name")
    List<User> findMeAUserWithName(@Param("name") String xxx);

    List<User> findByName (String name);

    User findByNameAndId(String name, int id);
}
