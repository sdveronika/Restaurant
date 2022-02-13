package com.example.restaurant.controller;

import com.example.restaurant.entity.User;
import com.example.restaurant.entity.enums.Role;
import com.example.restaurant.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import javax.validation.constraints.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
        user.setRole(Role.ADMIN);
        user.setBalance(0);
        model.addAttribute("user", user);
        return "user-info";
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        User user = new User();
        user.setRole(Role.USER);
        user.setBalance(0);
        model.addAttribute("user", user);
        return "registration-page";
    }

    @GetMapping("/logIn")
    public String logIn(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "log-in-page";
    }

    @PostMapping("/checkUser")
    public String checkUser(@ModelAttribute(name="user") User user){
        return userService.checkEmailAndPassword(user);
    }

    @PostMapping("/save")
    public String create(@Valid @ModelAttribute(name = "user") User user, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return "redirect:/api/users/addUser";
        }
        else {
            userService.create(user);
            if(user.getRole().equals(Role.USER)){
                return "redirect:/api/dishes/menu";
            }
            else {
                return "redirect:/api/users/all";
            }
        }
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