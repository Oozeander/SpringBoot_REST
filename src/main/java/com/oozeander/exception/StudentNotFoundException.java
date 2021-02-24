package com.oozeander.exception;

public class StudentNotFoundException extends RuntimeException {
    public StudentNotFoundException(Long id) {
        super("Student N°" + id + " does not exist");
    }
}