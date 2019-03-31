package com.github.wxisme.watchdog.exception;

public class InvalidParameterException extends RuntimeException {

    public InvalidParameterException() {
        super("Invalid parameter, it's null or not supported.");
    }

    public InvalidParameterException(String msg) {
        super(msg);
    }

}
