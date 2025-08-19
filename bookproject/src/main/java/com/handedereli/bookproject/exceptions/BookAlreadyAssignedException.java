package com.handedereli.bookproject.exceptions;


public class BookAlreadyAssignedException extends RuntimeException {
    public BookAlreadyAssignedException(Integer bookId, boolean alreadyOwned) {
        super(alreadyOwned
                ? "Book with id " + bookId + " is already assigned to you"
                : "Book with id " + bookId + " is already assigned to another user");
    }

}
