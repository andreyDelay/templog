package com.excel.test.config;

import com.excel.test.model.user.Role;
import com.excel.test.model.user.Status;
import com.excel.test.model.user.User;
import com.mongodb.client.MongoCollection;
import io.mongock.api.annotations.*;
import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@ChangeUnit(id="addAdminUser", order = "1", author = "andrey", systemVersion = "1")
public class DatabaseChangeLog {

    private final MongoTemplate template;
    private final PasswordEncoder passwordEncoder;

    @BeforeExecution
    public void before() {
        if (template.collectionExists("users")) {
            return;
        }
        template.createCollection("users");
    }

    @Execution
    public void migrationMethod() {
        template.insert(prepareAdminUser());
    }

    @RollbackBeforeExecution
    public void rollbackBefore() {

    }

    @RollbackExecution
    public void rollback() {

    }

    private User prepareAdminUser() {
        List<Role> userRoles = new ArrayList<>();
        Role userRole = new Role();
        userRole.setName("ROLE_USER");

        Role adminRole = new Role();
        adminRole.setName("ROLE_ADMIN");

        userRoles.add(userRole);
        userRoles.add(adminRole);

        User user = new User();
        user.setPassword(passwordEncoder.encode("admin"));
        user.setUsername("admin");
        user.setStatus(Status.ACTIVE);
        user.setRoles(userRoles);

        return user;
    }
}
