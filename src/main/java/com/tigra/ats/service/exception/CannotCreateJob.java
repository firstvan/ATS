package com.tigra.ats.service.exception;

public class CannotCreateJob extends RuntimeException {
    public CannotCreateJob(String message) {
        super(message);
    }
}
