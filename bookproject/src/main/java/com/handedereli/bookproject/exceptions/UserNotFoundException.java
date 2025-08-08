package com.handedereli.bookproject.exceptions;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(Integer id) {
        super("Kullanıcı bulunamadı. ID: " + id);
    }

}
