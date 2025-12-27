package com.blog.writeapi.dtos.postTag;

import com.blog.writeapi.utils.annotations.valid.global.isId.IsId;
import com.blog.writeapi.utils.annotations.valid.post.existsPostById.ExistsPostById;
import com.blog.writeapi.utils.annotations.valid.postTag.uniqueTagInPost.UniqueTagInPost;
import com.blog.writeapi.utils.annotations.valid.tag.existsTagById.ExistsTagById;

@UniqueTagInPost
public record CreatePostTagDTO(

        @IsId
        @ExistsPostById
        Long postId,

        @IsId
        @ExistsTagById
        Long tagId,

        Boolean active,
        Boolean visible
) {
}
