package com.blog.writeapi.dtos.user;

import java.util.Optional;

public record UpdateUserDTO(
    Optional<String> name,
    Optional<String> username,
    Optional<String> password
){}
