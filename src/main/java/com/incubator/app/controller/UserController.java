package com.incubator.app.controller;

import com.incubator.app.dao.AnswerDao;
import com.incubator.app.dto.AnswerDTO;
import com.incubator.app.entity.*;
import com.incubator.app.service.*;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import sun.util.resources.LocaleData;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.time.LocalDate;
import java.util.*;

@Controller
@SessionAttributes({"topic", "test", "question", "topics", "countQuestions"})
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
    public ModelAndView startTest(@RequestParam("test") String test) {
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
    String getResult(@RequestBody AnswerDTO answerDTO, Principal principal, HttpSession session) {
        List<Question> wrongQuestions = new ArrayList<>();
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
            } else {
                statistic.setIsCorrect(0);
                wrongQuestions.add(currentQuestion);
            }
            statistic.setQuestion(currentQuestion);
            statistic.setUser(user);
            statisticService.insert(statistic);
        }
        session.setAttribute("wrongQuestions", wrongQuestions);
        return "{\"msg\":\"success\"}";
    }

    @RequestMapping(value = {"/questions/{id}"}, method = RequestMethod.GET)
    public ModelAndView nextQuestion(@PathVariable("id") long id, HttpSession session) {
        String type = "radio";
        ModelAndView modelAndView = new ModelAndView();
        Question previousQuestion = (Question) session.getAttribute("question");
        long amount = questionService.countQuestionsInTest(previousQuestion.getTest().getId());
            Question question = questionService.findNextQuestionByTest(previousQuestion.getTest().getId(), id);
            modelAndView.addObject("question", question);
            modelAndView.setViewName("/user/user-test");
            long countCorrect = countCorrectAnswers(question.getAnswers());
            if (countCorrect > 1) {
                type = "checkbox";
            }
            modelAndView.addObject("type", type);
        return modelAndView;
    }

    @RequestMapping(value = {"/statistics"}, method = RequestMethod.GET)
    public ModelAndView showStatistics(HttpSession session, Principal principal) {
        //Question question = (Question) session.getAttribute("question");
        //User user = userService.findByLogin(principal.getName());
        ModelAndView modelAndView = new ModelAndView();
        //List<Question> questions = statisticService.findWrongAnswers(question.getTest().getId(), user.getId(), new Date());
        List<Question> questions = (List<Question>) session.getAttribute("wrongQuestions");
        modelAndView.addObject("wrongQuestions", questions);
        modelAndView.setViewName("/user/user-statistics");
        return modelAndView;
    }

    @RequestMapping(value = {"/stat"}, method = RequestMethod.GET)
    public ModelAndView showPersonalStatistics(Principal principal) {
        ModelAndView modelAndView = new ModelAndView();
        User user = userService.findByLogin(principal.getName());
        modelAndView.addObject("statistics", statisticService.userStatistic(user.getId()));
        modelAndView.setViewName("/user/user-stat");
        return modelAndView;
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
