package com.ee.car_test.service.impl;

import com.ee.car_test.dao.IUserDao;
import com.ee.car_test.model.User;
import com.ee.car_test.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {

    @Autowired
    IUserDao userDao;

    @Override
    public void newUser(String name, String password) {
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        user.setIsDelete(0);
        userDao.save(user);
    }

    @Override
    public void changePassword(String name, String password) {
        User user = userDao.findByName(name);
        user.setPassword(password);
        userDao.save(user);
    }

    @Override
    public void deleteUser(int id) {
        User user = userDao.findById(id);
        user.setIsDelete(1);
        userDao.save(user);
    }

    @Override
    public List<User> getUserList(int isDelete) {
        return userDao.findByIsDelete(isDelete);
    }

    @Override
    public User getUserByName(String name) {
        return userDao.findByName(name);
    }

    @Override
    public User getUserById(int id) {
        return userDao.findById(id);
    }
}
