package com.incubator.app.service;

import com.incubator.app.entity.Test;

import java.util.List;

public interface TestService extends GenericService<Test> {
    List<Test> findAll();
    void deleteAll(long []ids);
}
