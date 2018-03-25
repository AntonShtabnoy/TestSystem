package com.incubator.app.service.impl;

import com.incubator.app.dao.TopicDao;
import com.incubator.app.entity.Topic;
import com.incubator.app.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicServiceImpl implements TopicService {

    @Autowired
    private TopicDao topicDaoImpl;

    @Override
    public List<Topic> findAll() {
        return topicDaoImpl.findAll();
    }

    @Override
    public void insert(Topic entity) {

    }

    @Override
    public void update(Topic entity) {

    }

    @Override
    public void delete(long id) {

    }

    @Override
    public Topic findById(long id) {
        return null;
    }
}
