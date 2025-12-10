package com.blog.writeapi.services.providers;

import com.blog.writeapi.models.RoleModel;
import com.blog.writeapi.models.UserModel;
import com.blog.writeapi.services.interfaces.ITokenService;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.KeyLengthException;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class TokenService implements ITokenService {

    @Value("${spring.security.jwt.secret}")
    private String secret;
    @Value("${spring.security.jwt.exp.token}")
    private int expToken;
    @Value("${spring.security.jwt.exp.refresh}")
    private int expRefreshToken;

    @Override
    public String generateToken(UserModel user, List<RoleModel> roles) {
        if (secret.isBlank()) {
            log.error("Error! The secret is null! in class TokenService");
            throw new RuntimeException();
        }

        List<String> roleNames = roles.stream()
                .map(RoleModel::getName)
                .collect(Collectors.toList());

        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(user.getEmail())
                .claim("userId", user.getId())
                .claim("name", user.getName())
                .claim("username", user.getUsername())
                .claim("email", user.getEmail())
                .issueTime(Date.from(Instant.now()))
                .expirationTime(Date.from(this.genExpirationDate()))
                .claim("roles", roleNames)
                .build();

        JWSHeader header = new JWSHeader(JWSAlgorithm.HS256);
        SignedJWT signedJWT = new SignedJWT(header, jwtClaimsSet);

        try {
            signedJWT.sign(new MACSigner(secret.getBytes()));
            return signedJWT.serialize();
        } catch (KeyLengthException e) {
            log.error("Error the assign token! Error: {}", e.getMessage());
            throw new RuntimeException(e);
        } catch (JOSEException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao assinar o refresh token.");
        }
    }

    private Instant genExpirationDate() {
        return LocalDateTime.now().plusHours(expToken).toInstant(ZoneOffset.of("-03:00"));
    }

    private Instant genExpirationDateRefreshToken() {
        return LocalDateTime.now().plusHours(expRefreshToken).toInstant(ZoneOffset.of("-03:00"));
    }

}
