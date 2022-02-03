package com.example.restaurant.controller;

import com.example.restaurant.entity.User;
import com.example.restaurant.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private IUserService userService;

    @RequestMapping("/")
    public String hello(){
        return "hello-page";
    }

    @GetMapping("/all")
    public String findAll(Model model){
        List<User> users=userService.findAll();
        model.addAttribute("users", users);
        return "all-users";
    }

    @GetMapping("/addUser")
    public String addUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "user-info";
    }

    @PostMapping("/save")
    public String create(@ModelAttribute(name = "user") User user) {
        userService.create(user);
        return "redirect:/api/users/all";
    }

    @GetMapping("/updateUser")
    public String update(@RequestParam(name = "usId") Long usId, Model model){
        User user =userService.findById(usId);
        model.addAttribute("user",user);
        return "user-info";
    }

    @GetMapping("/deleteUser")
    public String delete(@RequestParam(name = "usId") Long usId){
        userService.delete(usId);
        return "redirect:/api/users/all";
    }
}