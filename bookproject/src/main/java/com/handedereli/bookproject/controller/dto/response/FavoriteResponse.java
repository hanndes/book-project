package com.handedereli.bookproject.controller.dto.response;

import java.time.LocalDate;

public record FavoriteResponse(String userName, String bookTitle, LocalDate addedAt) {

}