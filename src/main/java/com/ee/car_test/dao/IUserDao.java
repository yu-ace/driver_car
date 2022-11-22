package com.ee.car_test.dao;

import com.ee.car_test.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserDao extends JpaRepository<User,Integer> {
    User findById(int id);
    User findByName(String name);
    List<User> findByIsDelete(int isDelete);
}
