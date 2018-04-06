package com.incubator.app.dao.impl;

import com.incubator.app.dao.StatisticDao;
import com.incubator.app.dao.util.HibernateUtil;
import com.incubator.app.entity.Question;
import com.incubator.app.entity.Statistic;
import com.incubator.app.entity.Test;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class StatisticDaoImpl implements StatisticDao {

    //   private static final String L ="select question from Statistic s inner ;


    @Override
    public void insert(Statistic entity) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.persist(entity);
        session.getTransaction().commit();
        HibernateUtil.shutdown();
    }

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

    @Override
    public List<Question> findWrongAnswers(long testId, long userId, Date eDate) {
        long time = eDate.getTime();
        Date sDate = new Date(time - (2 * 60000));
        System.out.println(eDate);
        System.out.println(sDate);
        List<Question> wrongQuestions = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Statistic.class)
                .setProjection(Projections.projectionList()
                        .add(Projections.property("question"), "question"))
                .createAlias("question", "q")
                .createAlias("q.test", "t")
                .add(Restrictions.eq("t.id", 1L))
                .add(Restrictions.eq("user.id", userId))
                .add(Restrictions.eq("isCorrect", 0))
                .add(Restrictions.between("date", sDate, eDate));
        wrongQuestions = criteria.list();
        session.getTransaction().commit();
        HibernateUtil.shutdown();
        return wrongQuestions;
    }
}
