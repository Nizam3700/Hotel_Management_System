package com.example.Security.Exceptions;

public class HotelAlreadyExistsException extends RuntimeException{

    public HotelAlreadyExistsException(String message) {
        super(message);
    }
}
