package com.example.restaurant.entity;

import com.example.restaurant.entity.enums.Role;
import com.example.restaurant.entity.enums.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.validator.internal.util.stereotypes.Lazy;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Data
@Entity
@Table(name="user")
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name")
    @Pattern(message = "Bad formed person name: ${validatedValue}",
            regexp = "^[A-Z][a-z]*(\\s(([a-z]{1,3})|(([a-z]+\\')?[A-Z][a-z]*)))*$")
    @Size(min =1, max = 45, message="Invalid name size")
    @NotBlank(message="Required field")
    private String name;

    @Column(name="surname")
    @Size(min =1, max = 45, message="Invalid name size")
    @NotBlank(message="Required field")
    private String surname;

    @Column(name="phone")
    @Pattern(message="+375---------",regexp = "^\\+375 \\((17|25|29|33|44)\\) [0-9]{3}[0-9]{2}[0-9]{2}$")
    @NotBlank(message="Required field")
    private String phone;

    @Column(name="email")
    @Email(message = "Email address has invalid format",
            regexp = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$")
    private String email;

    @Column(name="password")
    @NotBlank(message="Required field")
    private String password;

    @Column(name="role")
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name="balance")
    @PositiveOrZero(message = "Число должно быть положительным или равным 0")
    @Pattern(message="Нельзя использовать буквы", regexp = "\\[0-9]{7}$")
    private double balance;

    @Column(name="status")
    @Enumerated(EnumType.STRING)
    private UserStatus status;

//    @OneToMany(mappedBy = "userId")
//    private List<Order> order;


    public User(String name, String surname, String phone, String email, String password, Role role, double balance) {
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.role = role;
        this.balance = balance;
    }

}
