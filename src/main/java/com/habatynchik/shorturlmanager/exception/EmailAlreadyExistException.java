package com.habatynchik.shorturlmanager.exception;

import lombok.experimental.StandardException;

@StandardException
public class EmailAlreadyExistException extends RuntimeException {
    @Override
    public String getMessage() {
        return String.format("User with address: %s already exist", super.getMessage());
    }
}
