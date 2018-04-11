package com.incubator.app.service.impl;

import com.incubator.app.dao.StatisticDao;
import com.incubator.app.entity.Question;
import com.incubator.app.entity.Statistic;
import com.incubator.app.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class StatisticServiceImpl implements StatisticService {

    @Autowired
    private StatisticDao statisticDao;

    @Override
    public void insert(Statistic entity) {
        statisticDao.insert(entity);
    }

    @Override
    public void update(Statistic entity) {
        statisticDao.update(entity);
    }

    @Override
    public void delete(long id) {
        statisticDao.delete(id);
    }

    @Override
    public Statistic findById(long id) {
        return statisticDao.findById(id);
    }

    @Override
    public List<Question> findWrongAnswers(long test, long userId, Date date) {
        return statisticDao.findWrongAnswers(test, userId, date);
    }

    @Override
    public List<Object[]> userStatistic(long id) {
        return statisticDao.userStatistic(id);
    }

    @Override
    public List<Object[]> adminTestStatistics() {
        return statisticDao.adminTestStatistics();
    }

    @Override
    public List<Object[]> adminQuestionStatistics() {
        return statisticDao.adminQuestionStatistics();
    }

    @Override
    public List<Object[]> adminUserStatistics() {
        return statisticDao.adminUserStatistics();
    }

    @Override
    public List<Object[]> tutorTestStatistics() {
        return statisticDao.tutorTestStatistics();
    }

    @Override
    public List<Object[]> tutorQuestionStatistics() {
        return statisticDao.tutorQuestionStatistics();
    }

    @Override
    public List<Object[]> tutorUserStatistics() {
        return statisticDao.tutorUserStatistics();
    }
}
