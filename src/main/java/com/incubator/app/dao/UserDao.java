package com.incubator.app.dao;

import com.incubator.app.entity.User;

import java.util.List;

public interface UserDao extends GenericDao<User> {
    User findByLogin(String login);
    List<User> findAll();
    void deleteSeveralUsers(long[] ids);
}
