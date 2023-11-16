package com.example.instagram.exception;

public class AccessIsDenied extends RuntimeException {
    public AccessIsDenied() {
        throw new RuntimeException("Access is denied");
    }
}
