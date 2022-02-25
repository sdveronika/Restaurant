package com.example.restaurant.entity;

import com.example.restaurant.entity.enums.CategoryOfDish;
import com.example.restaurant.entity.enums.DishStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@Entity
@Table(name="dishes")
@AllArgsConstructor
@NoArgsConstructor
public class Dish implements Serializable {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name="name")
    @Size(min=2, max=75,message = "Invalid name")
    @NotBlank(message="Required field")
    private String name;

    @Column(name="description")
    @Size(max=250, message="Invalid description: description size must be no more than 250 characters")
    private String description;

    @Column(name="category")
    @Enumerated(EnumType.STRING)
    @NotBlank(message="Required field")
    private CategoryOfDish category;

    @Column(name="price")
    @PositiveOrZero
    private double price;

    @Column(name="status")
    @Enumerated(EnumType.STRING)
    private DishStatus status;

//    @ManyToMany
//    @JoinTable(name="order_has_dishes",
//            joinColumns = @JoinColumn(name="dishes_id"),
//            inverseJoinColumns = @JoinColumn(name="order_id"))
//    private List<Order> orders;


    public Dish(String name, String description, CategoryOfDish category, double price) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.price = price;
    }

}
