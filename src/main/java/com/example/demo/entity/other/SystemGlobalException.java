package com.example.demo.entity.other;

import lombok.Data;


@Data
public class SystemGlobalException extends RuntimeException{

    static final long serialVersionUID = 11282021L;

    private int code;

    public SystemGlobalException(int code) {
        this.code = code;
    }

    public SystemGlobalException(String message, int code) {
        super(message);
        this.code = code;
    }

    public SystemGlobalException(String message, Throwable cause, int code) {
        super(message, cause);
        this.code = code;
    }

    public SystemGlobalException() {
        this.code = 500;
    }

    public SystemGlobalException(String message) {
        super(message);
        this.code = 500;
    }

    public SystemGlobalException(String message, Throwable cause) {
        super(message, cause);
        this.code = 500;
    }
}
