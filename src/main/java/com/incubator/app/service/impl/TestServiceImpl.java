package com.incubator.app.service.impl;

import com.incubator.app.dao.TestDao;
import com.incubator.app.entity.Test;
import com.incubator.app.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private TestDao testDaoImpl;

    @Override
    public List<Test> findAll() {
        return testDaoImpl.findAll();
    }

    @Override
    public List<Test> findALlByTopic(long topicId) {
        return testDaoImpl.findAllByTopic(topicId);
    }

    @Override
    public void deleteAll(long[] ids) {
        testDaoImpl.deleteAll(ids);
    }

    @Override
    public Long countQuestionsInTest(long testId) {
        return testDaoImpl.countQuestionsInTest(testId);
    }

    @Override
    public void insert(Test entity){
        testDaoImpl.insert(entity);
    }

    @Override
    public void update(Test entity) {
        testDaoImpl.update(entity);
    }

    @Override
    public void delete(long id) {
        testDaoImpl.delete(id);
    }

    @Override
    public Test findById(long id) {
        return testDaoImpl.findById(id);
    }
}
