package com.handedereli.bookproject.exceptions;


public class BookAlreadyAssignedException extends RuntimeException {
    public BookAlreadyAssignedException(Integer bookId) {
        super("Book with id " + bookId + " is already assigned to another user");
    }
}
