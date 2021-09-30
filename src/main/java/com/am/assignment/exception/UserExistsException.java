package com.am.assignment.exception;

public class UserExistsException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public UserExistsException(String exception) {
        super(exception);
    }
}
