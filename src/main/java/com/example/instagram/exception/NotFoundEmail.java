package com.example.instagram.exception;

import org.webjars.NotFoundException;

public class NotFoundEmail extends Error {
    public NotFoundEmail(String message) {
        throw new NotFoundException("Not found email or id:" + message);
    }
}
