package com.example.restaurant.entity;

import com.example.restaurant.entity.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name="order")
@AllArgsConstructor
@NoArgsConstructor
public class Order implements Serializable {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="amount")
    @PositiveOrZero(message="Amount can't be negative")
    @NotBlank(message="Required field")
    private double amount;

    @Column(name="status")
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Column(name="confirm_time")
    private String confirmTime;

    @Column(name="comment")
    @Size(message="Comments must be no more than 100 characters")
    private String comment;

    @Column(name="delivery_address")
    @NotBlank(message="Required field")
    private String deliveryAddress;

    @Column(name="user_id")
    private long userId;

    @ManyToMany
    @JoinTable(name="order_has_dishes",
            joinColumns = @JoinColumn(name="order_id"),
            inverseJoinColumns = @JoinColumn(name="dishes_id"))
    private List<Dishes> dishes;

    public Order(double amount, OrderStatus status, String confirmTime, String comment, String deliveryAddress, long userId) {
        this.amount = amount;
        this.status = status;
        this.confirmTime = confirmTime;
        this.comment = comment;
        this.deliveryAddress = deliveryAddress;
        this.userId = userId;
    }


}

