package com.handedereli.bookproject.controller.dto.response;

import com.handedereli.bookproject.entities.Book;

public record BookResponse(
        String title,
        String author
) {
    public static BookResponse from(Book b) {
        return new BookResponse(b.getTitle(), b.getAuthor());
    }
}
