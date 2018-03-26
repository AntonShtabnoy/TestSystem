package com.incubator.app.dao;

import com.incubator.app.dao.util.HibernateUtil;
import org.hibernate.Session;

public interface GenericDao<T> {
   default void insert(T entity){
       Session session = HibernateUtil.getSessionFactory().openSession();
       session.beginTransaction();
       session.save(entity);                      //? persist()
       session.getTransaction().commit();
       HibernateUtil.shutdown();
   };
   default void update(T entity){
       Session session = HibernateUtil.getSessionFactory().openSession();
       session.beginTransaction();
       session.merge(entity);                     //? update()
       session.getTransaction().commit();
       HibernateUtil.shutdown();
   };
    void delete(long id);
    T findById(long id);
}
