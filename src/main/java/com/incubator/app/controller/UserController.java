package com.incubator.app.controller;

import com.incubator.app.dto.AnswerDTO;
import com.incubator.app.entity.*;
import com.incubator.app.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import sun.util.resources.LocaleData;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@Controller
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
        List<Test> tests = testService.findAll();
        List<Topic> topics = topicService.findAll();
        modelAndView.addObject("tests", tests);
        modelAndView.addObject("topics", topics);
        modelAndView.setViewName("user/user-topic");
        return modelAndView;
    }

    @RequestMapping(value = {"/tests"}, method = RequestMethod.POST)
    public ModelAndView startTest(@RequestParam("test") String test, @RequestParam("topic") String topic) {
        String type = "radio";
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/user/user-test");
        Question question = questionService.findByTopicAndTest(Integer.valueOf(topic),
                Integer.valueOf(test), 5);
        modelAndView.addObject("question", question);
        long countCorrect = question.getAnswers().stream().filter(answer -> answer.getIsCorrect() == 1).count();
        if (countCorrect > 1) {
            type = "checkbox";
        }
        modelAndView.addObject("type", type);
        return modelAndView;
    }

    @RequestMapping(value = {"/tests/{id}"}, method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    String acceptAnswer(@RequestBody AnswerDTO answerDTO, @PathVariable("id") long id, Principal principal) {
        Statistic statistic = new Statistic();
        User user = userService.findByLogin(principal.getName());
        Question question = questionService.findById(id);
        LocalDate date = LocalDate.now();
        statistic.setUser(user);
        statistic.setQuestion(question);
        statistic.setDate(date);
        Boolean isCorrect = answerService.isCorrect(id);
        if (isCorrect) {
            statistic.setIsCorrect(1);
        } else statistic.setIsCorrect(0);
        statisticService.insert(statistic);
        System.out.println(answerDTO.getAnswers());
        return "{\"msg\":\"success\"}";
    }
}
