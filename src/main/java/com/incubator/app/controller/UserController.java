package com.incubator.app.controller;

import com.incubator.app.entity.Test;
import com.incubator.app.entity.Topic;
import com.incubator.app.service.TestService;
import com.incubator.app.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private TopicService topicService;

    @Autowired
    private TestService testService;


    @RequestMapping(value = {"/user"}, method = RequestMethod.GET)
    public String getMainPage() {
        return "/user/user";
    }

    @RequestMapping(value = {"/user/topics"}, method = RequestMethod.GET)
    public ModelAndView getTopicsAndTests() {
        ModelAndView modelAndView = new ModelAndView();
        List<Test> tests = testService.findAll();
        List<Topic> topics = topicService.findAll();
        modelAndView.addObject("tests", tests);
        modelAndView.addObject("topics", topics);
        modelAndView.setViewName("user/user-topic");
        return modelAndView;
    }


}
