package com.incubator.app.dao.impl;

import com.incubator.app.dao.TopicDao;
import com.incubator.app.dao.util.HibernateUtil;
import com.incubator.app.entity.Test;
import com.incubator.app.entity.Topic;
import com.incubator.app.entity.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TopicDaoImpl implements TopicDao {
    private final static String FIND_ALL = " from Topic t where t.isDeleted =0";
    private final static String DELETE_TOPIC = "update Topic t set t.isDeleted = 1 where t.id=:id";

    @Override
    public void delete(long id) {
      ActionDaoUtil.delete(DELETE_TOPIC, id);
    }

    @Override
    public Topic findById(long id) {
        Topic topic = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        topic = (Topic) session.get(Topic.class, id);
        session.getTransaction().commit();
        HibernateUtil.shutdown();
        return topic;
    }

    @Override
    public List<Topic> findAll() {
        ActionDaoUtil<Topic> actionDaoUtil = new ActionDaoUtil<>();
        return actionDaoUtil.findAll(FIND_ALL);
    }

    @Override
    public void deleteAll(long[] ids) {
        for (long id: ids) {
            delete(id);
        }
    }
}
