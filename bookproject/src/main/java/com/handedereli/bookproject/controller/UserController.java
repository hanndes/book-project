package com.handedereli.bookproject.controller;

import com.handedereli.bookproject.entities.User;
import com.handedereli.bookproject.services.BookService;
import com.handedereli.bookproject.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final BookService bookService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
        User created = userService.creatUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser( @PathVariable Integer id){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();

    }




}
