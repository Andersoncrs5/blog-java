package com.blog.writeapi.services.providers;

import com.blog.writeapi.services.interfaces.ITokenService;
import org.springframework.beans.factory.annotation.Value;

public class TokenService implements ITokenService {

    @Value("${spring.security.jwt.secret}")
    private String secret;

}
