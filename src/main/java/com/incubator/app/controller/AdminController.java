package com.incubator.app.controller;


import com.incubator.app.entity.Role;
import com.incubator.app.entity.Topic;
import com.incubator.app.entity.User;
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

import java.util.ArrayList;
import java.util.List;

//@RestController
@Controller
public class AdminController {

    @Autowired
    private UserService userService;
    @Autowired
    private TopicService topicService;


//    @RequestMapping(value = {"/admin/"}, method = RequestMethod.GET)
//    public ResponseEntity<List<User>> listAllUsers() {
//        List<User> users = userService.findAll();
//        if(users.isEmpty()){
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
//        }
//        return new ResponseEntity<>(users, HttpStatus.OK);
//    }

    @RequestMapping(value = {"/admin"}, method = RequestMethod.GET)
    public ModelAndView listAllUsers(){
        ModelAndView modelAndView = new ModelAndView();
        List<User> users = userService.findAll();
        modelAndView.setViewName("admin");
        modelAndView.addObject("users", users);
        return modelAndView;
    }

    @RequestMapping(value = {"/admin/create"}, method = RequestMethod.GET)
    public String redirectToCreatePage(Model model){
        model.addAttribute("user", new User());
        return "create-user";
    }

    @RequestMapping(value = {"/admin/create"}, method = RequestMethod.POST)
    public String registration(@ModelAttribute("user") User user){
        user.setPassword(encode(user.getPassword()));
        user.setRole(getRoleByName(user.getRole().getName()));
        user.setIsDeleted(0);
        userService.insert(user);
        return "redirect:/admin";
    }

    @RequestMapping(value = {"/admin/{id}"}, method = RequestMethod.GET)
    public String editUserRedirect(@PathVariable("id") long id, Model model){
        User user = userService.findById(id);
        user.setPassword("");
        model.addAttribute("user", user);
        return "create-user";
    }

    @RequestMapping(value = {"/admin/{id}"}, method = RequestMethod.POST)
    public String editUser(@PathVariable("id") long id, @ModelAttribute("editUser") User editUser){
        editUser.setId(id);
        editUser.setPassword(encode(editUser.getPassword()));
        editUser.setRole(getRoleByName(editUser.getRole().getName()));
        editUser.setIsDeleted(0);
        System.out.println(editUser);
        userService.update(editUser);
        return "redirect:/admin";
    }


    @RequestMapping(value = {"/admin/{ids}"}, method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable("ids") long[] ids){
        userService.deleteSeveralUsers(ids);
        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = {"/admin/topic"}, method = RequestMethod.GET)
    public ModelAndView getTopic(){
        ModelAndView modelAndView = new ModelAndView();
        List<Topic> topics = topicService.findAll();
        modelAndView.addObject("topics", topics);
        modelAndView.setViewName("topic");
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









