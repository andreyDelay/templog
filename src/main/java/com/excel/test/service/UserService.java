package com.excel.test.service;

import com.excel.test.model.user.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findByUsername(String username);
}
