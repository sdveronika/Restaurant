package com.example.restaurant.controller;


import com.example.restaurant.entity.Dishes;
import com.example.restaurant.entity.User;
import com.example.restaurant.service.impl.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/dishes")
public class DishController {

    @Autowired
    private DishService dishService;

    @GetMapping("/all")
    public String findAll(Model model){
        List<Dishes> dishes=dishService.findAll();
        model.addAttribute("dishes", dishes);
        return "all-dishes";
    }

    @PostMapping("/saveDish")
    public String create(@ModelAttribute(name = "dish") Dishes dish) {
        dishService.create(dish);
        return "redirect:/api/dishes/all";
    }

    @GetMapping("/addDish")
    public String addDish(Model model) {
        Dishes dish = new Dishes();
        model.addAttribute("dish", dish);
        return "dish-info";
    }

    @GetMapping("/updateDish")
    public String updateDish(@RequestParam(name="dishId") Long dishId, Model model){
        Dishes dish=dishService.findById(dishId);
        model.addAttribute("dish", dish);
        return "dish-info";
    }

    @GetMapping("/deleteDish")
    public String deleteDish(@RequestParam(name="dishId") Long dishId){
        dishService.delete(dishId);
        return "redirect:/api/dishes/all";
    }

    @GetMapping("/menu")
    public String menu(Model model){
        model.addAttribute("dish",new Dishes());
        return "second-page";
    }

    @GetMapping("/findByCategory")
    public String findByCategory(@ModelAttribute(name = "dish") Dishes dish,Model model){
        List<Dishes> dishes=dishService.findByCategory(dish.getCategory());
        model.addAttribute("dishes", dishes);
        return "menu-by-category";
    }
}
