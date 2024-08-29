package com.example.Security.Exceptions;

public class HotelNotFoundException extends  RuntimeException{

    public HotelNotFoundException(String message) {
        super(message);
    }
}
