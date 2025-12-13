package com.blog.writeapi.dtos.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginUserDTO(
        @Size(min = 10, max = 150)
        @NotBlank
        @Email
        String email,

        @Size(min = 8, max = 60)
        @NotBlank
        String password
) {
}
