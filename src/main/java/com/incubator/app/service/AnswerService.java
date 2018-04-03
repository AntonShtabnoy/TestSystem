package com.incubator.app.service;

import com.incubator.app.entity.Answer;

public interface AnswerService extends GenericService<Answer> {
    Boolean isCorrect(long id);
}
