package com.blog.writeapi.configs.security;

import com.blog.writeapi.repositories.UserRepository;
import com.blog.writeapi.services.interfaces.ITokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {
    private final ITokenService tokenService;
    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = this.recoverToken(request);

        if (token == null) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            String email = tokenService.validateToken(token);

            if (email != null && !email.isBlank()) {
                UserDetails user = this.userRepository.findByEmail(email);

                if (user != null) {
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            user,
                            null,
                            user.getAuthorities()
                    );

                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    log.debug("User '{}' authenticated successfully.", email);
                } else {
                    log.debug("Details of user not found for login: {}", email);
                }
            }

            filterChain.doFilter(request, response);

        } catch (ResponseStatusException e) {
            log.warn("Authentication failed ({}): {}", e.getStatusCode(), e.getReason());
            SecurityContextHolder.clearContext();

            response.setStatus(e.getStatusCode().value());
            response.getWriter().write(e.getReason());
        } catch (Exception e) {
            log.warn("Fail in authentication JWT for a request: {}", e.getMessage());
            SecurityContextHolder.clearContext();

            filterChain.doFilter(request, response);
        }
    }

    public String recoverToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || authHeader.isBlank() || !authHeader.startsWith("Bearer ")) {
            return null;
        }

        return authHeader.replace("Bearer ", "");
    }
}