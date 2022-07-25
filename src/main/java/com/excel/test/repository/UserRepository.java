package com.excel.test.repository;

import com.excel.test.model.user.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    /*@Query("{username:'?0'}")*/
    Optional<User> findUserByUsername(String username);
}
