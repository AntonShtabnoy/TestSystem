package com.incubator.app.service;

import com.incubator.app.entity.User;

import java.util.List;

public interface UserService extends GenericService<User> {
    List<User> findAll();

    User findByLogin(String login);
    void deleteAll(long[] ids);
}
