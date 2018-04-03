package com.incubator.app.dao.impl;

import com.incubator.app.dao.QuestionDao;
import com.incubator.app.dao.util.HibernateUtil;
import com.incubator.app.entity.Question;
import com.incubator.app.entity.Test;
import com.incubator.app.entity.User;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class QuestionDaoImpl implements QuestionDao {
    private final static String FIND_ALL = "from Question q where q.isDeleted =0";
    private final static String FIND_BY_TEST = "from Question q where q.test.id=:id";
    private final static String DELETE_QUESTION = "update Question q set q.isDeleted = 1 where q.id=:id";
    private final static String FIND_BY_TOPIC_AND_TEST =
            //"select q.id, q.description, q.test, q.answers " +
            "from Question q " +
                    "inner join q.test.topic t where q.test.id=:testId and q.id=:questionId and t.id=:topicId and q.isDeleted=0";


    @Override
    public List<Question> findAll() {
        ActionDaoUtil<Question> actionDaoUtil = new ActionDaoUtil<>();
        return actionDaoUtil.findAll(FIND_ALL);
    }

    @Override
    public List<Question> findByTest(long testId) {
        List<Question> questions = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery(FIND_BY_TEST);
        query.setParameter("id", testId);
        questions = query.list();
        session.getTransaction().commit();
        HibernateUtil.shutdown();
        return questions;
    }

    @Override
    public Question findByTopicAndTest(long topicId, long testId, long questionId) {
        Question question = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Question.class).add(Restrictions.eq("test.id", testId)).
                add(Restrictions.eq("id", questionId)).createAlias("test", "t").createAlias("t.topic", "tt").
                add(Restrictions.eq("tt.id", topicId));
//        Query query = session.createQuery(FIND_BY_TOPIC_AND_TEST);
//        query.setParameter("testId", testId);
//        query.setParameter("questionId", questionId);
//        query.setParameter("topicId", topicId);
//        question = (Question) query.uniqueResult();
        question = (Question) criteria.uniqueResult();
        System.out.println(question.getAnswers());
        System.out.println(question);
        session.getTransaction().commit();
        HibernateUtil.shutdown();
        return question;
    }

    @Override
    public void delete(long id) {
        ActionDaoUtil.delete(DELETE_QUESTION, id);
    }

    @Override
    public Question findById(long id) {
        Question question = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        question = (Question) session.get(Question.class, id);
        session.getTransaction().commit();
        HibernateUtil.shutdown();
        return question;
    }
}
