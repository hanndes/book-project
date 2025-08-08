package com.handedereli.bookproject.controller.dto;

public record BookRequestDTO(
        String title,
        String author,
        String category
) {
}
