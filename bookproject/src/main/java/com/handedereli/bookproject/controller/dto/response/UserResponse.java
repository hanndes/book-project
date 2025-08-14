package com.handedereli.bookproject.controller.dto.response;


import com.handedereli.bookproject.entities.User;

public record UserResponse(
        String name,
        String gender
) {
    public static UserResponse from(User user) {
        return new UserResponse(user.getName(), user.getGender());
    }
}

