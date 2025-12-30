package com.blog.writeapi.dtos.comment;

import com.blog.writeapi.utils.annotations.valid.global.isId.IsId;
import com.blog.writeapi.utils.annotations.valid.post.existsPostById.ExistsPostById;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateCommentDTO(

        @NotBlank
        @Size(min = 1, max = 600)
        String content,

        @IsId
        @ExistsPostById
        Long postID,

        Long parentId
) {
}
