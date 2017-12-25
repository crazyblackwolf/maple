package com.zjf.exception;

/**
 * @author: linziye
 * @description:
 * @date: 15:34 2017/12/21 .
 */
public class ParameterException extends RuntimeException {

    public ParameterException(String message) {
        super(message);
    }

    public ParameterException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParameterException(Throwable cause) {
        super(cause);
    }

}
