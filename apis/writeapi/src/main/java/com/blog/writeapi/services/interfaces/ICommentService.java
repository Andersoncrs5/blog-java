package com.blog.writeapi.services.interfaces;

import com.blog.writeapi.dtos.comment.CreateCommentDTO;
import com.blog.writeapi.dtos.comment.UpdateCommentDTO;
import com.blog.writeapi.models.CommentModel;
import com.blog.writeapi.models.PostModel;
import com.blog.writeapi.models.UserModel;
import com.blog.writeapi.utils.annotations.valid.global.isId.IsId;
import jakarta.validation.constraints.NotNull;

import java.util.Optional;

public interface ICommentService {
    Optional<CommentModel> getById(@IsId Long id);
    void delete(@NotNull CommentModel comment);
    CommentModel getByIdSimple(@IsId Long id);
    @Deprecated
    CommentModel create(CreateCommentDTO dto, PostModel post, UserModel user);
    CommentModel create(CreateCommentDTO dto, PostModel post, UserModel user, CommentModel comment);
    CommentModel update(UpdateCommentDTO dto, CommentModel comment);
}
