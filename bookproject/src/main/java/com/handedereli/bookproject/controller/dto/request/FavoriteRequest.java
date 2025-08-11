package com.handedereli.bookproject.controller.dto.request;


import jakarta.validation.constraints.NotNull;

public record FavoriteRequest(
        @NotNull Integer userId,
        @NotNull Integer bookId) {

}