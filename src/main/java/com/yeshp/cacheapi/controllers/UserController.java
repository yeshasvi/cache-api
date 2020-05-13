package com.yeshp.cacheapi.controllers;

import com.yeshp.cacheapi.pojos.User;
import com.yeshp.cacheapi.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@Validated
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping("/users")
    public User saveUser(@Valid @RequestBody User user) throws Exception {
        User savedUser = userService.createUser(user);
        return savedUser;
    }

   @GetMapping("/users/{id}")
    public User getUser(@PathVariable String id) throws Exception {
        return userService.getUser(id);
    }

    @GetMapping("/users")
    public Map<String, Object> getUsers() throws Exception {
        return userService.getUsers();
    }
}
