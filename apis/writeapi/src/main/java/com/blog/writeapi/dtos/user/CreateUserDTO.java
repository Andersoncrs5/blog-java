package com.blog.writeapi.dtos.user;

import com.blog.writeapi.utils.annotations.valid.user.uniqueEmail.UniqueEmail;
import com.blog.writeapi.utils.annotations.valid.user.uniqueUsername.UniqueUsername;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotBlank;

public record CreateUserDTO(
        @Size(max = 100)
        @NotBlank
        String name,

        @Size(max = 100)
        @NotBlank
        @UniqueUsername
        String username,

        @Size(max = 150)
        @NotBlank
        @UniqueEmail
        String email,

        @Size(max = 60)
        @NotBlank
        String password
){}
