package com.blog.writeapi.utils.res.classes.user;

import com.blog.writeapi.dtos.user.UserDTO;
import com.blog.writeapi.utils.res.ResponseHttp;

public record ResponseUserDTO(
        ResponseHttp<UserDTO> res
) {
}
