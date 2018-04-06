package com.incubator.app.service.impl;

import com.incubator.app.dao.QuestionDao;
import com.incubator.app.entity.Question;
import com.incubator.app.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {


    @Autowired
    private QuestionDao questionDao;

    @Override
    public List<Question> findAll() {
        return questionDao.findAll();
    }

    @Override
    public long countQuestionsInTest(long testId) {
        return questionDao.countQuestionsInTest(testId);
    }

    @Override
    public Question findNextQuestionByTest(long testId, long questionId) {
        return questionDao.findNextQuestionByTest(testId, questionId);
    }

    @Override
    public List<Question> findByTest(long testId) {
        return questionDao.findByTest(testId);
    }

    @Override
    public void insert(Question entity) {
        questionDao.insert(entity);
    }

    @Override
    public void update(Question entity) {
        questionDao.update(entity);
    }

    @Override
    public void delete(long id) {
        questionDao.delete(id);
    }

    @Override
    public Question findById(long id) {
        return questionDao.findById(id);
    }
}
