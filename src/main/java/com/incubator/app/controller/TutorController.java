package com.incubator.app.controller;


import com.incubator.app.dto.QuestionDTO;
import com.incubator.app.entity.*;
import com.incubator.app.service.QuestionService;
import com.incubator.app.service.StatisticService;
import com.incubator.app.service.TestService;
import com.incubator.app.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
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

    @Autowired
    private StatisticService statisticService;


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
        test.setTopic(topicService.findById(test.getTopic().getId()));
        test.setIsDeleted(0);
        testService.insert(test);
        return "redirect:/tutor/";
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
        test.setTopic(testService.findById(id).getTopic());
        testService.update(test);
        return "redirect:/tutor/";
    }

    @RequestMapping(value = {"/tests/{ids}"}, method = RequestMethod.DELETE)
    public @ResponseBody
    String deleteTests(@PathVariable("ids") long[] ids) {
        testService.deleteAll(ids);
        return "{\"msg\":\"success\"}";
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
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = {"/questions/create/{id}"}, method = RequestMethod.GET)
    public String createQuestion(@PathVariable("id") long id) {
        //System.out.println(id);
        return "/tutor/create-question";
    }

    @RequestMapping(value = {"/questions/create/{id}"}, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    String saveQuestion(@PathVariable("id") long id, @RequestBody QuestionDTO questionDTO) {
        Question question = new Question();
        Test test = new Test();
        Answer answer = new Answer();
        test.setId(id);
        question.setTest(test);
        Set<Answer> answers = new HashSet<>();
        Set<Literature> literature = new HashSet<>();
        Set<Link> links = new HashSet<>();
        Literature lit = new Literature();
        Link link = new Link();
        link.setLink(questionDTO.getLink());
        link.setLiterature(lit);
        links.add(link);
        lit.setQuestion(question);
        lit.setDescription(questionDTO.getLiterature());
        lit.setLinks(links);
        literature.add(lit);
        int j = 0;
        for (int i = 0; i < questionDTO.getAnswers().size(); i++) {
            answer = new Answer();
            answer.setDescription(questionDTO.getAnswers().get(i));
            answer.setIsCorrect(0);
            //System.out.println(questionDTO.getCorrect().size());
            if (j < questionDTO.getCorrect().size()) {
                //System.out.println(Integer.valueOf(questionDTO.getCorrect().get(i)));
                if (Integer.valueOf(questionDTO.getCorrect().get(j)) == i) {
                    answer.setIsCorrect(1);
                    j++;
                }
            }
            answer.setQuestion(question);
            answers.add(answer);
        }
        question.setDescription(questionDTO.getDescription());
        question.setAnswers(answers);
        question.setIsDeleted(0);
        question.setLiteratureList(literature);
        System.out.println(question);
        questionService.insert(question);
        return "{\"msg\":\"success\"}";
    }


    @RequestMapping(value = {"/statistics"})
    public ModelAndView adminStatistics() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("tutor/tutor-statistics");
        modelAndView.addObject("testStatistics", statisticService.tutorTestStatistics());
        modelAndView.addObject("questionStatistics", statisticService.tutorQuestionStatistics());
        modelAndView.addObject("userStatistics", statisticService.tutorUserStatistics());
        return modelAndView;
    }
}
