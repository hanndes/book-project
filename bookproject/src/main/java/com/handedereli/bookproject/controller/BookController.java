package com.handedereli.bookproject.controller;


import com.handedereli.bookproject.controller.dto.request.BookRequest;
import com.handedereli.bookproject.controller.dto.response.BookResponse;
import com.handedereli.bookproject.services.BookService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping
    @Operation(summary = "Create a new book")
    public ResponseEntity<BookResponse> createBook(@RequestBody BookRequest bookRequest){
        BookResponse created = bookService.createBook(bookRequest);
        return ResponseEntity.status(HttpStatus.OK).body(created);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a book by ID")
    public ResponseEntity<BookResponse> deleteBook(@PathVariable Integer id) {
        BookResponse deleted = bookService.deleteBook(id);
        return ResponseEntity.ok(deleted);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a book by ID")
    public ResponseEntity<BookResponse> updateBook(@PathVariable Integer id, @RequestBody BookRequest requestDTO) {
        BookResponse updated = bookService.updateBook(id, requestDTO);
        return ResponseEntity.ok(updated);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a book by ID")
    public ResponseEntity<BookResponse> getBookById(@PathVariable Integer id) {
        BookResponse response = bookService.getBookById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    @Operation(summary = "Get all books")
    public ResponseEntity<List<BookResponse>> getAllBooks() {
        List<BookResponse> books = bookService.getAllBooks();
        return ResponseEntity.ok(books);
    }


}
