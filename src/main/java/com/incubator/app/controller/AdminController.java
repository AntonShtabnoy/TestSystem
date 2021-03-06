package com.incubator.app.controller;


import com.incubator.app.entity.Role;
import com.incubator.app.entity.Topic;
import com.incubator.app.entity.User;
import com.incubator.app.service.StatisticService;
import com.incubator.app.service.TopicService;
import com.incubator.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

//@RestController
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;
    @Autowired
    private TopicService topicService;
    @Autowired
    private StatisticService statisticService;


//    @RequestMapping(value = {"/admin/"}, method = RequestMethod.GET)
//    public ResponseEntity<List<User>> listAllUsers() {
//        List<User> users = userService.findAll();
//        if(users.isEmpty()){
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
//        }
//        return new ResponseEntity<>(users, HttpStatus.OK);
//    }

    @RequestMapping(value = {""}, method = RequestMethod.GET)
    public ModelAndView listAllUsers(){
        ModelAndView modelAndView = new ModelAndView();
        List<User> users = userService.findAll();
        modelAndView.setViewName("admin/admin");
        modelAndView.addObject("users", users);
        return modelAndView;
    }

    @RequestMapping(value = {"/create"}, method = RequestMethod.GET)
    public String redirectToCreatePage(Model model){
        model.addAttribute("user", new User());
        return "admin/create-user";
    }

    @RequestMapping(value = {"/topics/create"}, method = RequestMethod.GET)
    public String redirectToCreateTopicPage(Model model){
        model.addAttribute("topic", new Topic());
        return "admin/create-topic";
    }

    @RequestMapping(value = {"/create"}, method = RequestMethod.POST)
    public String registration(@ModelAttribute("user") User user){
        user.setPassword(encode(user.getPassword()));
        user.setRole(getRoleByName(user.getRole().getName()));
        user.setIsDeleted(0);
        userService.insert(user);
        return "redirect:/admin";
    }

    @RequestMapping(value = {"/topics/create"}, method = RequestMethod.POST)
    public String registration(@ModelAttribute("topic") Topic topic){
        topic.setIsDeleted(0);
        topicService.insert(topic);
        return "redirect:/admin/topics";
    }

    @RequestMapping(value = {"/{id}"}, method = RequestMethod.GET)
    public String editUserRedirect(@PathVariable("id") long id, Model model){
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "admin/create-user";
    }

    @RequestMapping(value = {"/topics/{id}"}, method = RequestMethod.GET)
    public String editTopicRedirect(@PathVariable("id") long id, Model model){
        Topic topic = topicService.findById(id);
        model.addAttribute("topic", topic);
        return "admin/create-topic";
    }

    @RequestMapping(value = {"/{id}"}, method = RequestMethod.POST)
    public String editUser(@PathVariable("id") long id, @ModelAttribute("user") User editUser){
        editUser.setId(id);
        editUser.setPassword(userService.findById(id).getPassword());
        editUser.setRole(getRoleByName(editUser.getRole().getName()));
        editUser.setIsDeleted(0);
        userService.update(editUser);
        return "redirect:/admin";
    }

    @RequestMapping(value = {"/topics/{id}"}, method = RequestMethod.POST)
    public String editTopic(@PathVariable("id") long id, @ModelAttribute("topic") Topic topic){
        topic.setId(id);
        topic.setIsDeleted(0);
        topicService.update(topic);
        return "redirect:/admin/topics";
    }

    @RequestMapping(value = {"/{ids}"}, method = RequestMethod.DELETE)
    public @ResponseBody
    String delete(@PathVariable("ids") long[] ids) {
        userService.deleteAll(ids);
        return "{\"msg\":\"success\"}";
    }

    @RequestMapping(value = {"/topics/{ids}"}, method = RequestMethod.DELETE)
    public @ResponseBody
    String deleteTopoics(@PathVariable("ids") long[] ids) {
        topicService.deleteAll(ids);
        return "{\"msg\":\"success\"}";
    }

    @RequestMapping(value = {"/topics"}, method = RequestMethod.GET)
    public ModelAndView getTopic(){
        ModelAndView modelAndView = new ModelAndView();
        List<Topic> topics = topicService.findAll();
        modelAndView.addObject("topics", topics);
        modelAndView.setViewName("admin/topic");
        return modelAndView;
    }

    @RequestMapping(value = {"/statistics"})
    public ModelAndView adminStatistics() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/admin-statistics");
        modelAndView.addObject("testStatistics", statisticService.adminTestStatistics());
        modelAndView.addObject("questionStatistics", statisticService.adminQuestionStatistics());
        modelAndView.addObject("userStatistics", statisticService.adminUserStatistics());
        return modelAndView;
    }


    private String encode(String password) {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }

    private Role getRoleByName(String roleName){
        Role role = null;
        switch (roleName){
            case "ROLE_ADMIN":
                role = new Role(1, roleName);
                break;
            case "ROLE_TUTOR":
                role = new Role(2, roleName);
                break;
            case "ROLE_USER":
                role = new Role(3, roleName);
                break;
        }
//        if(role != null){
//            return role;
//        } else throw new Exception();
        return role;
    }
}









