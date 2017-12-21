package com.zjf.exception;

/**
 * @author : zhoujianfei
 * @description :
 * @date : 2017/12/20.
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
