package com.habatynchik.shorturlmanager.exception;

import lombok.experimental.StandardException;

@StandardException
public class TokenExpiredException extends RuntimeException {
    @Override
    public String getMessage() {
        return String.format("%s token is expired.", super.getMessage());
    }
}
