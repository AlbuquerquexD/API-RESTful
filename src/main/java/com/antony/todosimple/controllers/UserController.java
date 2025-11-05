package com.antony.todosimple.controllers;

import com.antony.todosimple.models.User;
import com.antony.todosimple.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {

    @Autowired
     private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable long id) {
        User Objuser = userService.findById(id);
        return ResponseEntity.ok().body(Objuser);
    }

    @Validated(User.CreateUser.class)
    public ResponseEntity<Void> create(@Valid @RequestBody User Objuser) {
        this.userService.create(Objuser);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/id").buildAndExpand(Objuser.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
