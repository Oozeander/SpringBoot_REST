package com.oozeander.exception;

public class StudentAlreadyExistsException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public StudentAlreadyExistsException(Long id) {
		super("Student N°" + id + " already exists");
	}
}