package com.aplication.com.aplication1.Models;

/**
 * Created by jairo880 on 14/07/16.
 */
public class Error extends Exception  {
    private int code;
    private String error;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
