package com.example.restaurant.controller;

import com.example.restaurant.entity.User;
import com.example.restaurant.entity.enums.Role;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/")
public class TemplateController {

    @GetMapping("index")
    public String getIndexPage(HttpSession session){
        User user=new User();
        user.setRole(Role.GUEST);
        session.setAttribute("user",user);
        return "index";
    }

    @GetMapping("/no-access")
    public String noAccess(){
        return "no-access";
    }

}

//TODO
//Валидация
//Добавить исключения

