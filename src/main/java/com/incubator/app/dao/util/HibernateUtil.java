package com.incubator.app.dao.util;


import com.incubator.app.entity.*;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class HibernateUtil {
    private static final SessionFactory sessionFactory;
    private static final Logger LOGGER = Logger.getLogger(HibernateUtil.class);

    static {
        try {
            sessionFactory = new AnnotationConfiguration().configure().addAnnotatedClass(User.class)
                    .addAnnotatedClass(Role.class)
                    .addAnnotatedClass(Answer.class)
                    .addAnnotatedClass(Link.class)
                    .addAnnotatedClass(Literature.class)
                    .addAnnotatedClass(Question.class)
                    .addAnnotatedClass(Statistic.class)
                    .addAnnotatedClass(Test.class)
                    .addAnnotatedClass(Topic.class)
                    .buildSessionFactory();
        } catch (Throwable ex) {
            LOGGER.error("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        // Close caches and connection pools
        getSessionFactory().close();
    }
}
