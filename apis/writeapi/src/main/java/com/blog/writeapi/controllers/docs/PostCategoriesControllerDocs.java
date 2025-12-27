package com.blog.writeapi.controllers.docs;

import com.blog.writeapi.dtos.postCategories.CreatePostCategoriesDTO;
import com.blog.writeapi.utils.annotations.valid.global.isId.IsId;
import com.blog.writeapi.utils.res.ResponseHttp;
import com.blog.writeapi.utils.res.swagger.postCategories.ResponsePostCategoriesDTO;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface PostCategoriesControllerDocs {
    String tag = "PostCategories";

    @PostMapping
    @Operation(summary = "To add category to post", tags = {tag})
    @CircuitBreaker(name = "tagCreateCB")
    @ApiResponse(responseCode = "201",
            description = "To add category to post",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ResponsePostCategoriesDTO.class)))
    @ApiResponse(responseCode = "401",
            description = "Unauthorized: Invalid or expired token",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ResponseHttp.class)))
    @ApiResponse(responseCode = "409",
            description = "Conflict: Category already exists",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ResponseHttp.class)))
    ResponseEntity<?> create(@Valid @RequestBody CreatePostCategoriesDTO dto, HttpServletRequest request);


    @DeleteMapping("/{id}")
    @Operation(summary = "To remove category to post", tags = {tag})
    @CircuitBreaker(name = "tagDeleteCB")
    @ApiResponse(responseCode = "200",
            description = "To remove category to post",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ResponseHttp.class)))
    @ApiResponse(responseCode = "403",
            description = "Forbidden: You are not the author of this post",
            content = @Content(mediaType = "application/json"))
    ResponseEntity<?> del(@PathVariable @IsId Long id, HttpServletRequest request);

}
