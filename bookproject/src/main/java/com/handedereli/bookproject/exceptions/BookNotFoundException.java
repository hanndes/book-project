package com.handedereli.bookproject.exceptions;

public class BookNotFoundException extends RuntimeException{

    public BookNotFoundException(Integer id) {
        super("Kitap bulunamadı. ID: " + id);
    }
}
