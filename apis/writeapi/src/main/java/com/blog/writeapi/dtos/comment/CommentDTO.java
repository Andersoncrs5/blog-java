package com.blog.writeapi.dtos.comment;

import com.blog.writeapi.dtos.post.PostDTO;
import com.blog.writeapi.dtos.user.UserDTO;
import com.blog.writeapi.models.enums.comment.CommentStatusEnum;

import java.time.OffsetDateTime;

public record CommentDTO(
        Long id,
        String content,
        CommentStatusEnum status,
        PostDTO post,
        UserDTO user,
        boolean edited,
        boolean pinned,
        String ipAddress,
        Long parentId,
        Long version,
        OffsetDateTime createdAt,
        OffsetDateTime updatedAt
) {
}
