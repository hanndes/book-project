package com.handedereli.bookproject.controller.dto.response;

import com.handedereli.bookproject.entities.Favorite;

import java.time.LocalDate;

public record FavoriteResponse(String userName, String bookTitle, LocalDate addedAt) {

    public static FavoriteResponse from(Favorite favorite) {
        return new FavoriteResponse(favorite.getUser().getName(),favorite.getBook().getTitle(),favorite.getAddedAt());
    }
}