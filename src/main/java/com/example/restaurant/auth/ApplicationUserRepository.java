package com.example.restaurant.auth;

import com.example.restaurant.entity.enums.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ApplicationUserRepository implements IApplicationUserRepository{

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Optional<ApplicationUser> loadUserByUsername(String username) {
        return getUsers().stream()
                .filter(applicationUser -> username.equals(applicationUser.getUsername()))
                .findFirst();
    }

    private List<ApplicationUser> getUsers() {
        List<ApplicationUser> users = List.of(
                new ApplicationUser(
                        Role.CLIENT.getGrantedAuthority(),
                        "alex",
                        passwordEncoder.encode("alex"),
                        true, true,
                        true, true
                ),
                new ApplicationUser(
                        Role.USER.getGrantedAuthority(),
                        "oleg",
                        passwordEncoder.encode("oleg"),
                        true, true,
                        true, true
                ),
                new ApplicationUser(
                        Role.ADMIN.getGrantedAuthority(),
                        "admin",
                        passwordEncoder.encode("admin"),
                        true, true,
                        true, true
                )
        );
        return users;
    }
}