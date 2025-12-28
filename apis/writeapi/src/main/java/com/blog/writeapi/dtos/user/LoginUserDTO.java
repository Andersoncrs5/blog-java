package com.blog.writeapi.dtos.user;

import com.blog.writeapi.utils.annotations.valid.global.emailConstraint.EmailConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginUserDTO(
        @EmailConstraint
        String email,

        @Size(min = 8, max = 60)
        @NotBlank
        String password
) {
}
