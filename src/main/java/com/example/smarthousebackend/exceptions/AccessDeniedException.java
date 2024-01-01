package com.example.smarthousebackend.exceptions;


public class AccessDeniedException extends Exception{

    public AccessDeniedException(String message){
        super(message);
    }
}
