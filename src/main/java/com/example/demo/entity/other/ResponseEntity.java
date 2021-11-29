package com.example.demo.entity.other;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResponseEntity<T> implements Serializable {

    private static final long serialVersionUID = -5118L;

    /**
     * status code
     */
    private Integer code;

    /**
     * message
     */
    private String message;

    /**
     * return data
     */
    private T data;

    public ResponseEntity() {
        super();
    }

    public ResponseEntity(Integer code) {
        super();
        this.code = code;
    }

    public ResponseEntity(Integer code, String message) {
        super();
        this.code = code;
        this.message = message;
    }

    public ResponseEntity(Integer code, Throwable throwable) {
        super();
        this.code = code;
        this.message = throwable.getMessage();
    }

    public ResponseEntity(Integer code, T data) {
        super();
        this.code = code;
        this.data = data;
    }

    public ResponseEntity(Integer code, String message, T data) {
        super();
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
