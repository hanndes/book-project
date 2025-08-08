package com.handedereli.bookproject.controller;

import com.handedereli.bookproject.controller.dto.AssignBookToUserRequestDTO;
import com.handedereli.bookproject.controller.dto.UserRequestDTO;
import com.handedereli.bookproject.controller.dto.UserResponseDTO;
import com.handedereli.bookproject.controller.dto.UserWithBooksResponseDTO;
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
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserRequestDTO dto){
        System.out.println(dto.name());
        UserResponseDTO created = userService.createUser(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserResponseDTO> deleteUser( @PathVariable Integer id){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();

    }
    @PostMapping("/{id}/assign-book")
    public ResponseEntity<UserWithBooksResponseDTO> assignBookToUser(@RequestBody AssignBookToUserRequestDTO request) {
        UserWithBooksResponseDTO response = userService.assignBookToUser(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}/mybooks")
    public ResponseEntity<UserWithBooksResponseDTO> getMyBooks(@PathVariable Integer id){
        UserWithBooksResponseDTO response = userService.getUserBooks(id);
        return ResponseEntity.ok(response);
    }




}
