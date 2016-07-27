package com.aplication.com.aplication1.Models;

/**
 * Created by jairo880 on 13/07/16.
 */
public class Cliente {

    private String success;
    private String authToken;
    private String email;
    private String zone;
    private String error;
    private String code;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSucces() {
        return success;
    }

    public void setSucces(String succes) {
        this.success = succes;
    }

    public String getAuthoken() {
        return authToken;
    }

    public void setAuthoken(String authoken) {
        this.authToken = authoken;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }
}
