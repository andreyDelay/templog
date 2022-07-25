package com.excel.test.controller;

import com.excel.test.dto.AuthenticationRequestDto;
import com.excel.test.dto.AuthenticationResponseDto;
import com.excel.test.model.user.User;
import com.excel.test.security.jwt.JwtAuthenticationException;
import com.excel.test.security.jwt.JwtTokenProvider;
import com.excel.test.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public AuthenticationResponseDto login(@RequestBody AuthenticationRequestDto authRequest) {
        try {
            String username = authRequest.getLogin();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,
                    authRequest.getPassword()));
            User user = userService.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User with username: " + username + " not found."));

            String token = jwtTokenProvider.createToken(username, user.getRoles());
            return new AuthenticationResponseDto(token);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    @PostMapping("/refresh")
    @ResponseStatus(HttpStatus.OK)
    public AuthenticationResponseDto refreshToken(HttpServletRequest request) {
        String accessToken = jwtTokenProvider.resolveToken(request);
        if (accessToken == null || !jwtTokenProvider.validateToken(accessToken)) {
            throw new JwtAuthenticationException("Jwt token is not valid.");
        }

        String username = jwtTokenProvider.getUsername(accessToken);
        User user = userService.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with username: " + username + " not found."));
        String token = jwtTokenProvider.createToken(username, user.getRoles());
        return new AuthenticationResponseDto(token);
    }
}
