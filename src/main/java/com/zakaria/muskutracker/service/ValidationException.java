package com.zakaria.muskutracker.service;


public class ValidationException extends RuntimeException{

    public ValidationException(String message){
        super(message);
    }
}
