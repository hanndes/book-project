package com.handedereli.bookproject.controller.dto;



public record AssignBookToUserRequestDTO(
        Integer userId,
        Integer bookId
) {}