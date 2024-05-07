package de.sebastian.wien.rest.controller;

import de.sebastian.wien.model.User;
import de.sebastian.wien.service.UserServiceClient;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
@Slf4j
@AllArgsConstructor
public class UserController {

    private UserServiceClient userServiceClient;

    @GetMapping(value = "/users/{id}", produces = "application/json")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        return userServiceClient.fetchUserByIdWithPosts(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

}