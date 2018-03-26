package com.incubator.app.service;

import com.incubator.app.entity.Topic;

import java.util.List;

public interface TopicService extends GenericService<Topic> {
    List<Topic> findAll();
    void deleteAll(long []ids);
}
