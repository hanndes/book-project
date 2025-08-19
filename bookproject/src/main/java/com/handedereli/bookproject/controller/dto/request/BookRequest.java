package com.handedereli.bookproject.controller.dto.request;

public record BookRequest(
        String title,
        String author,
        String category
) {
}
