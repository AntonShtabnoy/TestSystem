package com.incubator.app.dao.impl;

import com.incubator.app.dao.util.HibernateUtil;
import com.incubator.app.entity.Test;
import com.incubator.app.entity.User;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

public class ActionDaoUtil<T> {
    static void delete(String deleteQuery, long id){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery(deleteQuery);
        query.setParameter("id", id);
        query.executeUpdate();
        session.getTransaction().commit();
        HibernateUtil.shutdown();
    }

    List<T> findAll(String findAllQuery){
        List<T> list = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery(findAllQuery);
        list = query.list();
        session.getTransaction().commit();
        HibernateUtil.shutdown();
        return list;
    }
}
