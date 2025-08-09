package com.handedereli.bookproject.controller.dto.request;



public record AssignBookToUserRequestDTO(
        Integer userId,
        Integer bookId
) {}