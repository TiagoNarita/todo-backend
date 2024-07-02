package com.example.todo.app.api.controller;

import com.example.todo.app.api.model.UserModel;
import com.example.todo.app.api.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserRepository repository;

    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/listAll")
    public ResponseEntity<List<UserModel>> listAllUsers(){
        return ResponseEntity.ok(repository.findAll());
    }
}
