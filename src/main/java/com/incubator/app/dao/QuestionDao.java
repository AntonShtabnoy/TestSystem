package com.incubator.app.dao;

import com.incubator.app.entity.Question;

import java.util.List;

public interface QuestionDao extends GenericDao<Question> {
    List<Question> findAll();
    List<Question> findByTest(long testId);

    Question findNextQuestionByTest(long testId, long questionId);

    long countQuestionsInTest(long testId);
}
