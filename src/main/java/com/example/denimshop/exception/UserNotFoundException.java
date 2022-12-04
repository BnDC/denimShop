package com.example.denimshop.exception;


public class UserNotFoundException extends RuntimeException {
    @Override
    public String getMessage() {
        return "존재하지 않습니다.";
    }
}
