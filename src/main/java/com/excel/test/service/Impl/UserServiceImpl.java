package com.excel.test.service.Impl;

import com.excel.test.model.user.User;
import com.excel.test.repository.UserRepository;
import com.excel.test.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public Optional<User> findByUsername(String username) {
        log.info("In UserService findByUsername()");
        return userRepository.findUserByUsername(username);
    }
}
