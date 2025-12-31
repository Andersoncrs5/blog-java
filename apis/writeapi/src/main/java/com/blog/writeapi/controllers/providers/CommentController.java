package com.blog.writeapi.controllers.providers;

import com.blog.writeapi.controllers.docs.CommentControllerDocs;
import com.blog.writeapi.dtos.comment.CommentDTO;
import com.blog.writeapi.dtos.comment.CreateCommentDTO;
import com.blog.writeapi.models.CommentModel;
import com.blog.writeapi.models.PostModel;
import com.blog.writeapi.models.UserModel;
import com.blog.writeapi.services.interfaces.ICommentService;
import com.blog.writeapi.services.interfaces.IPostService;
import com.blog.writeapi.services.interfaces.ITokenService;
import com.blog.writeapi.services.interfaces.IUserService;
import com.blog.writeapi.utils.annotations.valid.comment.isAuthorComment.IsAuthorComment;
import com.blog.writeapi.utils.annotations.valid.global.isId.IsId;
import com.blog.writeapi.utils.mappers.CommentMapper;
import com.blog.writeapi.utils.res.ResponseHttp;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;
import java.util.UUID;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/comment")
public class CommentController implements CommentControllerDocs {

    private final ICommentService service;
    private final ITokenService tokenService;
    private final IPostService postService;
    private final IUserService userService;
    private final CommentMapper mapper;

    @Override
    @IsAuthorComment
    public ResponseEntity<?> del(
            @PathVariable @IsId Long id,
            HttpServletRequest request
    ) {
        CommentModel comment = this.service.getByIdSimple(id);

        this.service.delete(comment);

        ResponseHttp<Object> res = new ResponseHttp<>(
            null,
                "Comment deleted with successfully",
                UUID.randomUUID().toString(),
                1,
                true,
                OffsetDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @Override
    public ResponseEntity<?> create(
            @Valid @RequestBody CreateCommentDTO dto,
            HttpServletRequest request
    ) {
        Long userID = this.tokenService.extractUserIdFromRequest(request);

        CommentModel parent = null;
        PostModel post = this.postService.getByIdSimple(dto.postID());
        UserModel user = this.userService.GetByIdSimple(userID);

        if (dto.parentId() != null) {
            parent = this.service.getByIdSimple(dto.parentId());
        }

        CommentModel commentCreated = this.service.create(dto, post, user, parent);

        ResponseHttp<CommentDTO> res = new ResponseHttp<>(
                this.mapper.toDTO(commentCreated),
                "Comment created with successfully",
                UUID.randomUUID().toString(),
                1,
                true,
                OffsetDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }

}
