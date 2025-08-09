package com.handedereli.bookproject.controller.dto.response;

import java.util.List;

public record UserWithBooksResponseDTO(
        String name,
        String gender,
        List<String> books // kitap isimleri
) {
}