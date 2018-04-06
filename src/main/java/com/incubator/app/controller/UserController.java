package com.incubator.app.controller;

import com.incubator.app.dao.AnswerDao;
import com.incubator.app.dto.AnswerDTO;
import com.incubator.app.entity.*;
import com.incubator.app.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import sun.util.resources.LocaleData;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.time.LocalDate;
import java.util.*;

@Controller
@SessionAttributes({"topic", "test", "question", "topics"})
@RequestMapping("/user")
public class UserController {

    @Autowired
    private TopicService topicService;

    @Autowired
    private TestService testService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private UserService userService;

    @Autowired
    private AnswerService answerService;

    @Autowired
    private StatisticService statisticService;

    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public String getMainPage() {
        return "/user/user";
    }

    @RequestMapping(value = {"/topics"}, method = RequestMethod.GET)
    public ModelAndView getTopicsAndTests() {
        ModelAndView modelAndView = new ModelAndView();
        List<Topic> topics = topicService.findAll();
        List<Test> tests = testService.findALlByTopic(topics.get(0).getId());
        modelAndView.addObject("topics", topics);
        modelAndView.addObject("tests", tests);
        modelAndView.setViewName("user/user-topic");
        return modelAndView;
    }

    @RequestMapping(value = {"/tests/{id}"}, method = RequestMethod.GET)
    public ModelAndView getTestsByTopic(@PathVariable("id") long id, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        List<Test> tests = testService.findALlByTopic(id);
        modelAndView.addObject("tests", tests);
        modelAndView.addObject("topics", session.getAttribute("topics"));
        modelAndView.setViewName("user/user-topic");
        return modelAndView;
    }

    @RequestMapping(value = {"/tests"}, method = RequestMethod.POST)
    public ModelAndView startTest(@RequestParam("test") String test, @RequestParam("topic") String topic) {
        String type = "radio";
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/user/user-test");
        Question question = questionService.findNextQuestionByTest(Integer.valueOf(test), 0);
        modelAndView.addObject("question", question);
        long countCorrect = question.getAnswers().stream().filter(answer -> answer.getIsCorrect() == 1).count();
        if (countCorrect > 1) {
            type = "checkbox";
        }
        modelAndView.addObject("type", type);
        modelAndView.addObject("countQuestions", testService.countQuestionsInTest(Long.valueOf(test)));
        return modelAndView;
    }

    @RequestMapping(value = {"/questions"}, method = RequestMethod.POST)
    public @ResponseBody
    String getResult(@RequestBody AnswerDTO answerDTO, Principal principal) {
        System.out.println(answerDTO.getAnswers());
        Statistic statistic = null;
        Question currentQuestion = null;
        Boolean isCorrect = null;
        User user = userService.findByLogin(principal.getName());
        Map<String, List<String>> map = answerDTO.getAnswers();
        for (String key : map.keySet()) {
            currentQuestion = questionService.findById(Long.valueOf(key));
            isCorrect = isCorrectAnswers(map.get(key), currentQuestion.getAnswers());
            statistic = new Statistic();
            if (isCorrect) {
                statistic.setIsCorrect(1);
            } else statistic.setIsCorrect(0);
            statistic.setQuestion(currentQuestion);
            statistic.setUser(user);
            statisticService.insert(statistic);
        }
        return "{\"msg\":\"success\"}";
    }

//    @RequestMapping(value = {"/tests/{id}"}, method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
//    public @ResponseBody
//    String acceptAnswer(@RequestBody AnswerDTO answerDTO, @PathVariable("id") long id, Principal principal) {
//        Statistic statistic = new Statistic();
//        User user = userService.findByLogin(principal.getName());
//        Date date = new Date();
//        Question question = questionService.findById(id);
//        statistic.setUser(user);
//        statistic.setQuestion(question);
//        statistic.setDate(date);
////        Boolean isCorrect = isCorrectAnswers(answerDTO.getAnswers(), question.getAnswers());
////        if (isCorrect) {
////            statistic.setIsCorrect(1);
////        } else statistic.setIsCorrect(0);
////        statisticService.insert(statistic);
//        return "{\"msg\":\"success\"}";
//    }

    @RequestMapping(value = {"/questions/{id}"}, method = RequestMethod.GET)
    public ModelAndView nextQuestion(@PathVariable("id") long id, HttpSession session) {
        String type = "radio";
        ModelAndView modelAndView = new ModelAndView();
        Question previousQuestion = (Question) session.getAttribute("question");
        long amount = questionService.countQuestionsInTest(previousQuestion.getTest().getId());
        if (id < amount) {
            Question question = questionService.findNextQuestionByTest(previousQuestion.getTest().getId(), id);
            modelAndView.addObject("question", question);
            modelAndView.setViewName("/user/user-test");
            long countCorrect = countCorrectAnswers(question.getAnswers());
            if (countCorrect > 1) {
                type = "checkbox";
            }
            modelAndView.addObject("type", type);
        } else {

        }
        return modelAndView;
    }

    @RequestMapping(value = {"/statistics"}, method = RequestMethod.GET)
    public String showStatistics(HttpSession session) {
        System.out.println(session.getAttribute("question"));
        return "/user/user-statistics";
    }


    private long countCorrectAnswers(Set<Answer> answers) {
        return answers.stream().filter(answer -> answer.getIsCorrect() == 1).count();
    }

    private Boolean isCorrectAnswers(List<String> answers, Set<Answer> expectedAnswers) {
        long expectedCount = countCorrectAnswers(expectedAnswers);
        long count = 0;
        for (String answer : answers) {
            if (answerService.isCorrect(Long.valueOf(answer))) {
                count++;
            }
        }
        if (count == expectedCount) {
            return true;
        } else {
            return false;
        }
    }
}
