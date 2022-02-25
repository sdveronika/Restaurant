package com.example.restaurant.controller;


import com.example.restaurant.entity.Dish;
import com.example.restaurant.entity.Order;
import com.example.restaurant.entity.User;
import com.example.restaurant.entity.enums.DishStatus;
import com.example.restaurant.entity.enums.Role;
import com.example.restaurant.service.impl.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/api/dishes")
public class DishController {

    @Autowired
    private DishService dishService;

    @GetMapping("/all-dishes")
    public String findAllDishes(Model model, HttpSession session){
        User user1=(User)session.getAttribute("user");
        if(user1.getRole().equals(Role.ADMIN)) {
            List<Dish> dishList = dishService.findAll();
            model.addAttribute("dishes", dishList);
            return "all-dishes";
        }
        else return "no-access";
    }

    @PostMapping("/saveDish")
    public String create(@Valid @ModelAttribute(name = "dish") Dish dish, HttpSession session, BindingResult bindingResult) {
        User user1=(User)session.getAttribute("user");
        if(bindingResult.hasErrors()){
            return "dish-info";
        }
        if(user1.getRole().equals(Role.ADMIN)) {
            dishService.create(dish);
            return "redirect:/api/dishes/all-dishes";
        } else return "no-access";
    }

    @GetMapping("/addDish")
    public String addDish(Model model,HttpSession session) {
        User user1=(User)session.getAttribute("user");
        if(user1.getRole().equals(Role.ADMIN)) {
            Dish dish = new Dish();
            dish.setStatus(DishStatus.ACTIVE);
            model.addAttribute("dish", dish);
            return "dish-info";
        }
        else return "no-access";
    }

    @GetMapping("/updateDish")
    public String updateDish(@RequestParam(name="dishId") Long dishId, Model model,HttpSession session){
        User user1=(User)session.getAttribute("user");
        if(user1.getRole().equals(Role.ADMIN)) {
            Dish dish = dishService.findById(dishId);
            model.addAttribute("dish", dish);
            return "dish-info";
        }
        else return "no-access";
    }

    @GetMapping("/deleteDish")
    public String deleteDish(@RequestParam(name="dishId") Long dishId,HttpSession session){
        User user1=(User)session.getAttribute("user");
        if(user1.getRole().equals(Role.ADMIN)) {
            dishService.delete(dishId);
            return "redirect:/api/dishes/all-dishes";
        }
        else return "no-access";
    }

    @GetMapping("/menu")
    public String getMenuPage(Model model, HttpSession session) {
        List<Dish> basket=(ArrayList<Dish>)session.getAttribute("basket");
        User user1=(User)session.getAttribute("user");
        if(user1.getRole().equals(Role.USER)) {
        List<Dish> dishList = dishService.findAll();
        List<Dish> dishListActive=new ArrayList<>();
            for (int i = 0; i < dishList.size(); i++) {
                if(dishList.get(i).getStatus().equals(DishStatus.ACTIVE)){
                    dishListActive.add(dishList.get(i));
                }
            }
        model.addAttribute("dishes", dishListActive);
        if(basket.isEmpty()){
            session.setAttribute("basket_quantity",Integer.toString(0));
        }
        else {
            session.setAttribute("basket_quantity", Integer.toString(basket.size()));
        }
        return "menu";
        }
        else return "no-access";

    }

    @GetMapping("/addToBasket")
    public String addDishToBasket(@RequestParam(name="dishId") Long dishId,HttpSession session){
        List<Dish> basket=(ArrayList<Dish>)session.getAttribute("basket");
        User user1=(User)session.getAttribute("user");
        if(user1.getRole().equals(Role.USER)) {
            Dish dish = dishService.findById(dishId);
            basket.add(dish);
            session.setAttribute("basket",basket);
            return "redirect:/api/dishes/menu";
        }
        else return "no-access";
    }

    @GetMapping("/removeFromBasket")
    public String removeFromBasket(@RequestParam(name="dishId") Long dishId,HttpSession session){
        List<Dish> basket=(ArrayList<Dish>)session.getAttribute("basket");
        User user1=(User)session.getAttribute("user");
        if(user1.getRole().equals(Role.USER)) {
            Dish dish = dishService.findById(dishId);
            basket.remove(dish);
            session.setAttribute("basket",basket);
            return "redirect:/api/dishes/basket";
        }
        else return "no-access";
    }

    @GetMapping("/basket")
    public String basket(Model model,HttpSession session){
        List<Dish> basket=(ArrayList<Dish>)session.getAttribute("basket");
        User user1=(User)session.getAttribute("user");
        if(user1.getRole().equals(Role.USER)) {
            double amount = 0;
            for (int i = 0; i < basket.size(); i++) {
                amount += basket.get(i).getPrice();
            }
            session.setAttribute("amount", Double.toString(amount));
            session.setAttribute("basket", basket);
            model.addAttribute("dishes", basket);
            model.addAttribute("order", new Order());
            return "basket";
        }
        else return "no-access";
    }

    @GetMapping("/changeDishStatus")
    public String changeDishStatus(@RequestParam(name = "dishId") Long dishId, HttpSession session){
        User user1=(User) session.getAttribute("user");
        if(user1.getRole().equals(Role.ADMIN)) {
            Dish dish=dishService.findById(dishId);
            if(dish.getStatus().equals(DishStatus.ACTIVE)){
                dish.setStatus(DishStatus.NOTACTIVE);
            }
            else{
                dish.setStatus(DishStatus.ACTIVE);
            }
           dishService.create(dish);
            return "redirect:/api/dishes/all-dishes";
        }
        else return "no-access";
    }


}
