package com.Finsure.Finsure.utills;

public class ResponseModel {
    public Object data;
    public String message;

    public ResponseModel(Object data, String message) {
        this.data = data;
        this.message = message;
    }
}
