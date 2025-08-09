package com.handedereli.bookproject.exceptions;

import com.handedereli.bookproject.controller.dto.common.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleBookNotFound(BookNotFoundException ex, WebRequest request) {
        ErrorResponse error = new ErrorResponse(
                ex.getMessage(), "BOOK_NOT_FOUND",
                request.getDescription(false).replace("uri=", "")
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }


}
