package com.example.instagram.exception;

public class IsUserNotPartOfGroup extends RuntimeException {
    public IsUserNotPartOfGroup() {
        throw new RuntimeException("You are not a member of the group");
    }
}
