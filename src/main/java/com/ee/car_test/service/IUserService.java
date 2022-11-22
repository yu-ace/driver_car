package com.ee.car_test.service;

import com.ee.car_test.model.User;

import java.util.List;

public interface IUserService {
    void newUser(String name,String password);
    void changePassword(String name,String password);
    void deleteUser(int id);
    List<User> getUserList(int isDelete);
    User getUserByName(String name);
    User getUserById(int id);
}
