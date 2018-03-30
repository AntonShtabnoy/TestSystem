package com.incubator.app.dao.impl;

import com.incubator.app.dao.TestDao;
import com.incubator.app.dao.util.HibernateUtil;
import com.incubator.app.entity.Test;
import com.incubator.app.entity.User;
import org.hibernate.Query;
import org.hibernate.Session;
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
