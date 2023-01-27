package com.user.model;

import java.util.ArrayList;

public class RootResponse {
    String responseCode ="";
    String description  ="";
    String elapsedTime  ="";
    int    result       = 0;

    public RootResponse() {
    }

    public String getResponseCode() {
        return responseCode;
    }

    public String getDescription() {
        return description;
    }

    public String getElapsedTime() {
        return elapsedTime;
    }

    public int getResult() {
        return result;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setElapsedTime(String elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    public void setResult(int result) {
        this.result = result;
    }
}
