package com.incubator.app.dao;

import com.incubator.app.entity.Question;
import com.incubator.app.entity.Statistic;

import java.util.Date;
import java.util.List;

public interface StatisticDao extends GenericDao<Statistic> {
    List<Question> findWrongAnswers(long test, long userId, Date date);

    List<Object[]> userStatistic(long id);

    List<Object[]> adminTestStatistics();

    List<Object[]> adminUserStatistics();

    List<Object[]> adminQuestionStatistics();

    List<Object[]> tutorTestStatistics();

    List<Object[]> tutorQuestionStatistics();

    List<Object[]> tutorUserStatistics();
}
