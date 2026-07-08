package com.avinash.book_network.GlobalExceptionHandling;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(
            String message) {

        super(message);
    }
}
