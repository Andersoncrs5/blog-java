package com.blog.writeapi.utils.res.swagger.comment;

import com.blog.writeapi.dtos.comment.CommentDTO;
import com.blog.writeapi.utils.res.ResponseHttp;

public record ResponseCommentDTO(
        ResponseHttp<CommentDTO> dto
) {
}
