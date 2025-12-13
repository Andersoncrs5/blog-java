package com.blog.writeapi.dtos.user;

import com.blog.writeapi.utils.annotations.valid.user.uniqueEmail.UniqueEmail;
import com.blog.writeapi.utils.annotations.valid.user.uniqueUsername.UniqueUsername;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotBlank;

public record CreateUserDTO(
        @Size(min = 5, max = 100)
        @NotBlank
        String name,

        @Size(min = 6, max = 100)
        @NotBlank
        @UniqueUsername
        String username,

        @Size(min = 10, max = 150)
        @NotBlank
        @Email
        @UniqueEmail
        String email,

        @Size(min = 8, max = 60)
        @NotBlank
        String password
){}
