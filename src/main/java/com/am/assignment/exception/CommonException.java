package com.am.assignment.exception;

public class CommonException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public CommonException(String exception) {
        super(exception);
    }
}
