package com.incubator.app.dao.impl;

import com.incubator.app.dao.AnswerDao;
import com.incubator.app.dao.util.HibernateUtil;
import com.incubator.app.entity.Answer;
import com.incubator.app.entity.Statistic;
import com.incubator.app.entity.User;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

@Repository
public class AnswerDaoImpl implements AnswerDao {

    private final static String IS_CORRECT = "CALL isCorrect(:id)";


    @Override
    public Boolean isCorrect(long id) {
        Boolean result = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        SQLQuery query = session.createSQLQuery(IS_CORRECT);
        query.setParameter("id", id);
        result = (Boolean) query.uniqueResult();
        session.getTransaction().commit();
        HibernateUtil.shutdown();
        return result;
    }

    @Override
    public void delete(long id) {

    }

    @Override
    public Answer findById(long id) {
        Answer answer = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        answer = (Answer) session.get(Answer.class, id);
        session.getTransaction().commit();
        HibernateUtil.shutdown();
        return answer;
    }
}
