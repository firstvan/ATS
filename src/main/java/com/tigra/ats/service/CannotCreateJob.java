package com.tigra.ats.service;

public class CannotCreateJob extends RuntimeException {
    public CannotCreateJob(String message) {
        super(message);
    }
}
