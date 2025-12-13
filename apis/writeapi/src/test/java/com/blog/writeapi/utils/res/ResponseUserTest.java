package com.blog.writeapi.utils.res;

import com.blog.writeapi.dtos.user.CreateUserDTO;

public record ResponseUserTest(
        ResponseTokens tokens,
        CreateUserDTO dto
) {
}
