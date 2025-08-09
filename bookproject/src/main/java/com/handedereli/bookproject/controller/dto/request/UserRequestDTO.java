package com.handedereli.bookproject.controller.dto.request;

import jakarta.validation.constraints.NotBlank;

public record UserRequestDTO(

        @NotBlank(message ="name can not be empty")
        String name,

        String gender
) {
}
