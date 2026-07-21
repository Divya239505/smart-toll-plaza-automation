package com.sfl.journeyapi.Exceptions;

 public class JourneyNotFoundException extends RuntimeException {
    public JourneyNotFoundException(String message) {
        super(message);
    }
}