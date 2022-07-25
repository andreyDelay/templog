package com.excel.test.security.jwt;

import com.excel.test.model.user.Status;
import com.excel.test.model.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Builder
public class JwtUser implements UserDetails {

    private final String id;

    private final String username;

    @JsonIgnore
    private final String password;

    private final boolean enabled;

    private final Collection<? extends GrantedAuthority> authorities;

    public static JwtUser build(User user) {
        List<SimpleGrantedAuthority> simpleGrantedAuthorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName())).toList();
        return JwtUser.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(simpleGrantedAuthorities)
                .enabled(user.getStatus().equals(Status.ACTIVE))
                .build();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public String getId() {
        return id;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
