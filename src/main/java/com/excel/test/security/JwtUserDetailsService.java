package com.excel.test.security;

import com.excel.test.model.user.User;
import com.excel.test.security.jwt.JwtUser;
import com.excel.test.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("In loadUserByUsername() - trying to load user wth username {}.", username);
        User user = userService.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with username: " + username + " not found."));
        log.info("In loadUserByUsername() - user with username {} successfully loaded", username);
        return JwtUser.build(user);
    }
}
