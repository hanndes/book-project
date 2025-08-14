package com.handedereli.bookproject.controller;

import com.handedereli.bookproject.controller.dto.request.AssignBookToUserRequest;
import com.handedereli.bookproject.controller.dto.request.UserRequest;
import com.handedereli.bookproject.controller.dto.response.UserResponse;
import com.handedereli.bookproject.controller.dto.response.UserWithBooksResponse;
import com.handedereli.bookproject.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    @Operation(summary = "Create a new user")
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserRequest dto){
        UserResponse created = userService.createUser(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a user by ID")
    public ResponseEntity<UserResponse> deleteUser(@PathVariable Integer id){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();

    }
  /*  @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable Integer id,
                                                   @RequestBody UserRequest request) {
        return ResponseEntity.ok(userService.changeProfile(id, request));
    }*/
    @PostMapping("/assign-book")
    @Operation(summary = "Assign a book to a user")
    public ResponseEntity<UserWithBooksResponse> assignBookToUser(@RequestBody AssignBookToUserRequest request) {
        UserWithBooksResponse response = userService.assignBookToUser(request);
        return ResponseEntity.ok(response);
    }





}
