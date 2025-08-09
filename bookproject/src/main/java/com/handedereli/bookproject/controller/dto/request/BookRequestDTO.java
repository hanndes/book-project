package com.handedereli.bookproject.controller.dto.request;

public record BookRequestDTO(
        String title,
        String author,
        String category
) {
}
