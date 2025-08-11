package com.handedereli.bookproject.controller.dto.request;



public record AssignBookToUserRequest(
        Integer userId,
        Integer bookId
) {}