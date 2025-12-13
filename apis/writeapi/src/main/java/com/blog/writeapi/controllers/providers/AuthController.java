package com.blog.writeapi.controllers.providers;

import com.blog.writeapi.controllers.docs.AuthControllerDocs;
import com.blog.writeapi.dtos.user.CreateUserDTO;
import com.blog.writeapi.models.RoleModel;
import com.blog.writeapi.models.UserModel;
import com.blog.writeapi.services.interfaces.IRoleService;
import com.blog.writeapi.services.interfaces.ITokenService;
import com.blog.writeapi.services.interfaces.IUserRoleService;
import com.blog.writeapi.services.interfaces.IUserService;
import com.blog.writeapi.utils.res.ResponseHttp;
import com.blog.writeapi.utils.res.ResponseTokens;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
public class AuthController implements AuthControllerDocs {

    private final IUserService userService;
    private final IRoleService roleService;
    private final IUserRoleService userRoleService;
    private final ITokenService tokenService;

    @Override
    public ResponseEntity<?> Create(@Valid @RequestBody CreateUserDTO dto, HttpServletRequest request) {

        UserModel created = this.userService.Create(dto);

        Optional<RoleModel> roleOpt = this.roleService.findByName("USER_ROLE");

        if (roleOpt.isEmpty()) throw new RuntimeException("Role USER_ROLE not exists");

        this.userRoleService.create(created, roleOpt.get());

        String token = this.tokenService.generateToken(created, List.of(roleOpt.get()));
        String refreshToken = this.tokenService.generateRefreshToken(created);

        ResponseTokens tokens = new ResponseTokens(
                token,
                refreshToken
        );

        ResponseHttp<ResponseTokens> response = new ResponseHttp<>(
                tokens,
        "Welcome",
                UUID.randomUUID().toString(),
                1,
                true,
                OffsetDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
