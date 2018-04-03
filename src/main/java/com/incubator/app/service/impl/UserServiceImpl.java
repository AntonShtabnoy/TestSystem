package com.incubator.app.service.impl;

import com.incubator.app.dao.UserDao;
import com.incubator.app.entity.User;
import com.incubator.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public void insert(User entity) {
        userDao.insert(entity);
    }

    @Override
    public void update(User entity) {
        userDao.update(entity);
    }

    @Override
    public void delete(long id) {
          userDao.delete(id);
    }

    @Override
    public User findById(long id) {
    return userDao.findById(id);
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public User findByLogin(String login) {
        return userDao.findByLogin(login);
    }

    @Override
    public void deleteAll(long[] ids) {
        userDao.deleteAll(ids);
    }
}
