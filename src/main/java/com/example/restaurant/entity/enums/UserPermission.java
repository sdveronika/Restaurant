package com.example.restaurant.entity.enums;

public enum UserPermission {
    INFO_READ("info:read"),
    INFO_WRITE("info:write"),
    ADMIN_READ("admin:read"),
    ADMIN_WRITE("admin:write");
    //new category add to end

    private final String permission;


    UserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return this.permission;
    }
}