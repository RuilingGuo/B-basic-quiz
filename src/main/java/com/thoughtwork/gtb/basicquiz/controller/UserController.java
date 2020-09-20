package com.thoughtwork.gtb.basicquiz.controller;

import com.thoughtwork.gtb.basicquiz.domain.User;
import com.thoughtwork.gtb.basicquiz.dto.UserDto;
import com.thoughtwork.gtb.basicquiz.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public UserDto findUserById(@PathVariable Long id) {
        return userService.findUserById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto createUser(@RequestBody @Valid User user) {
        return userService.createUser(user);

    }

}
