package com.shivamrajput.finance.hw.shivamrajputhw.module.common;

/**
 *
 */
public class GenericResponse {
    public final static String SUCCESS="SUCCESS";
    public final static String FAILED="FAILED";
    public final static String ERROR="ERROR";
    String Type;
    String Message;

    public GenericResponse(String type, String message) {
        Type = type;
        Message = message;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }
}
