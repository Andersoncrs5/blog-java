package com.blog.writeapi.controllers.docs;

import com.blog.writeapi.dtos.post.CreatePostDTO;
import com.blog.writeapi.utils.res.swagger.post.ResponsePostDTO;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface PostControllerDocs {
    String tag = "Post";

    @PostMapping
    @Operation(summary = "Create new post", tags = {tag})
    @CircuitBreaker(name = "tagCreateCB")
    @ApiResponse(responseCode = "201",
            description = "Create new post",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ResponsePostDTO.class)))
    ResponseEntity<?> create(@Valid @RequestBody CreatePostDTO dto, HttpServletRequest request);
}
