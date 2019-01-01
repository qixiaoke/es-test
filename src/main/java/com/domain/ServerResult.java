package com.domain;

import java.io.Serializable;

public class ServerResult<T> implements Serializable {

    private int code;
    private String message;
    private T info;

    public ServerResult(int code, String message, T info) {
        this.code = code;
        this.message = message;
        this.info = info;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getInfo() {
        return info;
    }

    public void setInfo(T info) {
        this.info = info;
    }
}
