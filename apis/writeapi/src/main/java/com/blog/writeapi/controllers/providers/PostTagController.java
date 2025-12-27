package com.blog.writeapi.controllers.providers;

import com.blog.writeapi.controllers.docs.PostTagControllerDocs;
import com.blog.writeapi.dtos.postCategories.PostCategoriesDTO;
import com.blog.writeapi.dtos.postTag.CreatePostTagDTO;
import com.blog.writeapi.dtos.postTag.PostTagDTO;
import com.blog.writeapi.models.PostModel;
import com.blog.writeapi.models.PostTagModel;
import com.blog.writeapi.models.TagModel;
import com.blog.writeapi.models.UserModel;
import com.blog.writeapi.services.interfaces.*;
import com.blog.writeapi.utils.mappers.PostTagMapper;
import com.blog.writeapi.utils.res.ResponseHttp;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;
import java.util.UUID;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/post-tag")
public class PostTagController implements PostTagControllerDocs {

    private final IPostTagService service;
    private final PostTagMapper mapper;
    private final IPostService postService;
    private final ITagService tagService;
    private final ITokenService tokenService;
    private final IUserService userService;

    @Override
    public ResponseEntity<?> create(
            @Valid @RequestBody CreatePostTagDTO dto,
            HttpServletRequest request
            ) {
        Long userId = this.tokenService.extractUserIdFromRequest(request);

        PostModel post = this.postService.getByIdSimple(dto.postId());
        TagModel tag = this.tagService.getByIdSimple(dto.tagId());

        if (!post.getAuthor().getId().equals(userId)) {
            ResponseHttp<Object> res = new ResponseHttp<>(
                    null,
                    "You are not the author of this post",
                    UUID.randomUUID().toString(),
                    1,
                    false,
                    OffsetDateTime.now()
            );

            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(res);
        }

        PostTagModel model = this.service.create(dto, post, tag);

        PostTagDTO mapperDTO = this.mapper.toDTO(model);

        ResponseHttp<PostTagDTO> res = new ResponseHttp<>(
                mapperDTO,
                "Tag added with successfully",
                UUID.randomUUID().toString(),
                1,
                true,
                OffsetDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }

}
