package com.incubator.app.service;

import com.incubator.app.entity.Test;

import java.util.List;

public interface TestService extends GenericService<Test> {
    List<Test> findAll();

    List<Test> findALlByTopic(long topicId);
    void deleteAll(long []ids);

    Long countQuestionsInTest(long testId);
}
