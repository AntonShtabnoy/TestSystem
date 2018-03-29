package com.incubator.app.dao;

import com.incubator.app.entity.Test;

import java.util.List;

public interface TestDao extends GenericDao<Test> {
    List<Test> findAll();
    void deleteAll(long []ids);
}
