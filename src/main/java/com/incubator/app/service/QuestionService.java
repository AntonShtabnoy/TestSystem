package com.incubator.app.service;

import com.incubator.app.entity.Question;

import java.util.List;

public interface QuestionService extends GenericService<Question> {
    List<Question> findAll();

    List<Question> findByTest(long testId);
}
