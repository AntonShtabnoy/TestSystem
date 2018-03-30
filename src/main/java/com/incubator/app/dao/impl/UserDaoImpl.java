package com.incubator.app.dao.impl;

import com.incubator.app.dao.UserDao;
import com.incubator.app.dao.util.HibernateUtil;
import com.incubator.app.entity.Test;
import com.incubator.app.entity.User;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    private final static String DELETE_USER = "update User u set u.isDeleted = 1 where u.id=:id";
    private final static String FIND_BY_LOGIN = "from User u where login =:login and isDeleted=0";
    private final static String FIND_ALL = " from User u where u.isDeleted =0";


    @Override
    public void delete(long id) {
      ActionDaoUtil.delete(DELETE_USER, id);
    }

    @Override
    public User findById(long id) {
        User user = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        user = (User)session.get(User.class, id);
        session.getTransaction().commit();
        HibernateUtil.shutdown();
        return user;
    }

    @Override
    public User findByLogin(String login) {
        User user = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery(FIND_BY_LOGIN);            //select all fields, how only 3
        query.setParameter("login", login);
        user = (User) query.uniqueResult();
        session.getTransaction().commit();
        HibernateUtil.shutdown();
        return user;
    }

    @Override
    public List<User> findAll() {
        ActionDaoUtil<User> actionDaoUtil = new ActionDaoUtil<>();
        return actionDaoUtil.findAll(FIND_ALL);
    }

}
