package com.blog.writeapi.dtos.comment;

import com.blog.writeapi.models.enums.comment.CommentStatusEnum;
import com.blog.writeapi.utils.annotations.valid.comment.existsCommentById.ExistsCommentById;
import com.blog.writeapi.utils.annotations.valid.global.isId.IsId;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateCommentDTO(
        @IsId
        @ExistsCommentById
        Long id,


        @NotBlank
        @Size(min = 1, max = 600)
        String content,

        CommentStatusEnum status
) {
}
