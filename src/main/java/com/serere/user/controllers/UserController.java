package com.serere.user.controllers;

import com.serere.user.User;
import com.serere.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
//@Controller

//@RequestMapping
// (value = "")
public class UserController {

    @RequestMapping(value = "/")
    public ModelAndView home(){
        ModelAndView m = new ModelAndView();
        m.setViewName("index");
        return m;
    }

    @Autowired
    private UserService userService;

    @PostMapping(value = "/create")
    public User createUser(User user){
        return userService.createUser(user);
    }

    @GetMapping(value = "/view/{userId}")
    public User readFromUser(@PathVariable("userId") Integer userId){
        return userService.getUser(userId);
    }

    @RequestMapping(value = "/viewAll")
    public Iterable<User> getAllUsers(){
        return userService.getAlluser();
    }

    @DeleteMapping(value = "/delete/{userId}")
    public void deleteUser(@PathVariable("userId") Integer userId){
        userService.deleteUser(userId);
    }

    @PutMapping(value = "/edit/{userId}/{newPassword}")
    public User updateDetail(@PathVariable("userId") Integer userId, @PathVariable("newPassword") String newPassword){
        return userService.updateUser(userId, newPassword);
    }

    //This method gets specific value(s) from the db
    @GetMapping(value = "/getSpecific/{username}")
    public List<User> sortedUser(@PathVariable("username") String username){
        return userService.sortedUser(username);
    }

    //For Paging
    @GetMapping(value = "/page/{paging}")
    public List<User> pagedList(@PathVariable("paging") String paging){
        return userService.pagedList(new PageRequest(0, 3, Sort.Direction.ASC, paging));
    }

    //For Paging and Sorting***
    @GetMapping(value = "/sort/{username}/{sorting}")
    public List<User> sortedPagedList(@PathVariable("username") String username, @PathVariable("sorting") String sorting){
        return userService.sortedPagedList(username, new PageRequest(0, 5, Sort.Direction.ASC, sorting));
    }

}
