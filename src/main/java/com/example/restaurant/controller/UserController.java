package com.example.restaurant.controller;

import com.example.restaurant.entity.Dish;
import com.example.restaurant.entity.Order;
import com.example.restaurant.entity.User;
import com.example.restaurant.entity.enums.OrderStatus;
import com.example.restaurant.entity.enums.Role;
import com.example.restaurant.entity.enums.UserStatus;
import com.example.restaurant.service.IOrderService;
import com.example.restaurant.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpSession;
import javax.validation.constraints.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IOrderService orderService;

    @GetMapping("/index2")
    public String getIndexPage2(HttpSession session){
        User user=(User)session.getAttribute("user");
        List<Dish> basket=new ArrayList<>();
        session.setAttribute("basket",basket);
        if(user.getRole().equals(Role.USER)){
            session.setAttribute("name", user.getName()+" "+user.getSurname());
        return "index2";}
        else return "no-access";
    }

    @GetMapping("/index3")
    public String getIndexPage3(HttpSession session){
        User user=(User)session.getAttribute("user");
        if(user.getRole().equals(Role.ADMIN)){
            List<Order> orderList = orderService.findAll();
            int count=0;
            for (int i = 0; i < orderList.size(); i++) {
                if(orderList.get(i).getStatus().equals(OrderStatus.NOTCONFIRM)){
                    count++;
                }
            }
            session.setAttribute("notConfirmOrders", count);
        return "index3";}
        else return "no-access";
    }

    @GetMapping("/all-users")
    public String findAllUsers(Model model,HttpSession session){
        List<User> userList = userService.findAll();
        model.addAttribute("users",userList);
        User user=(User)session.getAttribute("user");
        if(user.getRole().equals(Role.ADMIN)){
            return "all-users";}
        else return "no-access";

    }

    @GetMapping("/addUser")
    public String addUser(Model model,HttpSession session) {
        User user1=(User)session.getAttribute("user");
        if(user1.getRole().equals(Role.ADMIN)){
        User user = new User();
        user.setRole(Role.ADMIN);
        user.setBalance(0);
        user.setStatus(UserStatus.ACTIVE);
        model.addAttribute("user", user);
        return "user-info";}
        else return "no-access";
    }


    //регистрация и вход
    @GetMapping("/registration")
    public String registration(Model model) {
        User user = new User();
        user.setRole(Role.USER);
        user.setBalance(0);
        user.setStatus(UserStatus.ACTIVE);
        model.addAttribute("user", user);
        return "registration";
    }

    @GetMapping("/login")
    public String logIn(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "login";
    }

    @PostMapping("/checkUser")
    public String checkUser(@ModelAttribute(name="user") User user, HttpSession session){
        String page="no-access";
        List<User> confirmUser=userService.findByEmailAndPassword(user);
        if(confirmUser.isEmpty()){
            page="redirect:/api/users/login";
        }
        else {
            User user1 = confirmUser.get(0);
            if (user1.getStatus().equals(UserStatus.ACTIVE)) {
                if (user1.getPassword().equals(user.getPassword())) {
                    session.setAttribute("name", user1.getName() + " " + user1.getSurname());
                    session.setAttribute("user", user1);
                    if (user1.getRole().equals(Role.USER)) {
                        page = "redirect:/api/users/index2";
                    } else {
                        page = "redirect:/api/users/index3";
                    }
                } else page = "redirect:/api/users/login";
            }
        }
        return page;
    }

    @PostMapping("/saveAdmin")
    public String createAdmin(@Valid @ModelAttribute(name="user") User user,HttpSession session, BindingResult bindingResult){
        User user1=(User)session.getAttribute("user");
        if(bindingResult.hasErrors()){
            return "redirect:/api/users/all-users";
        }
        if(user1.getRole().equals(Role.ADMIN)){
        userService.create(user);
        return "redirect:/api/users/all-users";
        }
        else {
            return "no-access";
        }
    }

    @PostMapping("/saveRegistration")
    public String createRegistration(@Valid @ModelAttribute(name="user") User user,
            BindingResult bindingResult,
            HttpSession session) {
        if(bindingResult.hasErrors()){
            return "redirect:/api/users/registration";
        }
        else {
            if (user.getBalance() < 0) {
                return "redirect:/api/users/balance";
            }
            userService.create(user);
            ArrayList<User> users = (ArrayList<User>) userService.findByEmailAndPassword(user);
            User user1 = users.get(0);
            session.setAttribute("user", user1);
            return "redirect:/api/users/index2";
        }
    }

    @GetMapping("/updateUser")
    public String update(
            @RequestParam(name = "usId") Long id, Model model,HttpSession session){
        User user1=(User) session.getAttribute("user");
        if(user1.getRole().equals(Role.ADMIN)) {
            User user = userService.findById(id);
            model.addAttribute("user", user);
            return "user-info";
        }
        else return "no-access";
    }

    @GetMapping("/deleteUser")
    public String delete(@RequestParam(name = "usId") Long usId, HttpSession session){
        User user1=(User) session.getAttribute("user");
        if(user1.getRole().equals(Role.ADMIN)) {
            userService.delete(usId);
            return "redirect:/api/users/all-users";
        }
        else return "no-access";
    }

    @GetMapping("/changeUserStatus")
    public String changeUserStatus(@RequestParam(name = "usId") Long usId, HttpSession session){
        User user1=(User) session.getAttribute("user");
        if(user1.getRole().equals(Role.ADMIN)) {
            User user2=userService.findById(usId);
            if(user2.getStatus().equals(UserStatus.ACTIVE)){
                user2.setStatus(UserStatus.BAN);
            }
            else{
                user2.setStatus(UserStatus.ACTIVE);
            }
            userService.create(user2);
            return "redirect:/api/users/all-users";
        }
        else return "no-access";
    }

    @GetMapping("/balance")
    public String balance(HttpSession session,Model model){
        User user1=(User)session.getAttribute("user");
        if(user1.getRole().equals(Role.USER)){
        model.addAttribute("user",user1);
        return "balance";
        }
        else return "no-access";


    }

     @GetMapping("/logout")
    public String logOut(HttpSession session){
        User user1=(User)session.getAttribute("user");
        if(user1.getRole().equals(Role.USER) || user1.getRole().equals(Role.ADMIN)) {
            session.invalidate();
            //тут очистило корзину, но при повторном входе все равно отображает ее
            return "redirect:/index";
        }
        else return "no-access";
     }

}