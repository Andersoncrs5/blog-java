package com.blog.writeapi.dtos.user;

public record CreateUserDTO(
    String name,
    String username,
    String email,
    String password
){}
