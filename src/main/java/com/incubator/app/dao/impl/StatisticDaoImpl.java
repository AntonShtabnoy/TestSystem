package com.incubator.app.dao.impl;

import com.incubator.app.dao.StatisticDao;
import com.incubator.app.dao.util.HibernateUtil;
import com.incubator.app.entity.Question;
import com.incubator.app.entity.Statistic;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class StatisticDaoImpl implements StatisticDao {


    private final static String USER_STATISTIC = "CALL user_statistics(:id)";
    private final static String ADMIN_TEST_STATISTICS = "CALL admin_test_statistics()";
    private final static String ADMIN_QUESTION_STATISTICS = "CALL admin_question_statistics()";
    private final static String ADMIN_USER_STATISTICS = "CALL admin_user_statistics()";
    private final static String TUTOR_TEST_STATISTICS = "CALL tutor_test_statistics()";
    private final static String TUTOR_QUESTION_STATISTICS = "CALL tutor_question_statistics()";
    private final static String TUTOR_USER_STATISTICS = "CALL tutor_user_statistics()";

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

    @Override
    public List<Object[]> userStatistic(long id) {
        List<Object[]> statistic = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        SQLQuery query = session.createSQLQuery(USER_STATISTIC);
        query.setParameter("id", id);
        statistic = query.list();
        session.getTransaction().commit();
        HibernateUtil.shutdown();
        return statistic;
    }

    @Override
    public List<Object[]> adminTestStatistics() {
        return statistics(ADMIN_TEST_STATISTICS);
    }

    @Override
    public List<Object[]> adminUserStatistics() {
        return statistics(ADMIN_USER_STATISTICS);
    }

    @Override
    public List<Object[]> adminQuestionStatistics() {
        return statistics(ADMIN_QUESTION_STATISTICS);
    }

    @Override
    public List<Object[]> tutorTestStatistics() {
        return statistics(TUTOR_TEST_STATISTICS);
    }

    @Override
    public List<Object[]> tutorQuestionStatistics() {
        return statistics(TUTOR_QUESTION_STATISTICS);
    }

    @Override
    public List<Object[]> tutorUserStatistics() {
        return statistics(TUTOR_USER_STATISTICS);
    }


    private List<Object[]> statistics(String procedure) {
        List<Object[]> statistic = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        SQLQuery query = session.createSQLQuery(procedure);
        statistic = query.list();
        session.getTransaction().commit();
        HibernateUtil.shutdown();
        return statistic;
    }
}
