package com.blog.writeapi.services.interfaces;

import com.blog.writeapi.dtos.postTag.CreatePostTagDTO;
import com.blog.writeapi.dtos.postTag.UpdatePostTagDTO;
import com.blog.writeapi.models.PostModel;
import com.blog.writeapi.models.PostTagModel;
import com.blog.writeapi.models.TagModel;
import com.blog.writeapi.utils.annotations.valid.global.isId.IsId;
import jakarta.validation.constraints.NotNull;

import java.util.Optional;

public interface IPostTagService {
    PostTagModel getByIdSimple(@IsId Long id);
    Optional<PostTagModel> getById(@IsId Long id);
    void delete(@NotNull PostTagModel model);
    PostTagModel create(
            @NotNull CreatePostTagDTO dto,
            @NotNull PostModel post,
            @NotNull TagModel tag
    );
    PostTagModel update(
            @NotNull UpdatePostTagDTO dto,
            @NotNull PostTagModel model
    );
    Boolean existsByPostAndTag(
            @IsId Long postId,
            @IsId Long tagId
    );
}
