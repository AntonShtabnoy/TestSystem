package com.incubator.app.dao.impl;

import com.incubator.app.dao.StatisticDao;
import com.incubator.app.dao.util.HibernateUtil;
import com.incubator.app.entity.Statistic;
import com.incubator.app.entity.Test;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

@Repository
public class StatisticDaoImpl implements StatisticDao {


//    @Override
//    public void insert(Statistic entity) {
//        Session session = HibernateUtil.getSessionFactory().openSession();
//        session.beginTransaction();
//        session.persist(entity);
//        session.getTransaction().commit();
//        HibernateUtil.shutdown();
//    }

    @Override
    public void delete(long id) {

    }

    @Override
    public Statistic findById(long id) {
        Statistic statistic = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        statistic = (Statistic) session.get(Statistic.class, id);
        session.getTransaction().commit();
        HibernateUtil.shutdown();
        return statistic;
    }
}
