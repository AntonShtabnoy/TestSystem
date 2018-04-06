package com.incubator.app.dao.impl;

import com.incubator.app.dao.TestDao;
import com.incubator.app.dao.util.HibernateUtil;
import com.incubator.app.entity.Question;
import com.incubator.app.entity.Test;
import com.incubator.app.entity.User;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TestDaoImpl implements TestDao {
    private final static String FIND_ALL = "from Test t where t.isDeleted =0";
    private final static String DELETE_TOPIC = "update Test t set t.isDeleted = 1 where t.id=:id";

    @Override
    public List<Test> findAll() {
        ActionDaoUtil<Test> actionDaoUtil = new ActionDaoUtil<>();
        return actionDaoUtil.findAll(FIND_ALL);
    }

    @Override
    public List<Test> findAllByTopic(long topicId) {
        List<Test> tests = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Test.class).add(Restrictions.eq("topic.id", topicId));
        tests = criteria.list();
        session.getTransaction().commit();
        HibernateUtil.shutdown();
        return tests;
    }

    @Override
    public Long countQuestionsInTest(long testId) {
        Long count = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Question.class).add(Restrictions.eq("test.id", testId)).setProjection(Projections.rowCount());
        count = (Long) criteria.uniqueResult();
        session.getTransaction().commit();
        HibernateUtil.shutdown();
        return count;
    }

    @Override
    public void deleteAll(long[] ids) {
        for (long id: ids) {
            delete(id);
        }
    }

    @Override
    public void delete(long id) {
       ActionDaoUtil.delete(DELETE_TOPIC, id);
    }

    @Override
    public Test findById(long id) {
        Test test = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        test = (Test)session.get(Test.class, id);
        session.getTransaction().commit();
        HibernateUtil.shutdown();
        return test;
    }
}
