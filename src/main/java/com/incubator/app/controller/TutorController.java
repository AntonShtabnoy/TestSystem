package com.incubator.app.controller;


import com.incubator.app.entity.Question;
import com.incubator.app.entity.Test;
import com.incubator.app.entity.Topic;
import com.incubator.app.service.QuestionService;
import com.incubator.app.service.TestService;
import com.incubator.app.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/tutor")
public class TutorController {

    @Autowired
    private TestService testService;

    @Autowired
    private TopicService topicService;

    @Autowired
    private QuestionService questionService;


    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public ModelAndView listAllTests(){
        ModelAndView modelAndView = new ModelAndView();
        List<Test> tests = testService.findAll();
        modelAndView.setViewName("tutor/tests-list");
        modelAndView.addObject("tests", tests);
        return modelAndView;
    }


    @RequestMapping(value = {"/tests/create"}, method = RequestMethod.GET)
    public String redirectToCreateTestPage(Model model){
        model.addAttribute("test", new Test());
        List<Topic> topics = topicService.findAll();
        Map<Long, String> topicsMap = topics.stream().collect(
                Collectors.toMap(Topic::getId, Topic::getName));
        model.addAttribute("topics", topicsMap);
        return "tutor/create-test";
    }

    @RequestMapping(value = {"/tests/create"}, method = RequestMethod.POST)
    public String addTest(@ModelAttribute("test") Test test){
        System.out.println(test);
        test.setTopic(topicService.findById(test.getTopic().getId()));
        test.setIsDeleted(0);
        testService.insert(test);
        return "redirect:/tutor";
    }

    @RequestMapping(value = {"/tests/{id}"}, method = RequestMethod.GET)
    public String editTestRedirect(@PathVariable("id") long id, Model model){
        Test test = testService.findById(id);
        model.addAttribute("test", test);
        return "tutor/create-test";
    }

    @RequestMapping(value = {"/tests/{id}"}, method = RequestMethod.POST)
    public String editTest(@PathVariable("id") long id, @ModelAttribute("test") Test test){
        test.setId(id);
        test.setIsDeleted(0);
        testService.update(test);
        return "redirect:/admin/tests";
    }

    @RequestMapping(value = {"/tests/{ids}"}, method = RequestMethod.DELETE)
    public ResponseEntity deleteTests(@PathVariable("ids") long[] ids){
        testService.deleteAll(ids);
        return new ResponseEntity<Topic>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = {"/questions/{id}"}, method = RequestMethod.GET)
    public ModelAndView findListQuestions(@PathVariable("id") long id) {
        ModelAndView modelAndView = new ModelAndView();
        //System.out.println(modelAndView.getModel().get("id"));
        List<Test> tests = testService.findAll();
        if (id == 0) {
            id = tests.get(0).getId();
        }
        List<Question> questions = questionService.findByTest(id);
        modelAndView.addObject("tests", tests);
        modelAndView.addObject("questions", questions);
        modelAndView.setViewName("/tutor/questions-list");
        return modelAndView;
    }

    @RequestMapping(value = {"/questions/{id}"}, method = RequestMethod.PUT)
    public ResponseEntity<Question> findQuestionsByTest(@PathVariable("id") long id) {
        return new ResponseEntity<Question>(HttpStatus.OK);
    }

    @RequestMapping(value = {"/questions/create/{id}"}, method = RequestMethod.GET)
    public String createQuestion(@PathVariable("id") long id) {
        System.out.println(id);
        return "/tutor/create-question";
    }
}
