package com.example.restaurant.entity.enums;

import com.google.common.collect.Sets;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.example.restaurant.entity.enums.UserPermission.*;

public enum Role {
    SUPERADMIN(Sets.newHashSet(INFO_READ, INFO_WRITE, ADMIN_READ, ADMIN_WRITE)),
    ADMIN(Sets.newHashSet(INFO_READ, INFO_WRITE, ADMIN_READ, ADMIN_WRITE)),
    USER(Sets.newHashSet(INFO_READ, INFO_WRITE, ADMIN_READ)),
    CLIENT(Sets.newHashSet(INFO_READ));
    //new category add to end

    private final Set<UserPermission> permissions;

    Role(Set<UserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<UserPermission> getPermissions() {
        return permissions;
    }

    public Set<GrantedAuthority> getGrantedAuthority() {
        Set<GrantedAuthority> permissions = this
                .getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;
    }
}