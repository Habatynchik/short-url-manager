package com.habatynchik.shorturlmanager.exception;

import jakarta.persistence.EntityNotFoundException;
import lombok.experimental.StandardException;

@StandardException
@SuppressWarnings("java:S110")
public class UrlNotFoundException extends EntityNotFoundException {
    @Override
    public String getMessage() {
        return String.format("URL with short code %s not found", super.getMessage());
    }
}
