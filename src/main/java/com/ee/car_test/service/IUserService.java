package com.ee.car_test.service;

import com.ee.car_test.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IUserService {
    void newUser(String name,String password);
    void changePassword(String name,String password);
    void deleteUser(int id);
    Page<User> getUserList(int isDelete, Pageable pageable);
    User getUserByName(String name);
    User getUserById(int id);
}
