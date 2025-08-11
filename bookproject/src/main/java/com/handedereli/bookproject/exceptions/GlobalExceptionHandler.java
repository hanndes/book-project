package com.handedereli.bookproject.exceptions;

import com.handedereli.bookproject.controller.dto.common.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleBookNotFound(BookNotFoundException ex, ServletWebRequest request) {
        return build(HttpStatus.NOT_FOUND, "BOOK_NOT_FOUND", ex.getMessage(), request);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFound(UserNotFoundException ex, ServletWebRequest request) {
        return build(HttpStatus.NOT_FOUND, "USER_NOT_FOUND", ex.getMessage(), request);
    }

    @ExceptionHandler(BookAlreadyAssignedException.class)
    public ResponseEntity<ErrorResponse> handleBookAlreadyAssigned(BookAlreadyAssignedException ex, ServletWebRequest request) {
        return build(HttpStatus.BAD_REQUEST, "BOOK_ALREADY_ASSIGNED", ex.getMessage(), request);
    }

    @ExceptionHandler(AlreadyInFavoritesException.class)
    public ResponseEntity<ErrorResponse> handleAlreadyInFavorites(AlreadyInFavoritesException ex, ServletWebRequest request) {
        return build(HttpStatus.BAD_REQUEST, "ALREADY_IN_Favorites", ex.getMessage(), request);
    }
    private ResponseEntity<ErrorResponse> build(HttpStatus status, String errorCode, String message, ServletWebRequest request) {
        ErrorResponse error = new ErrorResponse(
                message,
                errorCode,
                request.getRequest().getRequestURI()
        );
        return ResponseEntity.status(status).body(error);
    }




}
