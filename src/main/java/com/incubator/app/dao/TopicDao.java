package com.incubator.app.dao;

import com.incubator.app.entity.Topic;

import java.util.List;

public interface TopicDao extends GenericDao<Topic>{
    List<Topic> findAll();
}
