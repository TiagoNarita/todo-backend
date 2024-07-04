package com.example.todo.app.api.controller;

import com.example.todo.app.api.model.UserModel;
import com.example.todo.app.api.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserRepository repository;
    private final PasswordEncoder encoder;

    public UserController(UserRepository repository, PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    @GetMapping("/listAllUsers")
    public ResponseEntity<List<UserModel>> listAllUsers(){
        return ResponseEntity.ok(repository.findAll());
    }

    @PostMapping("/saveUser")
   public ResponseEntity<UserModel> salvar(@RequestBody UserModel user){
        user.setPassword(encoder.encode(user.getPassword()));
        return ResponseEntity.ok(repository.save(user));
   }

   @GetMapping("/validatePassword")
   public ResponseEntity<Boolean> validatePassword
            (@RequestParam String login, @RequestParam String password){


       Optional<UserModel> user = repository.findByLogin(login);
       if(user.isEmpty()){
           return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);
       }

        boolean valid = encoder.matches(password, user.get().getPassword());

       HttpStatus status = (valid) ? HttpStatus.OK : HttpStatus.UNAUTHORIZED;


        return ResponseEntity.status(status).body(valid);
   }
}
