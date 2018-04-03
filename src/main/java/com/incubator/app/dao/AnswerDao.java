package com.incubator.app.dao;

import com.incubator.app.entity.Answer;

public interface AnswerDao extends GenericDao<Answer> {
    Boolean isCorrect(long id);
}
