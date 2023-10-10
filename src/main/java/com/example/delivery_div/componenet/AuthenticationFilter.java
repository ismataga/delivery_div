
package com.example.delivery_div.componenet;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.delivery_div.dto.RegistrationDto.AuthenticationResponseDto;
import com.example.delivery_div.dto.RegistrationDto.LoginRequestDto;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.time.Duration;
import java.util.Collections;
import java.util.Date;

@Slf4j
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final String secret;

    public AuthenticationFilter(AuthenticationManager manager, String secret) {
        this.secret = secret;
        setAuthenticationManager(manager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        try {
            LoginRequestDto dto = new ObjectMapper().readValue(request.getInputStream(), LoginRequestDto.class);
            UsernamePasswordAuthenticationToken authRequest = UsernamePasswordAuthenticationToken
                    .authenticated(dto.getUsername(),
                            dto.getPassword(),
                            Collections.emptyList());
            this.setDetails(request, authRequest);

            return this.getAuthenticationManager().authenticate(authRequest);
        } catch (IOException e) {
            log.error("Error occurred while attempting authentication", e);
            throw new AuthenticationServiceException("Authentication failed", e);        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

        String token = JWT.create()
                .withSubject(authResult.getName())
                .withExpiresAt(new Date(System.currentTimeMillis() + Duration.ofMinutes(10).toMillis()))
                .withIssuedAt(new Date())
                .sign(Algorithm.HMAC512(secret.getBytes()));


        response.getWriter().write(new ObjectMapper().writeValueAsString(new AuthenticationResponseDto(token)));
    }
}
