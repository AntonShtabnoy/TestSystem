package com.incubator.app.dao;

import com.incubator.app.entity.Question;
import com.incubator.app.entity.Statistic;
import com.incubator.app.entity.Test;

import java.util.Date;
import java.util.List;

public interface StatisticDao extends GenericDao<Statistic> {
    List<Question> findWrongAnswers(long test, long userId, Date date);
}
