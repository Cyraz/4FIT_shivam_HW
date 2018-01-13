package com.shivamrajput.finance.hw.shivamrajputhw.module.common;

public class SuccessWithPayloadResponse<T> extends GenericResponse {

    private T payload;
    public SuccessWithPayloadResponse(String type, String message, T payload) {
        super(type, message);
        this.payload=payload;
    }

    public T getPayload() {
        return payload;
    }

    public void setPayload(T payload) {
        this.payload = payload;
    }
}
