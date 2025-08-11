package com.handedereli.bookproject.exceptions;

public class AlreadyInFavoritesException extends RuntimeException {
    public AlreadyInFavoritesException(Integer userId, Integer bookId) {
        super("Kullanıcı ID: " + userId + " için kitap ID: " + bookId + " zaten favorilerde.");
    }
}
