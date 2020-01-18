package com.task.telna.exception;

public class UserNotFoundException extends Exception {
    public UserNotFoundException(Long id) {
        super("User id not found : " + id);
    }
}
