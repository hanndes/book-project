package com.handedereli.bookproject.controller;


import com.handedereli.bookproject.controller.dto.request.BookRequest;
import com.handedereli.bookproject.controller.dto.response.BookResponse;
import com.handedereli.bookproject.services.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;


    @PostMapping
    public ResponseEntity<BookResponse> createBook(@RequestBody BookRequest bookRequest){
        BookResponse created = bookService.createBook(bookRequest);
        return ResponseEntity.status(HttpStatus.OK).body(created);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BookResponse> deleteBook(@PathVariable Integer id) {
        BookResponse deleted = bookService.deleteBook(id);
        return ResponseEntity.ok(deleted);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookResponse> updateBook(@PathVariable Integer id, @RequestBody BookRequest requestDTO) {
        BookResponse updated = bookService.updateBook(id, requestDTO);
        return ResponseEntity.ok(updated);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponse> getBookById(@PathVariable Integer id) {
        BookResponse response = bookService.getBookById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<BookResponse>> getAllBooks() {
        List<BookResponse> books = bookService.getAllBooks();
        return ResponseEntity.ok(books);
    }

    @GetMapping("/users/{id}/books")
    public ResponseEntity<List<BookResponse>> getBooksByUser(@PathVariable Integer id) {
        return ResponseEntity.ok(bookService.getBooksByUser(id));
    }




}
