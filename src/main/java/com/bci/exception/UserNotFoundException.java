package com.bci.exception;

public class UserNotFoundException extends Exception {
    private static final long serialVersionUID = 1850202913237769822L;

	public UserNotFoundException(String message) {
        super(message);
    }

}