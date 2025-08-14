package com.handedereli.bookproject.controller.dto.request;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record AssignBookToUserRequest(
        @NotNull @Positive Integer userId,
        @NotNull @Positive Integer bookId
) {}