package com.ideas2It.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ideas2It.model.User;

public interface UserDao extends JpaRepository<User, Integer> {

}
