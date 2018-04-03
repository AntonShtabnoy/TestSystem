package com.incubator.app.service.impl;

import com.incubator.app.dao.AnswerDao;
import com.incubator.app.entity.Answer;
import com.incubator.app.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnswerServiceImpl implements AnswerService {

    @Autowired
    private AnswerDao answerDao;

    @Override
    public Boolean isCorrect(long id) {
        return answerDao.isCorrect(id);
    }

    @Override
    public void insert(Answer entity) {

    }

    @Override
    public void update(Answer entity) {

    }

    @Override
    public void delete(long id) {

    }

    @Override
    public Answer findById(long id) {
        return null;
    }
}
