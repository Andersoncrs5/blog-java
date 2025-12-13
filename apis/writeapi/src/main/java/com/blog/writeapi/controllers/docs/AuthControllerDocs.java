package com.blog.writeapi.controllers.docs;

import com.blog.writeapi.dtos.user.CreateUserDTO;
import com.blog.writeapi.utils.res.ResponseHttp;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface AuthControllerDocs {

    @PostMapping("/register")
    @Operation(summary = "Registers a new user in the system and returns access tokens.",
            tags = {"Auth"})
    @ApiResponse(responseCode = "201",
            description = "User register with success",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ResponseHttp.class)))
    @ApiResponse(responseCode = "500", description = "Error internal in server")
    ResponseEntity<?> Create(@Valid @RequestBody CreateUserDTO dto, HttpServletRequest request);
}
