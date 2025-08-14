package com.handedereli.bookproject.controller.dto.request;

import com.handedereli.bookproject.entities.Book;

public record BookRequest(
        String title,
        String author,
        String category
) {
}
