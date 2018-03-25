package com.incubator.app.dao.impl;

import com.incubator.app.dao.TopicDao;
import com.incubator.app.dao.util.HibernateUtil;
import com.incubator.app.entity.Topic;
import com.incubator.app.entity.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TopicDaoImpl implements TopicDao {
    private final static String FIND_ALL = " from Topic t where t.isDeleted =0";


    @Override
    public void insert(Topic entity) {

    }

    @Override
    public void update(Topic entity) {

    }

    @Override
    public void delete(long id) {

    }

    @Override
    public Topic findById(long id) {
        return null;
    }

    @Override
    public List<Topic> findAll() {
        List<Topic> topics = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery(FIND_ALL);
        topics = query.list();
        session.getTransaction().commit();
        HibernateUtil.shutdown();
        return topics;
    }
}
