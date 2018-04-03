package com.incubator.app.service.impl;

import com.incubator.app.dao.StatisticDao;
import com.incubator.app.entity.Statistic;
import com.incubator.app.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
