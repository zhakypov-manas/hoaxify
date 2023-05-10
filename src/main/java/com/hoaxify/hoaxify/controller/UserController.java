package com.hoaxify.hoaxify.controller;

import com.hoaxify.hoaxify.model.User;
import com.hoaxify.hoaxify.service.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/users")
    void createUser(@RequestBody User user) {
        userService.save(user);
    }
}
