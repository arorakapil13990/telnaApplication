package com.task.telna.exception;

import com.task.telna.constants.Constants;

public class EmailIdAlreadyExistException extends Exception {
    public EmailIdAlreadyExistException() {
        super(Constants.EMAIL_ALREADY_EXIST);
    }
}
