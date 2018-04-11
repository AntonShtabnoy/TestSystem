package com.incubator.app.service;

import com.incubator.app.entity.Question;
import com.incubator.app.entity.Statistic;

import java.util.Date;
import java.util.List;

public interface StatisticService extends GenericService<Statistic> {
    List<Question> findWrongAnswers(long test, long userId, Date date);

    List<Object[]> userStatistic(long id);

    List<Object[]> adminTestStatistics();

    List<Object[]> adminQuestionStatistics();

    List<Object[]> tutorTestStatistics();

    List<Object[]> tutorQuestionStatistics();
}
