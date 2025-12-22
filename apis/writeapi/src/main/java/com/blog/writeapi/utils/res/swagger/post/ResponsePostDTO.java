package com.blog.writeapi.utils.res.swagger.post;

import com.blog.writeapi.dtos.post.PostDTO;
import com.blog.writeapi.utils.res.ResponseHttp;

public record ResponsePostDTO(
        ResponseHttp<PostDTO> dto
) {
}
