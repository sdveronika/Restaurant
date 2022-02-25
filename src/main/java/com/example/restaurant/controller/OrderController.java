package com.example.restaurant.controller;


import com.example.restaurant.entity.Dish;
import com.example.restaurant.entity.Order;
import com.example.restaurant.entity.User;
import com.example.restaurant.entity.enums.OrderStatus;
import com.example.restaurant.entity.enums.Role;
import com.example.restaurant.service.impl.OrderService;
import com.example.restaurant.service.impl.UserService;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @GetMapping("/all-orders")
    public String findAll(Model model,HttpSession session){
        User user1=(User)session.getAttribute("user");
        if(user1.getRole().equals(Role.ADMIN)) {
            List<Order> orderList = orderService.findAll();
//            int count=0;
//            for (int i = 0; i < orderList.size(); i++) {
//                if(orderList.get(i).getStatus().equals(OrderStatus.NOTCONFIRM)){
//                    count++;
//                }
//            }
//            session.setAttribute("notConfirmOrders", count);
            model.addAttribute("orders", orderList);
            return "all-orders";
        } else
        if (user1.getRole().equals(Role.USER)) {
            List<Order> orderList = orderService.findAll();
            List<Order> orderList2=new ArrayList<>();
            for (int i = 0; i < orderList.size(); i++) {
                if(orderList.get(i).getUserId()==user1.getId()){
                  orderList2.add(orderList.get(i));
                }
            }
            model.addAttribute("orders", orderList2);
            return "user-orders";
        } else {
            return "no-access";
        }
    }

    @GetMapping("/updateOrder")
    public String update(@RequestParam(name = "orderId") Long id, HttpSession session){
        User user1=(User)session.getAttribute("user");
        if(user1.getRole().equals(Role.ADMIN)) {
        Order order =orderService.findById(id);
        order.setStatus(OrderStatus.CONFIRM);
        orderService.create(order);
        return "redirect:/api/orders/all-orders";
        }
        else return "no-access";
    }

    @GetMapping("/createOrder")
    public String createOrder(Model model, HttpSession session){
        User user1=(User)session.getAttribute("user");
        if(user1.getRole().equals(Role.USER)) {
            ArrayList<Dish> basket = (ArrayList<Dish>) session.getAttribute("basket");
            if(basket.isEmpty()){
                return "redirect:/api/dishes/menu";
            }
            Order order = new Order();
            model.addAttribute("order", order);
            return "order";
        }
        else return "no-access";
    }

    @GetMapping("/saveOrder")
    public String addOrder(@ModelAttribute(name="order") Order order, HttpSession session){
        User user1=(User)session.getAttribute("user");
        if(user1.getRole().equals(Role.USER)) {
            ArrayList<Dish> basket = (ArrayList<Dish>) session.getAttribute("basket");
            double amount = 0;
            User user = (User) session.getAttribute("user");
            for (int i = 0; i < basket.size(); i++) {
                amount += basket.get(i).getPrice();
            }
            if (user.getBalance() >= amount) {
                user.setBalance(user.getBalance()-amount);
                userService.create(user);
                order.setStatus(OrderStatus.NOTCONFIRM);
                order.setAmount(amount);
                order.setConfirmTime(LocalDateTime.now());
                order.setUserId(user.getId());
                order.setDishes((List<Dish>)basket);
                orderService.create(order);
                basket.clear();
                session.setAttribute("basket",basket);
                basket.clear();
                session.setAttribute("basket_quantity",0);
                session.setAttribute("amount",0);
                return "redirect:/api/dishes/menu";
            }
            else return "redirect:/api/dishes/basket";
        }
        else return "no-access";
    }

    @GetMapping("/findOrder")
    public String findOrder(@RequestParam(name="orderId") Long id,Model model,HttpSession session){
        User user =(User)session.getAttribute("user");
        if(user.getRole().equals(Role.ADMIN) || user.getRole().equals(Role.USER)) {
            Order order = orderService.findById(id);
            List<Dish> dishes = (List<Dish>) order.getDishes();
            model.addAttribute("dishes", dishes);
            return "dishes-in-order";
        }
        else return "no-access";
    }

}
